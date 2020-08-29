package ViewManager;

import Model.PointLabel;
import Model.SHIP;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Random;

public class GameViewManager {
    public static final int GAME_WIDTH = 600;
    public static final int GAME_HEIGHT = 800;
    public static final int NUMBER_OF_LASER = 85;

    private static final String ENEMY_1 = "ViewManager/resource/Enemy/enemyBlack1.png";
    private static final String ENEMY_2 = "ViewManager/resource/Enemy/enemyBlue2.png";
    private static final String ENEMY_3 = "ViewManager/resource/Enemy/enemyGreen3.png";
    private static final String ENEMY_4 = "ViewManager/resource/Enemy/enemyRed4.png";

    private static final int ENEMY_RADIUS = 27;
    private static final int SHIP_RADIUS = 27;
    private static final int LASER_RADIUS = 1;

    private int countToReleaseLaser = 0, laserTh = 0;
    private ImageView[] enemy1;
    private ImageView[] enemy2;
    private ImageView[] enemy3;
    private ImageView[] enemy4;

    Random randomPositionGenerators;

    private PointLabel pointLabel;
    private ImageView chosenShip;
    private ImageView laser1;
    private ImageView laser2;

    private ImageView[] laser_1;
    private ImageView[] laser_2;

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;

    private AnimationTimer gameTimer;
    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private Stage menuStage;

    public GameViewManager(){
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);

        randomPositionGenerators = new Random();
        createGameElements();
        createGameBackground();
    }

    private void createKeyListener(){

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT){
                    isLeftKeyPressed = true;
                } else if (keyEvent.getCode() == KeyCode.RIGHT){
                    isRightKeyPressed = true;
                } else if (keyEvent.getCode() == KeyCode.UP){
                    isUpKeyPressed = true;
                } else if (keyEvent.getCode() == KeyCode.DOWN){
                    isDownKeyPressed = true;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.LEFT){
                    isLeftKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.RIGHT){
                    isRightKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.UP){
                    isUpKeyPressed = false;
                } else if (keyEvent.getCode() == KeyCode.DOWN){
                    isDownKeyPressed = false;
                }
            }
        });
    }

    public void createBoardGame(Stage menuStage, SHIP ship){
        this.menuStage = menuStage;
        this.menuStage.hide();

        chosenShip = new ImageView(ship.getUrlShip());
        laser1 = new ImageView(ship.getUrlLaser());
        laser2 = new ImageView(ship.getUrlLaser());


        laser_1 = new ImageView[NUMBER_OF_LASER];
        laser_2 = new ImageView[NUMBER_OF_LASER];

        for (int i = 0; i < laser_1.length; i++){
            laser_1[i] = new ImageView(ship.getUrlLaser());
            laser_2[i] = new ImageView(ship.getUrlLaser());
            gamePane.getChildren().add(laser_1[i]);
            gamePane.getChildren().add(laser_2[i]);
        }

        laser1.setLayoutX(GAME_WIDTH / 2 - 50);
        laser1.setLayoutY(GAME_HEIGHT - 95);
        laser2.setLayoutX(GAME_WIDTH / 2 + 43);
        laser2.setLayoutY(GAME_HEIGHT - 95);
        chosenShip.setLayoutX(GAME_WIDTH / 2 - 50);
        chosenShip.setLayoutY(GAME_HEIGHT - 90);

        gamePane.getChildren().add(chosenShip);
        gamePane.getChildren().addAll(laser1, laser2);

        createGameLoop();
        gameStage.show();
    }

    private void createGameLoop(){
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveShip();
                moveElements();
                createLaser();
                checkIfElementsCollide();
            }
        };
        gameTimer.start();
    }

    private void createLaser(){
        if (laserTh < NUMBER_OF_LASER) {
            if (countToReleaseLaser % 25 == 0) {
                laser_1[laserTh].setLayoutX(laser1.getLayoutX());
                laser_1[laserTh].setLayoutY(laser1.getLayoutY());
                laser_2[laserTh].setLayoutX(laser2.getLayoutX());
                laser_2[laserTh].setLayoutY(laser2.getLayoutY());
                laserTh++;
            }
            countToReleaseLaser++;
        }

    }
    private void moveShip(){
        createKeyListener();
        if (isLeftKeyPressed && !isRightKeyPressed){
            if(chosenShip.getLayoutX() > -20){
                chosenShip.setLayoutX(chosenShip.getLayoutX() - 5);
                laser1.setLayoutX(laser1.getLayoutX() - 5);
                laser2.setLayoutX(laser2.getLayoutX() - 5);

            }
        }
        if (isRightKeyPressed && !isLeftKeyPressed){
            if(chosenShip.getLayoutX() < 522){
                chosenShip.setLayoutX(chosenShip.getLayoutX() + 5);
                laser1.setLayoutX(laser1.getLayoutX() + 5);
                laser2.setLayoutX(laser2.getLayoutX() + 5);
            }
        }
        if (isUpKeyPressed && !isDownKeyPressed){
            if (chosenShip.getLayoutY() > 0){
                chosenShip.setLayoutY(chosenShip.getLayoutY() - 5);
                laser1.setLayoutY(laser1.getLayoutY() - 5);
                laser2.setLayoutY(laser2.getLayoutY() - 5);
            }
        }
        if (isDownKeyPressed && !isUpKeyPressed){
            if (chosenShip.getLayoutY() < 700){
                chosenShip.setLayoutY(chosenShip.getLayoutY() + 5);
                laser1.setLayoutY(laser1.getLayoutY() + 5);
                laser2.setLayoutY(laser2.getLayoutY() + 5);
            }
        }
    }

    private void createGameElements(){
        pointLabel = new PointLabel("POINTS: ");
        pointLabel.setLayoutX(460);
        pointLabel.setLayoutY(20);
        gamePane.getChildren().add(pointLabel);

        enemy1 = new ImageView[7];
        enemy2 = new ImageView[7];
        enemy3 = new ImageView[7];
        enemy4 = new ImageView[7];

        for (int i = 0; i < enemy1.length; i++){
            enemy1[i] = new ImageView(ENEMY_1);
            setNewElementsPosition(enemy1[i]);
            gamePane.getChildren().add(enemy1[i]);
        }
        for (int i = 0; i < enemy2.length; i++){
            enemy2[i] = new ImageView(ENEMY_2);
            setNewElementsPosition(enemy2[i]);
            gamePane.getChildren().add(enemy2[i]);
        }
        for (int i = 0; i < enemy3.length; i++){
            enemy3[i] = new ImageView(ENEMY_3);
            setNewElementsPosition(enemy3[i]);
            gamePane.getChildren().add(enemy3[i]);
        }
        for (int i = 0; i < enemy4.length; i++){
            enemy4[i] = new ImageView(ENEMY_4);
            setNewElementsPosition(enemy4[i]);
            gamePane.getChildren().add(enemy4[i]);
        }

    }

    private void setNewElementsPosition(ImageView image){
        image.setLayoutX(randomPositionGenerators.nextInt(400));
        image.setLayoutY(-(randomPositionGenerators.nextInt(3200) + 600));
    }

    private void moveElements(){
        for (ImageView imageView : laser_1) {
            imageView.setLayoutY(imageView.getLayoutY() - 3);
        }

        for (ImageView imageView : laser_2){
            imageView.setLayoutY(imageView.getLayoutY() - 3);
        }

        for (ImageView imageView : enemy1) {
            imageView.setLayoutY(imageView.getLayoutY() + 2.5);
        }
        for (ImageView imageView : enemy2) {
            imageView.setLayoutY(imageView.getLayoutY() + 2.5);
        }
        for (ImageView imageView : enemy3) {
            imageView.setLayoutY(imageView.getLayoutY() + 2.5);
        }
        for (ImageView imageView : enemy4) {
            imageView.setLayoutY(imageView.getLayoutY() + 2.5);
        }
    }

    private void checkIfElementsCollide(){
        for (int i = 0; i < enemy1.length; i++){
            for (int j = 0; j < laser_1.length; j++){
                if (checkDistanceBetween2Element(enemy1[i].getLayoutX() + 49, enemy1[i].getLayoutY() + 37,
                        laser_1[j].getLayoutX(), laser_1[j].getLayoutY() + 1, laser_2[j].getLayoutX(),
                        laser_2[j].getLayoutY() + 1) && (laser_1[j].getLayoutY() > 0)) {

                    //shift the laser and shot enemy out of the screen
                    enemy1[i].setLayoutX(-150);
                    enemy1[i].setLayoutY(-1);
                    laser_1[j].setLayoutX(-100);
                    laser_1[j].setLayoutY(-1);
                    laser_2[j].setLayoutX(-100);
                    laser_2[j].setLayoutY(-1);
                }
                if (checkDistanceBetween2Element(enemy2[i].getLayoutX() + 49, enemy2[i].getLayoutY() + 37,
                        laser_1[j].getLayoutX(), laser_1[j].getLayoutY() + 1, laser_2[j].getLayoutX(),
                        laser_2[j].getLayoutY() + 1) && (laser_1[j].getLayoutY() > 0)) {

                    //shift the laser and shot enemy out of the screen
                    enemy2[i].setLayoutX(-150);
                    enemy2[i].setLayoutY(-1);
                    laser_1[j].setLayoutX(-100);
                    laser_1[j].setLayoutY(-1);
                    laser_2[j].setLayoutX(-100);
                    laser_2[j].setLayoutY(-1);
                }
                if (checkDistanceBetween2Element(enemy3[i].getLayoutX() + 49, enemy3[i].getLayoutY() + 37,
                        laser_1[j].getLayoutX(), laser_1[j].getLayoutY() + 1, laser_2[j].getLayoutX(),
                        laser_2[j].getLayoutY() + 1) && (laser_1[j].getLayoutY() > 0)) {

                    //shift the laser and shot enemy out of the screen
                    enemy3[i].setLayoutX(-150);
                    enemy3[i].setLayoutY(-1);
                    laser_1[j].setLayoutX(-100);
                    laser_1[j].setLayoutY(-1);
                    laser_2[j].setLayoutX(-100);
                    laser_2[j].setLayoutY(-1);
                }
                if (checkDistanceBetween2Element(enemy4[i].getLayoutX() + 49, enemy4[i].getLayoutY() + 37,
                        laser_1[j].getLayoutX(), laser_1[j].getLayoutY() + 1, laser_2[j].getLayoutX(),
                        laser_2[j].getLayoutY() + 1) && (laser_1[j].getLayoutY() > 0)) {

                    //shift the laser and shot enemy out of the screen
                    enemy4[i].setLayoutX(-150);
                    enemy4[i].setLayoutY(-1);
                    laser_1[j].setLayoutX(-100);
                    laser_1[j].setLayoutY(-1);
                    laser_2[j].setLayoutX(-100);
                    laser_2[j].setLayoutY(-1);
                }
            }
        }
    }

    private boolean checkDistanceBetween2Element(double xEnemy, double yEnemy, double xLaser1, double yLaser1,
                                                 double xLaser2, double yLaser2){
        double distance1 = Math.sqrt(Math.pow(xEnemy - xLaser1, 2) + Math.pow(yEnemy - yLaser1, 2));
        double distance2 = Math.sqrt(Math.pow(xEnemy - xLaser2, 2) + Math.pow(yEnemy - yLaser2, 2));
        return (ENEMY_RADIUS + LASER_RADIUS > distance1) || (ENEMY_RADIUS + LASER_RADIUS > distance2);
    }

    private void createGameBackground(){
        Image image = new Image("ViewManager/resource/purple.png", 256, 256, false, true);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT, null);
        gamePane.setBackground(new Background(backgroundImage));
    }

}
