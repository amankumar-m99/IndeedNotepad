package configuration;

import java.util.prefs.Preferences;

import model.iconpack.IconpackType;

public class Configuration {
	public static Preferences preferences = Preferences.userNodeForPackage(Configuration.class);
	private static final String PREF_ICONPACK = "iconpack";
	private static final IconpackType DEFAULT_ICONPACK = IconpackType.COLOR;
	
	public static void setIconpack(IconpackType iconpackType) {
		preferences.put(PREF_ICONPACK, iconpackType.toString());
	}
	
	public static IconpackType getIconpack() {
		String iconpackString = preferences.get(PREF_ICONPACK, DEFAULT_ICONPACK.toString());
		return getIconpackFromString(iconpackString);
	}
	
	private static IconpackType getIconpackFromString(String string) {
		IconpackType iconpackType = IconpackType.COLOR;
		switch (string) {
		case "COLOR": iconpackType = IconpackType.COLOR; break;
		case "FILL": iconpackType = IconpackType.FILL; break;
		case "OUTLINE": iconpackType = IconpackType.OUTLINE; break;
		}
		return iconpackType;
	}
	
	public static void removePrefrences() {
        preferences.remove(PREF_ICONPACK);
    }
}
