package models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dto.CountryDto;
import dto.LeagueDto;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class League {
    private SimpleLongProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty countryName;
    private Country country;

    public League (Long id, String name, Country countryId){
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.country = countryId;
    }

    public League (String name, Country country) {
        this.name = new SimpleStringProperty(name);
        Country countryId = new Country(country.getId());
        this.country = countryId;
    }

    public League (JsonObject jsonObject) {
        name = new SimpleStringProperty(jsonObject.get("name").getAsString());
        country = new Country(jsonObject.get("country").getAsJsonObject());
        id = new SimpleLongProperty(jsonObject.get("id").getAsLong());
        countryName = new SimpleStringProperty(country.getName());
    }

    public String toJsonCreate() {

        Gson gson = new Gson();
        LeagueDto leagueDto = new LeagueDto(this.name.get(), new CountryDto(this.country.getId(), null, null));

        return gson.toJson(leagueDto);
    }

    public String toJsonUpdate() {
        Gson gson = new Gson();
        LeagueDto leagueDto = new LeagueDto(this.id.get(), this.name.get(), new CountryDto(this.country.getId(), null, null));

        return gson.toJson(leagueDto);
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

    public Country getCountryId() {
        return country;
    }

    public void setCountryId(Country countryId) {
        this.country = countryId;
    }

    public String getCountryName () {
        return countryName.get();
    }

    public String toString () {
        return this.name.get();
    }

}