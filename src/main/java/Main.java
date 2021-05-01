import controllers.MainController;
import javafx.application.Application;
import javafx.stage.Stage;
import controllers.LoginController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        primaryStage.setResizable(false);
        LoginController.loginView(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
