package com.qa.apartmentManager.util;

import com.google.gson.Gson;

public class JSONUtil {

	private static Gson gson = new Gson();

	public static String getJSONForObject(Object obj) {
		return gson.toJson(obj);
	}

	public static <T> T getObjectForJSON(String jsonString, Class<T> clazz) {
		return gson.fromJson(jsonString, clazz);
	}

}