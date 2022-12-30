package theme;

import java.util.Optional;

import configuration.AppStaticData;
import configuration.Configuration;
import controller.MainPageController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.stage.Stage;
import javafx.stage.Window;
import main.AppMain;

public class ThemeChooserDialog extends Dialog<ButtonType> {

	private ToggleGroup themeToggleGroup = new ToggleGroup();
	private Stage owner;
	private AppMain appMain;

	public ThemeChooserDialog(MainPageController mainPageController) {
		this.appMain = mainPageController.getAppMain(); 
		this.owner  = appMain.mainStage;
		initOwner(owner);
		setTitle("Choose theme");
		setDialogPane(getThisDialogPane());
	}

	private DialogPane getThisDialogPane() {
		DialogPane dialogPane = new DialogPane();
		dialogPane.setPadding(new Insets(20,35,20,35));
		dialogPane.setContent(getThisDialogPaneContent());
		dialogPane.getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
		return dialogPane;
	}

	private Node getThisDialogPaneContent() {
		RadioButton darkRadio = getRadio(Theme.DARK);
		RadioButton lightRadio = getRadio(Theme.LIGHT);
		themeToggleGroup.getToggles().addAll(darkRadio, lightRadio);
		VBox vbox = new VBox(30,darkRadio, lightRadio);
		vbox.setPadding(new Insets(30,30,30,20));
		vbox.setBorder(AppStaticData.getComponentBorder());
		return vbox;
	}

	private RadioButton getRadio(Theme theme) {
		String labelText="";
		switch (theme) {
		case DARK : labelText = "Dark Theme"; break;
		case LIGHT: labelText = "Ligth Theme"; break;
		}
		Label radioText = new Label(labelText);
		radioText.setAlignment(Pos.CENTER_RIGHT);
		radioText.setStyle("-fx-font-weight:bold;");
		ImageView imageView = getIconImageView("/resources/images/theme.png");
		HBox hbox = new HBox(15,radioText,imageView);
		hbox.setBorder(AppStaticData.getComponentBorder());
		hbox.setPadding(new Insets(15,25,15,25));
		RadioButton radioButton = new RadioButton();
		radioButton.setGraphic(hbox);
		radioButton.setGraphicTextGap(20);
		radioButton.setUserData(theme);
		radioButton.setContentDisplay(ContentDisplay.RIGHT);
		if(Configuration.getTheme().equals(theme))
			radioButton.setSelected(true);
		return radioButton;
	}

	private ImageView getIconImageView(String path) {
		ImageView imageView = new ImageView(new Image(path));
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(50);
		return imageView;
	}

	public void showThemeChooserDialog() {
		Optional<ButtonType> dialogResponse = showAndWait();
		if(dialogResponse.isPresent() && dialogResponse.get().equals(ButtonType.APPLY)) {
			Configuration.setTheme((Theme)themeToggleGroup.getSelectedToggle().getUserData());
			appMain.setTheme();
		}
	}

}
