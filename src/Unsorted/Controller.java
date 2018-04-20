package Unsorted;
import OtherStuff.PasswordGenerator;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {



	@FXML
	Button popup;
	
	@FXML
	Button addPasswordButton;

	@FXML
	TableView<PasswordLabel> table;

	@FXML
	TableColumn<PasswordLabel, String> label;

	@FXML
	TableColumn <PasswordLabel, String> password;

	@FXML
	TableColumn editButton;

	@FXML
	TableColumn deleteButton;
	
	@FXML
	Button generateButton;

	@FXML
	TextField labelField;

	@FXML
	TextField passwordField;

	Database database;

	PasswordGenerator generator;

	ArrayList<Tuple<String>> data;



	@FXML
	private void initialize() throws SQLException, Exception {
		System.out.println("hi");
		label.setCellValueFactory(cdf -> new SimpleStringProperty(cdf.getValue().getLabel()));
		password.setCellValueFactory(cdf -> new SimpleStringProperty(cdf.getValue().getPassword()));
				
		generator = new PasswordGenerator();
		database = new Database();
		database.createDatabase();
		data = database.getAllPasswords();
		for (Tuple t: data) {
			table.getItems().add(new PasswordLabel ((String)t.getLabel(), (String)t.getPassword()));
		}
		System.out.println(data.toString());
		System.out.println(generateButton == null);
		
		Platform.runLater(() -> {generateButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String genPassword = generator.generate();
				passwordField.setText(genPassword);
				
			}
			
			
		});});
		

		/*
        table.getItems().add(new PasswordLabel(label.getText(), 
                password.getText()));
        label.setText("asds");
        password.setText("asdsa");
		 */


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
	
	private void update() throws SQLException, Exception {
		table.getItems().clear();
		data = database.getAllPasswords();
		for (Tuple t: data) {
			table.getItems().add(new PasswordLabel ((String)t.getLabel(), (String)t.getPassword()));
		}
		System.out.println(data.toString());
		
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
	private void addPassword() throws SQLException, Exception {
		// TODO  make sure there is something in labelField & passwordField & that labelField is unique in this.data
		//        database.addPassword(new Tuple<String>());
		try {
			database.addPassword(new Tuple(labelField.getText(), passwordField.getText()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		labelField.clear();
		passwordField.clear();
		update();
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
		try {
			database.deletePassword(new Tuple(labelField.getText(), passwordField.getText()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
