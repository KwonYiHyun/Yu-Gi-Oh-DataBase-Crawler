package Project;


public class Main {

	public static void main(String[] args) {
		crawler c=new crawler();
		c.init();
		
		for (int i = 4000; i <= 4020; i++) {
			System.out.println("============================================================");
			System.out.println("i = "+i);
			c.crawling(i);
			c.crawlingDate(i);
		}
		
		c.outCSV();
		
		
	}
}