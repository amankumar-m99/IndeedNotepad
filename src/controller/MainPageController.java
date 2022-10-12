package controller;

import java.net.URL;
import java.util.ResourceBundle;

import configuration.Configuration;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import main.AppMain;
import model.FindReplaceDialog;
import model.HomePage;
import model.Menubar;
import model.iconpack.Iconpack;
import model.notepad.Notepad;
import notepadutils.NotepadSavedStatus;

public class MainPageController implements Initializable{
	@FXML
	public BorderPane root;
	@FXML
	public MenuItem newMenuItem;
	@FXML
	public MenuItem newWindowMenuItem;
	@FXML
	public MenuItem openMenuItem;
	@FXML
	public MenuItem saveMenuItem;
	@FXML
	public MenuItem saveAsMenuItem;
	@FXML
	public MenuItem closeMenuItem;	
	@FXML
	public MenuItem printMenuItem;
	@FXML
	public MenuItem exportMenuItem;
	@FXML
	public MenuItem exitMenuItem;
	@FXML
	public MenuItem cutMenuItem;
	@FXML
	public MenuItem undoMenuItem;
	@FXML
	public MenuItem redoMenuItem;
	@FXML
	public MenuItem copyMenuItem;
	@FXML
	public MenuItem pasteMenuItem;
	@FXML
	public MenuItem deleteMenuItem;
	@FXML
	public MenuItem findMenuItem;
	@FXML
	public MenuItem replaceMenuItem;
	@FXML
	public MenuItem selectAllMenuItem;
	@FXML
	public MenuItem deleteAllMenuItem;
	@FXML
	public MenuItem boldMenuItem;
	@FXML
	public MenuItem italicMenuItem;
	@FXML
	public MenuItem underlineMenuItem;	
	@FXML
	public CheckMenuItem lineNumbersMenuItem;
	@FXML
	public CheckMenuItem statusMenuItem;
	@FXML
	public MenuItem iconPackMenuItem;
	@FXML
	public MenuItem clearAppDataMenuItem;
	@FXML
	public MenuItem aboutMenuItem;
	@FXML
	public MenuItem shortcutMenuItem;
	
	private AppMain appMain;
	private Notepad notepad;
	private Node homeScreen;
	private Property<Boolean> disableMenuItems;
	private FindReplaceDialog findReplaceDialog;

	public MainPageController(AppMain appMain) {
		this.appMain = appMain;
		appMain.mainStage.setOnCloseRequest(e->closeMainstage(e));
		AppMain.iconPackProperty.addListener((observable, oldValue, newValue) -> setIconPack());
	}
	
	private void setIconPack(){
		Iconpack iconpack = new Iconpack(this);
		iconpack.setIconpack(Configuration.getIconpack());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		disableMenuItems = new SimpleBooleanProperty(true);
		Menubar.setAcceleratorsToMenuItems(this);
		Menubar.bindMenuItemsDisabledProperty(this,disableMenuItems);
		HomePage hp = new HomePage(this);
		setIconPack();
		homeScreen = hp.getContent();
		root.setCenter(homeScreen);
	}
	
	@FXML
	private void fileMenuClicked(ActionEvent event) {
		FileMenuController fileMenuController = new FileMenuController(this);
		fileMenuController.handleEvent(event);
	}
	
	@FXML
	private void editMenuClicked(Event event) {
		EditMenuController editMenuController = new EditMenuController(this);
		editMenuController.handleEvent(event);
	}
	
	@FXML
	private void viewMenuClicked(Event event) {
		ViewMenuController viewMenuController = new ViewMenuController(this);
		viewMenuController.handleEvent(event);
	}

	@FXML
	private void preferenceMenuClicked(Event event) {
		PreferenceMenuController preferenceMenuController = new PreferenceMenuController(this);
		preferenceMenuController.handleEvent(event);
	}

	@FXML
	private void helpMenuClicked(Event event) {
		HelpMenuController helpMenuController = new HelpMenuController(this);
		helpMenuController.handleEvent(event);
	}
	
	private void closeMainstage(Event e) {
		if(!new FileMenuController(this).canProceedNewNotepad())
			e.consume();
	}
	
	public AppMain getAppMain() {
		return appMain;
	}
	
	public void setNotepad(Notepad notepad) {
		this.notepad = notepad;
	}
	
	public Notepad getNotepad() {
		return notepad;
	}
	
	public Node getHomeScreen() {
		return homeScreen;
	}
	
	public Property<Boolean> getDisableMenuItems() {
		return disableMenuItems;
	}
	
	public FindReplaceDialog getFindReplaceDialog() {
		return findReplaceDialog;
	}
	
	public void setFileContent(String fileContent) {
		Notepad notepad = new Notepad(this, fileContent, true);
		this.notepad = notepad;
		root.setCenter(notepad);
	}
	
	public void updateStageTitle(NotepadSavedStatus status) {
		String aesterisk="";
		String value="";
		if(status.equals(NotepadSavedStatus.UNSAVED))
			aesterisk="*";
		if(notepad != null)
			value = AppMain.appName + " - "+ aesterisk +notepad.getFileName();
		else
			value = AppMain.appName;
		appMain.stageTitleProperty.setValue(value);
	}
}