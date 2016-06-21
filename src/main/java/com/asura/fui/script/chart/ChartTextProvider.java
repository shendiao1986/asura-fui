package com.asura.fui.script.chart;


public class ChartTextProvider implements IChartProvider {
	private String key;
	private String value;
	private String label;

	public ChartTextProvider() {
	}

	public ChartTextProvider(String key, String value, String label) {
		this.key = key;
		this.value = value;
		this.label = label;
	}

	public ChartData provide() {
		return ChartData.fromString(this.key, this.value, this.label);
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
