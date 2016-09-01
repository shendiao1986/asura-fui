package com.asura.fui.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Properties;
import java.util.Set;



public class StaticCacheService {
	private static Properties settings = new Properties();

	private StaticCacheService() {
		super();
	}

	public static void loadSettingResource() {

		InputStream in = StaticCacheService.class.getResourceAsStream("/fui_setting.properties");
		try {
			settings.load(in);
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getSettingProperty(String key) {
		if(settings.isEmpty())
			loadSettingResource();
		return settings.getProperty(key);
	}
	
	public static String getKeyByValue(String value){
		if(settings.isEmpty())
			loadSettingResource();
		Set<Map.Entry<Object,Object>> entries = settings.entrySet();
		for(Map.Entry<Object,Object> entry : entries){
			if(((String)entry.getValue()).equals(value)){
                  return (String)entry.getKey();				
			}
		}
		return value;
	}

}
