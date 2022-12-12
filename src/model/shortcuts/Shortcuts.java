package model.shortcuts;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class Shortcuts extends Dialog<ButtonType>{
	private static enum ShortcutTab{
		FILE, EDIT, FORMAT, VIEW, PREFERENCE, HElP;
	}
	private Border componentBorder = new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, new CornerRadii(10, 10, 10, 10,false), new BorderWidths(2)));
	public Shortcuts() {
		setTitle("Shortcuts");
		setDialogPane(getShortcutsDialogPane());
		setWidth(500);
	}

	private DialogPane getShortcutsDialogPane() {
		DialogPane dialogPane = new DialogPane();
		dialogPane.setContent(getShortCutTabPane());
		dialogPane.getButtonTypes().addAll(ButtonType.CLOSE);
		dialogPane.setPadding(new Insets(30,30,30,30));
		return dialogPane;
	}

	private TabPane getShortCutTabPane() {
		Tab fileTab = new Tab("File Shortcuts",getContentNode(ShortcutTab.FILE));
		fileTab.setClosable(false);
		Tab editTab = new Tab("Edit Shortcuts",getContentNode(ShortcutTab.EDIT));
		editTab.setClosable(false);
		Tab formatTab = new Tab("Format Shortcuts",getContentNode(ShortcutTab.FORMAT));
		formatTab.setClosable(false);
		Tab viewTab = new Tab("View Shortcuts",getContentNode(ShortcutTab.VIEW));
		viewTab.setClosable(false);
		Tab preferenceTab = new Tab("Preference Shortcuts",getContentNode(ShortcutTab.PREFERENCE));
		preferenceTab.setClosable(false);
		Tab helpTab = new Tab("Help Shortcuts",getContentNode(ShortcutTab.HElP));
		helpTab.setClosable(false);
		TabPane tabPane = new TabPane(fileTab,editTab,formatTab,viewTab,preferenceTab,helpTab);
		tabPane.setBorder(componentBorder);
		return tabPane;
	}

	private Node getContentNode(ShortcutTab tab) {
		VBox vbox = new VBox(0);
		vbox.getChildren().addAll(getSearchBox(),contentGrid(tab));
		vbox.setPrefWidth(500);
		vbox.setPadding(new Insets(20,20,20,20));
		VBox.setMargin(vbox, new Insets(2,2,10,2));
		return vbox;
	}

	private Node getSearchBox() {
		TextField searchBox = new TextField();
		searchBox.setPromptText("Search");
		HBox.setHgrow(searchBox, Priority.ALWAYS);
		return searchBox;
	}

	private Node contentGrid(ShortcutTab tab) {
		ColumnConstraints col1 = new ColumnConstraints(150,150,150);
		GridPane grid = new GridPane();
		grid.getColumnConstraints().add(col1);
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPrefWidth(400);
		switch (tab) {
		case FILE: FileMenuShortcuts.getShorcuts(grid); break;
		case EDIT: EditMenuShortcuts.getShorcuts(grid); break;
		case FORMAT: FormatMenuShortcuts.getShorcuts(grid); break;
		case VIEW: ViewMenuShortcuts.getShorcuts(grid); break;
		case PREFERENCE: PreferenceMenuShortcuts.getShorcuts(grid); break;
		case HElP: HelpMenuShortcuts.getShorcuts(grid); break;
		}
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(grid);
		scrollPane.setPrefSize(600, 400);
		scrollPane.setPadding(new Insets(10,20,10,30));
		return scrollPane;
	}
}
