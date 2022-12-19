package model.customcomponents;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CumpulsoryLabel extends Label{
	private ImageView imageView;
	public CumpulsoryLabel(String text) {
		imageView = new ImageView(new Image("/resources/images/asterisk.png"));
		imageView.setPreserveRatio(true);
		imageView.setFitHeight(10);
		setGraphic(imageView);
		setText(text);
	}
}
