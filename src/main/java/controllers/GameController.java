package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Country;
import models.Game;
import models.League;
import models.Team;
import restapi.GamesApi;

public class GameController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<Game> gamesTable = new TableView<>();

    @FXML
    private TableColumn<?, ?> guestTeamColumn;

    @FXML
    private TableColumn<?, ?> guestCountColumn;

    @FXML
    private TableColumn<?, ?> homeCountColumn;

    @FXML
    private TableColumn<?, ?> homeTeamColumn;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private Label errorLabel;

    @FXML
    private Label idLabel;

    @FXML
    private Button view;

    GamesApi gamesApi = new GamesApi();

    @FXML
    void onAddButtonAction(ActionEvent event) {
        gameAdd();
    }

    @FXML
    void onDeleteButtonAction(ActionEvent event) {
        Game selectedGame = gamesTable.getSelectionModel().getSelectedItem();
        if (selectedGame != null) {
            gamesApi.deleteGame(selectedGame);
        } else {
            errorLabel.setText("Выберите игру");
        }
        tableView();
    }

    @FXML
    void onEditButtonAction(ActionEvent event) {
    }

    @FXML
    void onSearchButtonAction(ActionEvent event) {

    }

    public void initialize() {
        tableView();
    }

    @FXML
    void onViewButtonAction(ActionEvent event) {
        Game game = gamesTable.getSelectionModel().getSelectedItem();
        if (game != null) {
            gameView(game);
        } else {
            errorLabel.setText("Выберите игру");
        }
    }

    void tableView() {
        ObservableList games = FXCollections.observableList(gamesApi.getGames());
        gamesTable.setItems(games);

        if (guestCountColumn != null) {
            guestCountColumn.setCellValueFactory(new PropertyValueFactory<>("guestCount"));
            homeCountColumn.setCellValueFactory(new PropertyValueFactory<>("homeCount"));
            guestTeamColumn.setCellValueFactory(new PropertyValueFactory<>("guestTeam"));
            homeTeamColumn.setCellValueFactory(new PropertyValueFactory<>("homeTeam"));

            FilteredList<Game> filteredData = new FilteredList<>(games, p -> true);
            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(myObject -> {

                    // If filter text is empty, display all persons.
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    // Compare first name and last name field in your object with filter.
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (String.valueOf(myObject.getId()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                        // Filter matches first name.
                    } else if (String.valueOf(myObject.getGuestTeam().getName()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (String.valueOf(myObject.getHomeTeam().getName()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false; // Does not match.
                });
            });
            // 3. Wrap the FilteredList in a SortedList.
            SortedList<Game> sortedData = new SortedList<>(filteredData);
            // 4. Bind the SortedList comparator to the TableView comparator.
            sortedData.comparatorProperty().bind(gamesTable.comparatorProperty());
            // 5. Add sorted (and filtered) data to the table.
            gamesTable.setItems(sortedData);
        }
    }

    void gameAdd () {
        try{
            FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/scenes/game_add.fxml"));
            anchorPane.getChildren().clear();
            Node node = loader.load();
            anchorPane.getChildren().add(node);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    void gameView (Game game) {
        try{
            FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/scenes/game_view.fxml"));
            GameViewController gameViewController = new GameViewController(game);
            loader.setController(gameViewController);
            anchorPane.getChildren().clear();
            Node node = loader.load();
            anchorPane.getChildren().add(node);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}

