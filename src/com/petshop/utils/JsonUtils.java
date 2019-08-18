package com.petshop.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

	public static JSONArray getJsonArray(String str){
		if(str==null) str="";
		str=str.trim();
		JSONArray ja;
		if(str.length()!=0&&str.charAt(0)=='{'&&str.charAt(str.length()-1)=='}')
			try {
				ja=new JSONArray();
				ja.put(new JSONObject(str));
				return ja;
			} catch (JSONException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		else if(str.length()!=0&&str.charAt(0)=='['&&str.charAt(str.length()-1)==']')
			try {
				ja=new JSONArray(str);
				return ja;
			} catch (JSONException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		return null;
	}
	
	
}
