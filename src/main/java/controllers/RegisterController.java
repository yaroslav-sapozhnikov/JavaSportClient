package controllers;

import dto.UserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import restapi.RegisterApi;
import services.LoginService;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button exitButton;

    @FXML
    private Button registerButton;

    @FXML
    private Label errorField;

    private static Stage thisStage;

    private RegisterApi registerApi = new RegisterApi();

    @FXML
    void exitOnAction(ActionEvent event) {

    }

    @FXML
    void registerOnAction(ActionEvent event) {

        UserDto userDto = new UserDto();
        userDto.username = loginField.getText();
        userDto.password = passwordField.getText();

        String message = registerApi.registerUser(userDto);
        errorField.setText(message);

        if (message.equals("USER_CREATED")) {
            registerButton.getScene().getWindow().hide();
            MainController.show();
        }
    }

    public static void registerView(Stage stage){
        thisStage = stage;
        try {

            FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/scenes/register.fxml"));
            Parent view = loader.load();

            stage.setScene(new Scene(view));

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

}

