package edu.utsa.cs3443.liftlog.Controller;

import edu.utsa.cs3443.liftlog.model.Workout;
import edu.utsa.cs3443.liftlog.model.WorkoutManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class HistoryController {

    @FXML
    private ListView<Workout> HistoryList;

    private WorkoutManager manager;
    private ObservableList<Workout> history;

    //global scope
    private static final DateTimeFormatter dateFormate = DateTimeFormatter.ofPattern("MMM d, yyyy");

    @FXML
    public void initialize() {
        manager = new WorkoutManager();
        try {
            manager.loadWorkouts();
        }catch(Exception e){
            System.out.println("Error loading workouts" + e.getMessage());
        }
        history = FXCollections.observableArrayList(manager.getWorkouts());
        history.sort(Comparator.comparing(Workout::getDate));
        HistoryList.setItems(history);
        Label placeholder = new Label("Oh NO! No workouts, yet!");
        placeholder.setStyle("-fx-text-fill:#b3b3b3");
        HistoryList.setPlaceholder(placeholder);
        HistoryList.setCellFactory(lv -> new WorkoutCell());
    }

    private class WorkoutCell extends javafx.scene.control.ListCell<Workout> {
        private final Circle circle = new Circle(25);
        private final ImageView dumbellIcon = new ImageView();
        private final StackPane iconPane = new StackPane(circle, dumbellIcon);
        private final Label nameLabel = new Label();
        private final Label descriptionLabel = new Label();
        private final VBox textBox = new VBox(2, nameLabel, descriptionLabel);
        private final Region space = new Region();
        private final Button editButton = new Button("Edit");
        private final Button deleteButton = new Button("Delete");
        private final HBox row = new HBox(2, iconPane, textBox, space, editButton, deleteButton);

        public WorkoutCell() {
            URL logo = HistoryController.class.getResource("/edu/utsa/cs3443/liftlog/images/logo.png");
            if (logo != null) {
                dumbellIcon.setImage(new Image(logo.toExternalForm()));
            }
            dumbellIcon.setFitWidth(25);
            dumbellIcon.setFitHeight(25);
            dumbellIcon.setPreserveRatio(true);

            HBox.setHgrow(space, Priority.ALWAYS);
            row.setAlignment(Pos.CENTER_LEFT);
            row.setPadding(new Insets(12, 20, 12, 20));
            row.setStyle("-fx-background-color: #757575; -fx-background-radius: 10;");
            nameLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 20px;");
            descriptionLabel.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 15px;");
            editButton.setStyle("-fx-background-color: #121416; -fx-text-fill: #FFFFFF;");
            deleteButton.setStyle("-fx-background-color: #C61A1A; -fx-text-fill: #FFFFFF;");

            //add editButton.setOnAction()
            //add deleteButton.setOnAction()
        }

            @Override
            protected void updateItem(Workout workout, boolean empty) {
                super.updateItem(workout, empty);
                if (empty || workout == null) {
                    setText(null);
                    setGraphic(null);
                    return;
                }
                nameLabel.setText(workout.getWorkoutName());
                String dateText = (workout.getDate() != null) ? dateFormate.format(workout.getDate()) : "Unknown date";
                descriptionLabel.setText(dateText);
                setText(null);
                setGraphic(row);
            }

        }
}

