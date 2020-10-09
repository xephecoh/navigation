package sample.Controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {


    @FXML
    private TextField Login;

    @FXML
    private TextField Password;

    @FXML
    private Button Enter;

    @FXML
    private Label wrongData;

    @FXML
    private Label copyright;

    @FXML
    private Label design;

    @FXML
    private Label reserved;


    @FXML
    void initialize() {

        wrongData.setVisible(false);
        Enter.setOnAction(event -> {
            String name = Login.getText();
            String pass = Password.getText();
            logInUser(name, pass);
        });
        Password.setOnKeyPressed(press -> {
                    switch (press.getCode()) {
                        case ENTER:
                            String name = Login.getText();
                            String pass = Password.getText();
                            logInUser(name, pass);
                    }
                }
        );
    }

    private void logInUser(String Input1, String Input2) {
        if (Input1.equals("Dima") && Input2.equals("Votumah1989")) {
            WindowsBuilder("/sample/View/App.fxml");
        } else {
            wrongData.setVisible(true);
        }
    }

    private void WindowsBuilder(String window) {
        Enter.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage s = new Stage();
        s.setScene(new Scene(root));
        s.showAndWait();
    }
}
