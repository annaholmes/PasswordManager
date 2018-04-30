package Unsorted;

import javafx.scene.control.Button;

public class PasswordLabel {
	private String label;
	private String password;
	//private Button edit;
	//private Button delete;
	
	 
	 
	  public PasswordLabel(String label, String password) {
	    this.label = label;
	    this.password = password;
	    //this.edit = new Button("edit");
	    //this.edit = new Button("delete");
	    
	  }
	  
	  public String getLabel() {
		  return label;
	  }
	  
	  public String getPassword() {
		  return password;
	  }
	  
	  public String setPassword(String password) {
		  this.password = password;
		  return password;
	  }
	  
	  public void hidePassword(){
		 this.password = "**********";
	  }
	  
	  public String toString() {
		  return password;
	  }

}
