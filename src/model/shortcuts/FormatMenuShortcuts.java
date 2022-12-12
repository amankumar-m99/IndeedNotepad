package model.shortcuts;

import javafx.scene.layout.GridPane;
import notepadutils.Key;

public class FormatMenuShortcuts {
	public static void getShorcuts(GridPane grid) {
		grid.addRow(0,ShortcutLabel.actionLabel("Word-wrap"),ShortcutActionKey.actionKeys(Key.CTRL, Key.Z));
		grid.addRow(1,ShortcutLabel.actionLabel("Format"),ShortcutActionKey.actionKeys(Key.CTRL, Key.Z));
	}
}
