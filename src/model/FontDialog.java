package model;

import java.util.EnumSet;
import java.util.Optional;

import configuration.Configuration;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
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
import notepadutils.CustomAlert;
import notepadutils.FontConverter;

public class FontDialog extends Dialog<ButtonType>{
	private final int MAX_FONT_SIZE = 80;
	private Window owner;
	private ComboBox<String> fontFamilyComboBox;
	private ComboBox<FontWeight> fontWeightComboBox;
	private ComboBox<FontPosture> fontPostureComboBox;
	private ComboBox<String> fontSizeComboBox;
	private String configFontString;
	private Text text;
	private ObservableList<String> fontFamilyList;
	private String appliedFontString;
	private Font appliedFont;
	private int choosenFontSize=0;

 	private static enum FontSizeChange{
		INCREMENT, DECREMENT;
	}

	public FontDialog(Window owner) {
		configFontString = Configuration.getFontString();
		fontFamilyList = FXCollections.observableArrayList(Font.getFamilies());
		this.owner = owner;
		initOwner(owner);
		setTitle("Font Settings");
		setDialogPane(getFontsDialogPane());
		setResizable(true);
		final Button applyButton = (Button) getDialogPane().lookupButton(ButtonType.APPLY);
		applyButton.addEventFilter(ActionEvent.ACTION, (event) -> {
			  if (!validateFont()) {
			    event.consume();
			  }
			});
		setOnShown(e->validateFont());
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
//		fontFamilyComboBox.setEditable(true);
		fontFamilyComboBox.setPromptText("-font family-");
		fontFamilyComboBox.setItems(fontFamilyList);
		int i = fontFamilyList.indexOf(FontConverter.getFontFamilyFromFontString(configFontString));
		fontFamilyComboBox.getSelectionModel().select(i);
		fontFamilyComboBox.setOnAction(e->validateFont());
//		fontFamilyComboBox.setOnKeyPressed(e -> {
//		    if (e.getCode() == KeyCode.ENTER) {
//		    	System.out.println(fontFamilyComboBox.getValue());
////		        validateFont(e);
//		    }
//		});
//		fontFamilyComboBox.setOn
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
		int i = list.indexOf(FontConverter.getFontPostureFromFontString(configFontString));
		fontPostureComboBox = new ComboBox<>();
		fontPostureComboBox.setPromptText("-font Posture-");
		fontPostureComboBox.setItems(list);
		fontPostureComboBox.getSelectionModel().select(i);
		fontPostureComboBox.setOnAction(e->validateFont());
		fontPostureComboBox.prefWidthProperty().bind(fontFamilyComboBox.widthProperty());
		return fontPostureComboBox;
	}

	private Node getFontSizeNode() {
		ObservableList<String> list = FXCollections.observableArrayList();
		int fontSizeArr[] = new int[] {8,10,12,14,16,18,20,22,24,26,28,32,36,40,44,48,52,60,72,80};
		for(int i:fontSizeArr) {
			list.add(String.valueOf(i));
		}
		int i = list.indexOf(FontConverter.getFontSizeFromFontString(configFontString));
		Button minusBtn = new Button("-");
		Button plusBtn = new Button("+");
		minusBtn.setStyle("-fx-font-weight:bold");
		plusBtn.setStyle("-fx-font-weight:bold");
		minusBtn.setOnAction(e->changeFontSize(FontSizeChange.DECREMENT));
		plusBtn.setOnAction(e->changeFontSize(FontSizeChange.INCREMENT));
		fontSizeComboBox = new ComboBox<>();
		fontSizeComboBox.setPromptText("-font size-");
//		fontSizeComboBox.setEditable(true);
		fontSizeComboBox.setItems(list);
		if(i != -1)
			fontSizeComboBox.getSelectionModel().select(i);
		else
			fontSizeComboBox.setValue(FontConverter.getFontSizeFromFontString(configFontString));
		HBox hbox = new HBox(0,minusBtn,fontSizeComboBox,plusBtn);
		choosenFontSize = Integer.parseInt(fontSizeComboBox.getValue());
		fontSizeComboBox.setOnAction(e->validateFont());
//		fontSizeComboBox.getEditor().setOnKeyPressed(e->{
//			System.out.println(",dnvkjfhoi;agheioh");
//			if(e.getCode() == KeyCode.ENTER) {
////				fontSizeComboBox.setValue(fontSizeComboBox.getEditor().getText());
//				System.out.println("Valllsakbweih");
//				validateFontSize();
//			}
//		});
		return hbox;
	}

	private void changeFontSize(FontSizeChange changer) {
		String currentFontSize = fontSizeComboBox.getValue();
		int fontSize=0;
		try {
			fontSize = Integer.parseInt(currentFontSize);
		}
		catch (Exception e) {
			fontSize = choosenFontSize;
		}
		switch (changer) {
		case INCREMENT:
			if(fontSize >= MAX_FONT_SIZE)
				return;
			fontSize++; break;
		case DECREMENT:
			if(fontSize <= 1)
				return;
			fontSize--; break;
		}
		choosenFontSize = fontSize;
		fontSizeComboBox.setValue(String.valueOf(fontSize));
	}

	private Optional<CustomAlert> validateFontSize() {
		int size=0;
		AlertType alertType = AlertType.ERROR;
		String titleText="";
		String headerText="";
		String contentText="";
		String fontSizeStrValue = fontSizeComboBox.getValue().trim();
		if(fontSizeStrValue == null || fontSizeStrValue.isEmpty()) {
			titleText = "Invalid font size";
			headerText = "Font size is required. Font size cannot be empty";
			return Optional.ofNullable(new CustomAlert(alertType, titleText, headerText, contentText, owner));
		}
		try {
			size = Integer.parseInt(fontSizeStrValue);
			if(size < 1 || size > MAX_FONT_SIZE) {
				titleText = "Font size out of range";
				headerText = String.format("Font size must be in the range 1 to %d.", MAX_FONT_SIZE);
				return Optional.ofNullable(new CustomAlert(alertType, titleText, headerText, contentText, owner));
			}
		}
		catch (Exception e) {
			titleText = "Invalid font size";
			headerText = "Invalid value for font size";
			contentText = "Font size must be a number";
			return Optional.ofNullable(new CustomAlert(alertType, titleText, headerText, contentText, owner));
		}
		choosenFontSize = size;
		return Optional.ofNullable(null);
	}

	public boolean validateFont() {
		Optional<CustomAlert> validFontSize = validateFontSize();
		if(validFontSize.isPresent()) {
			validFontSize.get().showAndWait();
			return false;
		}
		String family = fontFamilyComboBox.getValue();
		FontWeight weight = fontWeightComboBox.getValue();
		FontPosture posture = fontPostureComboBox.getValue();
		int fontSize = Integer.parseInt(fontSizeComboBox.getValue().trim());
		appliedFontString = FontConverter.getFontStringFromFontComponents(family, weight, posture,fontSize);
		appliedFont = Font.font(family,weight,posture,fontSize);
		text.setFont(appliedFont);
		return true;
	}

	public String getAppliedFontString() {
		return appliedFontString;
	}

	public Font getAppliedFont() {
		return appliedFont;
	}

}
