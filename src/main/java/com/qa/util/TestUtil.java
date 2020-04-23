package com.qa.util;

import org.json.JSONArray;
import org.json.JSONObject;

import com.qa.base.TestBase;

public class TestUtil {
	public static String getStringFromJsonResponse(JSONObject jsonobj, String key) {
		String value = jsonobj.getString(key);
		return value;
	}

	public static int getIntFromJsonResponse(JSONObject jsonobj, String key) {
		int value = jsonobj.getInt(key);
		return value;
	}

	public static float getFloatFromJsonResponse(JSONObject jsonobj, String key) {
		float value = jsonobj.getFloat(key);
		return value;
	}

	public static JSONObject getJsonObjectFromJsonObjects(JSONObject jsonobj, String arrname1, int index1,
			String arrname2, int index2, String key) {
		JSONArray jsonarr1 = jsonobj.getJSONArray(arrname1);
		JSONObject jsonobj1 = jsonarr1.getJSONObject(index1);
		JSONArray jsonarr2 = jsonobj1.getJSONArray(arrname2);
		JSONObject jsonobj2 = jsonarr2.getJSONObject(index2);
		JSONObject jsonobj3 = jsonobj2.getJSONObject(key);
		return jsonobj3;
	}

	public static JSONObject getJsonObjectFromJsonArray(JSONObject jsonobj, String arrname, int index) {
		JSONArray jsonarr = jsonobj.getJSONArray(arrname);
		JSONObject jsonobj1 = jsonarr.getJSONObject(index);
		return jsonobj1;
	}

	public static JSONObject getJsonObjectFromNestedJsonArray(JSONObject jsonobj, String arrname1, int index1,
			String arrname2, int index2) {
		JSONArray jsonarr1 = jsonobj.getJSONArray(arrname1);
		JSONObject jsonobj1 = jsonarr1.getJSONObject(index1);
		JSONArray jsonarr2 = jsonobj1.getJSONArray(arrname2);
		JSONObject jsonobj2 = jsonarr2.getJSONObject(index2);
		return jsonobj2;
	}

	public static int getJsonArrayLength(JSONObject jsonobj, String arrname) {
		JSONArray jsonarr = jsonobj.getJSONArray(arrname);
		int length = jsonarr.length();
		return length;
	}

	public static int getNestedJsonArrayLength(JSONObject jsonobj1, String arrname1, int index1, String arrname2) {
		JSONArray jsonarr1 = jsonobj1.getJSONArray(arrname1);
		JSONObject jsonobjx = jsonarr1.getJSONObject(index1);
		JSONArray jsonarr2 = jsonobjx.getJSONArray(arrname2);
		int length = jsonarr2.length();
		return length;
	}
}
