package model.notepad;

import configuration.Configuration;
import controller.MainPageController;
import main.AppMain;
import model.StatusBar;
import notepadutils.FontConverter;
import notepadutils.NotepadSavedStatus;

public class NotepadCreater {

	public static void createNewNotepad(MainPageController mainPageController) {
		String fileName = "Untitled-"+String.valueOf(++AppMain.untitledFileCounter);
		Notepad notepad = new Notepad(mainPageController);
		notepad.setFileName(fileName);
		notepad.getProperties().put("line.separator", System.getProperty("line.separator"));
		notepad.setFont(FontConverter.getFontFromFontString(Configuration.getFontString()));
		notepad.wrapTextProperty().bind(mainPageController.wordWrapMenuItem.selectedProperty());
		mainPageController.setNotepad(notepad);
		mainPageController.root.setCenter(notepad);
		addStatusBar(mainPageController);
		mainPageController.updateStageTitle(NotepadSavedStatus.SAVED);
	}

	private static void addStatusBar(MainPageController mainPageController) {
		StatusBar statusBar = new StatusBar(mainPageController);
		if(mainPageController.statusMenuItem.isSelected()) {
			mainPageController.root.setBottom(statusBar);
		}
		mainPageController.statusMenuItem.selectedProperty().addListener((observable,oldvalue,newValue)->{
			setStatusBarVisibility(mainPageController, statusBar, newValue);
		});
	}

	private static void setStatusBarVisibility(MainPageController mainPageController,StatusBar statusBar,boolean newValue) {
		if(newValue) {
			mainPageController.root.setBottom(statusBar);
		}
		else
			mainPageController.root.setBottom(null);
	}
}