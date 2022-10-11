package model;

import java.io.File;

import controller.MainPageController;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.notepad.NotepadOpener;
import notepadutils.Key;

public class HomePage {
	private ImageView logo;
	private MainPageController mainPageController;
	
	public HomePage(MainPageController mainPageController) {
		this.mainPageController = mainPageController;
		setLogo();
	}

	private void setLogo() {
		logo = new ImageView(new Image("/resources/images/notepad.png"));
		logo.setFitHeight(120);
		logo.setPreserveRatio(true);
	}

	public Node getContent() {
		VBox vbox = new VBox(50,logo,getGridPane(),getDropBox());
		vbox.setAlignment(Pos.CENTER);
		StackPane sp = new StackPane(vbox);
		return sp;
	}
	
	private Node getGridPane() {
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHalignment(HPos.RIGHT);
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(30);
		grid.setVgap(20);
		grid.getColumnConstraints().add(col1);
		Label newFileLink = getCustomLabel("Create new file");
		newFileLink.setOnMouseClicked(e->mainPageController.newMenuItem.fire());
		HBox newFileShortcut = getShortCutHBox(Key.CTRL,Key.N);
		Label openFileLink = getCustomLabel("Open File");
		openFileLink.setOnMouseClicked(e->mainPageController.openMenuItem.fire());
		HBox openFileShortcut = getShortCutHBox(Key.CTRL,Key.O);
		Label showAllCommandsLink = getCustomLabel("Show All Commands");
		showAllCommandsLink.setOnMouseClicked(e->mainPageController.shortcutMenuItem.fire());
		HBox showAllShortcut = getShortCutHBox(Key.CTRL,Key.ALT,Key.S);
		grid.addRow(0, newFileLink, newFileShortcut);
		grid.addRow(1, openFileLink, openFileShortcut);
		grid.addRow(2, showAllCommandsLink, showAllShortcut);
		return grid;
	}

	private Label getCustomLabel(String labelText) {
		String mouseEnteredCSS = "-fx-font-size:19;-fx-font-weight:bold;-fx-text-fill:blue";
		String mouseExitedCSS = "-fx-font-size:19;-fx-font-weight:bold;-fx-text-fill:black";
		Label label = new Label(labelText);
		label.setStyle(mouseExitedCSS);
		label.setOnMouseEntered(e->{
			label.setUnderline(true);
			label.setStyle(mouseEnteredCSS);
		});
		label.setOnMouseExited(e->{
			label.setUnderline(false);
			label.setStyle(mouseExitedCSS);
		});
		return label;
	}
	
	private HBox getShortCutHBox(Key... keys) {
		String plusTextStyle = "-fx-font-weight:bold;-fx-font-size:17;";
		String keyStyle = "-fx-font-weight:bold;-fx-font-size:17;-fx-text-fill:black";
		DropShadow dropShadow = new DropShadow();
		dropShadow.setBlurType(BlurType.GAUSSIAN); 
		dropShadow.setColor(Color.rgb(50, 50, 50, .8)); 
		dropShadow.setRadius(12); 
		dropShadow.setOffsetX(2); 
		dropShadow.setOffsetY(2); 
		dropShadow.setSpread(0);
		CornerRadii cornerRadii = new CornerRadii(10, 10, 10, 10,false);
		BorderWidths borderWidths = new BorderWidths(1.4);
		BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, cornerRadii, borderWidths);
		Border componentBorder = new Border(borderStroke);
		Insets insets = new Insets(5,10,5,10);
		HBox hbox = new HBox(7);
		for(int i=0; i<keys.length; i++) {
			Label keyLabel = new Label(keys[i].toString());
			keyLabel.setPadding(insets);
			keyLabel.setStyle(keyStyle);
			keyLabel.setEffect(dropShadow);
			keyLabel.setBorder(componentBorder);
			hbox.getChildren().add(keyLabel);
			if(i!= keys.length-1) {
				Label plus = new Label("+");
				plus.setPadding(insets);
				plus.setStyle(plusTextStyle);
				hbox.getChildren().add(plus);
			}
		}
		return hbox;
	}
	
	private Node getDropBox() {
		Border componentBorder = new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.DASHED, new CornerRadii(10, 10, 10, 10,false), new BorderWidths(5)));
		Label label = new Label("Drop the file here to open");
		label.setStyle("-fx-font-size:18;-fx-font-weight:bold;-fx-text-fill:black");
		ImageView imageView = new ImageView(new Image("/resources/images/dragndrop.png"));
		imageView.setFitHeight(50);
		imageView.setPreserveRatio(true);
		VBox vbox = new VBox(10,label, imageView);
		vbox.setAlignment(Pos.CENTER);
		StackPane dropBox = new StackPane(vbox);
		dropBox.setBorder(componentBorder);
		dropBox.setPrefSize(500, 220);
		dropBox.setMaxSize(500, 220);
		dropBox.setPadding(new Insets(20,20,20,20));
		dropBox.setOnDragOver(e->{
			
		});
		dropBox.setOnDragOver(e->dragOverAction(e,dropBox));
		dropBox.setOnDragDropped(e->dragDroppedAction(e));
		return dropBox;
	}

	private void dragOverAction(DragEvent event, Node node) {
		if (event.getGestureSource() != node && event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
	}

	private void dragDroppedAction(DragEvent event) {
		Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
        	success = true;
        	File file1 = new File(db.getFiles().get(0).getAbsolutePath());
        	mainPageController.getAppMain().mainStage.setMaximized(true);
        	NotepadOpener.openFile(mainPageController, file1);
        }
        event.setDropCompleted(success);
        event.consume();
	}
}