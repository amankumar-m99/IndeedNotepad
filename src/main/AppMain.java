package main;

import configuration.AppStaticData;
import configuration.Configuration;
import controller.MainPageController;
import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain extends Application{
	public static int untitledFileCounter = 0;
	
	public Stage mainStage;
	public Property<String> stageTitleProperty;
	public Property<String> fileSavedIndicator;
	
	public AppMain() {
		stageTitleProperty = new SimpleStringProperty(AppStaticData.getAppName());
		fileSavedIndicator = new SimpleStringProperty("");
	}
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainStage = primaryStage;
		MainPageController mainPageController = new MainPageController(this);
		FXMLLoader loader = new FXMLLoader(getClass().getResource(AppStaticData.getMainViewFXMLPath()));
		loader.setController(mainPageController);
		Parent root = loader.load();
		double sceneWidth = 0.6*AppStaticData.getScreenWidth();
		double sceneHeight = 0.7*AppStaticData.getScreenHeight();
		Scene scene = new Scene(root,sceneWidth,sceneHeight);
		primaryStage.setScene(scene);
		primaryStage.setMaximized(Configuration.getFullScreenLaunch());
		primaryStage.titleProperty().bind(stageTitleProperty);
		primaryStage.getIcons().add(AppStaticData.getAppIcon());
		primaryStage.show();
	}
}