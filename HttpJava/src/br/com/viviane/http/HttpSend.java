package br.com.viviane.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpSend {
	private static Logger logger = LogManager.getLogger(HttpSend.class);

	public HttpSend() {

	}

	public int Notify(String CONTRACT_NAME, String httpAddress, String ipHttp) {

		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost("https://" + httpAddress);
			List<NameValuePair> keyValue = new ArrayList<NameValuePair>();

			keyValue.add(new BasicNameValuePair("contract", CONTRACT_NAME));
			keyValue.add(new BasicNameValuePair("fileName", ipHttp + CONTRACT_NAME + ".zip"));

			logger.debug("File name sent to service => " + (ipHttp + CONTRACT_NAME + ".zip"));

			httpPost.setEntity(new UrlEncodedFormEntity(keyValue));
			CloseableHttpResponse responseHttp = httpClient.execute(httpPost);
			logger.info("[" + CONTRACT_NAME + "] HTTP Status => " + responseHttp.getStatusLine().getStatusCode());

			if (responseHttp.getStatusLine().getStatusCode() != 200) {
				logger.info("[" + CONTRACT_NAME + "] Warning...Response Error => " + responseHttp.getStatusLine().getStatusCode());
				responseHttp.close();
				return responseHttp.getStatusLine().getStatusCode();

			}
			responseHttp.close();
			return responseHttp.getStatusLine().getStatusCode();
		} catch (ClientProtocolException e) {
			logger.error("-> HTTP error service -> " + e.getMessage());
		} catch (IOException e) {
			logger.error("-> HTTP error IOException -> " + e.getMessage());
		}
		return 0;

	}

}
