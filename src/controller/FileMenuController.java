package controller;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import main.AppMain;
import model.SaveDialog;
import model.notepad.Notepad;
import model.notepad.NotepadCreater;
import model.notepad.NotepadOpener;
import model.notepad.NotepadSaver;
import notepadutils.FileChooserDialogType;
import notepadutils.NotepadSavedStatus;

public class FileMenuController {
	private MainPageController mainPageController;
	private Notepad notepad;

	public FileMenuController(MainPageController mainPageController) {
		this.mainPageController = mainPageController;
		this.notepad = mainPageController.getNotepad();
	}
	
	public void handleEvent(ActionEvent event) {
		MenuItem menuItem = (MenuItem) event.getTarget();
		String id = menuItem.getId();
		switch (id) {
		case "newMenuItem": createNewNotepad(); break;
		case "newWindowMenuItem": openNewWindow(); break;
		case "openMenuItem": openNotepad(); break;
		case "saveMenuItem": saveNotepad(); break;
		case "saveAsMenuItem": saveAsNotepad(); break;
		case "closeMenuItem": closeNotepad(); break;
		case "printMenuItem": printNotepad(); break;
		case "exportMenuItem": exportNotepad();break;
		case "exitMenuItem": exit(); break;
		default: break;
		}
	}
	
	private void openNewWindow() {
		AppMain newWindow = new AppMain();
		try {
			newWindow.start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createNewNotepad() {
		if(!canProceedNewNotepad())
			return;
		NotepadCreater.createNewNotepad(mainPageController);
		mainPageController.getDisableMenuItems().setValue(false);
	}

	private void openNotepad() {
		if(!canProceedNewNotepad())
			return;
		if(NotepadOpener.openFile(mainPageController,FileChooserDialogType.OPEN))
			mainPageController.getDisableMenuItems().setValue(false);
	}
	
	private void saveNotepad() {
		if(!isNodepadexisting())
			return;
		NotepadSaver.saveFile(mainPageController);
	}

	private void saveAsNotepad() {
		if(!isNodepadexisting())
			return;
		NotepadSaver.saveAsFile(mainPageController);
	}
	
	private void printNotepad() {
	}
	
	private void exportNotepad() {
	}
	
	private void closeNotepad() {
		if(!canProceedNewNotepad())
			return;
		mainPageController.root.setCenter(mainPageController.getHomeScreen());
		mainPageController.root.setBottom(null);
		this.notepad = null;
		mainPageController.updateStageTitle(NotepadSavedStatus.CLOSED);
		mainPageController.getDisableMenuItems().setValue(true);
	}
	
	private boolean isNodepadexisting() {
		if(notepad != null)
			return true;
		return false;
	}
	
	private boolean isNotepadSaved() {
		if(notepad.getSavedStatus().equals(NotepadSavedStatus.SAVED))
			return true;
		return false;
	}
	
	public boolean canProceedNewNotepad() {
		if(!isNodepadexisting() || isNotepadSaved())
			return true;
		boolean proceed = getAskSaveResponse();
		return proceed;
	}
	
	private boolean getAskSaveResponse() {
		Optional<ButtonType> response = askSaveNotepad();
		if(!response.isPresent())
			return false;
		switch (response.get().getButtonData()) {
		case CANCEL_CLOSE: return false;
		case NO: return true;
		case YES: // to save the notepad here
		default: break;
		}
		boolean saveSuccess = NotepadSaver.saveFile(mainPageController);
		return saveSuccess;
	}
	
	private Optional<ButtonType> askSaveNotepad() {
		SaveDialog saveDialog = new SaveDialog(notepad.getFileName());
		return saveDialog.showAndWait();
	}
	
	private void exit() {
		mainPageController.getAppMain().mainStage.close();
	}
}

