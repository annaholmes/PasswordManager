package Data;

public class PasswordLabel {
	private String label;
	private String password;

	public boolean isHidden() {
		return isHidden;
	}

	//private Button edit;
	private boolean isHidden;
	//private Button delete;
	
	 
	 
	public PasswordLabel(String label, String password, boolean isHidden) {
	    this.label = label;
	    this.password = password;
	    this.isHidden = isHidden;
	    //this.edit = new Button("edit");
	    //this.edit = new Button("delete");
	    
	}
	  
	  public String getLabel() {
		  return label;
	  }
	  
	  public String getPassword() {
		  if (isHidden) {
			  return "********";
		  } else {
			  return password;
		  }
	  }
	  
	  public void setPassword(String password) {
		  this.password = password;
		  isHidden = false;

	  }
	  
	  public void toggleHidden(){
		 isHidden = !isHidden;
	  }
	  
	  public String toString() {
	  	if (isHidden) {
	  		return "********";
		} else {
	  		return password;
		}
	  }

}
