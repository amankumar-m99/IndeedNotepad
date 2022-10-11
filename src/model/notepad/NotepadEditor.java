package model.notepad;

import controller.MainPageController;
import model.FindReplaceDialog;
import model.FindReplaceDialog.FindReplaceType;

public class NotepadEditor {
	private static Notepad notepad;
	private static MainPageController mainPageController;
	
	public static void edit(MainPageController mainPgController, String id) {
		mainPageController = mainPgController;
		notepad = mainPageController.getNotepad();
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
	
	private static void undo() {
		notepad.undo();
	}
	
	private static void redo() {
		notepad.redo();
	}
	
	private static void cut() {
		notepad.cut();
	}
	
	private static void copy() {
		notepad.copy();
		/*
		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent clipboardContent = new ClipboardContent();
		clipboardContent.putString(notepad.getSelectedText());
		clipboard.setContent(clipboardContent);
		*/
	}
	
	private static void paste() {
		notepad.paste();
		/*
		String text = Clipboard.getSystemClipboard().getString();
		if(text == null)
			return;
		int caretPosition = notepad.getCaretPosition();
		notepad.insertText(caretPosition, text);
		*/
	}

	private static void delete() {
		if(!notepad.getSelectedText().isEmpty()) {
			notepad.deleteNextChar(); 
		}
	}

	private static void find() {
		setFindReplaceDialog(FindReplaceType.FIND);
	}

	private static void replace() {
		setFindReplaceDialog(FindReplaceType.REPLACE);
	}
	
	private static void setFindReplaceDialog(FindReplaceType type) {
		FindReplaceDialog findReplaceDialog = mainPageController.getFindReplaceDialog();
		if(findReplaceDialog == null)
			findReplaceDialog = new FindReplaceDialog(mainPageController.getAppMain().mainStage);
		if(notepad.getSelectedText() != null)
			findReplaceDialog.setFindText(notepad.getSelectedText());
		findReplaceDialog.show(type);
	}

	private static void selectAll() {
		notepad.selectAll();
	}
	
	private static void deleteAll() {
		notepad.clear();
	}
}
