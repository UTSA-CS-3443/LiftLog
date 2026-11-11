package edu.utsa.cs3443.liftlog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LiftLogApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LiftLogApplication.class.getResource("layouts/main-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1100, 750);
        stage.setTitle("LiftLog");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}