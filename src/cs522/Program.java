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
			String[] contentArray  = twitterInfo.getContent().split(" ");
			String[] tags = posService.process(contentArray);
			for(int i = 0; i < tags.length; i++){
				if(tags[i].startsWith("VB") || tags[i].startsWith("NN") || tags[i].startsWith("JJ"))
					System.out.println(contentArray[i] + " -  "  + tags[i]) ;
			}
		}	
	}
}
