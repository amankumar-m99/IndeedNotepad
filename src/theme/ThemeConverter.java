package theme;

public class ThemeConverter {
	public static Theme getThemeFromString(String themeString) {
		Theme theme = Theme.LIGHT;
		switch (themeString) {
		case "DARK" : theme = Theme.DARK; break;
		case "LIGHT": theme = Theme.LIGHT; break;
		}
		return theme;
	}
}
