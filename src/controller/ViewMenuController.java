package controller;

import javafx.event.Event;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import model.About;
import model.Shortcuts;

public class ViewMenuController {
	private MainPageController mainPageController;

	public ViewMenuController(MainPageController mainPageController) {
		this.mainPageController = mainPageController;
	}
	
	public void handleEvent(Event event) {
		CheckMenuItem checkMenuItem = (CheckMenuItem) event.getTarget();
		String id = checkMenuItem.getId();
		boolean isSelected = checkMenuItem.isSelected();
		switch (id) {
		case "lineNumbersMenuItem": showLineNumbers(isSelected); break;
		case "statusMenuItem": showStatus(isSelected); break;
		default: break;
		}
	}

	private void showLineNumbers(boolean isSelected) {
	}

	private void showStatus(boolean isSelected) {
		
	}

}
