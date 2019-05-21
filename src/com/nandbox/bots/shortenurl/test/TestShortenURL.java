package com.nandbox.bots.shortenurl.test;

import com.nandbox.bots.shortenurl.ShortenURL;

public class TestShortenURL {

	public static void main(String[] args) throws Exception {
		String longURL = "www.nandbox.com";
		ShortenURL shortenurl = new ShortenURL();
		System.out.println("SHORTEN URL :  " + shortenurl.getShortURL(longURL));

	}
}
