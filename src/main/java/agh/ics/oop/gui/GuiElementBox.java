package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.IMapElement;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class GuiElementBox {

    private VBox vBox = new VBox();

    public GuiElementBox(IMapElement object) throws FileNotFoundException{

        Image image = new Image(new FileInputStream(object.getPath(object)));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);

        Label label;
        if(object instanceof Animal) label = new Label(object.getPosition().toString());
        else label = new Label("trawa");

        this.vBox.getChildren().add(imageView);
        this.vBox.getChildren().add(label);
        vBox.setAlignment(Pos.CENTER);
    }

    public VBox getvBox() {return vBox;}

}
