package model;

import controller.MainPageController;
import javafx.beans.property.Property;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyCombination;

public class Menubar {
	public static void setAcceleratorsToMenuItems(MainPageController mainPageController) {
		mainPageController.newMenuItem.setAccelerator(new KeyCharacterCombination("N", KeyCombination.CONTROL_DOWN));
		mainPageController.newWindowMenuItem.setAccelerator(new KeyCharacterCombination("N", KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN));
		mainPageController.openMenuItem.setAccelerator(new KeyCharacterCombination("O", KeyCombination.CONTROL_DOWN));
		mainPageController.saveMenuItem.setAccelerator(new KeyCharacterCombination("S", KeyCombination.CONTROL_DOWN));
		mainPageController.saveAsMenuItem.setAccelerator(new KeyCharacterCombination("S", KeyCombination.CONTROL_DOWN,KeyCombination.SHIFT_DOWN));
		mainPageController.closeMenuItem.setAccelerator(new KeyCharacterCombination("W", KeyCombination.CONTROL_DOWN));
		mainPageController.printMenuItem.setAccelerator(new KeyCharacterCombination("P", KeyCombination.CONTROL_DOWN));
		mainPageController.exportMenuItem.setAccelerator(new KeyCharacterCombination("E", KeyCombination.CONTROL_DOWN));
		mainPageController.exitMenuItem.setAccelerator(new KeyCharacterCombination("X", KeyCombination.ALT_DOWN));
		
		mainPageController.undoMenuItem.setAccelerator(new KeyCharacterCombination("Z",KeyCombination.CONTROL_DOWN));
		mainPageController.redoMenuItem.setAccelerator(new KeyCharacterCombination("Y",KeyCombination.CONTROL_DOWN));
		mainPageController.cutMenuItem.setAccelerator(new KeyCharacterCombination("X",KeyCombination.CONTROL_DOWN));
		mainPageController.copyMenuItem.setAccelerator(new KeyCharacterCombination("C",KeyCombination.CONTROL_DOWN));
		mainPageController.pasteMenuItem.setAccelerator(new KeyCharacterCombination("V",KeyCombination.CONTROL_DOWN));
		mainPageController.findMenuItem.setAccelerator(new KeyCharacterCombination("F",KeyCombination.CONTROL_DOWN));
		mainPageController.replaceMenuItem.setAccelerator(new KeyCharacterCombination("R",KeyCombination.CONTROL_DOWN));
		mainPageController.selectAllMenuItem.setAccelerator(new KeyCharacterCombination("A",KeyCombination.CONTROL_DOWN));
		
		mainPageController.shortcutMenuItem.setAccelerator(new KeyCharacterCombination("S",KeyCombination.CONTROL_DOWN,KeyCombination.ALT_DOWN));
	}
	
	public static void bindMenuItemsDisabledProperty(MainPageController mainPageController, Property<Boolean> disableMenuItems) {
		mainPageController.saveMenuItem.disableProperty().bind(disableMenuItems);
		mainPageController.saveAsMenuItem.disableProperty().bind(disableMenuItems);
		mainPageController.closeMenuItem.disableProperty().bind(disableMenuItems);
		mainPageController.printMenuItem.disableProperty().bind(disableMenuItems);
		mainPageController.exportMenuItem.disableProperty().bind(disableMenuItems);
		mainPageController.undoMenuItem.disableProperty().bind(disableMenuItems);
		mainPageController.redoMenuItem.disableProperty().bind(disableMenuItems);
		mainPageController.cutMenuItem.disableProperty().bind(disableMenuItems);
		mainPageController.copyMenuItem.disableProperty().bind(disableMenuItems);
		mainPageController.pasteMenuItem.disableProperty().bind(disableMenuItems);
		mainPageController.deleteMenuItem.disableProperty().bind(disableMenuItems);
		mainPageController.findMenuItem.disableProperty().bind(disableMenuItems);
		mainPageController.replaceMenuItem.disableProperty().bind(disableMenuItems);
		mainPageController.selectAllMenuItem.disableProperty().bind(disableMenuItems);
		mainPageController.deleteAllMenuItem.disableProperty().bind(disableMenuItems);
		mainPageController.wordWrapMenuItem.disableProperty().bind(disableMenuItems);
		mainPageController.fontsMenuItem.disableProperty().bind(disableMenuItems);
		mainPageController.lineNumbersMenuItem.disableProperty().bind(disableMenuItems);
		mainPageController.statusMenuItem.disableProperty().bind(disableMenuItems);
	}
}
