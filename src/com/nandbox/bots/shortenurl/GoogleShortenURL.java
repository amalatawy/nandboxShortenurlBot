package com.nandbox.bots.shortenurl;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import static com.nandbox.bots.shortenurl.util.Utility.isNotEmpty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class GoogleShortenURL {

	/**
	 * Bring your google shorten APK token from Google and set it in
	 * GOOGLE_SHORTNER_TOKEN constant
	 */
	 private static final String GOOGLE_SHORTNER_TOKEN = "PUT YOUR GOOGLE SHORTNER";
	// URL API TOKEN HERE";
	private static final String GOOGLE_SHORTEN_URL = " https://www.googleapis.com/urlshortener/v1/url?key="
			+ GOOGLE_SHORTNER_TOKEN;
	private static final int HTTP_STATUS_OK = 200;

	/**
	 * @param longURL
	 *            URL to shorten it .
	 * @return short URL if success and error message if fail
	 */
	public String getShortURL(String longURL) {
		String shortURLReply = "";
		HttpsURLConnection connection = null;
		try {
			Map<String, String> valueMap = new HashMap<>();
			valueMap.put("longUrl", longURL);
			String requestBody = new JSONSerializer().serialize(valueMap);
			connection = (HttpsURLConnection) new URL(GOOGLE_SHORTEN_URL).openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.getOutputStream().write(requestBody.getBytes());
			connection.getOutputStream().close();
			if (connection.getResponseCode() == HTTP_STATUS_OK) {
				StringBuilder sb = new StringBuilder();
				try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
					String line;
					while ((line = br.readLine()) != null) {
						sb.append(line);
					}
					System.out.println(sb);
					Map<String, String> map = new JSONDeserializer<Map<String, String>>().deserialize(sb.toString());

					if (map != null && isNotEmpty(map.get("id"))) {
						shortURLReply = map.get("id");
						System.out.println(shortURLReply);
					}
					connection.getInputStream().close();
					br.close();
				}
			} else {
				System.err.println("ResponseCode : " + connection.getResponseCode());
				System.err.println("ResponseMessage : " + connection.getResponseMessage());
				shortURLReply = "Unable to shorten URL link, please try again.";
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return shortURLReply;
	}
}
