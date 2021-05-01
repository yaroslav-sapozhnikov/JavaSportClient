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
import restapi.LoginApi;
import services.LoginService;

import java.io.IOException;

public class LoginController {

    LoginService loginService = new LoginService();

    private static Stage thisStage;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button exitButton;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorField;

    @FXML
    private Button registerButton;

    private LoginApi loginApi = new LoginApi();

    public static void loginView(Stage stage){
        thisStage = stage;
        try {

            FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/scenes/login.fxml"));
            Parent view = loader.load();

            stage.setScene(new Scene(view));

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    @FXML
    void exitOnAction(ActionEvent event) {

    }

    @FXML
    void loginOnAction(ActionEvent event) {
        //String message = loginService.login(loginField.getText(), passwordField.getText());
        UserDto userDto = new UserDto();
        userDto.username = loginField.getText();
        userDto.password = passwordField.getText();
        String message = loginApi.authorizeUser(userDto);
        errorField.setText(message);

        if (message.equals("USER_AUTHORIZED")) {
            loginButton.getScene().getWindow().hide();
            MainController.show();
        }
    }

    @FXML
    void registerOnAction(ActionEvent event) {
        loginButton.getScene().getWindow().hide();
        RegisterController.registerView(thisStage);
    }

}

