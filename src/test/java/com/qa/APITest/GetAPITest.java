package com.qa.APITest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.restclient.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
	TestBase testBase;
	RestClient restClient = new RestClient();
	String endPointUrl;
	String getUrl;
	String getAPIUrl;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void init() throws IOException {
		testBase = new TestBase();
		endPointUrl = prop.getProperty("endpointurl");
		getUrl = "/stores?state=MN";
		getAPIUrl = endPointUrl + getUrl;
	}

	@Test
	public void GetAPI() throws ClientProtocolException, IOException {
		HashMap<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");
		closeableHttpResponse = restClient.get(getAPIUrl, header);

		// status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, 200, "Status code is incorrect:Expected is 200:Actual is " + statusCode);

		// headers
		Header[] headersarray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> responseHeader = new HashMap<String, String>();
		for (Header head : headersarray) {
			responseHeader.put(head.getName(), head.getValue());
		}
		System.out.println(responseHeader);

		HashMap<String, String> expectedresponseHeader = new HashMap<String, String>();
		expectedresponseHeader.put("Transfer-Encoding", "chunked");
		expectedresponseHeader.put("Access-Control-Allow-Origin", "*");
		expectedresponseHeader.put("ETag", "W/\"7226-p6sbiiqpP3CYP8s3SVkFkvYPM8c\"");
		expectedresponseHeader.put("Connection", "keep-alive");
		expectedresponseHeader.put("Vary", "Accept, Accept-Encoding");
		expectedresponseHeader.put("Date", "Fri 10 Apr 2020 15:19:55 GMT");
		expectedresponseHeader.put("X-Powered-By", "Express");
		expectedresponseHeader.put("Allow", "GET,POST,PUT,PATCH,DELETE");
		expectedresponseHeader.put("Content-Type", "application/json; charset=utf-8");

		System.out.println(expectedresponseHeader);
		if (expectedresponseHeader.keySet().equals(responseHeader.keySet())) {
			System.out.println(responseHeader.keySet());
		}
		for (Entry<String, String> h : responseHeader.entrySet()) {
			String keyName = h.getKey();
			if (keyName.equals("Transfer-Encoding")) {
				Assert.assertEquals(responseHeader.get("Transfer-Encoding"),
						expectedresponseHeader.get("Transfer-Encoding"));
			} else if (keyName.equals("Access-Control-Allow-Origin")) {
				Assert.assertEquals(responseHeader.get("Access-Control-Allow-Origin"),
						expectedresponseHeader.get("Access-Control-Allow-Origin"));
			} else if (keyName.equals("ETag")) {
				Assert.assertEquals(responseHeader.get("ETag"), expectedresponseHeader.get("ETag"));
			} else if (keyName.equals("Connection")) {
				Assert.assertEquals(responseHeader.get("Connection"), expectedresponseHeader.get("Connection"));
			} else if (keyName.equals("Vary")) {
				Assert.assertEquals(responseHeader.get("Vary"), expectedresponseHeader.get("Vary"));
			} else if (keyName.equals("Date")) {
				Assert.assertTrue(responseHeader.get("Date").indexOf("2020") >= 0);
			} else if (keyName.equals("X-Powered-By")) {
				Assert.assertEquals(responseHeader.get("X-Powered-By"), expectedresponseHeader.get("X-Powered-By"));
			} else if (keyName.equals("Allow")) {
				Assert.assertEquals(responseHeader.get("Allow"), expectedresponseHeader.get("Allow"));
			} else if (keyName.equals("Content-Type")) {
				Assert.assertEquals(responseHeader.get("Content-Type"), expectedresponseHeader.get("Content-Type"));
			} else {
				System.out.println(keyName + responseHeader.get("keyName"));
			}
		}

		// response
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject responsJSON = new JSONObject(responseString);

		System.out.println(responsJSON);

		int total = TestUtil.getIntFromJsonResponse(responsJSON, "total");
		System.out.println("total: "+total);

		int limit = TestUtil.getIntFromJsonResponse(responsJSON, "limit");
		System.out.println("limit: "+limit);

		int skip = TestUtil.getIntFromJsonResponse(responsJSON, "skip");
		System.out.println("skip: "+skip);
		
		int data_len = TestUtil.getJsonArrayLength(responsJSON, "data");
		
		for (int i = 0; i < data_len; i++) {
			System.out.println("-------------data: "+i+" --------------");
			JSONObject data = TestUtil.getJsonObjectFromJsonArray(responsJSON, "data", i);
			//System.out.println(data);
			String zip = TestUtil.getStringFromJsonResponse(data, "zip");
			System.out.println("zip code: "+zip);
			
			String hours = TestUtil.getStringFromJsonResponse(data, "hours");
			System.out.println("hours: "+hours);
			
			String address = TestUtil.getStringFromJsonResponse(data, "address");
			System.out.println("address: "+address);
			
			float lng = TestUtil.getFloatFromJsonResponse(data, "lng");
			System.out.println("lng: "+lng);
			
			String address2 = TestUtil.getStringFromJsonResponse(data, "address2");
			System.out.println("address2: "+address2);
			
			String city = TestUtil.getStringFromJsonResponse(data, "city");
			System.out.println("city: "+city);

			String type = TestUtil.getStringFromJsonResponse(data, "type");
			System.out.println("type: "+type);
			
			String createdAt = TestUtil.getStringFromJsonResponse(data, "createdAt");
			System.out.println("createdAt: "+createdAt);
			
			String name = TestUtil.getStringFromJsonResponse(data, "name");
			System.out.println("name: "+name);
			
			int id = TestUtil.getIntFromJsonResponse(data, "id");
			System.out.println("id: "+id);
			
			String state = TestUtil.getStringFromJsonResponse(data, "state");
			System.out.println("state: "+state);
			
			float lat = TestUtil.getFloatFromJsonResponse(data, "lat");
			System.out.println("lat: "+lat);
			
			String updatedAt = TestUtil.getStringFromJsonResponse(data, "updatedAt");
			System.out.println("updatedAt: "+updatedAt);
			
			int services_len = TestUtil.getNestedJsonArrayLength(responsJSON, "data", i, "services");
			for (int j = 0; j < services_len; j++) {
				System.out.println("-----------services: "+j+" --------------");
				JSONObject services = TestUtil.getJsonObjectFromNestedJsonArray(responsJSON, "data", i, "services", j);
				//System.out.println(services);
				
				String createdAt1 = TestUtil.getStringFromJsonResponse(services, "createdAt");
				System.out.println("createdAt value for services: "+createdAt1);
				
				String name1 = TestUtil.getStringFromJsonResponse(services, "name");
				System.out.println("name value for services: "+name1);
				
				int id1 = TestUtil.getIntFromJsonResponse(services, "id");
				System.out.println("id value for services: "+id1);
				
				String updatedAt1 = TestUtil.getStringFromJsonResponse(services, "updatedAt");
				System.out.println("updatedAt value for services: "+updatedAt1);
				
				JSONObject storeservices = TestUtil.getJsonObjectFromJsonObjects(responsJSON, "data", i,
						"services", j, "storeservices");
				//System.out.println(storeservices);
				
				String createdAt2 = TestUtil.getStringFromJsonResponse(storeservices, "createdAt");
				System.out.println("createdAt value for store services: "+createdAt2);
				
				int storeId = TestUtil.getIntFromJsonResponse(storeservices, "storeId");
				System.out.println("storeId value for store services: "+storeId);
				
				int serviceId = TestUtil.getIntFromJsonResponse(storeservices, "serviceId");
				System.out.println("serviceId value for store services: "+serviceId);
				
				String updatedAt2 = TestUtil.getStringFromJsonResponse(storeservices, "updatedAt");
				System.out.println("updatedAt value for store services: "+updatedAt2);
				
				System.out.println("=============================================================");
			}
		}
	}

}
