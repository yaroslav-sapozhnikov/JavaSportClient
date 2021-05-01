package models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dto.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

public class Game {

    public SimpleLongProperty id;
    public Team homeTeam;
    public Team guestTeam;
    public SimpleIntegerProperty homeCount;
    public SimpleIntegerProperty guestCount;
    public Statistics homeStatistics;
    public Statistics guestStatistics;


    public Game(SimpleLongProperty id,
                Team homeTeam,
                Team guestTeam,
                SimpleIntegerProperty homeCount,
                SimpleIntegerProperty guestCount,
                Statistics homeStatistics,
                Statistics guestStatistics) {

        this.id = id;
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.homeCount = homeCount;
        this.guestCount = guestCount;
        this.homeStatistics = homeStatistics;
        this.guestStatistics = guestStatistics;
    }

    public Game(Team homeTeam,
                Team guestTeam,
                SimpleIntegerProperty homeCount,
                SimpleIntegerProperty guestCount,
                Statistics homeStatistics,
                Statistics guestStatistics) {

        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.homeCount = homeCount;
        this.guestCount = guestCount;
        this.homeStatistics = homeStatistics;
        this.guestStatistics = guestStatistics;
    }

    public Game(SimpleLongProperty id) {
        this.id = id;
    }

    public Game(JsonObject jsonObject) {

        this.id = new SimpleLongProperty(jsonObject.get("id").getAsLong());
        this.homeTeam = new Team(jsonObject.get("homeTeam").getAsJsonObject());
        this.guestTeam = new Team(jsonObject.get("guestTeam").getAsJsonObject());
        this.homeCount = new SimpleIntegerProperty(jsonObject.get("homeCount").getAsInt());
        this.guestCount = new SimpleIntegerProperty(jsonObject.get("guestCount").getAsInt());
        this.homeStatistics = new Statistics(jsonObject.get("homeStatistics").getAsJsonObject());
        this.guestStatistics = new Statistics(jsonObject.get("guestStatistics").getAsJsonObject());
    }

    public String toJsonCreate(Long guestId, Long homeId) {

        Gson gson = new Gson();
        GameDto gameDto = new GameDto(
                new TeamDto(this.homeTeam.getId(), null, null, null),
                new TeamDto(this.guestTeam.getId(), null, null, null),
                this.guestCount.get(),
                this.homeCount.get(),
                new StatisticsDto(guestId),
                new StatisticsDto(homeId)
        );

        return gson.toJson(gameDto);
    }

    public String toJsonUpdate() {
        Gson gson = new Gson();
        GameDto gameDto = new GameDto(
                this.id.get(),
                new TeamDto(this.homeTeam.getId(), null, null, null),
                new TeamDto(this.homeTeam.getId(), null, null, null),
                this.homeCount.get(),
                this.guestCount.get(),
                new StatisticsDto(this.homeStatistics.getId()),
                new StatisticsDto(this.guestStatistics.getId())
        );

        return gson.toJson(gameDto);
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

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(Team guestTeam) {
        this.guestTeam = guestTeam;
    }

    public int getHomeCount() {
        return homeCount.get();
    }

    public SimpleIntegerProperty homeCountProperty() {
        return homeCount;
    }

    public void setHomeCount(int homeCount) {
        this.homeCount.set(homeCount);
    }

    public int getGuestCount() {
        return guestCount.get();
    }

    public SimpleIntegerProperty guestCountProperty() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount.set(guestCount);
    }

    public Statistics getHomeStatistics() {
        return homeStatistics;
    }

    public void setHomeStatistics(Statistics homeStatistics) {
        this.homeStatistics = homeStatistics;
    }

    public Statistics getGuestStatistics() {
        return guestStatistics;
    }

    public void setGuestStatistics(Statistics guestStatistics) {
        this.guestStatistics = guestStatistics;
    }
}
