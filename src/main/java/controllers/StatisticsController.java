package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.Game;
import models.Team;
import restapi.GamesApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StatisticsController {

    @FXML
    private PieChart gamesPie;

    @FXML
    private Label totalGamesLabel;

    @FXML
    private ListView<Team> teamsListView;

    GamesApi gamesApi = new GamesApi();

    Team team;

    public StatisticsController (Team team) {
        this.team = team;
    }

    public void initialize() {

        List<Game> games = gamesApi.getGames();
        ArrayList<Team> teams = new ArrayList<>();

        int wins = 0;
        int loses = 0;
        int draws = 0;

        for (int i = 0; i < games.size(); i++) {
            if (games.get(i).getGuestTeam().getName().equals(team.getName())) {
                if (games.get(i).getGuestCount() > games.get(i).getHomeCount()) {
                    wins++;
                } else if (games.get(i).getGuestCount() < games.get(i).getHomeCount()) {
                    loses++;
                } else {
                    draws++;
                }
                if (!teams.contains(games.get(i).getHomeTeam())) {
                    teams.add(games.get(i).getHomeTeam());
                }

            } else if (games.get(i).getHomeTeam().getName().equals(team.getName())) {
                if (games.get(i).getHomeCount() > games.get(i).getGuestCount()) {
                    wins++;
                } else if (games.get(i).getHomeCount() < games.get(i).getGuestCount()) {
                    loses++;
                } else {
                    draws++;
                }
                if (!teams.contains(games.get(i).getGuestTeam())) {
                    System.out.println(games.get(i).getGuestTeam().getId());
                    teams.add(games.get(i).getGuestTeam());
                }
            }
        }

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Wins", wins),
                        new PieChart.Data("Loses", loses),
                        new PieChart.Data("Draws", draws));
        gamesPie.getData().addAll(pieChartData);
        gamesPie.setTitle(team.getName());

        totalGamesLabel.setText(String.valueOf(wins + loses + draws));

        ObservableList<Team> teamsListItems = FXCollections.observableArrayList(teams);
        teamsListView.setItems(teamsListItems);
    }

    public void statisticsView(Team team){
        this.team = team;
        try {

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/scenes/statistics.fxml"));
            Parent view = loader.load();

            stage.setScene(new Scene(view));

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

}

