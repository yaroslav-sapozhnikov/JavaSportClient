package utils;

public enum Menu {
    home("home"),
    teams("teams"),
    leagues("leagues"),
    countries("countries"),
    games("games");


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