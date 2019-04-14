package com.nandbox.bots.shortenurl.test;

import com.nandbox.bots.shortenurl.ShortenURL;

public class TestShortenURL {

	public static void main(String[] args) throws Exception {
		String longURL = "www.nandbox.com";
		ShortenURL googleShortenURL = new ShortenURL();
		System.out.println("UR SHORTEN URL <> " + googleShortenURL.getShortURL(longURL));

	}
}
