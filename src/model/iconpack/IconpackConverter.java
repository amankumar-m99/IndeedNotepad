package model.iconpack;

public class IconpackConverter {

	public static IconpackType getIconpackTypeFromString(String string) {
		IconpackType iconpackType = IconpackType.COLOR;
		switch (string) {
		case "COLOR": iconpackType = IconpackType.COLOR; break;
		case "FILL": iconpackType = IconpackType.FILL; break;
		case "OUTLINE": iconpackType = IconpackType.OUTLINE; break;
		}
		return iconpackType;
	}
}
