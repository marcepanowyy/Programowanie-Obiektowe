package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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


    public void start(Stage primaryStage){


        TextField widthTextField = new TextField();
        widthTextField.setPromptText("Width");
        GridPane.setConstraints(widthTextField, 0, 0);

        TextField heightTextField = new TextField();
        heightTextField.setPromptText("Height");
        GridPane.setConstraints(heightTextField, 1, 0);

        TextField plantsNumTextField = new TextField();
        plantsNumTextField.setPromptText("Opening number of plants");
        GridPane.setConstraints(plantsNumTextField, 2, 0);

        TextField plantsEnergyTextField = new TextField();
        plantsEnergyTextField.setPromptText("Plant energy");
        GridPane.setConstraints(plantsEnergyTextField, 3, 0);

        TextField plantsDailyTextField = new TextField();
        plantsDailyTextField.setPromptText("Plants daily");
        GridPane.setConstraints(plantsDailyTextField, 0, 1);

        TextField animalsNumTextField = new TextField();
        animalsNumTextField.setPromptText("Number of starting animals");
        GridPane.setConstraints(animalsNumTextField, 1, 1);

        TextField startingEnergyTextField = new TextField();
        startingEnergyTextField.setPromptText("Starting energy");
        GridPane.setConstraints(startingEnergyTextField, 2, 1);

        TextField genomeLengthTextField = new TextField();
        genomeLengthTextField.setPromptText("Genome length");
        GridPane.setConstraints(genomeLengthTextField, 3, 1);

        TextField energyToFullTextField = new TextField();
        energyToFullTextField.setPromptText("Min Energy To Breed");
        GridPane.setConstraints(energyToFullTextField, 0, 2);

        TextField energyToBreedTextField = new TextField();
        energyToBreedTextField.setPromptText("Energy Used For Breeding");
        GridPane.setConstraints(energyToBreedTextField, 1, 2);

        TextField minNumOfMutationsTextField = new TextField();
        minNumOfMutationsTextField.setPromptText("Min number of mutations");
        GridPane.setConstraints(minNumOfMutationsTextField, 2, 2);

        TextField maxNumOfMutationsTextField = new TextField();
        maxNumOfMutationsTextField.setPromptText("Max number of mutations");
        GridPane.setConstraints(maxNumOfMutationsTextField, 3, 2);

        ChoiceBox<String> mutationWarrantOption = new ChoiceBox<>();
        mutationWarrantOption.getItems().addAll("Brak mutacji", "Pelna losowosc", "Lekka korekta");
        mutationWarrantOption.setValue("Brak mutacji");
        GridPane.setConstraints(mutationWarrantOption, 0, 3);

        ChoiceBox<String> behaviourWarrantOption = new ChoiceBox<>();
        behaviourWarrantOption.getItems().addAll("Pelna predestynacja", "Nieco szalenstwa");
        behaviourWarrantOption.setValue("Pelna predestynacja");
        GridPane.setConstraints(behaviourWarrantOption, 1, 3);

        ChoiceBox<String> plantGrowthWarrantOption = new ChoiceBox<>();
        plantGrowthWarrantOption.getItems().addAll("Zalesione rowniki", "Toksyczne trupy");
        plantGrowthWarrantOption.setValue("Zalesione rowniki");
        GridPane.setConstraints(plantGrowthWarrantOption, 2, 3);

        ChoiceBox<String> worldWarrantOption = new ChoiceBox<>();
        worldWarrantOption.getItems().addAll("Kula ziemska", "Piekielny portal");
        worldWarrantOption.setValue("Kula ziemska");
        GridPane.setConstraints(worldWarrantOption, 3, 3);

        Button test1 = new Button("test1");
        GridPane.setConstraints(test1, 0, 4);
        test1.setOnAction(e -> {


            widthTextField.setText("10");
            heightTextField.setText("10");
            plantsNumTextField.setText("5");
            plantsEnergyTextField.setText("40");
            plantsDailyTextField.setText("3");
            animalsNumTextField.setText("5");
            startingEnergyTextField.setText("40");
            genomeLengthTextField.setText("5");
            energyToFullTextField.setText("50");
            energyToBreedTextField.setText("50");
            minNumOfMutationsTextField.setText("5");
            maxNumOfMutationsTextField.setText("5");
            mutationWarrantOption.setValue("Brak mutacji");
            behaviourWarrantOption.setValue("Pelna predestynacja");
            plantGrowthWarrantOption.setValue("Zalesione rowniki");
            worldWarrantOption.setValue("Kula ziemska");
        });

        Button test2 = new Button("test2");
        GridPane.setConstraints(test2, 1, 4);
        test2.setOnAction(e -> {

            widthTextField.setText("10");
            heightTextField.setText("10");
            plantsNumTextField.setText("3");
            plantsEnergyTextField.setText("10");
            plantsDailyTextField.setText("2");
            animalsNumTextField.setText("8");
            startingEnergyTextField.setText("50");
            genomeLengthTextField.setText("10");
            energyToFullTextField.setText("40");
            energyToBreedTextField.setText("100");
            minNumOfMutationsTextField.setText("5");
            maxNumOfMutationsTextField.setText("5");
            mutationWarrantOption.setValue("Brak mutacji");
            behaviourWarrantOption.setValue("Pelna predestynacja");
            plantGrowthWarrantOption.setValue("Zalesione rowniki");
            worldWarrantOption.setValue("Kula ziemska");
        });

        Button test3 = new Button("test3");
        GridPane.setConstraints(test3, 2, 4);
        test3.setOnAction(e -> {

            widthTextField.setText("10");
            heightTextField.setText("10");
            plantsNumTextField.setText("3");
            plantsEnergyTextField.setText("10");
            plantsDailyTextField.setText("4");
            animalsNumTextField.setText("10");
            startingEnergyTextField.setText("1000");
            genomeLengthTextField.setText("10");
            energyToFullTextField.setText("40");
            energyToBreedTextField.setText("50");
            minNumOfMutationsTextField.setText("5");
            maxNumOfMutationsTextField.setText("5");
            mutationWarrantOption.setValue("Pelna losowosc");
            behaviourWarrantOption.setValue("Nieco szalenstwa");
            plantGrowthWarrantOption.setValue("Toksyczne trupy");
            worldWarrantOption.setValue("Piekielny portal");
        });

        Button test4 = new Button("test4");
        GridPane.setConstraints(test4, 3, 4);
        test4.setOnAction(e -> {

            widthTextField.setText("10");
            heightTextField.setText("10");
            plantsNumTextField.setText("0");
            plantsEnergyTextField.setText("100");
            plantsDailyTextField.setText("5");
            animalsNumTextField.setText("8");
            startingEnergyTextField.setText("1000");
            genomeLengthTextField.setText("10");
            energyToFullTextField.setText("50");
            energyToBreedTextField.setText("50");
            minNumOfMutationsTextField.setText("5");
            maxNumOfMutationsTextField.setText("5");
            mutationWarrantOption.setValue("Pelna losowosc");
            behaviourWarrantOption.setValue("Nieco szalenstwa");
            plantGrowthWarrantOption.setValue("Toksyczne trupy");
            worldWarrantOption.setValue("Piekielny portal");
        });

        Button test5 = new Button("test5");
        GridPane.setConstraints(test5, 0, 5);
        test5.setOnAction(e -> {

            widthTextField.setText("8");
            heightTextField.setText("8");
            plantsNumTextField.setText("0");
            plantsEnergyTextField.setText("0");
            plantsDailyTextField.setText("5");
            animalsNumTextField.setText("5");
            startingEnergyTextField.setText("600");
            genomeLengthTextField.setText("20");
            energyToFullTextField.setText("50");
            energyToBreedTextField.setText("50");
            minNumOfMutationsTextField.setText("15");
            maxNumOfMutationsTextField.setText("15");
            mutationWarrantOption.setValue("Pelna losowosc");
            behaviourWarrantOption.setValue("Nieco szalenstwa");
            plantGrowthWarrantOption.setValue("Toksyczne trupy");
            worldWarrantOption.setValue("Piekielny portal");
        });

        Button test6 = new Button("test6");
        GridPane.setConstraints(test6, 1, 5);
        test6.setOnAction(e -> {

            widthTextField.setText("4");
            heightTextField.setText("4");
            plantsNumTextField.setText("10");
            plantsEnergyTextField.setText("10");
            plantsDailyTextField.setText("4");
            animalsNumTextField.setText("2");
            startingEnergyTextField.setText("400");
            genomeLengthTextField.setText("7");
            energyToFullTextField.setText("50");
            energyToBreedTextField.setText("50");
            minNumOfMutationsTextField.setText("5");
            maxNumOfMutationsTextField.setText("5");
            mutationWarrantOption.setValue("Pelna losowosc");
            behaviourWarrantOption.setValue("Nieco szalenstwa");
            plantGrowthWarrantOption.setValue("Toksyczne trupy");
            worldWarrantOption.setValue("Kula ziemska");
        });

        Button test7 = new Button("test7");
        GridPane.setConstraints(test7, 2, 5);
        test7.setOnAction(e -> {

            widthTextField.setText("8");
            heightTextField.setText("8");
            plantsNumTextField.setText("10");
            plantsEnergyTextField.setText("30");
            plantsDailyTextField.setText("4");
            animalsNumTextField.setText("6");
            startingEnergyTextField.setText("100");
            genomeLengthTextField.setText("10");
            energyToFullTextField.setText("30");
            energyToBreedTextField.setText("30");
            minNumOfMutationsTextField.setText("25");
            maxNumOfMutationsTextField.setText("25");
            mutationWarrantOption.setValue("Brak mutacji");
            behaviourWarrantOption.setValue("Nieco szalenstwa");
            plantGrowthWarrantOption.setValue("Zalesione rowniki");
            worldWarrantOption.setValue("Kula ziemska");
        });

        Button test8 = new Button("test8");
        GridPane.setConstraints(test8, 3, 5);
        test8.setOnAction(e -> {

            widthTextField.setText("8");
            heightTextField.setText("8");
            plantsNumTextField.setText("10");
            plantsEnergyTextField.setText("200");
            plantsDailyTextField.setText("1");
            animalsNumTextField.setText("5");
            startingEnergyTextField.setText("500");
            genomeLengthTextField.setText("4");
            energyToFullTextField.setText("10");
            energyToBreedTextField.setText("10");
            minNumOfMutationsTextField.setText("5");
            maxNumOfMutationsTextField.setText("5");
            mutationWarrantOption.setValue("Brak mutacji");
            behaviourWarrantOption.setValue("Pelna predestynacja");
            plantGrowthWarrantOption.setValue("Toksyczne trupy");
            worldWarrantOption.setValue("Piekielny portal");
        });

        Button start = new Button("start");
        GridPane.setConstraints(start, 0, 6);


        GridPane gridPane1 = new GridPane();
        gridPane1.setPadding(new Insets(10, 10, 10, 10));
        gridPane1.setAlignment(Pos.BOTTOM_CENTER);
        gridPane1.getChildren().addAll(widthTextField, heightTextField, plantsNumTextField, plantsEnergyTextField, plantsDailyTextField, animalsNumTextField, startingEnergyTextField,
                genomeLengthTextField, energyToFullTextField, energyToBreedTextField, minNumOfMutationsTextField, maxNumOfMutationsTextField, mutationWarrantOption, behaviourWarrantOption, plantGrowthWarrantOption, worldWarrantOption, start, test1, test2, test3, test4, test5, test6, test7, test8);

        start.setOnAction(e -> {
            this.width = Integer.parseInt(widthTextField.getText());
            this.height = Integer.parseInt(heightTextField.getText());
            int plantsNum = Integer.parseInt(plantsNumTextField.getText());
            int plantEnergy = Integer.parseInt(plantsEnergyTextField.getText());
            int plantsDaily = Integer.parseInt(plantsDailyTextField.getText());
            int animalsNum = Integer.parseInt(animalsNumTextField.getText());
            int startingEnergy = Integer.parseInt(startingEnergyTextField.getText());
            int genomeLength = Integer.parseInt(genomeLengthTextField.getText());
            int minEnergyToBreed = Integer.parseInt(energyToFullTextField.getText());
            int energyUsedForBreeding = Integer.parseInt(energyToBreedTextField.getText());
            int minNumOfMutations = Integer.parseInt(minNumOfMutationsTextField.getText());
            int maxNumOfMutations = Integer.parseInt(maxNumOfMutationsTextField.getText());
            int mutationMode = mutationWarrantOption.getSelectionModel().getSelectedItem() == "Brak mutacji" ? 0 : (mutationWarrantOption.getSelectionModel().getSelectedItem() == "Pelna losowosc" ? 1 : 2);
            int behaviourMode = behaviourWarrantOption.getSelectionModel().getSelectedItem() == "Pelna predestynacja" ? 0 : 1;
            int plantGrowthMode = plantGrowthWarrantOption.getSelectionModel().getSelectedItem() == "Zalesione rowniki" ? 0 : 1;
            int mapMode = worldWarrantOption.getSelectionModel().getSelectedItem() == "Kula ziemska" ? 0 : 1;

            gridPane1.getChildren().removeAll(test1, test2, test3, test4, test5, test6, test7, test8);

            this.map = new AbstractWorldMap(this.width, this.height, plantEnergy, energyUsedForBreeding, startingEnergy, plantGrowthMode);

            this.engine = new SimulationEngine(this.map, 50, this.width, this.height, plantsNum, plantEnergy, plantsDaily, animalsNum, startingEnergy,  genomeLength,  minEnergyToBreed,  energyUsedForBreeding,  minNumOfMutations,  maxNumOfMutations,
                    mutationMode, behaviourMode, plantGrowthMode, mapMode);
            engine.addObserver(this);

            try {
                makeScene();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            Thread engineThread = new Thread(engine);
            engineThread.start();
        });

        VBox vbox = new VBox(gridPane, gridPane1);

        Scene scene = new Scene(vbox, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    private void makeScene() throws FileNotFoundException {

        elementsList = map.getNewMapElements();
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
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }


}
