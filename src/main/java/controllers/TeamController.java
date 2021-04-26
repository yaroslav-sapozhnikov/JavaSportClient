package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.Country;
import models.League;
import models.Team;
import restapi.LeaguesApi;
import restapi.TeamsApi;

public class TeamController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<Team> leaguesTable;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> shortNameColumn;

    @FXML
    private TableColumn<?, ?> leagueColumn;

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
    private TextField nameField;

    @FXML
    private Label errorLabel;

    @FXML
    private Button editButton2;

    @FXML
    private Label idLabel;

    @FXML
    private ChoiceBox<League> leagueChoiceBox;

    @FXML
    private TextField shortNameField;

    LeaguesApi leaguesApi = new LeaguesApi();

    TeamsApi teamsApi = new TeamsApi();

    @FXML
    void onAddButtonAction(ActionEvent event) {
        String name = nameField.getText();
        String shortName = shortNameField.getText();
        League leagueName = leagueChoiceBox.getSelectionModel().getSelectedItem();
        System.out.println(leagueName.getId());
        Team addTeam = new Team(name, shortName, leagueName);
        String response = teamsApi.createTeam(addTeam);
        if (!response.equals("\"TEAM_CREATED\"")) {
            errorLabel.setText(response);
        }
        tableView();
    }

    @FXML
    void onDeleteButtonAction(ActionEvent event) {
        Team selectedTeam = leaguesTable.getSelectionModel().getSelectedItem();
        if (selectedTeam != null) {
            teamsApi.deleteTeam(selectedTeam);
        }
        tableView();
    }

    @FXML
    void onEditButton2Action(ActionEvent event) {
        Long id = Long.parseLong(idLabel.getText());
        String name = nameField.getText();
        String shortName = shortNameField.getText();
        League league = leagueChoiceBox.getSelectionModel().getSelectedItem();
        Team addTeam = new Team(id, name, shortName, league);
        String response = teamsApi.updateTeam(addTeam);
        if (response.equals("Команда успешно отредактирована")) {
            nameField.clear();
            leagueChoiceBox.setValue(null);
            idLabel.setText(" ");
            editButton.setVisible(true);
            editButton2.setVisible(false);
        } else {
            errorLabel.setText(response);
        }
        editButton.setVisible(true);
        editButton2.setVisible(false);
        tableView();
    }

    @FXML
    void onEditButtonAction(ActionEvent event) {
        Team selectedTeam = leaguesTable.getSelectionModel().getSelectedItem();
        nameField.setText(selectedTeam.getName());
        shortNameField.setText(selectedTeam.getShortName());
        idLabel.setText(String.valueOf(selectedTeam.getId()));
        editButton2.setVisible(true);
        editButton.setVisible(false);
        choiceBoxView(selectedTeam.getLeague());
    }

    @FXML
    void onSearchButtonAction(ActionEvent event) {

    }

    public void initialize() {
        editButton2.setVisible(false);
        tableView();
        choiceBoxView(null);
    }

    public void choiceBoxView (League league) {

        leagueChoiceBox.setValue(league);
        leagueChoiceBox.getItems().clear();
        ObservableList countries = FXCollections.observableList(leaguesApi.getLeagues());
        leagueChoiceBox.getItems().addAll(countries);
    }

    public void tableView () {
        ObservableList leagues = FXCollections.observableList(teamsApi.getTeams());
        leaguesTable.setItems(leagues);

        leagueColumn.setCellValueFactory(new PropertyValueFactory<>("leagueName"));
        shortNameColumn.setCellValueFactory(new PropertyValueFactory<>("shortName"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        FilteredList<Team> filteredData = new FilteredList<>(leagues, p -> true);
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
                } else if (String.valueOf(myObject.getName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(myObject.getLeagueName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } return false; // Does not match.
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Team> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(leaguesTable.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        leaguesTable.setItems(sortedData);
    }

}

