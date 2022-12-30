package controller;

import javafx.event.Event;
import javafx.scene.control.MenuItem;
import model.FindReplaceDialog;
import model.FindReplaceDialog.FindReplaceType;
import model.notepad.Notepad;

public class EditMenuController implements MenuController {
	private MainPageController mainPageController;
	private Notepad notepad;

	public EditMenuController(MainPageController mainPageController) {
		this.mainPageController = mainPageController;
		this.notepad = mainPageController.getNotepad();
	}

	@Override
	public void handleEvent(Event event) {
		MenuItem menuItem = (MenuItem) event.getTarget();
		String id = menuItem.getId();
		switch (id) {
		case "undoMenuItem": undo(); break;
		case "redoMenuItem": redo(); break;
		case "cutMenuItem": cut(); break;
		case "copyMenuItem": copy(); break;
		case "pasteMenuItem": paste(); break;
		case "deleteMenuItem": delete(); break;
		case "findMenuItem": find(); break;
		case "replaceMenuItem": replace(); break;
		case "selectAllMenuItem": selectAll(); break;
		case "deleteAllMenuItem": deleteAll(); break;
		default: break;
		}
	}

	private void undo() {
		notepad.undo();
	}

	private void redo() {
		notepad.redo();
	}

	private void cut() {
		notepad.cut();
	}

	private void copy() {
		notepad.copy();
	}

	private void paste() {
		notepad.paste();
	}

	private void delete() {
		if(!notepad.getSelectedText().isEmpty()) {
			notepad.deleteNextChar(); 
		}
	}

	private void find() {
		setFindReplaceDialog(FindReplaceType.FIND);
	}

	private void replace() {
		setFindReplaceDialog(FindReplaceType.REPLACE);
	}

	private void setFindReplaceDialog(FindReplaceType type) {
		if(mainPageController.getFindReplaceDialog() == null) {
			mainPageController.setFindReplaceDialog(new FindReplaceDialog(mainPageController.getAppMain().mainStage));
		}
		FindReplaceDialog findReplaceDialog = mainPageController.getFindReplaceDialog();
		if(notepad.getSelectedText() != null)
			findReplaceDialog.setFindText(notepad.getSelectedText());
		findReplaceDialog.show(type);
	}

	private void selectAll() {
		notepad.selectAll();
	}

	private void deleteAll() {
		notepad.clear();
	}

}
