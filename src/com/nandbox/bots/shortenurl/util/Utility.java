package com.nandbox.bots.shortenurl.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

	public static boolean isNotEmpty(String string) {
		return (string == null) ? false : !"".equals(string);
	}

	public static boolean IsMatch(String s, String pattern) {
		try {
			Pattern patt = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);

			Matcher matcher = patt.matcher(s);
			return matcher.matches();
		} catch (RuntimeException e) {
			return false;
		}

	}

	public static String getTokenFromPropFile() throws IOException {
		Properties prop = new Properties();

		InputStream input = new FileInputStream("token.properties");
		prop.load(input);
		return prop.getProperty("Token");
	}
}