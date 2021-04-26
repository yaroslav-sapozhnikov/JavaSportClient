package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import models.Country;
import models.League;
import restapi.CountriesApi;
import restapi.LeaguesApi;

import java.util.ArrayList;
import java.util.List;

public class LeagueController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<League> leaguesTable;

    CountriesApi countriesApi = new CountriesApi();

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> countryColumn;

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
    private ChoiceBox<Country> countryChoiceBox;

    LeaguesApi leaguesApi = new LeaguesApi();
    private SingleSelectionModel<Country> singleSelectionModel;


    @FXML
    void onAddButtonAction(ActionEvent event) {
        String name = nameField.getText();
        Country countryName = countryChoiceBox.getSelectionModel().getSelectedItem();
        System.out.println(countryName.getId());
        League addLeague = new League(name, countryName);
        String response = leaguesApi.createLeague(addLeague);
        if (!response.equals("\"LEAGUE_CREATED\"")) {
            errorLabel.setText(response);
        }
        tableView();
    }

    @FXML
    void onDeleteButtonAction(ActionEvent event) {
        League selectedLeague = leaguesTable.getSelectionModel().getSelectedItem();
        if (selectedLeague != null) {
            leaguesApi.deleteLeague(selectedLeague);
        }
        tableView();
    }

    @FXML
    void onEditButton2Action(ActionEvent event) {
        Long id = Long.parseLong(idLabel.getText());
        String name = nameField.getText();
        Country country = countryChoiceBox.getSelectionModel().getSelectedItem();
        League addLeague = new League(id, name, country);
        String response = leaguesApi.updateLeague(addLeague);
        if (response.equals("Лига успешно отредактирована")) {
            nameField.clear();
            countryChoiceBox.setValue(null);
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
        League selectedLeague = leaguesTable.getSelectionModel().getSelectedItem();
        nameField.setText(selectedLeague.getName());
        idLabel.setText(String.valueOf(selectedLeague.getId()));
        editButton2.setVisible(true);
        editButton.setVisible(false);
        choiceBoxView(selectedLeague.getCountryId());
    }

    @FXML
    void onSearchButtonAction(ActionEvent event) {

    }

    public void initialize() {
        editButton2.setVisible(false);
        tableView();
        choiceBoxView(null);
    }

    public void choiceBoxView (Country country) {

        countryChoiceBox.setValue(country);
        countryChoiceBox.getItems().clear();
        ObservableList countries = FXCollections.observableList(countriesApi.getCountries());
        countryChoiceBox.getItems().addAll(countries);
    }

    public void tableView () {
        ObservableList leagues = FXCollections.observableList(leaguesApi.getLeagues());
        leaguesTable.setItems(leagues);

        countryColumn.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        FilteredList<League> filteredData = new FilteredList<>(leagues, p -> true);
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
                } else if (String.valueOf(myObject.getCountryName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } return false; // Does not match.
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<League> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(leaguesTable.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        leaguesTable.setItems(sortedData);
    }
}
