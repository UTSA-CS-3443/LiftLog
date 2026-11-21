package edu.utsa.cs3443.liftlog.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import edu.utsa.cs3443.liftlog.model.Utilities;

public class HomeController {

    @FXML
    private Label date;

    Utilities util = new Utilities();

    @FXML
    public void initialize() {
        date.setText(util.getDate());
    }
}
