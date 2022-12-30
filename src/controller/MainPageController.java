package controller;

import java.net.URL;
import java.util.ResourceBundle;

import configuration.AppStaticData;
import configuration.Configuration;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
	public CheckMenuItem wordWrapMenuItem;
	@FXML
	public MenuItem fontsMenuItem;
	@FXML
	public CheckMenuItem lineNumbersMenuItem;
	@FXML
	public CheckMenuItem statusMenuItem;
	@FXML
	public CheckMenuItem launchFullScreenMenuItem;
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
	private HomePage homePage;
	private Property<Boolean> disableMenuItems;
	private FindReplaceDialog findReplaceDialog;

	public MainPageController(AppMain appMain) {
		this.appMain = appMain;
		appMain.getMainStage().setOnCloseRequest(e->closeMainstage(e));
		AppStaticData.getIconPackProperty().addListener((observable, oldValue, newValue) -> setIconPack());
	}

	private void setIconPack(){
		Iconpack iconpack = new Iconpack(this);
		iconpack.setUpdateIconpack();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		disableMenuItems = new SimpleBooleanProperty(true);
		wordWrapMenuItem.setSelected(Configuration.getWordWrap());
		statusMenuItem.setSelected(Configuration.getShowStatusBar());
		launchFullScreenMenuItem.setSelected(Configuration.getFullScreenLaunch());
		Menubar.setAcceleratorsToMenuItems(this);
		Menubar.bindMenuItemsDisabledProperty(this,disableMenuItems);
		homePage = new HomePage(this);
		setIconPack();
		root.setCenter(homePage.getHomeScreen());
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
	private void formatMenuClicked(Event event) {
		FormatMenuController formatMenuController = new FormatMenuController(this);
		formatMenuController.handleEvent(event);
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

	public HomePage getHomePage() {
		return homePage;
	}

	public Property<Boolean> getDisableMenuItems() {
		return disableMenuItems;
	}

	public FindReplaceDialog getFindReplaceDialog() {
		return findReplaceDialog;
	}

	public void setFindReplaceDialog(FindReplaceDialog findReplaceDialog) {
		this.findReplaceDialog = findReplaceDialog;
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
			value = AppStaticData.getAppName() + " - "+ aesterisk +notepad.getFileName();
		else
			value = AppStaticData.getAppName();
		appMain.stageTitleProperty.setValue(value);
	}
}