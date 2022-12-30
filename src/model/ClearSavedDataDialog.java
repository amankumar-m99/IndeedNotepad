package model;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

public class ClearSavedDataDialog extends Dialog<ButtonType>{

	private List<String> preferenceList;

	public ClearSavedDataDialog(List<String> list, Window owner) {
		preferenceList = list;
		initOwner(owner);
		setDialogPane(getThisDialogPane());
		setTitle("Clear Preferences");
	}

	private DialogPane getThisDialogPane() {
		DialogPane dialogPane = new DialogPane();
		dialogPane.setContent(getThisDialogContent());
		dialogPane.setPadding(new Insets(10));
		dialogPane.getButtonTypes().addAll(ButtonType.CANCEL,ButtonType.NEXT);
		return dialogPane;
	}

	private Node getThisDialogContent() {
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(preferenceListVBox());
		scrollPane.prefHeight(400);
		scrollPane.maxHeight(400);
		scrollPane.setPadding(new Insets(16));
		HBox expandHBox = new HBox();
		HBox.setHgrow(expandHBox, Priority.ALWAYS);
		Button invertSelectionButton = new Button("Invert Selection");
		Button toggleSelectAllButton = new Button("Select All");
		HBox hBox = new HBox(10, expandHBox, invertSelectionButton, toggleSelectAllButton);
		VBox vBox = new VBox(20,scrollPane, hBox);
		return vBox;
	}

	private Node preferenceListVBox() {
		VBox vBox = new VBox(10);
		for(String s: preferenceList) {
			vBox.getChildren().add(new CheckBox(s));
		}
		return vBox;
	}
}
