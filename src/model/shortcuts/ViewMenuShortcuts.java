package model.shortcuts;

import javafx.scene.layout.GridPane;
import notepadutils.Key;

public class ViewMenuShortcuts {
	public static void getShorcuts(GridPane grid) {
		grid.addRow(0,ShortcutLabel.actionLabel("Toggle Status Bar"),ShortcutActionKey.actionKeys(Key.CTRL, Key.Z));
	}
}