package model.notepad;

import java.io.File;

import controller.MainPageController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;
import notepadutils.NotepadSavedStatus;

public class Notepad extends TextArea{

	private NotepadSavedStatus state;
	private boolean isFileCreated;
	private File file;
	private String fileName;
	private MainPageController mainPageController;

	public Notepad(MainPageController mainPageController) {
		this(mainPageController, "", false);
	}

	public Notepad(MainPageController mainPageController, String text) {
		this(mainPageController, text, false);
	}

	public Notepad(MainPageController mainPageController, String text, boolean isFileCreated) {
		super(text);
		this.isFileCreated = isFileCreated;
		this.mainPageController = mainPageController;
		this.state = NotepadSavedStatus.SAVED;
		textProperty().addListener(getNotepadChangeListener());
		this.getCaretPosition();
	}

	public boolean isFileCreated() {
		return isFileCreated;
	}

	public void setFileCreated(boolean isFileCreated) {
		this.isFileCreated = isFileCreated;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
		this.fileName = file.getName();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public NotepadSavedStatus getSavedStatus() {
		return state;
	}

	public void setSavedStatus(NotepadSavedStatus state) {
		this.state = state;
	}

	private ChangeListener<String> getNotepadChangeListener(){
		ChangeListener<String> notepadChangeListener = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				setSavedStatus(NotepadSavedStatus.UNSAVED);
				mainPageController.updateStageTitle(state);
			}
		};
		return notepadChangeListener;
	}
}