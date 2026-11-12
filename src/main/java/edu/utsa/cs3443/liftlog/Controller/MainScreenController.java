package edu.utsa.cs3443.liftlog.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainScreenController {

    @FXML
    private BorderPane rootWindow;
    @FXML
    private Button HomeButton;
    @FXML
    private Button AddWorkoutButton;
    @FXML
    private Button HistoryButton;
    @FXML
    private Button SettingsButton;

    @FXML
    private void onHomeClick(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/utsa/cs3443/liftlog/XML Files/Home.fxml"));
            Parent screen = loader.load();
            rootWindow.setCenter(screen);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void onHistoryClick(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/utsa/cs3443/liftlog/XML Files/History.fxml"));
            Parent screen = loader.load();
            rootWindow.setCenter(screen);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
