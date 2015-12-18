package edu.nccu.misds.stu103306037.hw10;

//import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;
public class Main {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {
			String queryStr = sc.nextLine();
			GoogleSearch qSearch = new GoogleSearch(queryStr);
			HashMap<String,String> results = qSearch.getResults();
			for(Entry<String,String> entry : results.entrySet()){
				String title = entry.getKey();
				String url = entry.getValue();
				System.out.println(title + " " +url);
			}
		}
	}
}
