package agh.ics.oop;

import javafx.scene.Scene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Simulation implements Runnable {
    Thread gameThread;
    GridPane gridPane;
    public int width;
    public int height;
    public int plantsNum;
    public int plantEnergy;
    public int plantsDaily;
    public int animalsNum;
    public int genomeLength;
    public int startingEnergy;
    public int energyToBreed;
    public int energyToFull;
    public int minNumOfMutations;
    public int maxNumOfMutations;
    public String mutationWarrant;
    public String behaviourWarrant;
    public String plantGrowthWarrant;
    int SCREEN_WIDTH = 800;
    int SCREEN_HEIGHT = 800;
    private int COLUMN_WIDTH;
    private int ROW_HEIGHT;
    AbstractWorldMap map;

    public Simulation(int width, int height, int plantsNum, int plantEnergy, int plantsDaily, int animalsNum, int startingEnergy, int genomeLength, int energyToBreed, int energyToFull,
                      int minNumOfMutations, int maxNumOfMutations, String mutationWarrant, String behaviourWarrant, String plantGrowthWarrant){
        this.width = width;
        this.height = height;
        this.plantsNum = plantsNum;
        this.plantEnergy = plantEnergy;
        this.plantsDaily = plantsDaily;
        this.animalsNum = animalsNum;
        this.startingEnergy = startingEnergy;
        this.genomeLength = genomeLength;
        this.energyToBreed = energyToBreed;
        this.energyToFull = energyToFull;
        this.minNumOfMutations = minNumOfMutations;
        this.maxNumOfMutations = maxNumOfMutations;
        this.mutationWarrant = mutationWarrant;
        this.plantGrowthWarrant = plantGrowthWarrant;
        this.behaviourWarrant = behaviourWarrant;
        this.COLUMN_WIDTH = SCREEN_WIDTH / width;
        this.ROW_HEIGHT = SCREEN_HEIGHT / height;
        this.map = new GrassField(plantsNum);
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
                Object object = this.map.objectAt(new Vector2d(i,  j));
                if (object instanceof Grass) {
                    Pane pane = new Pane();
                    pane.setStyle("-fx-background-color: #454343;");
                    gridPane.add(pane, i, j);
                }
                if (object instanceof Animal){
                    Pane pane = new Pane();
                    pane.setStyle("-fx-background-color: #03ac13;");
                    gridPane.add(pane, i, j);
                }

                ColumnConstraints columnConstraints = new ColumnConstraints(COLUMN_WIDTH);
                RowConstraints rowConstraints = new RowConstraints(ROW_HEIGHT);
                gridPane.getRowConstraints().add(rowConstraints);
                gridPane.getColumnConstraints().add(columnConstraints);
            }
        }
        gridPane.setGridLinesVisible(true);
        System.out.println("koniec");
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
