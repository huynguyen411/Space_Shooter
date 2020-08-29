package Model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpaceShooterButton extends Button {
    private final String FONT_PATH = "src/Model/resource/kenvector_future.ttf";
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/Model/resource/green_button_pressed.png');";
    private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/Model/resource/green_button.png');";

    public SpaceShooterButton(String text){
        setText(text);
        setButtonFont();
        setPrefHeight(45);
        setPrefWidth(190);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();
    }

    private void setButtonFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
    }

    private void setButtonPressedStyle(){
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 3);
    }

    private void setButtonFreeStyle(){
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(45);
        setLayoutY(getLayoutY() - 3);
    }

    private void initializeButtonListeners(){
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)){
                    setButtonPressedStyle();
                }
            }
        });

        setOnMouseReleased(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                setButtonFreeStyle();
            }
        });

        setOnMouseEntered(event -> setEffect(new DropShadow()));

        setOnMouseExited(event -> setEffect(null));
    }


}
