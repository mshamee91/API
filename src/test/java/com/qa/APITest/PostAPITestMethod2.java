package com.qa.APITest;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.data.Users;
import com.qa.restclient.RestClient;
import com.qa.util.TestUtil;

public class PostAPITestMethod2 extends TestBase {
	String url;
	RestClient restclient = new RestClient();
	Random rand = new Random();
	int upc = rand.nextInt(100000000);
	CloseableHttpResponse closeableHttpResponse;
	JSONParser parser = new JSONParser();

	@BeforeMethod
	public void init() {
		String baseurl = prop.getProperty("endpointurl");
		String serviceurl = "/products";
		url = baseurl + serviceurl;

	}

	@Test
	public void PostAPI() throws ClientProtocolException, IOException, ParseException {
		HashMap<String, String> reqHeader = new HashMap<String, String>();
		reqHeader.put("Content-Type", "application/json");

		Users users = new Users("New Product", "Hard Good", String.valueOf(upc), 99.99,
				"This is a placeholder request for creating a new product.", "NP12345");

		/*users.setName("New Product");
		users.setType("Hard Good");
		users.setUpc(String.valueOf(upc));
		users.setPrice(99.99);
		users.setDescription("This is a placeholder request for creating a new product.");
		users.setModel("NP12345");*/

		ObjectMapper mapper = new ObjectMapper();
		String reqPayload = mapper.writeValueAsString(users);
		closeableHttpResponse = restclient.post(url, reqHeader, reqPayload);
		System.out.println(closeableHttpResponse);

		// status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, 201, "Status code is incorrect:Expected is 200:Actual is " + statusCode);

		// headers
		Header[] headersarray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> responseHeader = new HashMap<String, String>();
		for (Header head : headersarray) {
			responseHeader.put(head.getName(), head.getValue());
		}
		System.out.println(responseHeader);

		// response
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responsJSON = new JSONObject(responseString);

		System.out.println(responsJSON);

		//Users fetch = mapper.readValue(responseString, Users.class);
		//System.out.println(fetch.getId());
		
		int id = TestUtil.getIntFromJsonResponse(responsJSON, "id");
		System.out.println(id);
	}
}
