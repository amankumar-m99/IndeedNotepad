package configuration;

import java.util.prefs.Preferences;

import model.iconpack.IconpackType;

public class Configuration {
	public static Preferences preferences = Preferences.userNodeForPackage(Configuration.class);
	private static IconpackType DEFAULT_ICONPACK = IconpackType.COLOR;
	private static final String PREF_ICONPACK = "iconpack";
	
	public static void setIconpackType(IconpackType iconpackType) {
		preferences.put(PREF_ICONPACK, iconpackType.toString());
	}
	
	public static IconpackType getIconpackType() {
		String iconpackString = preferences.get(PREF_ICONPACK, DEFAULT_ICONPACK.toString());
		return getIconpackTypeFromString(iconpackString);
	}
	
	private static IconpackType getIconpackTypeFromString(String string) {
		IconpackType iconpackType = IconpackType.COLOR;
		switch (string) {
		case "COLOR": iconpackType = IconpackType.COLOR; break;
		case "FILL": iconpackType = IconpackType.FILL; break;
		case "OUTLINE": iconpackType = IconpackType.OUTLINE; break;
		}
		return iconpackType;
	}
	
	public static void removePrefrences() {
		DEFAULT_ICONPACK = getIconpackType();
        preferences.remove(PREF_ICONPACK);
    }
}
