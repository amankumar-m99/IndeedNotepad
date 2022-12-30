package controller;

import java.util.Optional;

import appearance.AppearanceDialog;
import configuration.Configuration;
import javafx.event.Event;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Window;
import model.iconpack.IconPackDialog;
import notepadutils.CustomAlert;
import theme.ThemeChooserDialog;

public class PreferenceMenuController implements MenuController{
	private MainPageController mainPageController;

	public PreferenceMenuController(MainPageController mainPageController) {
		this.mainPageController = mainPageController;
	}

	@Override
	public void handleEvent(Event event) {
		String id = "";
		Object obj = event.getTarget();
		if(obj instanceof Menu)
			id = "";
		else if(obj instanceof MenuItem)
			id = ((MenuItem)obj).getId();
		else if(obj instanceof CheckMenuItem)
			id = ((CheckMenuItem)obj).getId();
		else
			id = "";
		switch (id) {
		case "launchFullScreenMenuItem": toggleLaunchFullScreen(event); break;
		case "clearAppDataMenuItem": clearAppData(); break;
		case "iconPackMenuItem": showIconPacks(); break;
		case "themeMenuItem": showThemeChooser(); break;
//			showAppearanceMenu();
		default: break;
		}
	}

	private void showAppearanceMenu() {
		AppearanceDialog appearanceDialog = new AppearanceDialog(mainPageController);
		appearanceDialog.showAndWait();
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

	private void showThemeChooser(){
		ThemeChooserDialog dialog = new ThemeChooserDialog(mainPageController);
		dialog.showThemeChooserDialog();
	}

	private void clearAppData() {
		AlertType alertType = AlertType.CONFIRMATION;
		String titleText = "Clear Preferences";
		String headerText = "Clear all the preferences ?";
		String contentText = "This action will reset the default preferences.";
		Window owner = mainPageController.getAppMain().mainStage;
		CustomAlert alert = new CustomAlert(alertType, titleText, headerText, contentText, owner);
		Optional<ButtonType> result = alert.showAndWait();
		if(!result.isPresent() || result.get().equals(ButtonType.CANCEL))
			return;
		if(Configuration.removePrefrences()) {
			alertType = AlertType.INFORMATION;
			titleText = "Success";
			headerText = "Cleared all the preferences successfully.";
			contentText = "Preferences are restored to default values";
		}
		else {
			alertType = AlertType.ERROR;
			titleText = "Failed";
			headerText = "Failed to clear some preferences.";
			contentText = "";
		}
		alert = new CustomAlert(alertType, titleText, headerText, contentText, owner);
		alert.showAndWait();
	}

}
