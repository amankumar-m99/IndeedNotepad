package model.shortcuts;

import javafx.scene.layout.GridPane;
import notepadutils.Key;

public class PreferenceMenuShortcuts {
	public static void getShorcuts(GridPane grid) {
		grid.addRow(0,ShortcutLabel.actionLabel("IconPack"),ShortcutActionKey.actionKeys(Key.CTRL, Key.Z));
	}
}