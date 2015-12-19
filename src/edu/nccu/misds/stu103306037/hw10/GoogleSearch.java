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
	
		this.queryStr = queryStr;
	}


	private String fetchCountent() throws IOException {
		URL url = new URL("http://www.google.com/search?q=" + queryStr + "&num=5&oe=utf-8");
		URLConnection conn = url.openConnection(); //URLConnection只能由URL物件生成
		conn.setRequestProperty("user-agent", "Chrome/47.0.2526.80");
		InputStream in =  conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
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
		Document doc = Jsoup.parse(content);
		Elements divGs = doc.select("li.g");
		for (Element divG : divGs) {
			try {
			
				Element h3R = divG.select("h3.r").get(0);
				
				Element aTag = h3R.select("a").get(0);
				
				String title = aTag.text();
				
				String url = aTag.attr("herf");
				retVal.put(title, url);
			} catch (IndexOutOfBoundsException ex) {
			
			}

		}
		
		return retVal;
		
	}
}
