package notepadutils;

public class BooleanConverter {
	public static boolean getBooleanFromString(String string) {
		if(string.equalsIgnoreCase("true"))
			return true;
		return false;
	}
}
