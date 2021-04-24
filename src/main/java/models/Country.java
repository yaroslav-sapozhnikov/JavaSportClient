package models;

import com.google.gson.Gson;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.HashMap;
import java.util.Map;

public class Country {
    private SimpleLongProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty shortName;

    public Country (Long id, String name, String shortName){
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.shortName = new SimpleStringProperty(shortName);
    }

    public String toJson() {

        Map<String, String> map = new HashMap<>();
        map.put("id", id.toString());
        map.put("name", name.get());
        map.put("shortName", name.get());

        Gson gson = new Gson();
        return gson.toJson(map);
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
}
