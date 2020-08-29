package ViewManager;

import Model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    public static final int HEIGHT = 768;
    public static final int WIDTH = 1024;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private static final int NEW_BUTTONS_START_X = 100;
    public static final int NEW_BUTTONS_START_Y = 150;

    List<SpaceShooterButton> menuButtons;

    List<ShipPicker> shipList;
    SHIP chosenShip;

    private SpaceShooterSubScene creditsSubScene;
    private SpaceShooterSubScene helpSubScene;
    private SpaceShooterSubScene scoresSubScene;
    private SpaceShooterSubScene shipChooserSubScene;

    private SpaceShooterSubScene sceneToHide;

    public ViewManager(){
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButtons();
        createBackground();
        createSubScene();

        mainStage.show();
    };

    private void hideSubScene(SpaceShooterSubScene subScene){
        if (sceneToHide != null){
            sceneToHide.removeSubScene();
        }

        subScene.moveSubScene();
        sceneToHide = subScene;
    }
    private void addMenuButtons(SpaceShooterButton button){
        button.setLayoutX(NEW_BUTTONS_START_X);
        button.setLayoutY(NEW_BUTTONS_START_Y + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createButtons(){
        createStartButton();
        createScoresButton();
        createHelpButton();
        createCreditButton();
        createExitButton();
    }

    private void createStartButton(){
        SpaceShooterButton button = new SpaceShooterButton("PLAY");
        addMenuButtons(button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                hideSubScene(shipChooserSubScene);
            }
        });
    }

    private void createScoresButton(){
        SpaceShooterButton button = new SpaceShooterButton("SCORES");
        addMenuButtons(button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                hideSubScene(scoresSubScene);
            }
        });
    }

    private void createHelpButton(){
        SpaceShooterButton button = new SpaceShooterButton("HELP");
        addMenuButtons(button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                hideSubScene(helpSubScene);
            }
        });
    }

    private void createCreditButton(){
        SpaceShooterButton button = new SpaceShooterButton("CREDITS");
        addMenuButtons(button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                hideSubScene(creditsSubScene);
            }
        });
    }

    private void createExitButton(){
        SpaceShooterButton button = new SpaceShooterButton("EXIT");
        addMenuButtons(button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.close();
            }
        });
    }

    private void createBackground(){
        Image image = new Image("ViewManager/resource/purple.png", 256, 256, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(backgroundImage));
    }
    private void createSubScene(){
        creditsSubScene = new SpaceShooterSubScene();
        scoresSubScene = new SpaceShooterSubScene();
        helpSubScene = new SpaceShooterSubScene();
        mainPane.getChildren().addAll(creditsSubScene, scoresSubScene, helpSubScene);

        createChooserSubScene();
    }

    private void createChooserSubScene() {
        shipChooserSubScene = new SpaceShooterSubScene();
        mainPane.getChildren().add(shipChooserSubScene);

        InforLabel chooseShipLabel = new InforLabel("CHOOSE YOUR SHIP");
        chooseShipLabel.setLayoutX(110);
        chooseShipLabel.setLayoutY(15);

        shipChooserSubScene.getPane().getChildren().add(chooseShipLabel);
        shipChooserSubScene.getPane().getChildren().add(createShipToChoose());
        shipChooserSubScene.getPane().getChildren().add(createButtonToStart());
    }

    private HBox createShipToChoose(){
        HBox box = new HBox();
        box.setSpacing(20);
        shipList = new ArrayList<>();
        for (SHIP ship : SHIP.values()){
            ShipPicker shipToPick = new ShipPicker(ship);
            box.getChildren().add(shipToPick);
            shipList.add(shipToPick);

            shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for(ShipPicker ship : shipList){
                        ship.setIsCircleChosen(false);
                    }
                    shipToPick.setIsCircleChosen(true);
                    chosenShip = shipToPick.getShip();
                }
            });
        }
        box.setLayoutX(300 - (118 * 2));
        box.setLayoutY(100);
        return box;
    }

    private SpaceShooterButton createButtonToStart(){
        SpaceShooterButton startButton = new SpaceShooterButton("START");
        startButton.setLayoutX(350);
        startButton.setLayoutY(300);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (chosenShip != null){
                    GameViewManager gameViewManager = new GameViewManager();
                    gameViewManager.createBoardGame(mainStage, chosenShip);
                }
            }
        });

        return startButton;
    }
}
