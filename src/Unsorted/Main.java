package Unsorted;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	private static Stage primaryStage; 
	
    


    public static Stage getStage() {
        return Main.primaryStage;
    }
   

    private static void setPrimaryStage(Stage stage) {
        Main.primaryStage = stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LoginGUI.fxml"));
        primaryStage.setTitle("Password Manager");
        primaryStage.setScene(new Scene(root, 700, 400));
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
}
