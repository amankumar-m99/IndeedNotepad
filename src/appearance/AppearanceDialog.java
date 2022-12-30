package appearance;

import configuration.AppStaticData;
import controller.MainPageController;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import model.iconpack.IconPackDialog;
import theme.ThemeChooserDialog;

public class AppearanceDialog extends Dialog<ButtonType>{

	private MainPageController mainPageController;
	private BorderPane mainContainer;
	private Window owner;
	private final String title = "Appearance Menu";
	private final double widthSizingFactor = 0.4;
	private final double heightSizingFactor = 0.5;

	public AppearanceDialog(MainPageController mainPageController) {
		this.owner = mainPageController.getAppMain().getMainStage();
		this.mainPageController = mainPageController;
		setTitle(title);
		initOwner(owner);
		setResizable(true);
		setDialogPane(getAppearanceDialogPane());
	}

	private DialogPane getAppearanceDialogPane() {
		DialogPane dialogPane = new DialogPane();
		dialogPane.setContent(getAppearanceDialogContent());
		dialogPane.getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
		return dialogPane;
	}

	private Node getAppearanceDialogContent() {
		mainContainer = new BorderPane();
		double width = widthSizingFactor*AppStaticData.getScreenWidth();
		double height = heightSizingFactor*AppStaticData.getScreenHeight();
		mainContainer.setPrefSize(width, height);
		mainContainer.setBorder(AppStaticData.getComponentBorder());
		mainContainer.setLeft(getAppearanceMenu());
		return mainContainer;
	}

	private Node getAppearanceMenu() {
		Button iconpackMenu = getMenuButton("Icon Pack");
		iconpackMenu.setOnAction(e->{
			addIconPack();
		});
		Button themeMenu = getMenuButton("Theme");
		themeMenu.setOnAction(e->{
			addTheme();
		});
		Button accentColorMenu = getMenuButton("Accent Color");
		accentColorMenu.setOnAction(e->{
//			addIconPack();
		});
		Button fontsMenu = getMenuButton("Fonts");
		fontsMenu.setOnAction(e->{
//			addIconPack();
		});
		Button statusBar = getMenuButton("Status Bar");
		statusBar.setOnAction(e->{
//			addIconPack();
		});
		VBox menuBarVBox = new VBox(0, iconpackMenu, themeMenu, accentColorMenu, fontsMenu, statusBar);
//		menuBarVBox.setBorder(AppStaticData.getComponentBorder());
		menuBarVBox.setPadding(new Insets(20));
		Separator separator = new Separator();
		separator.setOrientation(Orientation.VERTICAL);
		separator.setPrefWidth(10);
		VBox.setVgrow(separator, Priority.ALWAYS);
		HBox hBox = new HBox(10, menuBarVBox, separator);
		menuBarVBox.setMinWidth(200);
		menuBarVBox.setPrefWidth(250);		
		return hBox;
	}

	private void addIconPack() {
		Node node = new IconPackDialog(owner).getDialogPane().getContent();
		mainContainer.setCenter(node);
	}

	private void addTheme() {
		Node node = new ThemeChooserDialog(mainPageController).getDialogPane().getContent();
		mainContainer.setCenter(node);
	}

	private Button getMenuButton(String text) {
		Button button = new Button(text);
		button.setMaxWidth(Integer.MAX_VALUE);
		button.getStyleClass().add("appearance-menu-button");
		return button;
	}

}
