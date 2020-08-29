package Model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class ShipPicker extends VBox {
    private final String circleNotChosen = "Model/resource/grey_circle.png";
    private final String circleChosen = "Model/resource/green_boxTick.png";

    private ImageView circleImage;
    private ImageView shipImage;

    private SHIP ship;

    private boolean isCircleChosen;

    public ShipPicker(SHIP ship){
        this.ship = ship;
        isCircleChosen = false;
        circleImage = new ImageView(circleNotChosen);
        shipImage = new ImageView(ship.getUrlShip());
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().add(circleImage);
        this.getChildren().add(shipImage);
    }

    public boolean getIsCircleChosen() {
        return isCircleChosen;
    }

    public void setIsCircleChosen(boolean isCircleChosen){
        this.isCircleChosen = isCircleChosen;
        String imageToSet = this.isCircleChosen? circleChosen : circleNotChosen;
        circleImage.setImage(new Image(imageToSet));
    }

    public SHIP getShip() {
        return ship;
    }
}

