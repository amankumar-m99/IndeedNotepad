package configuration;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class AppStaticData {
	private static final Image APPICON = new Image("/resources/images/appicon.png");
	private static String appName= "Indeed NotePad";
	private static String mainViewFXMLPath = "/resources/view/mainView.fxml";
	private static Border componentBorder = new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, new CornerRadii(10, 10, 10, 10,false), new BorderWidths(2)));
	private static Property<String> iconPackProperty = new SimpleStringProperty(Configuration.getIconpackType().toString());
	
	public static Image getAppIcon() {
		return APPICON;
	}

	public static double getScreenWidth() {
		return java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	}

	public static double getScreenHeight() {
		return java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	}

	public static String getAppName() {
		return appName;
	}

	public static String getMainViewFXMLPath() {
		return mainViewFXMLPath;
	}

	public static Property<String> getIconPackProperty(){
		return iconPackProperty;
	}

	public static Border getComponentBorder() {
		return componentBorder;
	}
}
