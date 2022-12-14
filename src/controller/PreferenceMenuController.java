package controller;

import java.util.List;

import configuration.Configuration;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.stage.Window;
import model.ClearSavedDataDialog;
import model.IconPackDialog;

public class PreferenceMenuController implements MenuController{
	private MainPageController mainPageController;

	public PreferenceMenuController(MainPageController mainPageController) {
		this.mainPageController = mainPageController;
	}
	
	@Override
	public void handleEvent(Event event) {
		String id = "";
		Object obj = event.getTarget();
		if(obj instanceof MenuItem)
			id = ((MenuItem)event.getTarget()).getId();
		else if(obj instanceof CheckMenuItem)
			id = ((CheckMenuItem)event.getTarget()).getId();
		else
			id = "";
		switch (id) {
		case "launchFullScreenMenuItem": toggleLaunchFullScreen(event); break;
		case "iconPackMenuItem": showIconPacks(); break;
		case "clearAppDataMenuItem": clearAppData(); break;
		default: break;
		}
	}
	
	private void toggleLaunchFullScreen(Event event) {
		CheckMenuItem checkMenuItem = (CheckMenuItem)event.getTarget();
		boolean selectedValue = checkMenuItem.selectedProperty().getValue();
		Configuration.setFullScreenLaunch(selectedValue);
	}

	private void showIconPacks() {
		IconPackDialog dialog = new IconPackDialog(mainPageController.getAppMain().mainStage);
		dialog.showIconPackDialog();
	}
	
	private void clearAppData() {
		Window owner = mainPageController.getAppMain().mainStage;
		List<String> list = Configuration.getPreferenceList();
		Dialog<ButtonType> dialog = new ClearSavedDataDialog(list, owner);
		dialog.showAndWait();
		Configuration.removePrefrences();
	}
}
