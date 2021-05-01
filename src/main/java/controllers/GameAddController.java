package controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.*;
import restapi.GamesApi;
import restapi.TeamsApi;

public class GameAddController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button backButton;

    @FXML
    private TextField guestCount;

    @FXML
    private TextField guestPossession;

    @FXML
    private TextField guestShots;

    @FXML
    private TextField guestShotsOnGoal;

    @FXML
    private TextField guestFreeKicks;

    @FXML
    private TextField guestCorners;

    @FXML
    private TextField guestFouls;

    @FXML
    private TextField guestOffsides;

    @FXML
    private TextField guestRedCards;

    @FXML
    private TextField guestYellowCards;

    @FXML
    private TextField homeCount;

    @FXML
    private TextField homePossession;

    @FXML
    private TextField homeShots;

    @FXML
    private TextField homeShotsOnGoals;

    @FXML
    private TextField homeFreeKicks;

    @FXML
    private TextField homeCorners;

    @FXML
    private TextField homeFouls;

    @FXML
    private TextField homeOffsides;

    @FXML
    private TextField homeRedCards;

    @FXML
    private TextField homeYellowCards;

    @FXML
    private ChoiceBox<Team> guestTeam;

    @FXML
    private ChoiceBox<Team> homeTeam;

    private GamesApi gamesApi = new GamesApi();

    private TeamsApi teamsApi = new TeamsApi();

    @FXML
    private Label errorLabel;

    @FXML
    private Button addButton;

    @FXML
    void onAddButtonAction(ActionEvent event) {
        Team guestTeamChoice = guestTeam.getSelectionModel().getSelectedItem();
        Team homeTeamChoice = homeTeam.getSelectionModel().getSelectedItem();

        try {
            SimpleIntegerProperty guestCountItem = new SimpleIntegerProperty(Integer.parseInt(guestCount.getText()));
            SimpleIntegerProperty homeCountItem = new SimpleIntegerProperty(Integer.parseInt(homeCount.getText()));
            int guestBallPossessionItem = Integer.parseInt(guestPossession.getText());
            int guestShotsItem = Integer.parseInt(guestShots.getText());
            int guestShotsOnGoalsItem = Integer.parseInt(guestShotsOnGoal.getText());
            int guestFreeKicksItem = Integer.parseInt(guestFreeKicks.getText());
            int guestCornersItem = Integer.parseInt(guestCorners.getText());
            int guestFoulsItem = Integer.parseInt(guestFouls.getText());
            int guestOffsidesItem = Integer.parseInt(guestOffsides.getText());
            int guestYellowCardsItem = Integer.parseInt(guestYellowCards.getText());
            int guestRedCardsItem = Integer.parseInt(guestRedCards.getText());

            int homeBallPossessionItem = Integer.parseInt(homePossession.getText());
            int homeShotsItem = Integer.parseInt(homeShots.getText());
            int homeShotsOnGoalsItem = Integer.parseInt(homeShotsOnGoals.getText());
            int homeFreeKicksItem = Integer.parseInt(homeFreeKicks.getText());
            int homeCornersItem = Integer.parseInt(homeCorners.getText());
            int homeFoulsItem = Integer.parseInt(homeFouls.getText());
            int homeOffsidesItem = Integer.parseInt(homeOffsides.getText());
            int homeYellowCardsItem = Integer.parseInt(homeYellowCards.getText());
            int homeRedCardsItem = Integer.parseInt(homeRedCards.getText());


            Statistics guestStatistics = new Statistics(
                    new SimpleIntegerProperty(guestBallPossessionItem),
                    new SimpleIntegerProperty(guestShotsItem),
                    new SimpleIntegerProperty(guestShotsOnGoalsItem),
                    new SimpleIntegerProperty(guestFreeKicksItem),
                    new SimpleIntegerProperty(guestCornersItem),
                    new SimpleIntegerProperty(guestFoulsItem),
                    new SimpleIntegerProperty(guestOffsidesItem),
                    new SimpleIntegerProperty(guestYellowCardsItem),
                    new SimpleIntegerProperty(guestRedCardsItem)
            );

            Statistics homeStatistics = new Statistics(
                    new SimpleIntegerProperty(homeBallPossessionItem),
                    new SimpleIntegerProperty(homeShotsItem),
                    new SimpleIntegerProperty(homeShotsOnGoalsItem),
                    new SimpleIntegerProperty(homeFreeKicksItem),
                    new SimpleIntegerProperty(homeCornersItem),
                    new SimpleIntegerProperty(homeFoulsItem),
                    new SimpleIntegerProperty(homeOffsidesItem),
                    new SimpleIntegerProperty(homeYellowCardsItem),
                    new SimpleIntegerProperty(homeRedCardsItem)
            );

            Game game = new Game(homeTeamChoice, guestTeamChoice, guestCountItem, homeCountItem, homeStatistics, guestStatistics);

            String response = gamesApi.createGame(game, guestStatistics, homeStatistics);
            errorLabel.setText(response);


        } catch (NumberFormatException e) {
            e.printStackTrace();
            errorLabel.setText("Неверно введены значения");
        }

    }

    @FXML
    void initialize () {
        choiceBoxView();
    }

    @FXML
    void onBackButtonAction(ActionEvent event) {
        anchorPane.getChildren().clear();
        try{
            FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/scenes/games.fxml"));
            Node node = loader.load();
            System.out.println(node);
            anchorPane.getChildren().add(node);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void choiceBoxView () {

        ObservableList teams = FXCollections.observableList(teamsApi.getTeams());
        guestTeam.getItems().addAll(teams);
        homeTeam.getItems().addAll(teams);
    }

}

