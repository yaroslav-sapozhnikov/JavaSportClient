package restapi;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dto.UserDto;

public class RegisterApi {
    private static final String ServerURL = "http://127.0.0.1:8080";

    public String registerUser(UserDto userDto) {
        String jsonString = new Gson().toJson(userDto);
        JsonObject jsonObject = new Gson().fromJson(Requests.PostRequest(ServerURL + "/user/register/", jsonString), JsonObject.class);

        if (jsonObject.get("message").isJsonNull()) {
            return jsonObject.get("error").getAsString();

        } else {
            return jsonObject.get("message").getAsString();
        }
    }
}
