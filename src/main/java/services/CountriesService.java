package services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Country;
import restapi.Requests;


public class CountriesService {

//    RestApi restapi = new RestApi();
//
//    public TableView<Country> getCountries () {
//        ObservableList<Country> countries = FXCollections.observableArrayList(restapi.getCountries());
//
//        TableView<Country> table = new TableView(countries);
//        table.setItems(countries);
//
//        TableColumn<Country, String> nameColumn = new TableColumn<Country, String>("Name");
//        nameColumn.setCellValueFactory(new PropertyValueFactory<Country, String>("name"));
//        table.getColumns().add(nameColumn);
//
//        TableColumn<Country, String> shortNameColumn = new TableColumn<Country, String>("Short name");
//        shortNameColumn.setCellValueFactory(new PropertyValueFactory<Country, String>("shortName"));
//        table.getColumns().add(shortNameColumn);
//
//        return table;
//    }

}
