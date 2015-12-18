package edu.nccu.misds.stu103306037.hw10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class GoogleSearch {
	private String queryStr;
	private String content;

	public GoogleSearch(String queryStr) {
		// TODO Auto-generated constructor stub
		this.queryStr = queryStr;
	}

	private String fetchCountent() throws IOException {
		URL url = new URL("https://www.google.com/search?=" + queryStr
				+ "&num=100&oe=utf8");
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in,
				"UTF-8"));
		conn.setRequestProperty("user-agent", "Mozilla/5.0");
		String retVal = "";
		String line = null;
		while ((line = br.readLine()) != null) {

			retVal += (line + "\n");

		}
		return retVal;

	}

	public HashMap<String, String> getResults() throws IOException {
		HashMap<String, String> retVal = new HashMap<>();
		if (content == null) {
			content = fetchCountent();
		}
		org.jsoup.nodes.Document doc = Jsoup.parse(content);
		org.jsoup.select.Elements divGs = doc.select("li.g");
		for (Element divG : divGs) {
			try {
				Element h3R = divG.select("h3.r").get(0);
				Element aTag = h3R.select("a").get(0);
				String title = aTag.text();
				String url = aTag.attr("herf");
				retVal.put(title, url);
			} catch (IndexOutOfBoundsException ex) {
				System.out.println("google改版，檢查div.g");
			}

		}
		return retVal;
	}
}
