package model.shortcuts;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import notepadutils.Key;

public class ShortcutActionKey {
	public static HBox actionKeys(Key... keys) {
		HBox hbox = new HBox(5);
		for(int i=0; i<keys.length; i++) {
			hbox.getChildren().add(createKeyboardKeys(keys[i]));
			if(i < keys.length-1)
				hbox.getChildren().add(createAddButton());
		}
		return hbox;
	}

	private static Label createKeyboardKeys(Key key) {
		DropShadow dropShadow = new DropShadow();
		dropShadow.setBlurType(BlurType.GAUSSIAN); 
		dropShadow.setColor(Color.rgb(50, 50, 50, .8)); 
		dropShadow.setRadius(12); 
		dropShadow.setOffsetX(2); 
		dropShadow.setOffsetY(2); 
		dropShadow.setSpread(0);
		Label keyLabel = new Label(key.toString());
		keyLabel.setPadding(new Insets(5,7,5,7));
		keyLabel.setStyle("-fx-font-weight:bold;");
		keyLabel.setEffect(dropShadow);
		Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10, 10, 10, 10,false), new BorderWidths(1.4)));
		keyLabel.setBorder(border);
		return keyLabel;
	}

	private static Label createAddButton() {
		Label label = new Label("+");
		label.setPadding(new Insets(5,7,5,7));
		label.setStyle("-fx-font-weight:bold;");
		return label;
	}
}
