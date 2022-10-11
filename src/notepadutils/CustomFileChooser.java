package notepadutils;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

public class CustomFileChooser {
	public static File showFileChooser(FileChooserDialogType type, Window owner) {
		String titleText="";
		FileChooser fileChooser = new FileChooser();
		ExtensionFilter allFilesExtensionFilter = new ExtensionFilter("All files","*.*");
		ExtensionFilter txtExtensionFilter = new ExtensionFilter("Text files","*.txt");
		File file = null;
		switch(type) {
		case OPEN:
			titleText = "Choose file to open";
			fileChooser.setTitle(titleText);
			fileChooser.getExtensionFilters().addAll(allFilesExtensionFilter,txtExtensionFilter);
		    file = fileChooser.showOpenDialog(owner);
			break;
		case SAVE:
			titleText = "Choose location to save file";
			fileChooser.setTitle(titleText);
			fileChooser.getExtensionFilters().addAll(txtExtensionFilter,allFilesExtensionFilter);
			file = fileChooser.showSaveDialog(owner);
			break;
		case SAVE_AS:
			titleText = "Save As";
			fileChooser.setTitle(titleText);
			fileChooser.getExtensionFilters().addAll(txtExtensionFilter,allFilesExtensionFilter);
			file = fileChooser.showSaveDialog(owner);
		}
	    return file;
	}
}
