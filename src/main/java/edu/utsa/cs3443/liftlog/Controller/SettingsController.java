
package edu.utsa.cs3443.liftlog.Controller;

import edu.utsa.cs3443.liftlog.model.WorkoutManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

/**
 * SettingsController manages the settings screen.
 * It allows users to convert workout weights
 * between pounds and kilograms.
 *
 * @author akhandaker06
 */
public class SettingsController {

    private WorkoutManager workoutManager;

    @FXML
    private TextField weightInput;

    @FXML
    private Label resultLabel;

    /**
     * Initializes the settings screen and loads workout data.
     */
    @FXML
    public void initialize() {
        workoutManager = new WorkoutManager();

        try {
            workoutManager.loadWorkouts();
        } catch (Exception e) {
            System.out.println("Error loading workouts: " + e.getMessage());
        }
    }

    /**
     * Converts a weight from pounds to kilograms.
     */
    @FXML
    public void convertPoundsToKilo() {
        try {
            double pounds = Double.parseDouble(weightInput.getText());

            if (pounds < 0) {
                resultLabel.setText("Please enter a positive weight.");
                return;
            }

            double kilograms = pounds / 2.20462;

            resultLabel.setText(
                    String.format("%.2f kg", kilograms)
            );

        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter a valid number.");
        }
    }

    /**
     * Converts a weight from kilograms to pounds.
     */
    @FXML
    public void convertKiloToPounds() {
        try {
            double kilograms = Double.parseDouble(weightInput.getText());

            if (kilograms < 0) {
                resultLabel.setText("Please enter a positive weight.");
                return;
            }

            double pounds = kilograms * 2.20462;

            resultLabel.setText(
                    String.format("%.2f lbs", pounds)
            );

        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter a valid number.");
        }
    }
}

