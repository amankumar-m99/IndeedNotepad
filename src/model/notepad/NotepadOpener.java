package model.notepad;

import java.io.File;

import controller.MainPageController;
import javafx.stage.Window;
import model.StatusBar;
import notepadutils.CustomFileChooser;
import notepadutils.FileChooserDialogType;
import notepadutils.NotepadFileReader;
import notepadutils.NotepadSavedStatus;

public class NotepadOpener {
	private static File file;
	private static FileChooserDialogType type;
	private static Window owner;
	private static MainPageController mainPageController;
	
	public static boolean openFile(MainPageController mp,FileChooserDialogType t) {
		mainPageController = mp;
		type = t;
		owner = mp.root.getScene().getWindow();
		showOpenDialog();
		if(file == null)
			return false;
		openFile(file);
		return true;
	}

	private static void showOpenDialog() {
		file = CustomFileChooser.showFileChooser(type, owner);
	}

	public static void openFile(MainPageController mp,File file) {
		mainPageController = mp;
		openFile(file);
		mainPageController.getDisableMenuItems().setValue(false);
	}
	
	private static void openFile(File file) {
		mainPageController.setFileContent(NotepadFileReader.read(file));
		mainPageController.getNotepad().setFile(file);
		mainPageController.updateStageTitle(NotepadSavedStatus.SAVED);
		mainPageController.root.setBottom(new StatusBar());
	}
}
