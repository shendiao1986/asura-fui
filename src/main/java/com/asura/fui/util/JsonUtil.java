package com.asura.fui.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.asura.tools.data.DataRecord;
import com.asura.tools.util.math.NumberUtil;

public class JsonUtil {
	public static String toJson(DataRecord dr) {
		JSONObject jn = getJObject(dr);

		return jn.toString();
	}

	private static JSONObject getJObject(DataRecord dr) {
    JSONObject jn = new JSONObject();
    for (String key : dr.getAllFields()) {
      try {
        if ("value".equals(key)) {
          jn.put(key, NumberUtil.getInt(dr.getFieldValue(key)));
          break;
        }
        jn.put(key, dr.getFieldValue(key));
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }

    return jn;
  }

	public static String toJson(List<DataRecord> list) {
		JSONArray ja = new JSONArray();
		for (DataRecord dr : list) {
			ja.put(getJObject(dr));
		}

		return ja.toString();
	}
}