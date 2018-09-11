package com.nandbox.bots.shortenurl.test;

import com.nandbox.bots.shortenurl.GoogleShortenURL;

public class TestGoogleShortenURL {

	public static void main(String[] args) {
		String longURL = "www.nandbox.com";
		GoogleShortenURL googleShortenURL = new GoogleShortenURL();
		System.out.println(googleShortenURL.getShortURL(longURL));
	}
}
