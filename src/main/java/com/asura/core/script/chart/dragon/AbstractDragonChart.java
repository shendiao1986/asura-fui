import com.asura.fui.script.chart.dragon;

import java.util.ArrayList;
import java.util.List;

import com.cpkf.yyjd.tools.util.StringUtil;
import com.asura.core.script.chart.ChartData;
import com.asura.core.script.chart.IChartProvider;
import com.asura.core.script.chart.IUIChart;
import com.asura.core.util.ParamterUtil;
import com.asura.fui.FrontData;

public abstract class AbstractDragonChart implements IUIChart {
	protected IChartProvider provider;
	protected String click;
	protected String id;
	protected String skin;

	public String toScript(FrontData paras) {
		ChartData chart = this.provider.provide();

		if (StringUtil.isNullOrEmpty(this.skin)) {
			this.skin = "RadialGradient_SkyBlue";
		}

		List list = new ArrayList();

		list.add(" var options = {");
		list.add("     animation: true,");
		System.out.println("dragon para:" + paras);
		list.add(getCommonOption() + ",");
		for (String op : getOptions()) {
			list.add("    " + ParamterUtil.getValue(op, paras) + ",");
		}
		list.add("     legend: { sidelength: 10, fontsize: 13, enablecontrol: true },");
		list.add(" };");
		list.add(" window.dchart = new DChart." + getChartType() + "('" + this.id + "', 'CN');");
		list.add(" dchart.SetSkin('" + this.skin + "');");
		list.add(" dchart.SetOptions(options);");
		list.add(" dchart.Draw(" + chart.toData() + ");");

		return StringUtil.getStringFromStrings(list, "\n");
	}

	private String getCommonOption() {
		String op = "BackGround:{BorderWidth:1,BorderColor:'#000000',LinearGradient:{Location:{minX:0,minY:0,maxX:null,maxY:null},ColorStops:[{offset:0,color:'#87CEEB'},{offset:0.5,color:'#ffffff'},{offset:1,color:'#87CEEB'}]}},MarbleFaceColor:'#87CEEB',SplitLineColor:'#87CEEB',LegendBorderColor:'#00BFFF',ScaleLineColor:'#ffffff',ScaleBackColors:['#87CEEB','#ADD8E6'],ShadowColor:'#000000',Pie:{SeparateLineColor:'#000000',OuterLabelBackColor:'rgba(255,255,255,0.3)'},Pie3D:{SeparateLineColor:'#000000',OuterLabelBackColor:'rgba(255,255,255,0.3)'},NestedPie:{SeparateLineColor:'#000000',OuterLabelBackColor:'rgba(255,255,255,0.3)'},NestedPie3D:{SeparateLineColor:'#000000',OuterLabelBackColor:'rgba(255,255,255,0.3)'},Ring:{SeparateLineColor:'#000000',OuterLabelBackColor:'rgba(255,255,255,0.3)'},Ring3D:{SeparateLineColor:'#000000',OuterLabelBackColor:'rgba(255,255,255,0.3)'},MultiRing:{SeparateLineColor:'#000000',OuterLabelBackColor:'rgba(255,255,255,0.3)'},MultiRing3D:{SeparateLineColor:'#000000',OuterLabelBackColor:'rgba(255,255,255,0.3)'},Polar:{SeparateLineColor:'#000000',OuterLabelBackColor:'rgba(255,255,255,0.3)',ScaleLineColors:'rgba(0,0,0,0.6)'},Polar3D:{SeparateLineColor:'#000000',OuterLabelBackColor:'rgba(255,255,255,0.3)',ScaleLineColors:'rgba(0,0,0,0.6)'},Line:{NodeLineColor:'#ffffff'},Points:{NodeLineColor:'#ffffff'},Area:{NodeLineColor:'#ffffff'},Radar:{ScaleLineColor:'rgba(0,0,0,0.6)',StaffBackColor:'rgba(0,0,0,0.2)',RadarNodeLinecolors:['#ffffff'],LabelsFontColors:'#000000'},RangeArea:{NodeLineColor:'#ffffff'}";

		return op;
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