import com.asura.fui.script.chart;


public class ChartSimpleProvider implements IChartProvider {
	private String label;
	private String dataVar;
	private String keyName;
	private String valueNames;

	public ChartData provide() {
		ChartData cd = ChartData.fromString("", "", this.label);
		cd.setKeyName(this.keyName);
		cd.setValueNames(this.valueNames);
		cd.setDataVar(this.dataVar);

		return cd;
	}

	public String getDataVar() {
		return this.dataVar;
	}

	public void setDataVar(String dataVar) {
		this.dataVar = dataVar;
	}

	public String getKeyName() {
		return this.keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getValueNames() {
		return this.valueNames;
	}

	public void setValueNames(String valueNames) {
		this.valueNames = valueNames;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
