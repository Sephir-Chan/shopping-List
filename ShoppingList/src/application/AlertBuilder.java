package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

public class AlertBuilder {

	private AlertType type;
	private Window owner;
	private String title;
	private String header;
	private String content;
	private ButtonType[] buttonTypes;
	
	public void setType(AlertType type){
		this.type = type;
	}
	
	public void setOwner(Window owner){
		this.owner = owner;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setHeader(String header){
		this.header = header;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public void setButtonTypes(ButtonType ... buttonTypes){
		this.buttonTypes = buttonTypes;
	}
	
	public Alert getAlert(){
		Alert alert;
		if (buttonTypes == null){
			alert = new Alert(type);
			if (content != null){
				alert.setContentText(content);
			}
		}else{
			alert = new Alert(type, content, buttonTypes);
		}
		alert.initOwner(owner);
		alert.setTitle(title);
		alert.setHeaderText(header);
		return alert;
	}
}
