package Unsorted;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI.fxml"));
        primaryStage.setTitle("Password Manager");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        this.stage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
