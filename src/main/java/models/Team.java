package models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dto.CountryDto;
import dto.LeagueDto;
import dto.TeamDto;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class Team {
    private SimpleLongProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty shortName;
    private League league;
    private SimpleStringProperty leagueName;

    public Team(Long id, String name, String shortName, League league) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.shortName = new SimpleStringProperty(shortName);
        this.league = league;
    }

    public Team(String name, String shortName, League league) {
        this.name = new SimpleStringProperty(name);
        this.shortName = new SimpleStringProperty(shortName);
        this.league = league;
    }

    public Team(Long id) {
        this.id = new SimpleLongProperty(id);
    }

    public Team (JsonObject jsonObject) {
        name = new SimpleStringProperty(jsonObject.get("name").getAsString());
        shortName = new SimpleStringProperty(jsonObject.get("shortName").getAsString());
        league = new League(jsonObject.get("league").getAsJsonObject());
        id = new SimpleLongProperty(jsonObject.get("id").getAsLong());
        leagueName = new SimpleStringProperty(league.getName());
    }

    public String toJsonCreate() {

        Gson gson = new Gson();
        TeamDto teamDto = new TeamDto(
                this.name.get(),
                this.shortName.get(),
                new LeagueDto(this.league.getId(), null, null)
        );

        return gson.toJson(teamDto);
    }

    public String toJsonUpdate() {

        Gson gson = new Gson();
        TeamDto teamDto = new TeamDto(
                this.id.get(),
                this.name.get(),
                this.shortName.get(),
                new LeagueDto(this.league.getId(), null, null)
        );

        return gson.toJson(teamDto);
    }

    public String toString () {
        return this.name.get();
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

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public String getLeagueName() {
        return this.leagueName.get();
    }
}
