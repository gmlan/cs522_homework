package cs522.business;

import java.io.FileInputStream;
import java.io.IOException;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.util.InvalidFormatException;
 

public class PosService {
 
	private POSModel model;
	private POSTaggerME tagger;
	
	public PosService() {
		try {
			String path = this.getClass().getResource("/").getPath()  + "en-pos-maxent.bin";
			model = new POSModel(new FileInputStream(path));
			
			tagger = new POSTaggerME(model);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public String[] process(String str){
		return tagger.tag(str.split(" "));
	}
}
