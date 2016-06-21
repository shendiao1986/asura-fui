import com.asura.fui.script.chart.mirris;


public class MirrisArea extends AbstractMirrisChart {
	public String getChartType() {
		return "Area";
	}

	public String[] getOptions() {
		return new String[] { "behaveLikeLine: true" };
	}
}