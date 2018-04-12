package cong.struc.api.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	public static final String namePatternString = "([\\w\\s]+)";
	public static final String addressPatternString = "([\\`\\~\\@\\#\\$\\%\\^\\&\\*\\(\\)\\+\\{\\}\\[\\]\\;\\:\\\\|]+)";
	public static final String codePatternString = "([\\w\\d]{7,20})";
	private static final String urlPatternString = "^(https?://)?[\\d\\w\\.\\-\\+%&/]+";
	private static final String uuidPatternString = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";
	private static final Pattern namePattern = Pattern.compile(namePatternString);
	private static final Pattern addrPattern = Pattern.compile(addressPatternString);

	public static boolean isValidName(String name) {
		Matcher m = namePattern.matcher(name);
		if (m != null) {
			return !m.find();
		}
		return false;
	}

	public static boolean isValidAddr(String addr) {
		Matcher m = addrPattern.matcher(addr);
		if (m != null) {
			return !m.find();
		}
		return false;
	}

	public static boolean isValidProductName(String name) {
		Matcher m = addrPattern.matcher(name);
		if (m != null) {
			return !m.find();
		}
		return false;
	}

	public static boolean isValidUrl(String url) {
		return url.matches(urlPatternString);
	}

	public static boolean isValidCode(String code) {
		return code.matches(codePatternString);
	}

	public static boolean isUUID(String id) {
		if (id == null) {
			return false;
		}
		id=id.toLowerCase();
		return id.matches(uuidPatternString);
	}
	
	public static String formatNumber(long number) {
		return String.format("%,10d", number).trim();
	}
}
