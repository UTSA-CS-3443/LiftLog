package edu.utsa.cs3443.liftlog.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainScreenController {

    @FXML
    private BorderPane rootWindow;
    @FXML
    private ToggleButton HomeButton;
    @FXML
    private ToggleButton AddWorkoutButton;
    @FXML
    private ToggleButton HistoryButton;
    @FXML
    private ToggleButton SettingsButton;


    @FXML
    public void loadScene(String fileLocation){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fileLocation));
            Parent screen = loader.load();
            rootWindow.setCenter(screen);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Loads the home screen first when you open the program
    @FXML
    public void initialize(){
        loadScene("/edu/utsa/cs3443/liftlog/XML Files/Home.fxml");
    }

    @FXML
    private void onHomeClick(){
        loadScene("/edu/utsa/cs3443/liftlog/XML Files/Home.fxml");
    }

    @FXML
    private void onHistoryClick(){
      loadScene("/edu/utsa/cs3443/liftlog/XML Files/History.fxml");
    }
    @FXML
    private void onAddWorkoutClick(){
       loadScene("/edu/utsa/cs3443/liftlog/XML Files/AddWorkout.fxml");
    }

    @FXML
    private void onSettingsClick(){
        loadScene("/edu/utsa/cs3443/liftlog/XML Files/Settings.fxml");
    }
}
