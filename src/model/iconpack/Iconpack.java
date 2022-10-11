package model.iconpack;

import controller.MainPageController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Iconpack {
	private MainPageController mainPageController;
	public Iconpack(MainPageController mainPageController) {
		this.mainPageController = mainPageController;
	}
	
	public void setIconpack(IconpackType iconpackType) {
		String type = iconpackType.toString().toLowerCase();
		mainPageController.newMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/new.png"));
		mainPageController.newWindowMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/newwindow.png"));
		mainPageController.openMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/open.png"));
		mainPageController.saveMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/save.png"));
		mainPageController.saveAsMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/save.png"));
		mainPageController.closeMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/close.png"));
		mainPageController.printMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/print.png"));
		mainPageController.exportMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/export.png"));
		mainPageController.exitMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/exit.png"));
		mainPageController.undoMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/undo.png"));
		mainPageController.redoMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/redo.png"));
		mainPageController.cutMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/cut.png"));
		mainPageController.copyMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/copy.png"));
		mainPageController.pasteMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/paste.png"));
		mainPageController.deleteMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/delete.png"));
		mainPageController.findMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/find.png"));
		mainPageController.replaceMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/replace.png"));
		mainPageController.selectAllMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/selectall.png"));
		mainPageController.iconPackMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/iconpack.png"));
		mainPageController.clearAppDataMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/cleardata.png"));
		mainPageController.aboutMenuItem.setGraphic(getMenuItemGraphic("/resources/images/"+type+"/menuitems/about.png"));
	}
	
	private ImageView getMenuItemGraphic(String path) {
		ImageView imageView = new ImageView(new Image(path));
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(22);
		return imageView;
	}
}
