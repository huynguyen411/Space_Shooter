package Model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class SpaceShooterSubScene extends SubScene {

    private final String FONT_PATH = "src/Model/resource/kenvector_future.ttf";
    private final String BACKGROUND_IMAGE ="Model/resource/green_panel.png";

    public SpaceShooterSubScene() {
        super(new AnchorPane(), 600, 800);
        prefHeight(400);
        prefWidth(600);
        createBackground();
        this.setLayoutX(3000);
        this.setLayoutY(180);
    }

    private void createBackground(){
        AnchorPane pane = (AnchorPane) this.getRoot();
        Image image = new Image(BACKGROUND_IMAGE, 600, 400, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, null);
        pane.setBackground(new Background(backgroundImage));
    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        transition.setToX(-2600);
        transition.play();
    }

    public void removeSubScene(){
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        transition.setToX(2600);
        transition.play();
    }

    public AnchorPane getPane(){
        return (AnchorPane) this.getRoot();
    }
}
