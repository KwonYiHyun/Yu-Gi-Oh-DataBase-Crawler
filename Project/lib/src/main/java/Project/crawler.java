package Project;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class crawler {
	
	public String urlKr = "https://www.db.yugioh-card.com/yugiohdb/card_search.action?ope=2&cid=4800&request_locale=ko";
	public String urlJa = "https://www.db.yugioh-card.com/yugiohdb/card_search.action?ope=2&cid=4027&request_locale=ja";
	public Document doc;
	
	public ArrayList<String>[] arrCSV=new ArrayList[3];
	int count=1;
	
	public void init() {
		doc=null;
		
		for (int i = 0; i < 3; i++) {
			arrCSV[i]=new ArrayList<String>();
		}
	}
	
	public void crawling(int num) {
		urlKr="https://www.db.yugioh-card.com/yugiohdb/card_search.action?ope=2&cid="+num+"&request_locale=ko";
		
		try {
			doc = Jsoup.connect(urlKr).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements cardName_elem=doc.select("div[id=\"cardname\"]");
		if(cardName_elem.isEmpty()) {
			System.out.println("cardName is Empty!");
			return;
		}
		
		String cardName=cardName_elem.toString().split("<h1>")[1].split("<span>")[0].trim();
		System.out.println(cardName);
		arrCSV[0].add(count+"");
		arrCSV[1].add(cardName);
		count++;
	}
	
	public void crawlingDate(int num) {
		urlJa="https://www.db.yugioh-card.com/yugiohdb/card_search.action?ope=2&cid="+num+"&request_locale=ja";
		
		try {
			doc = Jsoup.connect(urlJa).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements cardInside_elem=doc.select("div[class=\"inside\"]").select("div[class=\"time\"]");
		if(cardInside_elem.isEmpty()) {
			System.out.println("cardInside is Empty!");
			return;
		}
		
		ArrayList<String> arr=new ArrayList<>();
		for (Element e : cardInside_elem) {
			arr.add(e.text().trim());
		}
		String cardInside=arr.get(arr.size()-1);
		System.out.println(cardInside);
		
		arrCSV[2].add(cardInside);
		
	}
	
	public void outCSV() {
		
		File file=new File(".\\result\\DB.csv");;
		String rootPath=file.getAbsolutePath();
		
		BufferedWriter bw=null;
		String NEWLINE=System.lineSeparator();
		
		try {
			bw=new BufferedWriter(new FileWriter(rootPath));
			
			bw.write("번호,이름,발매일");
			bw.write(NEWLINE);
			for (int i = 0; i < count-1; i++) {
				bw.write(arrCSV[0].get(i)+","+arrCSV[1].get(i)+","+arrCSV[2].get(i));
				bw.write(NEWLINE);
			}
			
			bw.flush();
			bw.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
}
