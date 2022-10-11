package model;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Window;

public class About extends Dialog<ButtonType>{
	private Border componentBorder = new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, new CornerRadii(10, 10, 10, 10,false), new BorderWidths(2)));
	public About(Window owner)
	{
        initOwner(owner);
        setTitle("About");
        DialogPane dialogPane = getAboutDialogPane();
        dialogPane.setContent(getDialogPaneContent());
        dialogPane.getButtonTypes().addAll(ButtonType.CLOSE);
        setDialogPane(dialogPane);
	}

	private DialogPane getAboutDialogPane() {
		DialogPane dialogPane = new DialogPane();
		dialogPane.setPadding(new Insets(30,30,20,30));
		return dialogPane;
	}

	private Node getDialogPaneContent() {
        Image image = new Image("resources/images/appicon.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);
        StackPane sp1 = new StackPane(imageView);
        
        Label compName = new Label();
        compName.setText("Indeed Notepad");
        compName.setFont(Font.font ("Verdana",FontWeight.BOLD, 20));
        StackPane sp2 = new StackPane(compName);
        
        Label version = new Label();
        version.setText("App Version :");
        version.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
        Label versionVal = new Label();
        versionVal.setText("1.0.0");
        versionVal.setFont(Font.font ("Verdana", 18));
        
        Label developerInfo = new Label();
        developerInfo.setText("Developed By :");
        developerInfo.setFont(Font.font ("Verdana", FontWeight.BOLD, 15));
        Label developerName = new Label();
        developerName.setText("Aman Kumar");
        developerName.setFont(Font.font ("Verdana", FontWeight.BOLD, 17));
        
        Label email = new Label("Email :");
        email.setFont(Font.font ("Verdana", FontWeight.BOLD, 15 ));
        Hyperlink emailVal = new Hyperlink("amankumar.m99@gmail.com");
        emailVal.setFont(Font.font ("Verdana", 17));        
        
        GridPane gpan = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHalignment(HPos.RIGHT);
        gpan.getColumnConstraints().add(col1);
        gpan.setHgap(20);
        gpan.setVgap(18);
        gpan.addRow(0,version,versionVal);
        gpan.addRow(1,developerInfo,developerName);
        gpan.addRow(2,email,emailVal);
        gpan.setPadding(new Insets(20,20,20,20));
        gpan.setBorder(componentBorder);
        
        Label copyright = new Label();
        copyright.setText("Copyright by Indeed Coder \u00a92021 All Rights Reserved.");
        copyright.setFont(Font.font ("Verdana", 14));
        VBox vbx = new VBox(30);
        vbx.getChildren().addAll(sp1,sp2,gpan/*,new StackPane(copyright)*/);
        vbx.setPadding(new Insets(20,20,20,20));
        VBox.setMargin(vbx, new Insets(5,15,5,5));
        vbx.setBorder(componentBorder);
        return vbx;
	}
}
