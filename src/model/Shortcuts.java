package model;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import notepadutils.Key;


public class Shortcuts extends Dialog<ButtonType>{
	private Border componentBorder = new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, new CornerRadii(10, 10, 10, 10,false), new BorderWidths(2)));
	public Shortcuts() {
		setTitle("Shortcuts");
		setDialogPane(getShortcutsDialogPane());
		setWidth(500);
	}
	
	private DialogPane getShortcutsDialogPane() {
		DialogPane dialogPane = new DialogPane();
		dialogPane.setContent(getContentNode());
		dialogPane.getButtonTypes().addAll(ButtonType.CLOSE);
		dialogPane.setPadding(new Insets(30,30,30,30));
		return dialogPane;
	}
	
	private Node getContentNode() {
		VBox vbox = new VBox(0);
		vbox.getChildren().addAll(getSearchBox(),contentGrid());
		vbox.setPrefWidth(500);
		vbox.setPadding(new Insets(20,20,20,20));
		vbox.setBorder(componentBorder);
		VBox.setMargin(vbox, new Insets(2,2,10,2));
		return vbox;
	}

	private Node getSearchBox() {
		TextField searchBox = new TextField();
		searchBox.setPromptText("Search");
		HBox.setHgrow(searchBox, Priority.ALWAYS);
		return searchBox;
	}
	
	private Node contentGrid() {
		GridPane grid = new GridPane();
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPrefWidth(400);
		grid.addRow(0,actionLabel("New File"),actionKeys(Key.CTRL, Key.N));
		grid.addRow(1,actionLabel("New Window"),actionKeys(Key.CTRL, Key.SHIFT, Key.N));
		grid.addRow(2,actionLabel("Open File"),actionKeys(Key.CTRL, Key.O));
		grid.addRow(3,actionLabel("Save File"),actionKeys(Key.CTRL, Key.S));
		grid.addRow(4,actionLabel("Save File As"),actionKeys(Key.CTRL, Key.SHIFT, Key.S));
		grid.addRow(5,actionLabel("Close File"),actionKeys(Key.CTRL, Key.W));
		grid.addRow(6,actionLabel("Print File"),actionKeys(Key.CTRL, Key.P));
		grid.addRow(7,actionLabel("Export File"),actionKeys(Key.CTRL, Key.E));
		grid.addRow(8,actionLabel("Exit File"),actionKeys(Key.CTRL, Key.N));
		grid.addRow(9,actionLabel("Undo"),actionKeys(Key.CTRL, Key.Z));
		grid.addRow(10,actionLabel("Redo"),actionKeys(Key.CTRL, Key.Y));
		grid.addRow(11,actionLabel("Cut"),actionKeys(Key.CTRL, Key.X));
		grid.addRow(12,actionLabel("Copy"),actionKeys(Key.CTRL, Key.C));
		grid.addRow(13,actionLabel("Paste"),actionKeys(Key.CTRL, Key.V));
		grid.addRow(14,actionLabel("Find"),actionKeys(Key.CTRL, Key.F));
		grid.addRow(15,actionLabel("Replace"),actionKeys(Key.CTRL, Key.R));
		grid.addRow(16,actionLabel("Select All"),actionKeys(Key.CTRL, Key.A));
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(grid);
		scrollPane.setPrefSize(600, 400);
		scrollPane.setPadding(new Insets(10,20,10,30));
		return scrollPane;
	}
	
	private Label actionLabel(String text) {
		Label label = new Label(text);
		label.setStyle("-fx-font-weight:bold;");
		return label;
	}
	
	private HBox actionKeys(Key... keys) {
		HBox hbox = new HBox(5);
		for(int i=0; i<keys.length; i++) {
			hbox.getChildren().add(createKeyboardKeys(keys[i]));
			if(i < keys.length-1)
				hbox.getChildren().add(createAddButton());
		}
		return hbox;
	}
	
	private Label createKeyboardKeys(Key key) {
		DropShadow dropShadow = new DropShadow();
		dropShadow.setBlurType(BlurType.GAUSSIAN); 
		dropShadow.setColor(Color.rgb(50, 50, 50, .8)); 
		dropShadow.setRadius(12); 
		dropShadow.setOffsetX(2); 
		dropShadow.setOffsetY(2); 
		dropShadow.setSpread(0);
		Label keyLabel = new Label(key.toString());
		keyLabel.setPadding(new Insets(5,7,5,7));
		keyLabel.setStyle("-fx-font-weight:bold;");
		keyLabel.setEffect(dropShadow);
		Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10, 10, 10, 10,false), new BorderWidths(1.4)));
		keyLabel.setBorder(border);
		return keyLabel;
	}

	private Label createAddButton() {
		Label label = new Label("+");
		label.setPadding(new Insets(5,7,5,7));
		label.setStyle("-fx-font-weight:bold;");
		return label;
	}
}
