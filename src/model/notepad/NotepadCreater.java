package model.notepad;

import controller.MainPageController;
import main.AppMain;
import model.StatusBar;
import notepadutils.NotepadSavedStatus;

public class NotepadCreater {
	
	public static void createNewNotepad(MainPageController mainPageController) {
		String fileName = "Untitled-"+String.valueOf(++AppMain.untitledFileCounter);
		Notepad notepad = new Notepad(mainPageController);
		notepad.setFileName(fileName);
		mainPageController.setNotepad(notepad);
		mainPageController.root.setCenter(notepad);
		mainPageController.root.setBottom(new StatusBar());
		mainPageController.updateStageTitle(NotepadSavedStatus.SAVED);
	}	
}