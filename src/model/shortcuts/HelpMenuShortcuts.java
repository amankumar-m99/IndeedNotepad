package model.shortcuts;

import javafx.scene.layout.GridPane;
import notepadutils.Key;

public class HelpMenuShortcuts {
	public static void getShorcuts(GridPane grid) {
		grid.addRow(0,ShortcutLabel.actionLabel("Shortcuts"),ShortcutActionKey.actionKeys(Key.CTRL, Key.Z));
	}
}