package com.example.bb;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    static Stage globleStage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("start.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500 , 500);

        globleStage=stage;
        stage.setTitle("Start Page");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    protected void startClickButton() throws IOException {
        // on click start we want to open game screen so thats why
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gameScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500 , 500);
//        scene.setFill(Color.BLACK);
        System.out.println("welcome to the game");
        globleStage.setTitle("Game Screen");
        globleStage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }
}