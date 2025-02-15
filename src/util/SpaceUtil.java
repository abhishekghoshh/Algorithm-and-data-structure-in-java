package util;

public class SpaceUtil {
	public static String withSpace(Object i) {
		int remainingSpace = 5 - i.toString().length();
		StringBuilder sb = new StringBuilder(i.toString());
		for (int j = 0; j < remainingSpace; j++)
			sb.append(" ");
		return sb.toString();
	}
}
