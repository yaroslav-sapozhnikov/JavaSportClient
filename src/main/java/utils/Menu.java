package utils;

public enum Menu {
    Home("home"),
    Teams("teams"),
    Leagues("leagues"),
    Countries("countries"),
    Games("games");


    private String title;

    Menu(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getFxml() {
        return String.format("%s.fxml", name());
    }
}