package main.java.Unsorted;
import main.java.OtherStuff.PasswordGenerator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Controller {

    @FXML
    Button popup;

    @FXML
    TableView table;

    @FXML
    TableColumn label;

    @FXML
    TableColumn password;

    @FXML
    TableColumn editButton;

    @FXML
    TableColumn deleteButton;

    @FXML
    TextField labelField;

    @FXML
    TextField passwordField;

  Database database;

    PasswordGenerator generator;

    ArrayList<Tuple<String>> data;



    @FXML
    private void initialize() {
        generator = new PasswordGenerator();

//        popup.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                final Stage dialog = new Stage();
//                dialog.initModality(Modality.APPLICATION_MODAL);
//                dialog.initOwner(Main.getStage());
//                VBox dialogVbox = new VBox(20);
//
//
//                Scene dialogScene = new Scene(dialogVbox, 500, 300);
//                dialog.setScene(dialogScene);
//                dialog.show();
//            }
//        });
    }

    @FXML
    private void generate() {
        // TODO:
        // pull up popup with checkboxes for generation parameters
        // change parameters in this.generator after user clicks 'save'
        // get new password with generator.generate()
        // store password in passwordField
    }

    @FXML
    private void addPassword() {
        // TODO  make sure there is something in labelField & passwordField & that labelField is unique in this.data
//        database.addPassword(new Tuple<String>());
    }

    @FXML
    private void editLabel() {
        // TODO turn label in row into an editable textfield
        // change data in database
        // update table
    }

    @FXML
    private void editPassword() {
        // TODO  turn password label in row into editable textfield
        // change data in database
        // update table
    }

    @FXML
    private void delete() {
        // TODO delete password from database using database.delete(oldlabel/password, newlabel/password)
    }

    @FXML
    private void show() {
        // TODO password from clicked-on row
    }

    private void populateTable() {
        // TODO clear table & repopulate with database info
        // *** out passwords

    }




}
