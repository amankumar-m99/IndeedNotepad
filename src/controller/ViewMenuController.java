package controller;

import configuration.Configuration;
import javafx.event.Event;
import javafx.scene.control.CheckMenuItem;

public class ViewMenuController implements MenuController{

	public ViewMenuController(MainPageController mainPageController) {
	}
	
	@Override
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
		Configuration.setShowStatusBar(isSelected);
	}

}
