package com.example.hescoin.Threads;

import com.example.hescoin.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UI extends Application {

    @Override
    public void start(Stage stage) {
        Parent root = null;
        try {
            
            root = FXMLLoader.load(HelloApplication.class.getResource("MainWindow.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("HES-Coin");
        stage.setScene(new Scene(root, 900, 700));
        stage.show();
    }
}