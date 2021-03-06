package models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dto.CountryDto;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Country {
    private SimpleLongProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty shortName;

    public Country (Long id, String name, String shortName){
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.shortName = new SimpleStringProperty(shortName);
    }

    public Country (String name, String shortName) {
        this.name = new SimpleStringProperty(name);
        this.shortName = new SimpleStringProperty(shortName);
    }

    public Country (Long id) {
        this.id = new SimpleLongProperty(id);
    }

    public Country (JsonObject jsonObject) {
        name = new SimpleStringProperty(jsonObject.get("name").getAsString());
        shortName = new SimpleStringProperty(jsonObject.get("shortName").getAsString());
        id = new SimpleLongProperty(jsonObject.get("id").getAsLong());
    }

    public String toCreateDto() {
        Gson gson = new Gson();
        CountryDto countryDto = new CountryDto(this.name.get(), this.shortName.get());
        return gson.toJson(countryDto);
    }

    public String toUpdateDto() {
        Gson gson = new Gson();
        CountryDto countryDto = new CountryDto(this.id.get(), this.name.get(), this.shortName.get());
        return gson.toJson(countryDto);
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getShortName() {
        return shortName.get();
    }

    public SimpleStringProperty shortNameProperty() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName.set(shortName);
    }

    @Override
    public String toString() {
        return name.get();
    }
}
