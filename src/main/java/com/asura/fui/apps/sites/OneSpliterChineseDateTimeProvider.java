package com.asura.fui.apps.sites;

import com.asura.tools.data.DataRecord;
import com.asura.fui.service.data.IOneDataProvider;
import com.asura.fui.FrontData;

public class OneSpliterChineseDateTimeProvider implements IOneDataProvider {
	public void build(FrontData data, DataRecord record) {
		record.AddField("cnmonth", getChineseMonth(Integer.parseInt(record.getFieldValue("month"))));
	}

	private String getChineseMonth(int month) {
		String str = "";
		switch (month) {
		case 1:
			str = "一";
			break;
		case 2:
			str = "二";
			break;
		case 3:
			str = "三";
			break;
		case 4:
			str = "四";
			break;
		case 5:
			str = "五";
			break;
		case 6:
			str = "六";
			break;
		case 7:
			str = "七";
			break;
		case 8:
			str = "八";
			break;
		case 9:
			str = "九";
			break;
		case 10:
			str = "十";
			break;
		case 11:
			str = "十一";
			break;
		case 12:
			str = "十二";
		}

		return str;
	}
}
