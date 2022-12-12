package model.shortcuts;

import javafx.scene.layout.GridPane;
import notepadutils.Key;

public class EditMenuShortcuts {
	public static void getShorcuts(GridPane grid) {
		grid.addRow(0,ShortcutLabel.actionLabel("Undo"),ShortcutActionKey.actionKeys(Key.CTRL, Key.Z));
		grid.addRow(1,ShortcutLabel.actionLabel("Redo"),ShortcutActionKey.actionKeys(Key.CTRL, Key.Y));
		grid.addRow(2,ShortcutLabel.actionLabel("Cut"),ShortcutActionKey.actionKeys(Key.CTRL, Key.X));
		grid.addRow(3,ShortcutLabel.actionLabel("Copy"),ShortcutActionKey.actionKeys(Key.CTRL, Key.C));
		grid.addRow(4,ShortcutLabel.actionLabel("Paste"),ShortcutActionKey.actionKeys(Key.CTRL, Key.V));
		grid.addRow(5,ShortcutLabel.actionLabel("Find"),ShortcutActionKey.actionKeys(Key.CTRL, Key.F));
		grid.addRow(6,ShortcutLabel.actionLabel("Replace"),ShortcutActionKey.actionKeys(Key.CTRL, Key.R));
		grid.addRow(7,ShortcutLabel.actionLabel("Select All"),ShortcutActionKey.actionKeys(Key.CTRL, Key.A));
	}
}
