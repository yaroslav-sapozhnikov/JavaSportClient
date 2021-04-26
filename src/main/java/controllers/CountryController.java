package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Country;
import restapi.CountriesApi;

public class CountryController {

    @FXML
    private TableView<Country> countriesTable = new TableView<>();

    @FXML
    private TableColumn<Country, String> nameColumn = new TableColumn<>();

    @FXML
    private TableColumn<Country, String> shortNameColumn = new TableColumn<>();

    private CountriesApi countriesApi = new CountriesApi();

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton = new Button();

    @FXML
    private Button editButton2 = new Button();

    @FXML
    private TextField nameField;

    @FXML
    private TextField shortNameField;

    @FXML
    private Label errorLabel;

    @FXML
    private Label idLabel;

    @FXML
    private TextField searchField = new TextField();

    @FXML
    private Button searchButton;

    @FXML
    void onDeleteButtonAction(ActionEvent event) {
        Country selectedCountry = countriesTable.getSelectionModel().getSelectedItem();
        if (selectedCountry != null) {
            countriesApi.deleteCountry(selectedCountry);
        }
        tableView();
    }

    @FXML
    void onAddButtonAction(ActionEvent event) {
        String name = nameField.getText();
        String shortName = shortNameField.getText();
        Country addCountry = new Country(name, shortName);
        String response = countriesApi.createCountry(addCountry);
        if (response.equals("COUNTRY_SUCCESSFULLY_CREATED")) {
            nameField.getScene().getWindow().hide();
        } else {
            errorLabel.setText(response);
        }
        tableView();
    }

    @FXML
    void onEditButtonAction (ActionEvent event) {
        Country selectedCountry = countriesTable.getSelectionModel().getSelectedItem();
        nameField.setText(selectedCountry.getName());
        shortNameField.setText(selectedCountry.getShortName());
        idLabel.setText(String.valueOf(selectedCountry.getId()));
        editButton2.setVisible(true);
        editButton.setVisible(false);
    }

    @FXML
    void onEditButton2Action(ActionEvent event) {
        Long id = Long.parseLong(idLabel.getText());
        String name = nameField.getText();
        String shortName = shortNameField.getText();
        Country addCountry = new Country(id, name, shortName);
        String response = countriesApi.updateCountry(addCountry);
        if (response.equals("Страна успешно отредактирована")) {
            nameField.clear();
            shortNameField.clear();
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

    public void initialize() {
        editButton2.setVisible(false);
        tableView();
    }

    public void tableView () {
        ObservableList countries = FXCollections.observableList(countriesApi.getCountries());
        countriesTable.setItems(countries);

        shortNameColumn.setCellValueFactory(new PropertyValueFactory<>("shortName"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        FilteredList<Country> filteredData = new FilteredList<>(countries, p -> true);
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
                } else if (String.valueOf(myObject.getShortName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } return false; // Does not match.
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Country> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(countriesTable.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        countriesTable.setItems(sortedData);
    }

    @FXML
    void onSearchButtonAction (ActionEvent event) {

    }
}

