package restapi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.League;
import models.League;
import models.Team;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TeamsApi {

    private static final String ServerURL = "http://127.0.0.1:8080";

    CountriesApi leaguesApi = new CountriesApi();

    public String createTeam(Team team) {
        JsonObject response = new Gson().fromJson(Requests.PostRequest(ServerURL + "/team/create", team.toJsonCreate()), JsonObject.class);
        if (response.get("error").toString().equals("\"LEAGUE_ALREADY_EXISTS\"")) {
            return "Страна уже существует";
        } else {
            return "Страна успешно создана";
        }


    }

    public List<Team> getTeams() {
        List<Team> result = new ArrayList<>();
        String buffer = Requests.GetRequest(ServerURL + "/team/getall");

        JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();

        for (int i = 0; i < jsonResult.size(); i++) {
            JsonObject currentTeam = jsonResult.get(i).getAsJsonObject();

            Team newTeam = new Team(currentTeam);
            result.add(newTeam);
        }
        return result;
    }

    public Team getTeam(Long teamId) {
        String buffer = Requests.GetRequest(ServerURL + "/team/get/" + teamId);

        JsonObject jsonResult = JsonParser.parseString(buffer).getAsJsonObject();

        Team newTeam = new Team(jsonResult);
        return newTeam;
    }

    public String updateTeam(Team team) {
        String jsonString = team.toJsonUpdate();
        JsonObject response = new Gson().fromJson(Requests.PutRequest(ServerURL + "/team/edit", jsonString), JsonObject.class);
        if (response.get("error").toString().equals("\"LEAGUE_DOES_NOT_EXIST\"")) {
            return "Лига не существует";
        } else {
            return "Лига успешно отредактирована";
        }
    }

    public boolean deleteTeam(Team team) {
        Long id = team.getId();
        if (id == null)
            return false;
        return Requests.DeleteRequest(ServerURL + "/team/delete/" + id);
    }
}
