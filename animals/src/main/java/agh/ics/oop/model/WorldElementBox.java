package agh.ics.oop.model;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class WorldElementBox {
    private Image image;
    private Label positionLabel;
    private VBox container;

    public WorldElementBox(WorldElement worldElement) {

        this.image = new Image(worldElement.getFileName());
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        if(Objects.equals(worldElement.toString(), "*")){
            this.positionLabel = new Label("Grass");
        }
        else{
            this.positionLabel = new Label(worldElement.getPosition().toString());
        }

        this.container = new VBox();
        this.container.getChildren().addAll(imageView, positionLabel);
        this.container.setAlignment(Pos.CENTER);
    }

    public Image getImage() {
        return image;
    }

    public Label getPositionLabel() {
        return positionLabel;
    }
    public VBox getContainer(){
        return container;
    }

}
