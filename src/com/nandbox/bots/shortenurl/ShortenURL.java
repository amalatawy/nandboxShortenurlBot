package com.nandbox.bots.shortenurl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ShortenURL {

	private static final String IS_GD_SHORTEN_URL = "https://is.gd/create.php?format=simple&url=";

	private static final int HTTP_STATUS_OK = 200;

	/**
	 * @param longURL
	 *            URL to shorten it .
	 * @return short URL if success and error message if fail
	 */

	public String getShortURL(String longURL) {

		String url = IS_GD_SHORTEN_URL + longURL;

		String shortURLReply = "";
		int responseCode = 0;
		HttpsURLConnection con = null;
		URL obj = null;
		try {
			obj = new URL(url);
			con = null;
			con = (HttpsURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0\"");
			responseCode = con.getResponseCode();

			if (responseCode == HTTP_STATUS_OK) {

				BufferedReader in = null;
				in = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}

				shortURLReply = response.toString();
				in.close();
			} else {
				System.err.println("ResponseCode : " + con.getResponseCode());
				System.err.println("ResponseMessage : " + con.getResponseMessage());
				shortURLReply = "Unable to shorten URL link, please try again.";

			}

		} catch (IOException e) {
			e.printStackTrace();

		}

		// print result
		return shortURLReply;

	}
}
