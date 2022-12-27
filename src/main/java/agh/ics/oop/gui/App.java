package agh.ics.oop.gui;

import agh.ics.oop.main.GamePanel;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class App extends  Application{

    private TextField widthTextField;
    private TextField heightTextField;
    private TextField plantsNumTextField;
    private TextField plantsEnergyTextField;
    private TextField plantsDailyTextField;
    private TextField animalsNumTextField;
    private TextField startingEnergyTextField;
    private TextField genomeLengthTextField;

    public int width;
    public int height;
    public int plantsNum;
    public int plantEnergy;
    public int plantsDaily;
    public int animalsNum;
    public int startingEnergy;
    public int genomeLength;
    public GamePanel gamePanel;

    @Override
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
        TextField name6 = new TextField();
        name6.setPromptText("warrant");
        GridPane.setConstraints(name6, 1, 5);

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
        TextField name9 = new TextField();
        name9.setPromptText("energy");
        GridPane.setConstraints(name9, 1, 8);

        Label label10 = new Label("Energy used after new animal");
        GridPane.setConstraints(label10, 0, 9);
        TextField name10 = new TextField();
        name10.setPromptText("energy");
        GridPane.setConstraints(name10, 1, 9);

        Label label11 = new Label("Min num of mutations");
        GridPane.setConstraints(label11, 0, 10);
        TextField name11 = new TextField();
        name11.setPromptText("min num");
        GridPane.setConstraints(name11, 1, 10);


        Label label12 = new Label("Max num of mutations");
        GridPane.setConstraints(label12, 0, 11);
        TextField name12 = new TextField();
        name12.setPromptText("max num");
        GridPane.setConstraints(name12, 1, 11);

        Label label13 = new Label("Mutation warrant");
        GridPane.setConstraints(label13, 0, 12);
        TextField name13 = new TextField();
        name13.setPromptText("min num");
        GridPane.setConstraints(name13, 1, 12);

        Label label14 = new Label("Genome length");
        GridPane.setConstraints(label14, 0, 13);
        genomeLengthTextField = new TextField();
        genomeLengthTextField.setPromptText("length");
        GridPane.setConstraints(genomeLengthTextField, 1, 13);

        Label label15 = new Label("Behaviour warrant");
        GridPane.setConstraints(label15, 0, 14);
        TextField name15 = new TextField();
        name15.setPromptText("warrant");
        GridPane.setConstraints(name15, 1, 14);

        Button startButton = new Button("Start");
        startButton.setOnAction(this::submit);
        GridPane.setConstraints(startButton, 1, 15);
        grid.getChildren().addAll(label1, widthTextField, label2, heightTextField, label3, plantsNumTextField, label4, plantsEnergyTextField, label5, plantsDailyTextField, label6, name6, label7, animalsNumTextField, label8, startingEnergyTextField, label9, name9,
                label10, name10, label11, name11, label12, name12, label13, name13, label14, genomeLengthTextField, label15, name15, startButton);



        Scene scene = new Scene(grid, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void submit(ActionEvent event){
        try{
            width = Integer.parseInt(widthTextField.getText());
            height = Integer.parseInt(heightTextField.getText());
            plantsNum = Integer.parseInt(plantsNumTextField.getText());
            plantEnergy = Integer.parseInt(plantsEnergyTextField.getText());
            plantsDaily = Integer.parseInt(plantsDailyTextField.getText());
            animalsNum = Integer.parseInt(animalsNumTextField.getText());
            startingEnergy = Integer.parseInt(startingEnergyTextField.getText());
            genomeLength = Integer.parseInt(genomeLengthTextField.getText());
            gamePanel = new GamePanel(width, height, plantsNum, plantEnergy, plantsDaily, animalsNum, startingEnergy, genomeLength);
        }
        catch (NumberFormatException e){
            System.out.println("Wrong data");

        }

    }
}
