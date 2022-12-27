package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
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
    int size = 40;
    private Set<IMapElement> elementsList;
    private int height;
    private int width;

    public void init() {
        try {
            this.map = new GrassField(20);
            Vector2d[] positions = {
                    new Vector2d(6, 8),
//                    new Vector2d(10, 4),
                    new Vector2d(10, 10),
                    new Vector2d(6, 6),
            };

            this.engine = new SimulationEngine(this.map, positions, 500);
            engine.addObserver(this);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void start(Stage primaryStage) throws FileNotFoundException{




        TextField widthTextField = new TextField("Width");
        GridPane.setConstraints(widthTextField, 0, 0);
        TextField heightTextField = new TextField("Height");
        GridPane.setConstraints(heightTextField, 1, 0);
        TextField plantsNumTextField = new TextField("Number of plants");
        GridPane.setConstraints(plantsNumTextField, 2, 0);
        TextField plantsEnergyTextField = new TextField("Plant energy");
        GridPane.setConstraints(plantsEnergyTextField, 3, 0);
        TextField plantsDailyTextField = new TextField("Plants daily");
        GridPane.setConstraints(plantsDailyTextField, 0, 1);
        TextField animalsNumTextField = new TextField("Number of starting animals");
        GridPane.setConstraints(animalsNumTextField, 1, 1);
        TextField startingEnergyTextField = new TextField("Starting energy");
        GridPane.setConstraints(startingEnergyTextField, 2, 1);
        TextField genomeLengthTextField = new TextField("Genome length");
        GridPane.setConstraints(genomeLengthTextField, 3, 1);
        TextField energyToFullTextField = new TextField("Full energy");
        GridPane.setConstraints(energyToFullTextField, 0, 2);
        TextField energyToBreedTextField = new TextField("Energy to breed");
        GridPane.setConstraints(energyToBreedTextField, 1, 2);
        TextField minNumOfMutationsTextField = new TextField("Min number of mutations");
        GridPane.setConstraints(minNumOfMutationsTextField, 2, 2);
        TextField maxNumOfMutationsTextField = new TextField("Max number of mutations");
        GridPane.setConstraints(maxNumOfMutationsTextField, 3, 2);
        TextField mutationWarrantTextField = new TextField("Mutation warrant");
        GridPane.setConstraints(mutationWarrantTextField, 0, 3);
        TextField behaviourWarrantTextField = new TextField("Behaviour warrant");
        GridPane.setConstraints(behaviourWarrantTextField, 1, 3);
        TextField plantGrowthWarrantTextField = new TextField("plant growth warrant");
        GridPane.setConstraints(plantGrowthWarrantTextField, 2, 3);
        Button start = new Button("start");
        GridPane.setConstraints(start, 3, 3);
        start.setOnAction(e -> {
            this.width = Integer.parseInt(widthTextField.getText());
            this.height = Integer.parseInt(heightTextField.getText());
            int plantsNum = Integer.parseInt(plantsNumTextField.getText());
            int plantEnergy = Integer.parseInt(plantsEnergyTextField.getText());
            int plantsDaily = Integer.parseInt(plantsDailyTextField.getText());
            int animalsNum = Integer.parseInt(animalsNumTextField.getText());
            int startingEnergy = Integer.parseInt(startingEnergyTextField.getText());
            int genomeLength = Integer.parseInt(genomeLengthTextField.getText());
            int energyToFull = Integer.parseInt(energyToFullTextField.getText());
            int energyToBreed = Integer.parseInt(energyToBreedTextField.getText());
            int minNumOfMutations = Integer.parseInt(minNumOfMutationsTextField.getText());
            int maxNumOfMutations = Integer.parseInt(maxNumOfMutationsTextField.getText());
            String mutationWarrant = mutationWarrantTextField.getText();
            String behaviourWarrant = behaviourWarrantTextField.getText();
            String plantGrowthWarrant = plantGrowthWarrantTextField.getText();
            this.map = new GrassField(plantsNum);
            Vector2d[] positions = {
                    new Vector2d(6, 8),
//                    new Vector2d(10, 4),
                    new Vector2d(10, 10),
                    new Vector2d(6, 6),
            };
            this.engine = new SimulationEngine(this.map, positions , 500);
            engine.addObserver(this);

            try {
                makeScene();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            engine.setParameters(plantsNum, plantEnergy, plantsDaily, animalsNum, startingEnergy, genomeLength, energyToFull, energyToBreed, minNumOfMutations, maxNumOfMutations,
                    mutationWarrant, behaviourWarrant, plantGrowthWarrant);
            Thread engineThread = new Thread(engine);
            engineThread.start();
        });

        GridPane gridPane1 = new GridPane();
        gridPane1.setPadding(new Insets(10, 10, 10, 10));
        gridPane1.setAlignment(Pos.BOTTOM_CENTER);
        gridPane1.getChildren().addAll(widthTextField, heightTextField, plantsNumTextField, plantsEnergyTextField, plantsDailyTextField, animalsNumTextField, startingEnergyTextField,
                genomeLengthTextField, energyToFullTextField, energyToBreedTextField, minNumOfMutationsTextField, maxNumOfMutationsTextField, mutationWarrantTextField, behaviourWarrantTextField, plantGrowthWarrantTextField, start);

        VBox vbox = new VBox(gridPane, gridPane1);



        Scene scene = new Scene(vbox, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void makeScene() throws FileNotFoundException {

        elementsList = map.getNewMapElements();
//        for(IMapElement element: elementsList){
//            if(element instanceof Animal){
//                System.out.println(((Animal) element).getGenomeList());
//                System.out.println(((Animal) element).getRotationNum());
//                System.out.println(((Animal) element).getOrientation());
//            }
//        }

        gridPane.setGridLinesVisible(true);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();

        Label label = new Label("y\\x");

        Vector2d lowerLeft = map.getLowerLeft();
        Vector2d upperRight = map.getUpperRight();

        int startX = 0;
        int startY = 0;
        int endX = width;
        int endY = height;

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