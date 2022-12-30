package model;

import controller.MainPageController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import model.notepad.Notepad;

public class StatusBar extends HBox{
	private Notepad notepad;
	private Label lengthCountLabel;
	private Label linesCountLabel;
	private Label wordsCountLabel;
	private Label currentLineLabel;
	private Label currentColLabel;
	private Label currentPosLabel;
	private CornerRadii cornerRadii = new CornerRadii(0, 0, 0, 0,false);
	private BorderWidths rightBorderWidths = new BorderWidths(0,2,0,0);
	private BorderStroke rightBorderStroke = new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, cornerRadii, rightBorderWidths);
	private Border rightBorder = new Border(rightBorderStroke);
	private BorderWidths leftBorderWidths = new BorderWidths(0,0,0,2);
	private BorderStroke leftBorderStroke = new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, cornerRadii, leftBorderWidths);
	private Border leftBorder = new Border(leftBorderStroke);
	private Insets rightInsets = new Insets(0,12,0,8);
	private Insets leftInsets = new Insets(0,8,0,12);

	public StatusBar(MainPageController mainPageController) {
		this.notepad = mainPageController.getNotepad();
		getStyleClass().add("status-bar");
		HBox expane = new HBox(10);
		HBox.setHgrow(expane, Priority.ALWAYS);
		getChildren().addAll(getLengthCountHBox(),getLinesCountHBox(),getWordsCountHBox(),expane);
		getChildren().addAll(getCurrentLineHBox(),getCurrentColHBox(),getCurrentPosHBox(),newLineHBox()/*,encodingHBox()*/);
		setPadding(new Insets(6,20,6,20));
		notepad.textProperty().addListener((observable, oldValue, newValue) -> updateStatus(newValue));
		notepad.caretPositionProperty().addListener((observable, oldValue, newValue) -> updateCaret(newValue));
	}

	private void updateCaret(Number newValue) {
		int caretPosition = notepad.getCaretPosition();
		String prefixText = notepad.getText().substring(0,caretPosition);
		int currentLine = countLines(prefixText);
		int currentCol = getCurrCaretCol(prefixText);
		if(prefixText.length() == 0)
			currentLine=1;
		currentLineLabel.setText(String.valueOf(currentLine));
		currentColLabel.setText(String.valueOf(currentCol));
		currentPosLabel.setText(String.valueOf(prefixText.length()+1));
	}

	private int getCurrCaretCol(String str) {
		final int OUT=0;
		final int IN=1;
        int state = OUT;
        int lc = 0;
        int i = 0;
        while (i < str.length()) {
        	if (str.charAt(i) == '\n') {
        		state = IN;
                lc=i+1;
        	}
            else if (state == OUT) {
            	state = OUT;
            }
            ++i;
        }        
        return str.substring(lc).length()+1;
	}

	private void updateStatus(String newValue) {
//		String splitText = (String) notepad.getProperties().get("line.separator");
		int totalLines = countLines(newValue);
		int totalWords = countWords(newValue);
		lengthCountLabel.setText(String.valueOf(newValue.length()));
		linesCountLabel.setText(String.valueOf(totalLines));
		wordsCountLabel.setText(String.valueOf(totalWords));
	}

	private int countLines(String str){
		final int OUT=0;
		final int IN=1;
        int state = OUT;
        int lc = 0;
        int i = 0;
        while (i < str.length()) {
        	if (str.charAt(i) == '\n') {
        		state = IN;
                ++lc;
        	}
            else if (state == OUT) {
            	state = OUT;
            }
            ++i;
        }
        if(str.length() == 0)
        	lc=-1;
        return ++lc;
    }

	private int countWords(String str) {
		final int OUT=0;
		final int IN=1;
        int state = OUT;
        int wc = 0;  // word count
        int i = 0;
        while (i < str.length()){
            if (str.charAt(i) == ' ' || str.charAt(i) == '\n' || str.charAt(i) == '\t')
                state = OUT;
            else if (state == OUT){
                state = IN;
                ++wc;
            }
            ++i;
        }
        return wc;
    }

	private Node getLengthCountHBox() {
		lengthCountLabel = new Label("0");
		HBox hBox = new HBox(new Label("Total Length: "),lengthCountLabel);
		hBox.setBorder(rightBorder);
		hBox.setPadding(rightInsets);
		return hBox;
	}

	private Node getLinesCountHBox() {
		linesCountLabel = new Label("0");
		HBox hBox = new HBox(new Label("Total Lines: "),linesCountLabel);
		hBox.setBorder(rightBorder);
		hBox.setPadding(rightInsets);
		return hBox;
	}

	private Node getWordsCountHBox() {
		wordsCountLabel = new Label("0");
		HBox hBox = new HBox(new Label("Words Count: "),wordsCountLabel);
		hBox.setBorder(rightBorder);
		hBox.setPadding(rightInsets);
		return hBox;
	}

	private HBox getCurrentLineHBox() {
		currentLineLabel = new Label("1");
		HBox hBox = new HBox(new Label("Line: "),currentLineLabel);
		hBox.setBorder(leftBorder);
		hBox.setPadding(leftInsets);
		return hBox;
	}

	private HBox getCurrentColHBox() {
		currentColLabel = new Label("1");
		HBox hBox = new HBox(new Label("Col: "),currentColLabel);
		hBox.setBorder(leftBorder);
		hBox.setPadding(leftInsets);
		return hBox;
	}

	private HBox getCurrentPosHBox() {
		currentPosLabel = new Label("1");
		HBox hBox = new HBox(new Label("Pos: "),currentPosLabel);
		hBox.setBorder(leftBorder);
		hBox.setPadding(leftInsets);
		return hBox;
	}

	private HBox newLineHBox() {
		String lineSeparator = "";
		if(notepad.getProperties().containsKey("line.separator")) {
			String value = (String) notepad.getProperties().get("line.separator");
			switch (value) {
			case "\r\n": lineSeparator = "CRLF"; break;
			case "\r": lineSeparator = "CR"; break;
			case "\n": lineSeparator = "LF"; break;
			}
		}
		else
			lineSeparator = (String)System.getProperty("line.separator");
		HBox hBox = new HBox(new Label(lineSeparator));
		hBox.setBorder(leftBorder);
		hBox.setPadding(leftInsets);
		return hBox;
	}
}