package model;

import java.util.EnumSet;
import java.util.Optional;

import configuration.Configuration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Window;
import notepadutils.FontConverter;

public class FontDialog extends Dialog<ButtonType>{
	private ComboBox<String> fontFamilyComboBox;
	private ComboBox<FontWeight> fontWeightComboBox;
	private ComboBox<FontPosture> fontPostureComboBox;
	private ComboBox<String> fontSizeComboBox;
	private String configFontString;
	private Text text;
	private boolean isFontValid;
	private ObservableList<String> fontFamilyList;
	private Font appliedFont;
	private int choosenFontSize=0;

 	private static enum FontSizeChange{
		INCREMENT, DECREMENT;
	}

	public FontDialog(Window owner) {
		configFontString = Configuration.getFontString();
		fontFamilyList = FXCollections.observableArrayList(Font.getFamilies());
		initOwner(owner);
		setTitle("Font Settings");
		setDialogPane(getFontsDialogPane());
		setResizable(true);
		isFontValid=false;
		setOnShown(e->validateFont());
		setOnCloseRequest(e->{
			isFontValid = validateFont();
		});
	}

	private DialogPane getFontsDialogPane() {
		DialogPane dialogPane = new DialogPane();
		dialogPane.setContent(getFontsDialogPaneContent());
		dialogPane.getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
		dialogPane.setPadding(new Insets(20,35,20,35));
		dialogPane.setPrefWidth(700);
		return dialogPane;
	}

	private Node getFontsDialogPaneContent() {
		GridPane grid = new GridPane();
		grid.setHgap(30);
		grid.setVgap(20);		
		grid.addRow(0, getGenerateGridLabel("Font Family :"),getFontFamilyNode());
		grid.addRow(1, getGenerateGridLabel("Font Weight :"),getFontWeightNode());
		grid.addRow(2, getGenerateGridLabel("Font Posture :"),getFontPostureNode());
		grid.addRow(3, getGenerateGridLabel("Font Size :"),getFontSizeNode());
		VBox vbox = new VBox(30,getSampleTextPane(),getInfoPane(),grid);
		VBox.setMargin(vbox, new Insets(20, 10, 30, 10));
		return vbox;
	}

	private Node getInfoPane() {
		Label label1  = new Label("*");
		label1.setStyle("-fx-font-weight:bold;-fx-font-size:18;-fx-text-fill:red;");
		Label label2  = new Label("Note that sample text does not updates for combinations that are not available.");
		label2.setWrapText(true);
		label2.setStyle("-fx-font-weight:bold;-fx-text-fill:blue;");
		return new HBox(5,label1,label2);
	}

	private Node getSampleTextPane() {
		CornerRadii cornerRadii = new CornerRadii(8, 8, 8, 8,false);
		BorderWidths borderWidths = new BorderWidths(1.6);
		BorderStroke borderStroke = new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, cornerRadii, borderWidths);
		Border border = new Border(borderStroke);
		text = new Text("Sample Text");
		StackPane stackPane = new StackPane(text);
		stackPane.setPrefHeight(300);
		stackPane.setMaxHeight(300);
		stackPane.setBorder(border);
		return stackPane;
	}

	private Label getGenerateGridLabel(String string) {
		Label label = new Label(string);
		label.setStyle("-fx-font-weight:bold; -fx-font-size:16;");
		return label;
	}

	private Node getFontFamilyNode() {
		fontFamilyComboBox = new ComboBox<>();
		fontFamilyComboBox.setEditable(true);
		fontFamilyComboBox.setPromptText("-font family-");
		fontFamilyComboBox.setItems(fontFamilyList);
		int i = fontFamilyList.indexOf(FontConverter.getFontFamilyFromFontString(configFontString));
		fontFamilyComboBox.getSelectionModel().select(i);
		fontFamilyComboBox.setOnAction(e->validateFont());
		return fontFamilyComboBox;
	}

	private Node getFontWeightNode(){
		ObservableList<FontWeight> list = FXCollections.observableArrayList();
		EnumSet.allOf(FontWeight.class).forEach(fontWeight -> list.add(fontWeight));
		fontWeightComboBox = new ComboBox<>();
		fontWeightComboBox.setPromptText("-font weight-");
		fontWeightComboBox.setItems(list);
		int i = list.indexOf(FontConverter.getFontWeightFromFontString(configFontString));
		fontWeightComboBox.getSelectionModel().select(i);
		fontWeightComboBox.setOnAction(e->validateFont());
		fontWeightComboBox.prefWidthProperty().bind(fontFamilyComboBox.widthProperty());
		return fontWeightComboBox;
	}

	private Node getFontPostureNode() {
		ObservableList<FontPosture> list = FXCollections.observableArrayList();
		EnumSet.allOf(FontPosture.class).forEach(fontPosture -> list.add(fontPosture));
		fontPostureComboBox = new ComboBox<>();
		fontPostureComboBox.setPromptText("-font Posture-");
		fontPostureComboBox.setItems(list);
		int i = list.indexOf(FontConverter.getFontPostureFromFontString(configFontString));
		fontPostureComboBox.getSelectionModel().select(i);
		fontPostureComboBox.setOnAction(e->validateFont());
		fontPostureComboBox.prefWidthProperty().bind(fontFamilyComboBox.widthProperty());
		return fontPostureComboBox;
	}

	private Node getFontSizeNode() {
		ObservableList<String> list = FXCollections.observableArrayList();
		int fontSizeArr[] = new int[] {8,10,12,14,16,18,20,22,24,26,28,36,48,72};
		for(int i:fontSizeArr) {
			list.add(String.valueOf(i));
		}
		Button minusBtn = new Button("-");
		Button plusBtn = new Button("+");
		minusBtn.setStyle("-fx-font-weight:bold");
		plusBtn.setStyle("-fx-font-weight:bold");
		minusBtn.setOnAction(e->plusMinusFont(FontSizeChange.DECREMENT));
		plusBtn.setOnAction(e->plusMinusFont(FontSizeChange.INCREMENT));
		fontSizeComboBox = new ComboBox<>();
		fontSizeComboBox.setPromptText("-font size-");
		fontSizeComboBox.setEditable(true);
		fontSizeComboBox.setItems(list);
		int i = list.indexOf(FontConverter.getFontSizeFromFontString(configFontString));
		fontSizeComboBox.getSelectionModel().select(i);
		HBox hbox = new HBox(0,minusBtn,fontSizeComboBox,plusBtn);
//		choosenFontSize = Integer.parseInt(fontSizeComboBox.getSelectionModel().getSelectedItem());
		fontSizeComboBox.setOnAction(e->validateFont());
		return hbox;
	}

	private void plusMinusFont(FontSizeChange changer) {
		String currentFontSize = fontSizeComboBox.getValue();
		int fontSize=0;
		try {
			fontSize = Integer.parseInt(currentFontSize);
		}
		catch (Exception e) {
			fontSize = choosenFontSize;
		}
		switch (changer) {
		case INCREMENT: fontSize++; break;
		case DECREMENT: fontSize--; break;
		}
		fontSizeComboBox.setValue(String.valueOf(fontSize));
	}
	
	private boolean validateFont() {
		int size=0;
		String family = fontFamilyComboBox.getValue();
		FontWeight weight = fontWeightComboBox.getValue();
		FontPosture posture = fontPostureComboBox.getValue();
		try {			
			size = Integer.parseInt(fontSizeComboBox.getValue().trim());
		}
		catch (Exception e) {
			return false;
		}
		if(size < 1 || size >80)
			return false;
		if(!fontFamilyList.contains(family))
			return false;
		appliedFont = Font.font(family,weight,posture,size);
		text.setFont(appliedFont);
		Configuration.setFontString(FontConverter.getFontStringFromFontComponents(family, weight, posture,size));
		return true;
	}

	public Optional<Font> getChoosenFont() {
		Optional<ButtonType> result = showAndWait();
		if(!result.isPresent() || result.get().equals(ButtonType.CANCEL)) {
			return Optional.ofNullable(null);
		}
		if(!isFontValid)
			return Optional.ofNullable(null);
		return Optional.ofNullable(appliedFont);
	}

}
