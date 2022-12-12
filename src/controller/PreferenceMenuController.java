package controller;

import configuration.Configuration;
import javafx.event.Event;
import javafx.scene.control.MenuItem;
import model.IconPackDialog;

public class PreferenceMenuController implements MenuController{
	private MainPageController mainPageController;

	public PreferenceMenuController(MainPageController mainPageController) {
		this.mainPageController = mainPageController;
	}
	
	@Override
	public void handleEvent(Event event) {
		MenuItem menuItem = (MenuItem) event.getTarget();
		String id = menuItem.getId();
		switch (id) {
		case "iconPackMenuItem": showIconPacks(); break;
		case "clearAppDataMenuItem": clearAppData(); break;
		default: break;
		}
	}
	
	private void showIconPacks() {
		IconPackDialog dialog = new IconPackDialog(mainPageController.getAppMain().mainStage);
		dialog.showIconPackDialog();
	}
	
	private void clearAppData() {
		Configuration.removePrefrences();
	}
}
