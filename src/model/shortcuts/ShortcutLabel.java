package model.shortcuts;

import javafx.scene.control.Label;

public class ShortcutLabel {
	public static Label actionLabel(String text) {
		Label label = new Label(text);
		label.setStyle("-fx-font-weight:bold;");
		return label;
	}
}
