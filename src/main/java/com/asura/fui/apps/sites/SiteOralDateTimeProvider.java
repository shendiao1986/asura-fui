package com.asura.fui.apps.sites;

import java.text.ParseException;
import java.util.Date;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.util.DateUtil;
import com.asura.fui.service.data.IOneDataProvider;
import com.asura.fui.FrontData;

public class SiteOralDateTimeProvider implements IOneDataProvider {
	private static final String TAG = "oraldate";

	public void build(FrontData data, DataRecord record) {
		String dateTime = record.getFieldValue("date");

		Date date = DateUtil.getDateFromString(dateTime);
		try {
			int n = DateUtil.getWeeksAgo(date);

			if (n > 0) {
				record.AddField("oraldate", n + "周前");
				return;
			}
			int days=0;
			if (n == 0) {
				days = DateUtil.getDaysAgo(date);
			}
			switch (days) {
			case 0:
				record.AddField("oraldate", "今天");
				break;
			case 1:
				record.AddField("oraldate", "昨天");
				break;
			case 2:
				record.AddField("oraldate", "前天");
				break;
			case -1:
				record.AddField("oraldate", "明天");
				break;
			case -2:
				record.AddField("oraldate", "后天");
				break;
			default:
				if (days > 0) {
					record.AddField("oraldate", days + "天前");
					return;
				}
				record.AddField("oraldate", Math.abs(days) + "天后");

				return;

				//record.AddField("oraldate", n + "周后");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
