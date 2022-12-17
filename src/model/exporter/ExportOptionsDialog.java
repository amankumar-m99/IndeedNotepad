package model.exporter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.MainPageController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

public class ExportOptionsDialog extends Dialog<ButtonType> {
	private String fileName;
	private Window owner;
	private String path;
	private TextField nameTextField;

	public ExportOptionsDialog(MainPageController mainPageController) {
		this.fileName = mainPageController.getNotepad().getFileName();
		this.owner = mainPageController.getAppMain().mainStage;
		initOwner(owner);
		setTitle("Export document");
		setDialogPane(getThisDialogPane());
	}

	private DialogPane getThisDialogPane() {
		DialogPane dialogPane = new DialogPane();
		dialogPane.setPadding(new Insets(20,35,20,35));
		dialogPane.setContent(getThisDialogPaneContent());
		dialogPane.getButtonTypes().addAll(ButtonType.CLOSE);
		Button button = (Button) dialogPane.lookupButton(ButtonType.CLOSE);
		button.setVisible(false);
		return dialogPane;
	}

	private Node getThisDialogPaneContent() {
		List<String> list = new ArrayList<>();
		list.add("PDF (*.pdf)");
		list.add("MS Word (*.docx");
		ComboBox<String> docTyppeCombo = new ComboBox<>();
		docTyppeCombo.getItems().addAll(list);
		docTyppeCombo.getSelectionModel().select(0);
		nameTextField = new TextField();
		nameTextField.setText(fileName);
		TextField locationTextField = new TextField();
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Browse location to export document");
		Button browseButton = new Button("Browse");
		browseButton.setOnAction(e->{
			File file = directoryChooser.showDialog(owner);
			if(file == null || !file.exists())
				return;
			try {
				locationTextField.setText(file.getCanonicalPath());
				path = file.getCanonicalPath();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		locationTextField.setEditable(false);
		GridPane gridPane = new GridPane();
		gridPane.setHgap(20);
		gridPane.setVgap(15);
		gridPane.addRow(0, new Label("Type of document"), docTyppeCombo);
		gridPane.addRow(1, new Label("Name of document"), nameTextField);
		gridPane.addRow(2, new Label("Location of document"), locationTextField, browseButton);
		return gridPane;
	}

	public void createPDF() {
		new PDFExporter(path+File.separator+nameTextField.getText()+".pdf").createPDF();
	}

}
