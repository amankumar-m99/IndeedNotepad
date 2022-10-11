package notepadutils;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public class CustomAlert extends Alert {
	public CustomAlert(AlertType alertType, String titleText, String headerText, String contentText, Window owner) {
		super(alertType);
		if(titleText != null)
			setTitle(titleText);
		if(headerText != null)
			setHeaderText(headerText);
		if(contentText != null)
			setContentText(contentText);
		if(owner != null)
			initOwner(owner);
	}
}