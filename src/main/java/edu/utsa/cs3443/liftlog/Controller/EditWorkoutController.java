package edu.utsa.cs3443.liftlog.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import edu.utsa.cs3443.liftlog.model.Exercise;
import edu.utsa.cs3443.liftlog.model.Workout;
import edu.utsa.cs3443.liftlog.model.WorkoutManager;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public class EditWorkoutController {

        @FXML
        private VBox exerciseRows;

        @FXML
        private TextField workoutNameField;

        @FXML
        private DatePicker workoutDatePicker;

        @FXML
        private void initialize() {
            handleAddExercise();
        }

        @FXML
        private void handleAddExercise() {
            TextField exerciseNameField = new TextField();
            exerciseNameField.setPromptText("Exercise name");
            HBox.setHgrow(exerciseNameField, Priority.ALWAYS);

            TextField setsField = new TextField();
            setsField.setPromptText("Sets");
            setsField.getStyleClass().add("exercise-count");

            TextField repsField = new TextField();
            repsField.setPromptText("Reps");
            repsField.getStyleClass().add("exercise-count");

            TextField weightField = new TextField();
            weightField.setPromptText("Weight (lbs)");
            weightField.getStyleClass().add("exercise-weight");

            Button removeButton = new Button("Remove");
            removeButton.getStyleClass().addAll("remove-exercise-button", "exercise-remove");

            HBox row = new HBox(8, exerciseNameField, setsField, repsField,
                    weightField, removeButton);

            removeButton.setOnAction(event -> exerciseRows.getChildren().remove(row));
            exerciseRows.getChildren().add(row);
        }
        @FXML
        private void handleSaveWorkout() {
            String workoutName = workoutNameField.getText().trim();
            LocalDate workoutDate = workoutDatePicker.getValue();

            if (!isValidName(workoutName)) {
                showMessage(Alert.AlertType.ERROR,
                        "Enter a workout name without commas or line breaks.");
                return;
            }

            if (workoutDate == null) {
                showMessage(Alert.AlertType.ERROR, "Select a workout date.");
                return;
            }

            if (exerciseRows.getChildren().isEmpty()) {
                showMessage(Alert.AlertType.ERROR, "Add at least one exercise.");
                return;
            }

            Workout workout = new Workout(
                    UUID.randomUUID().toString(), workoutName, workoutDate);

            try {
                for (Node node : exerciseRows.getChildren()) {
                    HBox row = (HBox) node;
                    workout.addExercise(readExercise(row));
                }
            } catch (IllegalArgumentException e) {
                showMessage(Alert.AlertType.ERROR, e.getMessage());
                return;
            }

            WorkoutManager manager = new WorkoutManager();
            try {
                manager.loadWorkouts();
                manager.addWorkout(workout);
                manager.saveWorkoutsOrThrow();
            } catch (IOException | RuntimeException e) {
                showMessage(Alert.AlertType.ERROR, "Could not save workout. " + e.getMessage());
                return;
            }

            workoutNameField.clear();
            workoutDatePicker.setValue(null);
            exerciseRows.getChildren().clear();
            handleAddExercise();
            showMessage(Alert.AlertType.INFORMATION, "Workout saved. Open Workout History to see it.");
        }

        private Exercise readExercise(HBox row) {
            String name = ((TextField) row.getChildren().get(0)).getText().trim();
            String setsText = ((TextField) row.getChildren().get(1)).getText().trim();
            String repsText = ((TextField) row.getChildren().get(2)).getText().trim();
            String weightText = ((TextField) row.getChildren().get(3)).getText().trim();

            if (!isValidName(name)) {
                throw new IllegalArgumentException(
                        "Enter a name for each exercise without commas or line breaks.");
            }

            int sets;
            int reps;
            double weight;
            try {
                sets = Integer.parseInt(setsText);
                reps = Integer.parseInt(repsText);
                weight = Double.parseDouble(weightText);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "Enter whole numbers for sets and reps, and a number for weight.");
            }

            if (sets < 1 || reps < 1 || !Double.isFinite(weight) || weight < 0) {
                throw new IllegalArgumentException(
                        "Sets and reps must be at least 1. Weight must be 0 or greater.");
            }

            return new Exercise(name, sets, reps, weight);
        }

        private boolean isValidName(String name) {
            return !name.isEmpty() && !name.contains(",")
                    && !name.contains("\n") && !name.contains("\r");
        }

        private void showMessage(Alert.AlertType type, String message) {
            Alert alert = new Alert(type);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
}

