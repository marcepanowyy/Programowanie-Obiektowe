package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Set;

public class App extends Application {

    private GridPane gridPane;
    private AbstractWorldMap abstractWorldMap;
    int size = 50;
    private Set<IMapElement> elementsList;


    public void start(Stage primaryStage) throws RuntimeException {

        gridPane = new GridPane();
        MoveDirection[] directions = OptionsParser.parse(getParameters().getRaw().toArray(new String[0]));
        AbstractWorldMap map = new GrassField(10);

        Vector2d[] positions = {
//                new Vector2d(2,2),
//                new Vector2d(3,4),
                new Vector2d(10, 4),
                new Vector2d(5, 7),
        };

        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        abstractWorldMap = map;
        elementsList = map.getNewMapElements();

        drawMap();
        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void drawMap() {

        Label label = new Label("y\\x");

        Vector2d lowerLeft = abstractWorldMap.getLowerLeft();
        Vector2d upperRight = abstractWorldMap.getUpperRight();

        int startX = lowerLeft.getX();
        int startY = lowerLeft.getY();
        int endX = upperRight.getX();
        int endY = upperRight.getY();

        gridPane.add(label, 0, 0);
        gridPane.getRowConstraints().add(new RowConstraints(size));
        gridPane.getColumnConstraints().add(new ColumnConstraints(size));
        GridPane.setHalignment(label, HPos.CENTER);
        gridPane.setGridLinesVisible(true);

        createAxisX(startX, endX);
        createAxisY(startY, endY);

        for(IMapElement element: elementsList){
            Label el = new Label(element.toString());
            Vector2d pos = element.getPosition();
            gridPane.add(el, pos.getX() - startX + 1, endY - pos.getY() + 1);
            GridPane.setHalignment(el, HPos.CENTER);
        }
    }
    private void createAxisX(int startX, int endX){

        for (int i = startX; i <= endX; i++) {
            Label xAxisNumber = new Label("" + i);
            gridPane.add(xAxisNumber, i - startX + 1, 0);
            gridPane.getColumnConstraints().add(new ColumnConstraints(size));
            GridPane.setHalignment(xAxisNumber, HPos.CENTER);
        }
    }
    private void createAxisY(int startY, int endY){
        for (int i = startY; i <= endY; i++) {
            Label yAxisNumber = new Label("" + i);
            gridPane.add(yAxisNumber, 0, endY - i + 1);
            gridPane.getRowConstraints().add(new RowConstraints(size));
            GridPane.setHalignment(yAxisNumber, HPos.CENTER);
        }
    }

}


