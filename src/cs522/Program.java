package cs522;
import java.util.List;

import cs522.business.PosService;
import cs522.business.TwitterCrawlerService;
import cs522.domain.TwitterInfo;

public class Program {
	
	private static TwitterCrawlerService crawlerService = null;
	private static PosService posService = null;
	
	public static void main(String[] args) {		
		crawlerService = new TwitterCrawlerService();
		posService = new PosService();	
		
		String keyword = "#rio";
		
		//Crawle data and save to database
		crawlerService.crawle(keyword);
		
		
		//read all data from db and process with POS
		List<TwitterInfo> list = crawlerService.getAllTwitters(keyword);		
		for (TwitterInfo twitterInfo : list) {
			for (String string : posService.process(twitterInfo.getContent())) {
				System.out.print(string + " ");
			}
			System.out.println("");
		}	
	}
}
