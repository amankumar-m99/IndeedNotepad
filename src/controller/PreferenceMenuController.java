package controller;

import configuration.Configuration;
import javafx.event.Event;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
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
		Configuration.removePrefrences();
	}
}
