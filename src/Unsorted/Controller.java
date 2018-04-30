package Unsorted;
import OtherStuff.PasswordGenerator;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Controller {
	
	private String hide = "**********";



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
	TableColumn <PasswordLabel, Button> editButton;

	@FXML
	TableColumn <PasswordLabel, Button> deleteButton;
	
	@FXML
	Button generateButton;

	@FXML
	TextField labelField;

	@FXML
	TextField passwordField;

	@FXML
	Button showButt;
	
	Database database;

	PasswordGenerator generator;

	ArrayList<Tuple<String>> data;
	
	Map<String ,String> passworMap = new HashMap<String ,String>();



	@FXML
	private void initialize() throws SQLException, Exception {
		
		
		System.out.println("hi");
		label.setCellValueFactory(cdf -> new SimpleStringProperty(cdf.getValue().getLabel()));
		password.setCellValueFactory(cdf -> new SimpleStringProperty(cdf.getValue().getPassword()));
		ContextMenu contextMenu = new ContextMenu();
		MenuItem edit = new MenuItem("Edit");
		contextMenu.getItems().add(edit);
		MenuItem delete = new MenuItem("Delete");
		contextMenu.getItems().add(delete);
		MenuItem copy = new MenuItem("Copy to Clipboard");
		contextMenu.getItems().add(copy);
		table.addEventHandler(MouseEvent.MOUSE_CLICKED,  new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent t) {
				if(t.getButton() == MouseButton.SECONDARY) {
					contextMenu.show(table, t.getScreenX(), t.getScreenY());
				}
			}
		});
		
		delete.setOnAction(new EventHandler<ActionEvent>() {


			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				PasswordLabel selectedColunm = table.getSelectionModel().getSelectedItem();
				String label = selectedColunm.getLabel();
				String password = selectedColunm.getPassword();
				Tuple<String> toDel = new Tuple<String>(label, password);
				try {
					database.deletePassword(toDel);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				passworMap.remove(label);
				try {
					update();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				
				
			}
			
			
		});

				
		generator = new PasswordGenerator();
		database = new Database();
		database.createDatabase();
		data = database.getAllPasswords();
		for (Tuple t: data) {
			passworMap.put((String)t.getLabel(), (String)t.getPassword());
			
			table.getItems().add(new PasswordLabel ((String)t.getLabel(), hide));
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
		

		
	}
	
	private void update() throws SQLException, Exception {
		table.getItems().clear();
		data = database.getAllPasswords();
		for (Tuple t: data) {
			table.getItems().add(new PasswordLabel ((String)t.getLabel(), hide));
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
			passworMap.put(labelField.getText(), passwordField.getText());
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
	private void deletePassword(String label) {
		// TODO delete password from database using database.delete(oldlabel/password, newlabel/password)
		try {
			database.deletePassword(new Tuple(labelField.getText(), passwordField.getText()));
			//passworMap.
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void show() throws Exception {
		// TODO password from clicked-on row
		PasswordLabel selectedColunm = table.getSelectionModel().getSelectedItem();
		String name = selectedColunm.getLabel();
		selectedColunm.setPassword(passworMap.get(name));
		table.refresh();
		System.out.println(selectedColunm.toString());
		
		
		
	}
	
	@FXML
	private void hide() {
		PasswordLabel shane = table.getSelectionModel().getSelectedItem();
		String name = shane.getLabel();
		shane.hidePassword();
		table.refresh();
	}

	private void populateTable() {
		// TODO clear table & repopulate with database info
		// *** out passwords

	}
}
