package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import models.Game;

public class GameViewController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button backButton = new Button();

    @FXML
    private Label errorLabel;

    @FXML
    private Label guestTeam;

    @FXML
    private Label guestCount;

    @FXML
    private Label guestPossesion;

    @FXML
    private Label guestShots;

    @FXML
    private Label guestShotsOnGoals;

    @FXML
    private Label guestFreeKicks;

    @FXML
    private Label guestCorners;

    @FXML
    private Label guestFouls;

    @FXML
    private Label guestOffsides;

    @FXML
    private Label guestRedCards;

    @FXML
    private Label guestYellowCards;

    @FXML
    private Label homeTeam;

    @FXML
    private Label homeCount;

    @FXML
    private Label homePossesion;

    @FXML
    private Label homeShots;

    @FXML
    private Label homeShotsOnGoals;

    @FXML
    private Label homeFreeKicks;

    @FXML
    private Label homeCorners;

    @FXML
    private Label homeFouls;

    @FXML
    private Label homeOffsides;

    @FXML
    private Label homeRedCards;

    @FXML
    private Label homeYellowCards;

    private Game game;

    public GameViewController (Game game) {
        this.game = game;
    }

    private void gameView(Game game) {
        this.guestTeam.setText(game.getGuestTeam().getName());
        this.guestCount.setText(String.valueOf(game.getGuestCount()));
        this.guestCorners.setText(String.valueOf(game.getGuestStatistics().getCorners()));
        this.guestPossesion.setText(String.valueOf(game.getGuestStatistics().getBallPossession()));
        this.guestShots.setText(String.valueOf(game.getGuestStatistics().getTotalShots()));
        this.guestShotsOnGoals.setText(String.valueOf(game.getGuestStatistics().getShotsOnGoals()));
        this.guestFouls.setText(String.valueOf(game.getGuestStatistics().getFouls()));
        this.guestFreeKicks.setText(String.valueOf(game.getGuestStatistics().getFreeKicks()));
        this.guestOffsides.setText(String.valueOf(game.getGuestStatistics().getOffsides()));
        this.guestYellowCards.setText(String.valueOf(game.getGuestStatistics().getYellowCards()));
        this.guestRedCards.setText(String.valueOf(game.getGuestStatistics().getRedCards()));


        this.homeTeam.setText(game.getHomeTeam().getName());
        this.homeCount.setText(String.valueOf(game.getHomeCount()));
        this.homeCorners.setText(String.valueOf(game.getHomeStatistics().getCorners()));
        this.homePossesion.setText(String.valueOf(game.getHomeStatistics().getBallPossession()));
        this.homeShots.setText(String.valueOf(game.getHomeStatistics().getTotalShots()));
        this.homeShotsOnGoals.setText(String.valueOf(game.getHomeStatistics().getShotsOnGoals()));
        this.homeFouls.setText(String.valueOf(game.getHomeStatistics().getFouls()));
        this.homeFreeKicks.setText(String.valueOf(game.getHomeStatistics().getFreeKicks()));
        this.homeOffsides.setText(String.valueOf(game.getHomeStatistics().getOffsides()));
        this.homeYellowCards.setText(String.valueOf(game.getHomeStatistics().getYellowCards()));
        this.homeRedCards.setText(String.valueOf(game.getHomeStatistics().getRedCards()));
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

    @FXML
    private void initialize () {
        this.backButton.setOnAction(this::onBackButtonAction);
        gameView(game);
    }

}

