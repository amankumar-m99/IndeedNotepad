package controller;

import java.util.Optional;

import configuration.Configuration;
import javafx.event.Event;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;
import model.FontDialog;

public class FormatMenuController implements MenuController {
	
	private MainPageController mainPageController;
	public FormatMenuController(MainPageController mainPageController) {
		this.mainPageController = mainPageController;
	}
	@Override
	public void handleEvent(Event event) {
		Object object = event.getTarget();
		String id="";
		if(object instanceof MenuItem)
			id = ((MenuItem)object).getId();
		else
			id = ((CheckMenuItem)object).getId();
		switch (id) {
		case "wordWrapMenuItem": wordWrap(event); break;
		case "fontsMenuItem": handleFonts(); break;
		default: break;
		}
	}

	private void wordWrap(Event event) {
		CheckMenuItem checkMenuItem = (CheckMenuItem)event.getTarget();
		boolean selectedValue = checkMenuItem.selectedProperty().getValue();
		Configuration.setWordWrap(selectedValue);
	}

	private void handleFonts() {
		FontDialog fontDialog = new FontDialog(mainPageController.getAppMain().mainStage);
		Optional<Font> font = fontDialog.getChoosenFont();
		if(font.isPresent())
		mainPageController.getNotepad().setFont(font.get());
	}

}
