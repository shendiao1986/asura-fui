package com.asura.fui.script.chart.mirris;

import java.util.ArrayList;
import java.util.List;

import com.asura.tools.util.StringUtil;
import com.asura.fui.script.chart.ChartData;
import com.asura.fui.script.chart.IChartProvider;
import com.asura.fui.script.chart.IUIChart;
import com.asura.fui.FrontData;

public abstract class AbstractMirrisChart implements IUIChart {
	protected IChartProvider provider;
	protected String id;
	protected boolean keyVar;
	protected boolean labelVar;
	protected boolean valueVar;

	public String toScript(FrontData paras) {
		ChartData chart = this.provider.provide();
		String s = "";

		s = s + "Morris." + getChartType() + "({\n";

		List list = new ArrayList();
		list.add("  element: '" + this.id + "'");
		list.add("  data: " + chart.toData());
		list.add("  xkey: " + getScriptFields(new String[] { chart.getKeyName(paras) }, this.keyVar));
		list.add("  ykeys:  " + getScriptFields(chart.getValueNames(paras), this.valueVar));
		list.add("  labels:  " + getScriptFields(chart.getLabels(paras), this.labelVar));

		if (getOptions() != null) {
			for (String op : getOptions()) {
				list.add("  " + op);
			}
		}

		s = s + StringUtil.getStringFromStrings(list, ",\n");

		s = s + "\n});";
		return s;
	}

	private String getScriptFields(String[] fields, boolean var) {
		List list = new ArrayList();

		for (String field : fields) {
			if (var)
				list.add(field);
			else {
				list.add("'" + field + "'");
			}
		}

		if (var) {
			return StringUtil.getStringFromStrings(list, ", ");
		}
		return "[ " + StringUtil.getStringFromStrings(list, ", ") + " ]";
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IChartProvider getProvider() {
		return this.provider;
	}

	public void setProvider(IChartProvider provider) {
		this.provider = provider;
	}

	public abstract String getChartType();

	public String[] getOptions() {
		return new String[0];
	}
}
