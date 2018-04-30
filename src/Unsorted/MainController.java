package Unsorted;




import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainController {
@FXML 
Button loginButton;

@FXML
ImageView img;

@FXML
TextField password;

Stage stage = Main.getStage();

private void initialize() {
	
}
@FXML
private void switchGui() throws IOException{
	Parent parentLogin = FXMLLoader.load(getClass().getClassLoader().getResource("GUI.fxml"));
	Scene sceneLogin = new Scene(parentLogin);
    stage.setScene(sceneLogin);
    stage.show();
	
}

}
 