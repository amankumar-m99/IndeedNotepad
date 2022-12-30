package notepadutils;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class FontConverter {

	public static Font getFontFromFontString(String fontString) {
		return getFontFromFontString(processFontString(fontString));
	}

	public static Font getFontFromFontString(String[] arr) {
		String fontfamily = arr[0];
		FontWeight fontWeight = getFontWeightFromWeightString(arr[1]);
		FontPosture fontPosture = getFontPostureFromPostureString(arr[2]);
		Double fontSize = Double.parseDouble(arr[3]);
		Font font = Font.font(fontfamily,fontWeight,fontPosture,fontSize);
		return font;
	}

	public static String getFontFamilyFromFontString(String fontString) {
		return processFontString(fontString)[0];
	}

	public static FontWeight getFontWeightFromFontString(String fontString) {
		String weightStr = processFontString(fontString)[1];
		return getFontWeightFromWeightString(weightStr);
	}

	private static FontWeight getFontWeightFromWeightString(String weight) {
		switch (weight) {
		case "BLACK": return FontWeight.BLACK;
		case "BOLD": return FontWeight.BOLD;
		case "EXTRA_BOLD": return FontWeight.EXTRA_BOLD;
		case "EXTRA_LIGHT": return FontWeight.EXTRA_LIGHT;
		case "LIGHT": return FontWeight.LIGHT;
		case "MEDIUM": return FontWeight.MEDIUM;
		case "NORMAL": return FontWeight.NORMAL;
		case "SEMI_BOLD": return FontWeight.SEMI_BOLD;
		case "THIN": return FontWeight.THIN;
		}
		return FontWeight.NORMAL;
	}

	public static FontPosture getFontPostureFromFontString(String fontString) {
		String postureString = processFontString(fontString)[2];
		return getFontPostureFromPostureString(postureString);
	}

	private static FontPosture getFontPostureFromPostureString(String posture) {
		switch (posture) {
		case "ITALIC": return FontPosture.ITALIC;
		case "REGULAR": return FontPosture.REGULAR;
		}
		return FontPosture.REGULAR;
	}

	public static String getFontSizeFromFontString(String fontString) {
		return processFontString(fontString)[3];
	}

	private static String[] processFontString(String fontString) {
		String arr[] = fontString.split(":");
		return arr;
	}

	public static String getFontStringFromFontComponents(String family, FontWeight weight, FontPosture posture, int size) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(family);
		stringBuilder.append(":");
		stringBuilder.append(weight.toString());
		stringBuilder.append(":");
		stringBuilder.append(posture.toString());
		stringBuilder.append(":");
		stringBuilder.append(String.valueOf(size));
		return stringBuilder.toString();
	}
}