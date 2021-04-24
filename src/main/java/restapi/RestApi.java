package restapi;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import models.Country;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RestApi {

    private static final String ServerURL = "http://127.0.0.1:8080";
    
    public void CreateCountry(Country country) {
        Requests.PostRequest(ServerURL + "/country", country.toJson());
    }

    
    public List<Country> GetCountries() {
        List<Country> result = new ArrayList<>();
        String buffer = Requests.GetRequest(ServerURL + "/country/getall");

        JsonArray jsonResult = JsonParser.parseString(buffer).getAsJsonArray();

        for (int i = 0; i < jsonResult.size(); i++) {
            JsonObject currentCountry = jsonResult.get(i).getAsJsonObject();

            String name = currentCountry.get("name").getAsString();
            String shortName = currentCountry.get("shortName").getAsString();
            Long id = currentCountry.get("id").getAsLong();

            Country newCountry = new Country(id, name, shortName);
            result.add(newCountry);
        }
        return result;
    }
    
//    public void updateCountry(Country country) {
//        Integer id = country.getId();
//        String jsonString = country.toJson();
//        HttpClass.PutRequest(ServerURL + "/countrys/" + id, jsonString);
//    }
    
    public boolean deleteCountry(Country country) {
        Long id = country.getId();
        if (id == null)
            return false;
        return Requests.DeleteRequest(ServerURL + "/country/delete/" + id);
    }
}
