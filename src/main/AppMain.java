package main;

import configuration.Configuration;
import controller.MainPageController;
import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppMain extends Application{
	public static final Image FAVICON = new Image("/resources/images/appicon.png");
	public static int untitledFileCounter = 0;
	public static String appName= "Indeed NotePad";
	public static Property<String> iconPackProperty = new SimpleStringProperty(Configuration.getIconpackType().toString());
	
	public Stage mainStage;
	public Property<String> stageTitleProperty;
	public Property<String> fileSavedIndicator;
	
	public AppMain() {
		stageTitleProperty = new SimpleStringProperty(appName);
		fileSavedIndicator = new SimpleStringProperty("");
	}
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		MainPageController mainPageController = new MainPageController(this);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/view/mainView.fxml"));
		loader.setController(mainPageController);
		Parent root = loader.load();
		Scene scene = new Scene(root,800,800);
		primaryStage.setScene(scene);
//		primaryStage.setMaximized(true);
		primaryStage.titleProperty().bind(stageTitleProperty);
		primaryStage.getIcons().add(FAVICON);
		primaryStage.show();
	}
}