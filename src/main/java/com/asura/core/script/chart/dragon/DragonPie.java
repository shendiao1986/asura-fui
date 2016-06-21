import com.asura.fui.script.chart.dragon;


public class DragonPie extends AbstractDragonChart {
	private String suffix;

	public String getChartType() {
		return "Pie3D";
	}

	public String[] getOptions() {
		return new String[] {
				"outerLabel:{content: function(data) {return data.text + ' ' + data.value + '" + this.suffix + "';}}",
				"click: function(data,e){" + this.click + "}" };
	}
}
