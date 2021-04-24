package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Country;
import services.CountriesService;
import utils.Menu;

import java.io.IOException;
import java.io.InputStream;

public class MainController {

    @FXML
    private HBox sideBar;

    @FXML
    private Button mainPage;

    @FXML
    private Button games;

    @FXML
    private Button teams;

    @FXML
    private Button leagues;

    @FXML
    private Button сountries;

    @FXML
    private StackPane contentView;

    Stage stage;

    @FXML
    void onCountriesAction(ActionEvent event) {
        CountryController countryController = new CountryController();
        countryController.countriesView(stage);

        CountriesService countriesService = new CountriesService();
        TableView<Country> table = countriesService.getCountries();

        contentView.getChildren().addAll(table);
    }

    @FXML
    void onGamesAction(ActionEvent event) {

    }

    @FXML
    void onHomeAction(ActionEvent event) {

    }

    @FXML
    void onLeaguesAction(ActionEvent event) {

    }

    @FXML
    void onTeamsAction(ActionEvent event) {

    }

    @FXML
    private void initialize() {
//        loadView(Menu.home);
//        System.out.println("ikfjsejfgkdsjgfjgddhjgiutdrhgbiuthuirhg");
    }

    @FXML
    private void clickMenu(MouseEvent event) {

        Node node = (Node) event.getSource();
        Menu menu = Menu.valueOf(node.getId());
        System.out.println(node.getId());
//        loadView(menu);
    }

    public void loadView(Stage stage) {

        this.stage = stage;
        try {

            FXMLLoader loader = new FXMLLoader(CountryController.class.getResource("/scenes/main.fxml"));
            Parent view = loader.load();

            FXMLLoader homeLoader = new FXMLLoader(CountryController.class.getResource("/scenes/home.fxml"));
            Parent homeView = homeLoader.load();

//            contentView.getChildren().addAll(homeView);

            stage.setScene(new Scene(view));

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }


    public static void show() {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(MainController.class.getResource("/views.fxml/main.fxml"));
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}