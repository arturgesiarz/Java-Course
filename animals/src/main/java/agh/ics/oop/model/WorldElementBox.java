package agh.ics.oop.model;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class WorldElementBox {
    private final Image image;
    private final Label positionLabel;
    private final VBox container;

    public WorldElementBox(String imageUrl, String position) {

        this.image = new Image(imageUrl);
        ImageView imageView = new ImageView(image);

        imageView.setFitWidth(20);
        imageView.setFitHeight(20);

        this.positionLabel = new Label(position);

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
