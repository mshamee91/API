package com.qa.restclient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.qa.base.TestBase;

public class RestClient extends TestBase {
	CloseableHttpClient httpClient;
	HttpGet httpGet;
	CloseableHttpResponse closeableHttpResponse;
	public CloseableHttpResponse get(String url, HashMap<String, String> header)
			throws ClientProtocolException, IOException {
		httpClient = HttpClients.createDefault();
		httpGet = new HttpGet(url);
		for (Map.Entry<String, String> h : header.entrySet()) {
			httpGet.addHeader(h.getKey(), h.getValue());
		}
		closeableHttpResponse = httpClient.execute(httpGet);
		return closeableHttpResponse;
	}
	
	public CloseableHttpResponse post(String url,HashMap<String,String> header,String entity) throws ClientProtocolException, IOException
	{
		httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		for(Entry<String,String> h:header.entrySet())
		{
			httpPost.addHeader(h.getKey(),h.getValue());
		}
		httpPost.setEntity(new StringEntity(entity));
		closeableHttpResponse = httpClient.execute(httpPost);
		return closeableHttpResponse;
	}
	
}
