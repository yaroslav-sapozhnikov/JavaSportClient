package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Country;
import services.CountriesService;

import java.io.IOException;

public class CountryController {

    @FXML
    private Button games;

    @FXML
    private Button leagues;

    @FXML
    private Button Countries;

    @FXML
    private Button teams;

    @FXML
    private Button mainPage;


    Stage stage;

    public Parent countriesView (Stage stage) {
        this.stage = stage;
        try {

            FXMLLoader loader = new FXMLLoader(CountryController.class.getResource("/scenes/countries.fxml"));
            Parent view = loader.load();
            return view;
//            VBox vBox = new VBox();
//            vBox.getChildren().addAll(view);
//            stage.setScene(new Scene(vBox));
//
//            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    @FXML
    void onCountriesAction(ActionEvent event) {

//        CountriesService countriesService = new CountriesService();
//        TableView<Country> table = countriesService.getCountries();
//
//        main.getChildren().addAll(table);
    }

    @FXML
    void onGamesAction(ActionEvent event) {

    }

    @FXML
    void onLeaguesAction(ActionEvent event) {

    }

    @FXML
    void onTeamsAction(ActionEvent event) {

    }
}

