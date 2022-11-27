package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.application.Application;
import java.util.Set;
import java.io.FileNotFoundException;
public class App extends Application implements ISimulationEngineObserver {

    GridPane gridPane = new GridPane();
    private AbstractWorldMap map;
    SimulationEngine engine;
    int size = 50;
    private Set<IMapElement> elementsList;


    public void init() {
        try {
            this.map = new GrassField(10);
            Vector2d[] positions = {
                    new Vector2d(3, 4),
                    new Vector2d(10, 4),
                    new Vector2d(5, 7),
            };

            this.engine = new SimulationEngine(this.map, positions, 500);
            engine.addObserver(this);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void start(Stage primaryStage) throws FileNotFoundException{

        makeScene();

        TextField textField = new TextField();
        textField.setText("animal moves sequence");
        textField.setPrefWidth(200);
        textField.setMaxWidth(200);
        Button start = new Button("start");
        start.setOnAction(e -> {

            MoveDirection[] directions = new OptionsParser().parse(
                    textField.getText().split(" ")
            );
            engine.setMoves(directions);
            Thread engineThread = new Thread(engine);
            engineThread.start();
        });

        VBox vbox = new VBox(gridPane, textField, start);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);


        Scene scene = new Scene(vbox, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void makeScene() throws FileNotFoundException {

        elementsList = map.getNewMapElements();
        for(IMapElement element: elementsList){
            System.out.println(element.toString());
        }

        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();

        Label label = new Label("y\\x");

        Vector2d lowerLeft = map.getLowerLeft();
        Vector2d upperRight = map.getUpperRight();

        int startX = lowerLeft.getX();
        int startY = lowerLeft.getY();
        int endX = upperRight.getX();
        int endY = upperRight.getY();

        gridPane.add(label, 0, 0);
        gridPane.getRowConstraints().add(new RowConstraints(size));
        gridPane.getColumnConstraints().add(new ColumnConstraints(size));
        GridPane.setHalignment(label, HPos.CENTER);


        for (int i = startX; i <= endX + 1; i++) {
            for (int j = startY; j <= endY + 1; j++) {
                Object object = this.map.objectAt(new Vector2d(i, endY + startY - j));
                if (object != null) {
                    VBox el = new GuiElementBox((IMapElement) object).getvBox();
                    gridPane.add(el, i+1 - lowerLeft.getX(), j+1 -lowerLeft.getY());
                    GridPane.setHalignment(el, HPos.CENTER);
                }
            }
        }

        for (int i = startX; i <= endX; i++) {
            Label xAxisNumber = new Label("" + i);
            gridPane.add(xAxisNumber, i - startX + 1, 0);
            gridPane.getColumnConstraints().add(new ColumnConstraints(size));
            GridPane.setHalignment(xAxisNumber, HPos.CENTER);
        }

        for (int i = startY; i <= endY; i++) {
            Label yAxisNumber = new Label("" + i);
            gridPane.add(yAxisNumber, 0, endY - i + 1);
            gridPane.getRowConstraints().add(new RowConstraints(size));
            GridPane.setHalignment(yAxisNumber, HPos.CENTER);
        }
    }

    @Override
    public void mapChanged() {
        Platform.runLater(() -> {
            gridPane.setGridLinesVisible(false);
            gridPane.getChildren().clear();
            try {
                makeScene();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }



}


