package Model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PointLabel extends Label {
    private static final String FONT_PATH = "src/Model/resource/kenvector_future.ttf";
    private static final String POINT_LABEL = "Model/resource/green_label.png";

    public PointLabel (String text){
        setPrefWidth(130);
        setPrefHeight(50);
        setText(text);
        setLabelFont();
        setAlignment(Pos.CENTER_LEFT);

        BackgroundImage backgroundImage = new BackgroundImage(new Image(POINT_LABEL, 130, 50, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(backgroundImage));
        setPadding(new Insets(10, 10, 10, 10));
    }

    private void setLabelFont(){
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 15));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 15));
        }
    }
}
