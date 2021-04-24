package services;

public class LoginService {

    public String login (String username, String password) {
        if (username.equals("a") && password.equals("a")) {
            return "LOGIN_SUCCESSFUL";
        } else {
            return "LOGIN_UNSUCCESSFUL";
        }
    }
}
