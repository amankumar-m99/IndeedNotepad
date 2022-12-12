package model.shortcuts;

import javafx.scene.layout.GridPane;
import notepadutils.Key;

public class FileMenuShortcuts {
	public static void getShorcuts(GridPane grid) {
		grid.addRow(0,ShortcutLabel.actionLabel("New File"),ShortcutActionKey.actionKeys(Key.CTRL, Key.N));
		grid.addRow(1,ShortcutLabel.actionLabel("New Window"),ShortcutActionKey.actionKeys(Key.CTRL, Key.SHIFT, Key.N));
		grid.addRow(2,ShortcutLabel.actionLabel("Open File"),ShortcutActionKey.actionKeys(Key.CTRL, Key.O));
		grid.addRow(3,ShortcutLabel.actionLabel("Save File"),ShortcutActionKey.actionKeys(Key.CTRL, Key.S));
		grid.addRow(4,ShortcutLabel.actionLabel("Save File As"),ShortcutActionKey.actionKeys(Key.CTRL, Key.SHIFT, Key.S));
		grid.addRow(5,ShortcutLabel.actionLabel("Close File"),ShortcutActionKey.actionKeys(Key.CTRL, Key.W));
		grid.addRow(6,ShortcutLabel.actionLabel("Print File"),ShortcutActionKey.actionKeys(Key.CTRL, Key.P));
		grid.addRow(7,ShortcutLabel.actionLabel("Export File"),ShortcutActionKey.actionKeys(Key.CTRL, Key.E));
		grid.addRow(8,ShortcutLabel.actionLabel("Exit File"),ShortcutActionKey.actionKeys(Key.CTRL, Key.N));
	}
}
