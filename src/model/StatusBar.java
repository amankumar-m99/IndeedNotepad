package model;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class StatusBar extends HBox{
	public StatusBar() {
		getChildren().add(new Label("Status Bar"));
		setPadding(new Insets(10,20,10,20));
	}
}
