package com.asura.fui.apps.sites;

import java.util.Date;

import com.asura.tools.data.DataRecord;
import com.asura.tools.util.DateUtil;
import com.asura.fui.service.data.IOneDataProvider;
import com.asura.fui.FrontData;

public class SiteOneSpliterCurrentDateTimeProvider implements IOneDataProvider {
	public void build(FrontData data, DataRecord record) {
		String dateTimeNow = DateUtil.getDateAndTimeString(new Date());

		record.AddField("currentyear", dateTimeNow.substring(0, 4));
		record.AddField("currentmonth", dateTimeNow.substring(5, 7));
		record.AddField("currentday", dateTimeNow.substring(8, 10));
		record.AddField("currenthour", dateTimeNow.substring(11, 13));
		record.AddField("currentminute", dateTimeNow.substring(14, 16));
		record.AddField("currentsecond", dateTimeNow.substring(17, 19));
	}
}
