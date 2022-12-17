package configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

import model.iconpack.IconpackConverter;
import model.iconpack.IconpackType;
import notepadutils.BooleanConverter;
import theme.Theme;
import theme.ThemeConverter;

public class Configuration {
	public static Preferences preferences = Preferences.userNodeForPackage(Configuration.class);
	
	private static IconpackType DEFAULT_ICONPACK = IconpackType.COLOR;
	private static Theme DEFAULT_THEME = Theme.LIGHT;
	private static boolean DEFAULT_WORD_WRAP = false;
	private static boolean DEFAULT_SHOW_STATUS_BAR = true;
	private static boolean DEFAULT_FULLSCREEN = false;
	private static String DEFAULT_FONT = "Verdana:NORMAL:REGULAR:12";
	private static final String PREF_ICONPACK = "indeedNotepadIconpack";
	private static final String PREF_WORD_WRAP = "indeedNotepadWordwrap";
	private static final String PREF_FONT = "indeedNotepadFont";
	private static final String PREF_THEME = "indeedNotepadTheme";
	private static final String PREF_STATUSBAR = "indeedNotepadStatusBar";
	private static final String PREF_FULLSCREEN = "indeedNotepadFullScreen";
	private static final List<String> preferencesList = setPreferenceList();

	private static List<String> setPreferenceList() {
		List<String> list = new ArrayList<>();
		list.add(PREF_ICONPACK);
		list.add(PREF_WORD_WRAP);
		list.add(PREF_FONT);
		list.add(PREF_THEME);
		list.add(PREF_STATUSBAR);
		list.add(PREF_FULLSCREEN);
		return list;
	}

	public static List<String> getPreferenceList(){
		return preferencesList;
	}

	public static void setIconpackType(IconpackType iconpackType) {
		preferences.put(PREF_ICONPACK, iconpackType.toString());
	}

	public static IconpackType getIconpackType() {
		String iconpackString = preferences.get(PREF_ICONPACK, DEFAULT_ICONPACK.toString());
		return IconpackConverter.getIconpackTypeFromString(iconpackString);
	}

	public static void setTheme(Theme theme) {
		preferences.put(PREF_THEME, theme.toString());
	}

	public static Theme getTheme() {
		String themeString = preferences.get(PREF_THEME, DEFAULT_THEME.toString());
		return ThemeConverter.getThemeFromString(themeString);
	}

	public static void setFontString(String fontString) {
		preferences.put(PREF_FONT, fontString);
	}

	public static String getFontString() {
		return preferences.get(PREF_FONT, DEFAULT_FONT);
	}

	public static void setFullScreenLaunch(boolean value) {
		preferences.put(PREF_FULLSCREEN, String.valueOf(value));
	}

	public static boolean getFullScreenLaunch() {
		String result =  preferences.get(PREF_FULLSCREEN, String.valueOf(DEFAULT_FULLSCREEN)).toLowerCase();
		return BooleanConverter.getBooleanFromString(result);
	}

	public static void setWordWrap(boolean value) {
		preferences.put(PREF_WORD_WRAP, String.valueOf(value));
	}

	public static boolean getWordWrap() {
		String wordWrapString = preferences.get(PREF_WORD_WRAP, String.valueOf(DEFAULT_WORD_WRAP));
		return BooleanConverter.getBooleanFromString(wordWrapString);
	}

	public static void setShowStatusBar(boolean value) {
		preferences.put(PREF_STATUSBAR, String.valueOf(value));
	}

	public static boolean getShowStatusBar() {
		String result =  preferences.get(PREF_STATUSBAR, String.valueOf(DEFAULT_SHOW_STATUS_BAR));
		return BooleanConverter.getBooleanFromString(result);
	}

	public static boolean removePrefrences() {
		try {
			for(String s: preferencesList)
				preferences.remove(s);
		}
		catch (Exception e) {
			return false;
		}
		return true;
    }

}
