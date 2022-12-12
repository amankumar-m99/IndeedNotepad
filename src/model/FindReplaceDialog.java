package model;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Window;

public class FindReplaceDialog extends Dialog<ButtonType>{
	public static enum FindReplaceType{
		FIND, REPLACE;
	}
	private TabPane tabPane;
	private TextField findTextField;
	private TextField findReplaceTextField;
	private TextField replaceTextField;
	
	public FindReplaceDialog(Window owner) {
		findTextField = new TextField();
		findTextField.setPrefWidth(400);
		findReplaceTextField = new TextField();
		findReplaceTextField.setPrefWidth(400);
		replaceTextField = new TextField();
		replaceTextField.setPrefWidth(400);
		initOwner(owner);
		initModality(Modality.NONE);
		setTitle("Find/Replace");
		setDialogPane(getFindReplaceDialogPane());
	}
	
	private DialogPane getFindReplaceDialogPane() {
		DialogPane dialogPane = new DialogPane();
		dialogPane.getButtonTypes().add(ButtonType.CLOSE);
		dialogPane.setContent(getFindReplaceDialogPaneContent());
		dialogPane.setPrefWidth(500);
		dialogPane.setPadding(new Insets(20,30,20,30));
		return dialogPane;
	}

	private Node getFindReplaceDialogPaneContent() {
		tabPane = new TabPane();
		tabPane.getTabs().addAll(getFindTab(),getReplaceTab());
		return tabPane;
	}

	private Tab getFindTab() {
		Tab tab = new Tab("Find");
		tab.setClosable(false);
		tab.setContent(getGenerateTabContent(FindReplaceType.FIND));
		return tab;
	}

	private Tab getReplaceTab() {
		Tab tab = new Tab("Replace");
		tab.setClosable(false);
		tab.setContent(getGenerateTabContent(FindReplaceType.REPLACE));
		return tab;
	}
	
	private Node getGenerateTabContent(FindReplaceType type) {
		VBox vbox = new VBox(10);
		vbox.setPadding(new Insets(20,5,10,5));
		GridPane gridPane = getGenerateMainGrid(type);
		vbox.getChildren().addAll(gridPane,scopePane(),optionsPane(),directionPane(),buttonPallete(type));
		return vbox;
	}
	
	private GridPane getGenerateMainGrid(FindReplaceType type) {
		ColumnConstraints col1 = new ColumnConstraints(70,70,70);
		GridPane gridPane;
		if(type.equals(FindReplaceType.FIND))
			gridPane = getFindGrid();
		else
			gridPane = getReplaceGrid();
		gridPane.setHgap(15);
		gridPane.setVgap(15);
		gridPane.getColumnConstraints().add(col1);
		return gridPane;
	}
	
	private GridPane getFindGrid() {
		GridPane gridPane = new GridPane();
		gridPane.addRow(0, new Label("Find :"),findTextField);
		gridPane.addRow(1, new Label("Replace :"),new TextField());
		gridPane.getChildren().get(2).setVisible(false);
		gridPane.getChildren().get(3).setVisible(false);
		return gridPane;
	}

	private GridPane getReplaceGrid() {
		GridPane gridPane = new GridPane();
		gridPane.addRow(0, new Label("Find :"),findReplaceTextField);
		gridPane.addRow(1, new Label("Replace :"),replaceTextField);
		return gridPane;
	}
	
	private Node scopePane() {
		ToggleGroup scopeToggleGroup = new ToggleGroup();
		RadioButton allRadioBtn = new RadioButton("All");
		allRadioBtn.setSelected(true);
		allRadioBtn.setToggleGroup(scopeToggleGroup);
		RadioButton selectedLinesRadioBtn = new RadioButton("Selected Lines");
		selectedLinesRadioBtn.setToggleGroup(scopeToggleGroup);
		VBox vbox = new VBox(4,new Label("Scope :"),allRadioBtn,selectedLinesRadioBtn);
		return vbox;
	}
	
	private Node optionsPane() {
		ToggleGroup wordToggleGroup = new ToggleGroup();
		RadioButton wordRadioBtn = new RadioButton("Whole Word");
		wordRadioBtn.setToggleGroup(wordToggleGroup);
		RadioButton regexRadioBtn = new RadioButton("Regular expression");
		regexRadioBtn.setSelected(true);
		regexRadioBtn.setToggleGroup(wordToggleGroup);
		VBox vbox = new VBox(2,new Label("Options :"),new CheckBox("Case sensitive"),wordRadioBtn,regexRadioBtn);
		return vbox;
	}
	
	private Node directionPane() {
		ToggleGroup directionToggleGroup = new ToggleGroup();
		RadioButton forwardRadioBtn = new RadioButton("Forward");
		forwardRadioBtn.setSelected(true);
		forwardRadioBtn.setToggleGroup(directionToggleGroup);
		RadioButton backwardRadioBtn = new RadioButton("Backward");
		backwardRadioBtn.setToggleGroup(directionToggleGroup);
		VBox vbox = new VBox(4,new Label("Direction :"),forwardRadioBtn,backwardRadioBtn);
		return vbox;
	}
	
	private Node buttonPallete(FindReplaceType type) {
		HBox expane = new HBox();
		HBox.setHgrow(expane, Priority.ALWAYS);
		HBox hbox = new HBox(15);
		switch (type) {
		case FIND: hbox.getChildren().add(new Button("Find")); break;
		case REPLACE: hbox.getChildren().addAll(new Button("Replace"),new Button("Replace All")); break;
		}
		return new HBox(expane,hbox);
	}
	
	public void setFindText(String findText) {
		findTextField.setText(findText);
		findReplaceTextField.setText(findText);
	}
	
	public void show(FindReplaceType type) {
		if(type.equals(FindReplaceType.FIND))
			tabPane.getSelectionModel().select(0);
		else
			tabPane.getSelectionModel().select(1);
		show();
	}
}
