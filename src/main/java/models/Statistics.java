package models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dto.GameDto;
import dto.StatisticsDto;
import dto.TeamDto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

public class Statistics {
    public SimpleLongProperty id;
    public SimpleIntegerProperty ballPossession;
    public SimpleIntegerProperty totalShots;
    public SimpleIntegerProperty shotsOnGoals;
    public SimpleIntegerProperty freeKicks;
    public SimpleIntegerProperty corners;
    public SimpleIntegerProperty fouls;
    public SimpleIntegerProperty offsides;
    public SimpleIntegerProperty yellowCards;
    public SimpleIntegerProperty redCards;

    public Statistics(SimpleIntegerProperty ballPossession,
                      SimpleIntegerProperty totalShots,
                      SimpleIntegerProperty shotsOnGoals,
                      SimpleIntegerProperty freeKicks,
                      SimpleIntegerProperty corners,
                      SimpleIntegerProperty fouls,
                      SimpleIntegerProperty offsides,
                      SimpleIntegerProperty yellowCards,
                      SimpleIntegerProperty redCards) {

        this.ballPossession = ballPossession;
        this.totalShots = totalShots;
        this.shotsOnGoals = shotsOnGoals;
        this.freeKicks = freeKicks;
        this.corners = corners;
        this.fouls = fouls;
        this.offsides = offsides;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
    }

    public Statistics(SimpleLongProperty id,
                      SimpleIntegerProperty ballPossession,
                      SimpleIntegerProperty totalShots,
                      SimpleIntegerProperty shotsOnGoals,
                      SimpleIntegerProperty freeKicks,
                      SimpleIntegerProperty corners,
                      SimpleIntegerProperty fouls,
                      SimpleIntegerProperty offsides,
                      SimpleIntegerProperty yellowCards,
                      SimpleIntegerProperty redCards) {

        this.id = id;
        this.ballPossession = ballPossession;
        this.totalShots = totalShots;
        this.shotsOnGoals = shotsOnGoals;
        this.freeKicks = freeKicks;
        this.corners = corners;
        this.fouls = fouls;
        this.offsides = offsides;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
    }

    public Statistics(SimpleLongProperty id) {
        this.id = id;
    }

    public Statistics(JsonObject jsonObject) {
        this.id = new SimpleLongProperty(jsonObject.get("id").getAsLong());
        this.ballPossession = new SimpleIntegerProperty(jsonObject.get("ballPossession").getAsInt());
        this.totalShots = new SimpleIntegerProperty(jsonObject.get("totalShots").getAsInt());
        this.shotsOnGoals = new SimpleIntegerProperty(jsonObject.get("shotsOnGoals").getAsInt());
        this.freeKicks = new SimpleIntegerProperty(jsonObject.get("freeKicks").getAsInt());
        this.corners = new SimpleIntegerProperty(jsonObject.get("corners").getAsInt());
        this.fouls = new SimpleIntegerProperty(jsonObject.get("fouls").getAsInt());
        this.offsides = new SimpleIntegerProperty(jsonObject.get("offsides").getAsInt());
        this.yellowCards = new SimpleIntegerProperty(jsonObject.get("yellowCards").getAsInt());
        this.redCards = new SimpleIntegerProperty(jsonObject.get("redCards").getAsInt());
    }

    public String toJsonCreate() {

        Gson gson = new Gson();
        StatisticsDto statisticsDto = new StatisticsDto(
                this.ballPossession.get(),
                this.totalShots.get(),
                this.shotsOnGoals.get(),
                this.freeKicks.get(),
                this.corners.get(),
                this.fouls.get(),
                this.offsides.get(),
                this.yellowCards.get(),
                this.redCards.get()
        );

        return gson.toJson(statisticsDto);
    }

    public String toJsonUpdate() {
        Gson gson = new Gson();
        StatisticsDto statisticsDto = new StatisticsDto(
                this.id.get(),
                this.ballPossession.get(),
                this.totalShots.get(),
                this.shotsOnGoals.get(),
                this.freeKicks.get(),
                this.corners.get(),
                this.fouls.get(),
                this.offsides.get(),
                this.yellowCards.get(),
                this.redCards.get()
        );

        return gson.toJson(statisticsDto);
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

    public int getBallPossession() {
        return ballPossession.get();
    }

    public SimpleIntegerProperty ballPossessionProperty() {
        return ballPossession;
    }

    public void setBallPossession(int ballPossession) {
        this.ballPossession.set(ballPossession);
    }

    public int getTotalShots() {
        return totalShots.get();
    }

    public SimpleIntegerProperty totalShotsProperty() {
        return totalShots;
    }

    public void setTotalShots(int totalShots) {
        this.totalShots.set(totalShots);
    }

    public int getShotsOnGoals() {
        return shotsOnGoals.get();
    }

    public SimpleIntegerProperty shotsOnGoalsProperty() {
        return shotsOnGoals;
    }

    public void setShotsOnGoals(int shotsOnGoals) {
        this.shotsOnGoals.set(shotsOnGoals);
    }

    public int getFreeKicks() {
        return freeKicks.get();
    }

    public SimpleIntegerProperty freeKicksProperty() {
        return freeKicks;
    }

    public void setFreeKicks(int freeKicks) {
        this.freeKicks.set(freeKicks);
    }

    public int getCorners() {
        return corners.get();
    }

    public SimpleIntegerProperty cornersProperty() {
        return corners;
    }

    public void setCorners(int corners) {
        this.corners.set(corners);
    }

    public int getFouls() {
        return fouls.get();
    }

    public SimpleIntegerProperty foulsProperty() {
        return fouls;
    }

    public void setFouls(int fouls) {
        this.fouls.set(fouls);
    }

    public int getOffsides() {
        return offsides.get();
    }

    public SimpleIntegerProperty offsidesProperty() {
        return offsides;
    }

    public void setOffsides(int offsides) {
        this.offsides.set(offsides);
    }

    public int getYellowCards() {
        return yellowCards.get();
    }

    public SimpleIntegerProperty yellowCardsProperty() {
        return yellowCards;
    }

    public void setYellowCards(int yellowCards) {
        this.yellowCards.set(yellowCards);
    }

    public int getRedCards() {
        return redCards.get();
    }

    public SimpleIntegerProperty redCardsProperty() {
        return redCards;
    }

    public void setRedCards(int redCards) {
        this.redCards.set(redCards);
    }
}
