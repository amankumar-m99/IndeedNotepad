package model.notepad;

import java.io.File;

import controller.MainPageController;
import javafx.stage.Window;
import notepadutils.CustomFileChooser;
import notepadutils.FileChooserDialogType;
import notepadutils.NotePadFileWriter;
import notepadutils.NotepadSavedStatus;

public class NotepadSaver {
	private static File file;
	private static Window owner;	
	private static Notepad notepad;
	private static MainPageController mainPageController;
	
	public static boolean saveFile(MainPageController mp) {
		initData(mp);
		if(notepad.isFileCreated())
			file = notepad.getFile();
		else
			file = showSaveDialog();
		if(file == null)
			return false;
		return saveFile();
	}

	public static boolean saveAsFile(MainPageController mp) {
		initData(mp);
		file = showSaveAsDialog();
		if(file == null)
			return false;
		return saveFile();
	}
	
	private static void initData(MainPageController mp) {
		mainPageController = mp;
		notepad = mp.getNotepad();
		owner = mp.root.getScene().getWindow();
	}

	private static File showSaveAsDialog() {
		return CustomFileChooser.showFileChooser(FileChooserDialogType.SAVE_AS, owner);
	}

	private static File showSaveDialog() {
		return CustomFileChooser.showFileChooser(FileChooserDialogType.SAVE, owner);
	}
	
	private static boolean saveFile() {
		String fileContent = notepad.getText();
		NotePadFileWriter.write(file, fileContent);
	    notepad.setFileCreated(true);
		notepad.setFile(file);
		mainPageController.updateStageTitle(NotepadSavedStatus.SAVED);
		return true;
	}
}
