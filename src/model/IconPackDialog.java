package model;

import java.util.Optional;

import configuration.AppStaticData;
import configuration.Configuration;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import model.iconpack.IconpackType;

public class IconPackDialog extends Dialog<ButtonType>{
	private ToggleGroup iconPackToggleGroup = new ToggleGroup();
	
	public IconPackDialog(Window owner) {
		initOwner(owner);
		setTitle("Icon packs");
		setDialogPane(getIconPackDialogPane());
	}

	private DialogPane getIconPackDialogPane() {
		DialogPane dialogPane = new DialogPane();
		dialogPane.setContent(getIconPackDialogPaneContent());
		dialogPane.getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
		dialogPane.setPadding(new Insets(20,35,20,35));
		return dialogPane;
	}

	private Node getIconPackDialogPaneContent() {
		RadioButton colorRadio = getRadio(IconpackType.COLOR);
		RadioButton fillRadio = getRadio(IconpackType.FILL);
		RadioButton outlineRadio = getRadio(IconpackType.OUTLINE);
		iconPackToggleGroup.getToggles().addAll(colorRadio,fillRadio,outlineRadio);
		VBox vbox = new VBox(30,colorRadio,fillRadio, outlineRadio);
		vbox.setPadding(new Insets(30,30,30,20));
		vbox.setBorder(AppStaticData.getComponentBorder());
		return vbox;
	}

	private RadioButton getRadio(IconpackType iconPackType) {
		String type = iconPackType.toString().toLowerCase();
		String labelText="";
		switch (iconPackType) {
		case COLOR: labelText = "Colored Icons"; break;
		case FILL: labelText = "Filled Icons"; break;
		case OUTLINE: labelText = "Outlined Icons"; break;
		}
		Label radioText = new Label(labelText);
		radioText.setStyle("-fx-font-weight:bold;");
		ImageView imageView1 = getIconImageView("/resources/images/"+type+"/menuitems/copy.png");
		ImageView imageView2 = getIconImageView("/resources/images/"+type+"/menuitems/paste.png");
		ImageView imageView3 = getIconImageView("/resources/images/"+type+"/menuitems/print.png");
		ImageView imageView4 = getIconImageView("/resources/images/"+type+"/menuitems/export.png");
		ImageView imageView5 = getIconImageView("/resources/images/"+type+"/menuitems/save.png");
		HBox hbox = new HBox(20,imageView1,imageView2,imageView3,imageView4,imageView5);
		VBox vbox = new VBox(15,radioText,hbox);
		vbox.setBorder(AppStaticData.getComponentBorder());
		vbox.setPadding(new Insets(15,25,15,25));
		RadioButton radioButton = new RadioButton();
		radioButton.setGraphic(vbox);
		radioButton.setGraphicTextGap(20);
		radioButton.setUserData(iconPackType);
		radioButton.setContentDisplay(ContentDisplay.RIGHT);
		if(Configuration.getIconpackType().equals(iconPackType))
			radioButton.setSelected(true);
		return radioButton;
	}
	
	private ImageView getIconImageView(String path) {
		ImageView imageView = new ImageView(new Image(path));
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(50);
		return imageView;
	}
	
	public void showIconPackDialog() {
		Optional<ButtonType> dialogResponse = showAndWait();
		if(dialogResponse.isPresent() && dialogResponse.get().equals(ButtonType.APPLY)) {
			Configuration.setIconpackType((IconpackType)iconPackToggleGroup.getSelectedToggle().getUserData());
			AppStaticData.getIconPackProperty().setValue(Configuration.getIconpackType().toString());
		}
	}

}
