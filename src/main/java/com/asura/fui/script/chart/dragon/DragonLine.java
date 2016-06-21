package com.asura.fui.script.chart.dragon;

public class DragonLine extends AbstractDragonChart {
	private String suffix;

	public String getChartType() {
		return "Line";
	}

	public String[] getOptions() {
		return new String[] {
				"outerLabel:{content: function(data) {return data.text + ' ' + data.value + '" + this.suffix + "';}}",
				"click: function(data,e){" + this.click + "}" };
	}
}
