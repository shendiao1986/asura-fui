package com.asura.fui.apps.sites;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.asura.fui.service.data.IOneDataProvider;
import com.asura.fui.FrontData;

public class SiteOneSpliterDateTimeProvider implements IOneDataProvider {
	public void build(FrontData data, DataRecord record) {
		String dateTime = record.getFieldValue("date");

		record.AddField("year", dateTime.substring(0, 4));
		record.AddField("month", dateTime.substring(5, 7));
		record.AddField("day", dateTime.substring(8, 10));
		record.AddField("hour", dateTime.substring(11, 13));
		record.AddField("minute", dateTime.substring(14, 16));
		record.AddField("second", dateTime.substring(17, 19));
	}
}
