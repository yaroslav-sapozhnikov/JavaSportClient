package restapi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.Country;
import models.League;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LeaguesApi {

    private static final String ServerURL = "http://127.0.0.1:8080";

    CountriesApi countriesApi = new CountriesApi();

    public String createLeague(League league) {
        JsonObject response = new Gson().fromJson(Requests.PostRequest(ServerURL + "/league/create", league.toJsonCreate()), JsonObject.class);
        if (response.get("error").toString().equals("\"COUNTRY_ALREADY_EXISTS\"")) {
            return "Страна уже существует";
        } else {
            return "Страна успешно создана";
        }
    }

    public List<League> getLeagues() {
        List<League> result = new ArrayList<>();
        String buffer = Requests.GetRequest(ServerURL + "/league/getall");

        JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();

        for (int i = 0; i < jsonResult.size(); i++) {
            JsonObject currentLeague = jsonResult.get(i).getAsJsonObject();

            League newLeague = new League(currentLeague);
            result.add(newLeague);
        }
        return result;
    }

    public League getLeague(Long leagueId) {
        String buffer = Requests.GetRequest(ServerURL + "/league/get/" + leagueId);

        JsonObject jsonResult = JsonParser.parseString(buffer).getAsJsonObject();

        League newLeague = new League(jsonResult);
        return newLeague;
    }

    public String updateLeague(League league) {
        String jsonString = league.toJsonUpdate();
        JsonObject response = new Gson().fromJson(Requests.PutRequest(ServerURL + "/league/edit", jsonString), JsonObject.class);
        if (response.get("error").toString().equals("\"LEAGUE_DOES_NOT_EXIST\"")) {
            return "Лига не существует";
        } else {
            return "Лига успешно отредактирована";
        }
    }

    public boolean deleteLeague(League league) {
        Long id = league.getId();
        if (id == null)
            return false;
        return Requests.DeleteRequest(ServerURL + "/league/delete/" + id);
    }
}
