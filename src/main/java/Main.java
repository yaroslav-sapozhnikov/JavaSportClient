import javafx.application.Application;
import javafx.stage.Stage;
import controllers.Controller;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        primaryStage.setResizable(false);
        Controller.loginView(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
