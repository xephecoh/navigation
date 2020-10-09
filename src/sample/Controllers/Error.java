package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Error {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label LB;

    @FXML
    private Button Ok;

    @FXML
    void initialize() {

        Ok.setOnAction( e ->Ok.getScene().getWindow().hide());
    }


}