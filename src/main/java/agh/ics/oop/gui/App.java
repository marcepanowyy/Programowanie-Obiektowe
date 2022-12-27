package agh.ics.oop.gui;

import agh.ics.oop.*;

import javafx.event.ActionEvent;

import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.application.Application;

public class App extends Application {


    private AbstractWorldMap map;
    Simulation simulation;
    private TextField widthTextField;
    private TextField heightTextField;
    private TextField plantsNumTextField;
    private TextField plantsEnergyTextField;
    private TextField plantsDailyTextField;
    private TextField animalsNumTextField;
    private TextField startingEnergyTextField;
    private TextField genomeLengthTextField;
    private TextField energyToFullTextField;
    private TextField energyToBreedTextField;
    private TextField minNumOfMutationsTextField;
    private TextField maxNumOfMutationsTextField;
    private TextField mutationWarrantTextField;
    private TextField behaviourWarrantTextField;
    private TextField plantGrowthWarrantTextField;
    public int energyToBreed;
    public int energyToFull;
    public int minNumOfMutations;
    public int maxNumOfMutations;
    public String mutationWarrant;
    public String behaviourWarrant;
    public String plantGrowthWarrant;
    public int width;
    public int height;
    public int plantsNum;
    public int plantEnergy;
    public int plantsDaily;
    public int animalsNum;
    public static int genomeLength;
    public int startingEnergy;
    int SCREEN_WIDTH = 800;
    int SCREEN_HEIGHT = 800;
    private int COLUMN_WIDTH;
    private int ROW_HEIGHT;


    public void start(Stage primaryStage) throws Exception {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);

        //
        Label label1 = new Label("Map width: ");
        GridPane.setConstraints(label1, 0, 0);
        widthTextField = new TextField();
        widthTextField.setPromptText("width");
        GridPane.setConstraints(widthTextField, 1, 0);

        //
        Label label2 = new Label("Map height: ");
        GridPane.setConstraints(label2, 0, 1);
        heightTextField = new TextField();
        heightTextField.setPromptText("height");
        GridPane.setConstraints(heightTextField, 1, 1);


        Label label3 = new Label("Number of plants: ");
        GridPane.setConstraints(label3, 0, 2);
        plantsNumTextField = new TextField();
        plantsNumTextField.setPromptText("plants");
        GridPane.setConstraints(plantsNumTextField, 1, 2);

        Label label4 = new Label("Plants energy: ");
        GridPane.setConstraints(label4, 0, 3);
        plantsEnergyTextField = new TextField();
        plantsEnergyTextField.setPromptText("plant energy");
        GridPane.setConstraints(plantsEnergyTextField, 1, 3);

        Label label5 = new Label("Number of plants per day ");
        GridPane.setConstraints(label5, 0, 4);
        plantsDailyTextField = new TextField();
        plantsDailyTextField.setPromptText("plants per day");
        GridPane.setConstraints(plantsDailyTextField, 1, 4);

        Label label6 = new Label("Plant's growth warrant");
        GridPane.setConstraints(label6, 0, 5);
        plantGrowthWarrantTextField = new TextField();
        plantGrowthWarrantTextField.setPromptText("zalesione równiki/toksyczne trupy");
        GridPane.setConstraints(plantGrowthWarrantTextField, 1, 5);

        Label label7 = new Label("Number of starting animals");
        GridPane.setConstraints(label7, 0, 6);
        animalsNumTextField = new TextField();
        animalsNumTextField.setPromptText("starting animals");
        GridPane.setConstraints(animalsNumTextField, 1, 6);

        Label label8 = new Label("Starting energy");
        GridPane.setConstraints(label8, 0, 7);
        startingEnergyTextField = new TextField();
        startingEnergyTextField.setPromptText("starting energy");
        GridPane.setConstraints(startingEnergyTextField, 1, 7);

        Label label9 = new Label("Necessary energy to create new animal");
        GridPane.setConstraints(label9, 0, 8);
        energyToFullTextField = new TextField();
        energyToFullTextField.setPromptText("energy");
        GridPane.setConstraints(energyToFullTextField, 1, 8);

        Label label10 = new Label("Energy used after new animal");
        GridPane.setConstraints(label10, 0, 9);
        energyToBreedTextField = new TextField();
        energyToBreedTextField.setPromptText("energy");
        GridPane.setConstraints(energyToBreedTextField, 1, 9);

        Label label11 = new Label("Min num of mutations");
        GridPane.setConstraints(label11, 0, 10);
        minNumOfMutationsTextField = new TextField();
        minNumOfMutationsTextField.setPromptText("min num");
        GridPane.setConstraints(minNumOfMutationsTextField, 1, 10);


        Label label12 = new Label("Max num of mutations");
        GridPane.setConstraints(label12, 0, 11);
        maxNumOfMutationsTextField = new TextField();
        maxNumOfMutationsTextField.setPromptText("max num");
        GridPane.setConstraints(maxNumOfMutationsTextField, 1, 11);

        Label label13 = new Label("Mutation warrant");
        GridPane.setConstraints(label13, 0, 12);
        mutationWarrantTextField = new TextField();
        mutationWarrantTextField.setPromptText("pełna losowość/lekka korekta");
        GridPane.setConstraints(mutationWarrantTextField, 1, 12);

        Label label14 = new Label("Genome length");
        GridPane.setConstraints(label14, 0, 13);
        genomeLengthTextField = new TextField();
        genomeLengthTextField.setPromptText("length");
        GridPane.setConstraints(genomeLengthTextField, 1, 13);

        Label label15 = new Label("Behaviour warrant");
        GridPane.setConstraints(label15, 0, 14);
        behaviourWarrantTextField = new TextField();
        behaviourWarrantTextField.setPromptText("pełna predestynacja/nieco szaleństwa");
        GridPane.setConstraints(behaviourWarrantTextField, 1, 14);
        Button startButton = new Button("Start");
        startButton.setOnAction(this::submit);
        GridPane.setConstraints(startButton, 1, 15);
        grid.getChildren().addAll(label1, widthTextField, label2, heightTextField, label3, plantsNumTextField, label4, plantsEnergyTextField, label5, plantsDailyTextField, label6, plantGrowthWarrantTextField, label7, animalsNumTextField, label8, startingEnergyTextField, label9, energyToFullTextField,
                label10, energyToBreedTextField, label11, minNumOfMutationsTextField, label12, maxNumOfMutationsTextField, label13, mutationWarrantTextField, label14, genomeLengthTextField, label15, behaviourWarrantTextField, startButton);


        Scene scene = new Scene(grid, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void submit(ActionEvent event) {
        try {
            width = Integer.parseInt(widthTextField.getText());
            height = Integer.parseInt(heightTextField.getText());
            plantsNum = Integer.parseInt(plantsNumTextField.getText());
            plantEnergy = Integer.parseInt(plantsEnergyTextField.getText());
            plantsDaily = Integer.parseInt(plantsDailyTextField.getText());
            animalsNum = Integer.parseInt(animalsNumTextField.getText());
            startingEnergy = Integer.parseInt(startingEnergyTextField.getText());
            genomeLength = Integer.parseInt(genomeLengthTextField.getText());
            energyToFull = Integer.parseInt(energyToFullTextField.getText());
            energyToBreed = Integer.parseInt(energyToBreedTextField.getText());
            minNumOfMutations = Integer.parseInt(minNumOfMutationsTextField.getText());
            maxNumOfMutations = Integer.parseInt(maxNumOfMutationsTextField.getText());
            mutationWarrant = mutationWarrantTextField.getText();
            behaviourWarrant = behaviourWarrantTextField.getText();
            plantGrowthWarrant = plantGrowthWarrantTextField.getText();
            simulation = new Simulation(width, height, plantsNum, plantEnergy, plantsDaily, animalsNum, startingEnergy, genomeLength, energyToFull, energyToBreed, minNumOfMutations,
                    maxNumOfMutations, mutationWarrant, behaviourWarrant, plantGrowthWarrant);
        } catch (NumberFormatException e) {
            System.out.println("Wrong data");

        }

    }
}
