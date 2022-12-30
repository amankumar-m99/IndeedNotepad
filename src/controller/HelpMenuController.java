package controller;

import javafx.event.Event;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import model.About;
import model.shortcuts.Shortcuts;

public class HelpMenuController implements MenuController{
	private MainPageController mainPageController;

	public HelpMenuController(MainPageController mainPageController) {
		this.mainPageController = mainPageController;
	}

	@Override
	public void handleEvent(Event event) {
		MenuItem menuItem = (MenuItem) event.getTarget();
		String id = menuItem.getId();
		switch (id) {
		case "aboutMenuItem": showAbout(); break;
		case "shortcutMenuItem": showShortCuts(); break;
		default: break;
		}
	}

	private void showAbout() {
		About about = new About(mainPageController.getAppMain().getMainStage());
		about.show();
	}

	private void showShortCuts() {
		Dialog<ButtonType> dialog = new Shortcuts();
		dialog.initOwner(mainPageController.getAppMain().getMainStage());
		dialog.showAndWait();
	}

}
