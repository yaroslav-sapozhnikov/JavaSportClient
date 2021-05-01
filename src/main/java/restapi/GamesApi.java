package restapi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.Game;
import models.Statistics;

import java.util.ArrayList;
import java.util.List;

public class GamesApi {
    private static final String ServerURL = "http://127.0.0.1:8080";

    CountriesApi countriesApi = new CountriesApi();

    public String createGame(Game game, Statistics guestStatistics, Statistics homeStatistics) {

        JsonObject guestId = new Gson().fromJson(Requests.PostRequest(ServerURL + "/detailedStatistics/create",
                guestStatistics.toJsonCreate()),
                JsonObject.class);

        JsonObject homeId = new Gson().fromJson(Requests.PostRequest(ServerURL + "/detailedStatistics/create",
                homeStatistics.toJsonCreate()),
                JsonObject.class);

        JsonObject response = new Gson().fromJson(Requests.PostRequest(ServerURL + "/game/create",
                game.toJsonCreate(homeId.get("message").getAsLong(), guestId.get("message").getAsLong())),
                JsonObject.class);

        return "Игра успешно создана";
    }

    public List<Game> getGames() {
        List<Game> result = new ArrayList<>();
        String buffer = Requests.GetRequest(ServerURL + "/game/getall");

        JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();

        for (int i = 0; i < jsonResult.size(); i++) {
            JsonObject currentGame = jsonResult.get(i).getAsJsonObject();

            Game newGame = new Game(currentGame);
            result.add(newGame);
        }
        return result;
    }

    public Game getGame(Long gameId) {
        String buffer = Requests.GetRequest(ServerURL + "/game/get/" + gameId);

        JsonObject jsonResult = JsonParser.parseString(buffer).getAsJsonObject();

        Game newGame = new Game(jsonResult);
        return newGame;
    }

    public String updateGame(Game game) {
        String jsonString = game.toJsonUpdate();
        JsonObject response = new Gson().fromJson(Requests.PutRequest(ServerURL + "/game/edit", jsonString), JsonObject.class);
        if (response.get("error").toString().equals("\"GAME_DOES_NOT_EXIST\"")) {
            return "Игра не существует";
        } else {
            return "Игра успешно отредактирована";
        }
    }

    public boolean deleteGame(Game game) {
        Long id = game.getId();
        if (id == null)
            return false;
        return Requests.DeleteRequest(ServerURL + "/game/delete/" + id);
    }
}
