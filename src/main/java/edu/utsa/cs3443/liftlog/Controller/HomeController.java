package edu.utsa.cs3443.liftlog.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import edu.utsa.cs3443.liftlog.model.Utilities;
import edu.utsa.cs3443.liftlog.model.Workout;
import edu.utsa.cs3443.liftlog.model.WorkoutManager;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class HomeController {

    @FXML
    private Label date;

    @FXML
    private AnchorPane anchor;

    @FXML
    private VBox recentWorkoutsBox;

    @FXML
    private Button viewAllButton;

    private Utilities util = new Utilities();
    private WorkoutManager workoutManager = new WorkoutManager();

    @FXML
    public void initialize() {
        date.setText(util.getDate());

        try {
            workoutManager.loadWorkouts();

            ArrayList<Workout> recent = new ArrayList<>(
                    workoutManager.getWorkouts()
            );

            recent.sort(
                    Comparator.comparing(Workout::getDate).reversed()
            );

            int count = Math.min(3, recent.size());

            for (int i = 0; i < count; i++) {
                Workout workout = recent.get(i);

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource(
                                "/edu/utsa/cs3443/liftlog/XML Files/WorkoutCard.fxml"
                        )
                );

                Parent card = loader.load();

                WorkoutCardController controller =
                        loader.getController();

                controller.setWorkout(workout);

                recentWorkoutsBox.getChildren().add(card);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void onViewAllClick(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/utsa/cs3443/liftlog/XML Files/History.fxml"));
            Parent screen = loader.load();
            BorderPane root = (BorderPane) anchor.getScene().getRoot();
            root.setCenter(screen);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}