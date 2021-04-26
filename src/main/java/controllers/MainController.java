package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.Menu;

import java.io.InputStream;

public class MainController {


    @FXML
    private HBox sideBar;

    @FXML
    private StackPane contentView;

    @FXML
    private Menu menu;

    @FXML
    private void initialize() {
        loadView(Menu.Home);
    }

    @FXML
    void onCountriesAction(ActionEvent event) {
        clickMenu(event);
    }

    @FXML
    void onGamesAction(ActionEvent event) {
        clickMenu(event);
    }

    @FXML
    void onHomeAction(ActionEvent event) {
        clickMenu(event);
    }

    @FXML
    void onLeaguesAction(ActionEvent event) {
        clickMenu(event);
    }

    @FXML
    void onTeamsAction(ActionEvent event) {
        clickMenu(event);
    }

    @FXML
    private void clickMenu(ActionEvent event) {

        Node node = (Node) event.getSource();
        Menu menu = Menu.valueOf(node.getId());
        loadView(menu);

    }

    private void loadView(Menu menu) {
        try {

            for (Node node : sideBar.getChildren()) {

                node.getStyleClass().remove("active");

                if (node.getId().equals(menu.name())) {
                    node.getStyleClass().add("active");
                }
            }

            contentView.getChildren().clear();
            System.out.println("FXML: " + menu.getFxml());
            InputStream stream = getClass().getResourceAsStream("/scenes/" + menu.getFxml());

            FXMLLoader loader = new FXMLLoader();
            Parent view = loader.load(stream);

            contentView.getChildren().add(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void show() {
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(MainController.class.getResource("/scenes/main.fxml"));
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}