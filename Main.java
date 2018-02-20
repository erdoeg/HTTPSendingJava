package br.com.viviane.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	
	/*
	 * java -Dlog4j.configurationFile=log4j.properties -jar
	 * PDFUtilities-0.0.1-SNAPSHOT-jar-with-dependencies.jar
	 */
	
	private static Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		
		if (args.length < 1 && args.length > 3)
		{
			logger.error("Passar Argumento do Path Properties");
			System.exit(-1);
		}
		String CONTRACT_NAME = "0987654321BGH";
		String httpAddress = "abcd.abcservices.com";
		String ipHttp = "192.168.10.22";
		
		int response = new HttpSend().Notify(CONTRACT_NAME, httpAddress, ipHttp);
		logger.info("HTTP Response = " + response);

	}

}
