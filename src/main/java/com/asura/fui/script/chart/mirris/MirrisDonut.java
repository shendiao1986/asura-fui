package com.asura.fui.script.chart.mirris;

import java.util.ArrayList;
import java.util.List;

import com.cpkf.yyjd.tools.util.StringUtil;
import com.asura.fui.script.chart.ChartData;
import com.asura.fui.FrontData;

public class MirrisDonut extends AbstractMirrisChart {
	public String getChartType() {
		return "Donut";
	}

	public String toScript(FrontData paras) {
		ChartData chart = this.provider.provide();
		String s = "";

		s = s + "Morris." + getChartType() + "({\n";

		List list = new ArrayList();
		list.add("  element: '" + this.id + "'");
		if (chart.isNoData())
			list.add("  data: " + chart.toData());
		else {
			list.add("  data: " + toData(chart));
		}

		s = s + StringUtil.getStringFromStrings(list, ",\n");

		s = s + "\n});";
		return s;
	}

	private String toData(ChartData chart) {
		String s = "[\n";

		List list = new ArrayList();

		for (String key : chart.getKeys()) {
			String[] vs = chart.getValues(key);
			List subs = new ArrayList();

			subs.add("label: '" + key + "'");

			list.add("    { value: '" + vs[0] + "', " + StringUtil.getStringFromStrings(subs, ", ") + "}");
		}

		s = s + StringUtil.getStringFromStrings(list, ",\n") + "\n    ]";

		return s;
	}

	public String[] getOptions() {
		return new String[0];
	}
}
