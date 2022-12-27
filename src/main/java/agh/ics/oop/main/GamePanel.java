package agh.ics.oop.main;

import agh.ics.oop.Animal;
import agh.ics.oop.Vector2d;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;


public class GamePanel implements Runnable{

    Thread gameThread;
    GridPane gridPane;
    public int width;
    public int height;
    public int plantsNum;
    public int plantEnergy;
    public int plantsDaily;
    public int animalsNum;
    public static int genomeLength;
    public int startingEnergy;
    int SCREEN_WIDTH = 1000;
    int SCREEN_HEIGHT = 700;
    private int COLUMN_WIDTH;
    private int ROW_HEIGHT;
    Animal animal;

    public GamePanel(int width, int height, int plantsNum, int plantEnergy, int plantsDaily, int animalsNum, int startingEnergy, int genomeLength){
        this.width = width;
        this.height = height;
        this.plantsNum = plantsNum;
        this.plantEnergy = plantEnergy;
        this.plantsDaily = plantsDaily;
        this.animalsNum = animalsNum;
        this.startingEnergy = startingEnergy;
        this.genomeLength = genomeLength;
        this.COLUMN_WIDTH = SCREEN_WIDTH / width;
        this.ROW_HEIGHT = SCREEN_HEIGHT / height;
        Stage stage1 = new Stage();
        gridPane = createMap(width, height);
        Scene scene = new Scene(gridPane, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage1.setScene(scene);
        stage1.setResizable(false);
        stage1.show();
        startGameThread();
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    public GridPane createMap(int width, int height){
        GridPane gridPane = new GridPane();
        for (int i = 0; i < height; i++){
            for ( int j = 0; j < width; j++){
                Label label = new Label("" + i +"," + j);
                GridPane.setConstraints(label, i, j);
                gridPane.getChildren().add(label);
                ColumnConstraints columnConstraints = new ColumnConstraints(COLUMN_WIDTH);
                RowConstraints rowConstraints = new RowConstraints(ROW_HEIGHT);
                gridPane.getRowConstraints().add(rowConstraints);
                gridPane.getColumnConstraints().add(columnConstraints);
            }
        }
        gridPane.setGridLinesVisible(true);
        return gridPane;
    }


    @Override
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if (delta >= 1){
                update();
                delta--;
            }
        }

    }
    public void update(){


    }

}
