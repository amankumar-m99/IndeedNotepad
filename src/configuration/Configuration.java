package configuration;

import java.util.prefs.Preferences;

import model.iconpack.IconpackConverter;
import model.iconpack.IconpackType;
import notepadutils.BooleanConverter;

public class Configuration {
	public static Preferences preferences = Preferences.userNodeForPackage(Configuration.class);
	
	private static IconpackType DEFAULT_ICONPACK = IconpackType.COLOR;
	private static String DEFAULT_WORD_WRAP = "true";
	private static String DEFAULT_FONT = "Verdana:NORMAL:REGULAR:12";
	private static final String PREF_ICONPACK = "iconpack";
	private static final String PREF_WORD_WRAP = "wordwrap";
	private static final String PREF_FONT = "font";

	public static void setIconpackType(IconpackType iconpackType) {
		preferences.put(PREF_ICONPACK, iconpackType.toString());
	}

	public static void setWordWrap(Boolean isWraped) {
		preferences.put(PREF_WORD_WRAP, isWraped.toString());
	}

	public static void setFontString(String fontString) {
		preferences.put(PREF_FONT, fontString);
	}

	public static IconpackType getIconpackType() {
		String iconpackString = preferences.get(PREF_ICONPACK, DEFAULT_ICONPACK.toString());
		return IconpackConverter.getIconpackTypeFromString(iconpackString);
	}

	public static boolean getWordWrap() {
		String wordWrapString = preferences.get(PREF_WORD_WRAP, DEFAULT_WORD_WRAP.toString());
		return BooleanConverter.getBooleanFromString(wordWrapString);
	}

	public static String getFontString() {
		return preferences.get(PREF_FONT, DEFAULT_FONT);
	}

	public static void removePrefrences() {
		DEFAULT_ICONPACK = getIconpackType();
        preferences.remove(PREF_ICONPACK);
        DEFAULT_WORD_WRAP= preferences.get(PREF_WORD_WRAP, DEFAULT_WORD_WRAP.toString());
        preferences.remove(PREF_WORD_WRAP);
        preferences.remove(PREF_FONT);
    }

	public static void setFullScreenLaunch(boolean value) {
		preferences.put("FullScreenLaunch", String.valueOf(value));
	}

	public static boolean getFullScreenLaunch() {
		String result =  preferences.get("FullScreenLaunch", "false").toLowerCase();
		if(result.startsWith("f"))
			return false;
		return true;
	}

}
