package model;

import java.io.File;

import configuration.Configuration;
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
import javafx.scene.shape.Rectangle;
import model.iconpack.IconpackType;
import model.notepad.NotepadOpener;
import notepadutils.Key;

public class HomePage {

	private Label applogo;
	private Label dragndropImg;
	private MainPageController mainPageController;
	private StackPane homeScreen;

	public HomePage(MainPageController mainPageController) {
		this.mainPageController = mainPageController;
		applogo = new Label();
		dragndropImg = new Label();
		setHomeScreen();
		setUpdateIcons();
	}

	public void setUpdateIcons() {
		String type = Configuration.getIconpackType().toString().toLowerCase();
		applogo.setGraphic(getIconGraphic("/resources/images/"+type+"/home/notepad.png", 120));
		dragndropImg.setGraphic(getIconGraphic("/resources/images/"+type+"/home/dragndrop.png", 50));
		VBox vbox = (VBox)homeScreen.getChildren().get(0);
		vbox.getChildren().set(1,getGridPane());
	}

	private ImageView getIconGraphic(String path, int height) {
		ImageView imageView = new ImageView(new Image(path));
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(height);
		return imageView;
	}

	private void setHomeScreen() {
		VBox vbox = new VBox(50,applogo,getGridPane(),getDropBox());
		vbox.setAlignment(Pos.CENTER);
		StackPane sp = new StackPane(vbox);
		this.homeScreen = sp;
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
		Label label = new Label(labelText);
		label.getStyleClass().add("homepage-shortcut-label");
		return label;
	}

	private HBox getShortCutHBox(Key... keys) {
		String plusTextStyle = "-fx-font-weight:bold;-fx-font-size:17;";
		Insets insets = new Insets(5,10,5,10);
		HBox hbox = new HBox(7);
		for(int i=0; i<keys.length; i++) {
			hbox.getChildren().add(getKeyImage(keys[i], insets));			
			if(i!= keys.length-1) {
				Label plus = new Label("+");
				plus.setPadding(insets);
				plus.setStyle(plusTextStyle);
				hbox.getChildren().add(plus);
			}
		}
		return hbox;
	}

	private Node getKeyImage(Key key, Insets insets) {
		IconpackType type = Configuration.getIconpackType();
		String textColor = "";
		StackPane root = new StackPane();
		int rectWidth = 75;
		int rectHeight = 37;
		DropShadow dropShadow = new DropShadow();
		dropShadow.setBlurType(BlurType.GAUSSIAN); 
		dropShadow.setColor(Color.rgb(50, 50, 50, .8)); 
		dropShadow.setRadius(15); 
		dropShadow.setOffsetX(2); 
		dropShadow.setOffsetY(2); 
		dropShadow.setSpread(0);
		CornerRadii cornerRadii = new CornerRadii(10, 10, 10, 10,false);
		BorderWidths borderWidths = new BorderWidths(2);
		BorderStroke borderStroke = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, cornerRadii, borderWidths);
		
		Rectangle rect = new Rectangle();
		rect.setX(150.0f);
		rect.setY(75.0f);
		rect.setWidth(rectWidth);
		rect.setHeight(rectHeight);
		rect.setArcHeight(11);
		rect.setArcWidth(11);
//		rect.setFill(Color.web("#3d3d3d"));
//		rect.setStroke(Color.BLACK);
//		rect.setStrokeWidth(2);
//		rect.setEffect(dropShadow);
		Label label = new Label(key.toString());
//		label.prefWidth(20);
//		label.prefHeight(10);
		switch(type) {
		case COLOR: textColor = "blue"; rect.setFill(Color.web("#b6e7f2"));borderStroke = new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, cornerRadii, borderWidths); break;
		case FILL: textColor = "white"; rect.setFill(Color.web("#3d3d3d")); break;
		case OUTLINE: textColor = "black"; rect.setFill(Color.TRANSPARENT); break;
		}
		Border border = new Border(borderStroke);
		label.setStyle(String.format("-fx-text-fill:%s;-fx-font-weight:bold", textColor).toString());
//		label.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
		root.setBorder(border);
		root.setEffect(dropShadow);
		root.getChildren().addAll(rect, label);
		return root;
	}

	/*
	private Label getOutlineKey(Key key, Insets insets) {
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
		Border border = new Border(borderStroke);
		Label keyLabel = new Label(key.toString());
		keyLabel.setPadding(insets);
		keyLabel.setStyle(keyStyle);
		keyLabel.setEffect(dropShadow);
		keyLabel.setBorder(border);
		return keyLabel;
	}
	*/

	private Node getDropBox() {
		Border componentBorder = new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.DASHED, new CornerRadii(10, 10, 10, 10,false), new BorderWidths(5)));
		Label label = new Label("Drop the file here to open");
		label.getStyleClass().add("dropbox-label");
		VBox vbox = new VBox(10,label,dragndropImg);
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
        	mainPageController.getAppMain().getMainStage().setMaximized(true);
        	NotepadOpener.openFile(mainPageController, file1);
        }
        event.setDropCompleted(success);
        event.consume();
	}

	public StackPane getHomeScreen() {
		return homeScreen;
	}
}