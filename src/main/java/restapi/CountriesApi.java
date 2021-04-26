package restapi;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.Country;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CountriesApi {

    private static final String ServerURL = "http://127.0.0.1:8080";
    
    public String createCountry(Country country) {
        JsonObject response = new Gson().fromJson(Requests.PostRequest(ServerURL + "/country/create", country.toCreateDto()), JsonObject.class);
        if (response.get("error").toString().equals("\"COUNTRY_ALREADY_EXISTS\"")) {
            return "Страна уже существует";
        } else {
            return "Страна успешно создана";
        }


    }

    
    public List<Country> getCountries() {
        List<Country> result = new ArrayList<>();
        String buffer = Requests.GetRequest(ServerURL + "/country/getall");

        JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();

        for (int i = 0; i < jsonResult.size(); i++) {
            JsonObject currentCountry = jsonResult.get(i).getAsJsonObject();

            Country newCountry = new Country(currentCountry);
            result.add(newCountry);
        }
        return result;
    }

    public List<String> getCountriesNames() {
        List<String> result = new ArrayList<>();
        String buffer = Requests.GetRequest(ServerURL + "/country/getall");

        JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();

        for (int i = 0; i < jsonResult.size(); i++) {
            JsonObject currentCountry = jsonResult.get(i).getAsJsonObject();

            Country newCountry = new Country(currentCountry);
            result.add(newCountry.getName());
        }
        return result;
    }

    public Country getCountry(Long countryId) {
        String buffer = Requests.GetRequest(ServerURL + "/country/get/" + countryId);

        JsonObject jsonResult = JsonParser.parseString(buffer).getAsJsonObject();

        Country newCountry = new Country(jsonResult);
        return newCountry;
    }
    
    public String updateCountry(Country country) {
        String jsonString = country.toUpdateDto();
        JsonObject response = new Gson().fromJson(Requests.PutRequest(ServerURL + "/country/edit", jsonString), JsonObject.class);
        if (response.get("error").toString().equals("\"COUNTRY_DOES_NOT_EXIST\"")) {
            return "Страна не существует";
        } else {
            return "Страна успешно отредактирована";
        }
    }
    
    public boolean deleteCountry(Country country) {
        Long id = country.getId();
        if (id == null)
            return false;
        return Requests.DeleteRequest(ServerURL + "/country/delete/" + id);
    }
}
