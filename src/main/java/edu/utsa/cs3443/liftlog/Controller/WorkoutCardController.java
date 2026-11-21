package edu.utsa.cs3443.liftlog.Controller;

import edu.utsa.cs3443.liftlog.model.Workout;
import edu.utsa.cs3443.liftlog.model.Exercise;
import edu.utsa.cs3443.liftlog.model.Utilities;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class WorkoutCardController {

    Utilities utilities = new  Utilities();

    @FXML
    private Label workoutName;

    @FXML
    private Label workoutDate;

    @FXML
    private VBox exerciseList;

    public void setWorkout(Workout workout) {
        workoutName.setText(workout.getWorkoutName());

        workoutDate.setText(utilities.formatDate(workout.getDate()));

        exerciseList.getChildren().clear();

        for (Exercise exercise : workout.getExercises()) {
            Label exerciseLabel = new Label(
                    exercise.getName() + "     " +
                            exercise.getSets() + " x " +
                            exercise.getReps() + "     " +
                            exercise.getWeight() + " lbs"
            );

            exerciseLabel.setStyle("-fx-text-fill: #aaaaaa; -fx-font-size: 13;");

            exerciseList.getChildren().add(exerciseLabel);
        }
    }
}
