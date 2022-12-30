package model;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Window;

public class SaveDialog extends Dialog<ButtonType>{

	private String fileName;

	public SaveDialog(String fileName, Window owner) {
		this.fileName = fileName;
		setTitle("Confirm save");
		initOwner(owner);
		makeDialog();
	}

	private void makeDialog() {
		DialogPane dialogPane = new DialogPane();
		dialogPane.setContent(getDialogContent());
		dialogPane.setPadding(new Insets(30));
		dialogPane.getButtonTypes().addAll(getDialogButtonTypes());
		setDialogPane(dialogPane);
	}

	private Node getDialogContent() {
		Label message = new Label("Do you want to save the changes to '"+fileName+"' ?");
		StackPane container = new StackPane(message);
		container.setPadding(new Insets(10));
		return container;
	}

	private ButtonType[] getDialogButtonTypes() {
		ButtonType saveBtn = new ButtonType("Save",ButtonData.YES);
		ButtonType discardBtn = new ButtonType("Don't save",ButtonData.NO);
		ButtonType cancelBtn = new ButtonType("Cancel",ButtonData.CANCEL_CLOSE);
		ButtonType[] list = new ButtonType[] {saveBtn,discardBtn,cancelBtn};
		return list;
	}
}
