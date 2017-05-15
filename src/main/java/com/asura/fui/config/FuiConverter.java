package com.asura.fui.config;

import java.util.ArrayList;

import com.asura.fui.FuiPage;
import com.asura.fui.PageDef;
import com.asura.fui.apps.sites.SiteArticleProvider;
import com.asura.fui.apps.sites.SiteArticlesProvider;
import com.asura.fui.apps.sites.SiteArticlesRandomProvider;
import com.asura.fui.apps.sites.SiteCatRelatedArticlesProvider;
import com.asura.fui.apps.sites.SiteHomeProvider;
import com.asura.fui.apps.sites.SiteOneCatProvider;
import com.asura.fui.component.AbstractUIComponent;
import com.asura.fui.component.bootstrap.BootBadge;
import com.asura.fui.component.bootstrap.BootBlock1;
import com.asura.fui.component.bootstrap.BootBreadCrumb;
import com.asura.fui.component.bootstrap.BootButton;
import com.asura.fui.component.bootstrap.BootButtons;
import com.asura.fui.component.bootstrap.BootChart;
import com.asura.fui.component.bootstrap.BootDialog;
import com.asura.fui.component.bootstrap.BootFooter;
import com.asura.fui.component.bootstrap.BootHeader;
import com.asura.fui.component.bootstrap.BootNavi;
import com.asura.fui.component.bootstrap.BootNotification;
import com.asura.fui.component.bootstrap.BootPage;
import com.asura.fui.component.bootstrap.BootPageNoWrap;
import com.asura.fui.component.bootstrap.BootPanel;
import com.asura.fui.component.bootstrap.BootProgress;
import com.asura.fui.component.bootstrap.BootTab;
import com.asura.fui.component.bootstrap.SimpleRadio;
import com.asura.fui.component.custom.AlternateList;
import com.asura.fui.component.custom.HeadElementList;
import com.asura.fui.component.custom.ListViewConditionColumn;
import com.asura.fui.component.custom.SimpleDiv;
import com.asura.fui.component.custom.SimpleFilter;
import com.asura.fui.component.custom.SimpleHr;
import com.asura.fui.component.custom.SimpleIFrame;
import com.asura.fui.component.custom.SimpleImg;
import com.asura.fui.component.custom.SimpleInput;
import com.asura.fui.component.custom.SimpleLabel;
import com.asura.fui.component.custom.SimpleList;
import com.asura.fui.component.custom.SimpleListView;
import com.asura.fui.component.custom.SimpleNavi;
import com.asura.fui.component.custom.SimpleNode;
import com.asura.fui.component.custom.SimpleSelect;
import com.asura.fui.component.custom.SimpleText;
import com.asura.fui.component.custom.SimpleUrl;
import com.asura.fui.component.custom.TitledGroup;
import com.asura.fui.component.data.AbstractUIData;
import com.asura.fui.component.data.AlternateDataList;
import com.asura.fui.component.data.DataBadge;
import com.asura.fui.component.data.DataBlock1;
import com.asura.fui.component.data.DataButtonGroup;
import com.asura.fui.component.data.DataChart;
import com.asura.fui.component.data.DataDialog;
import com.asura.fui.component.data.DataDiv;
import com.asura.fui.component.data.DataFilter;
import com.asura.fui.component.data.DataFooter;
import com.asura.fui.component.data.DataForm;
import com.asura.fui.component.data.DataHeader;
import com.asura.fui.component.data.DataIFrame;
import com.asura.fui.component.data.DataInput;
import com.asura.fui.component.data.DataLabel;
import com.asura.fui.component.data.DataList;
import com.asura.fui.component.data.DataListView;
import com.asura.fui.component.data.DataNavi;
import com.asura.fui.component.data.DataNotification;
import com.asura.fui.component.data.DataPage;
import com.asura.fui.component.data.DataPanel;
import com.asura.fui.component.data.DataProgress;
import com.asura.fui.component.data.DataRef;
import com.asura.fui.component.data.DataSet;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.data.UIDatas;
import com.asura.fui.component.data.basic.DataHr;
import com.asura.fui.component.data.basic.DataImg;
import com.asura.fui.component.data.basic.DataText;
import com.asura.fui.component.data.basic.DataUrl;
import com.asura.fui.component.event.UIEvent;
import com.asura.fui.component.event.UIEvents;
import com.asura.fui.component.layout.ForgeLayout;
import com.asura.fui.component.layout.LayoutValue;
import com.asura.fui.component.layout.LayoutValueSet;
import com.asura.fui.component.layout.SimpleLayout;
import com.asura.fui.script.IUIScript;
import com.asura.fui.script.ParaMapping;
import com.asura.fui.script.ScriptAssign;
import com.asura.fui.script.ScriptChangeHtml;
import com.asura.fui.script.ScriptCondition;
import com.asura.fui.script.ScriptFunction;
import com.asura.fui.script.ScriptParaMapping;
import com.asura.fui.script.ScriptParseJson;
import com.asura.fui.script.ScriptSimple;
import com.asura.fui.script.ScriptSwitch;
import com.asura.fui.script.UIScripts;
import com.asura.fui.script.chart.ChartSimpleProvider;
import com.asura.fui.script.chart.ChartTextProvider;
import com.asura.fui.script.chart.dragon.AbstractDragonChart;
import com.asura.fui.script.chart.dragon.DragonLine;
import com.asura.fui.script.chart.dragon.DragonPie;
import com.asura.fui.script.chart.mirris.AbstractMirrisChart;
import com.asura.fui.script.chart.mirris.MirrisArea;
import com.asura.fui.script.chart.mirris.MirrisBar;
import com.asura.fui.script.chart.mirris.MirrisDonut;
import com.asura.fui.script.chart.mirris.MirrisLine;
import com.asura.fui.script.jquery.JQueryDatePicker;
import com.asura.fui.script.jquery.JQueryGet;
import com.asura.fui.service.data.DataProvider;
import com.asura.fui.service.data.MysqlDataProvider;
import com.asura.fui.service.data.MysqlExecuteDataProvider;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class FuiConverter {
	public static IUIData getData(String xml) {
		return ((IUIData) getXstream().fromXML(xml));
	}

	public static IUIScript getScript(String xml) {
		return ((IUIScript) getXstream().fromXML(xml));
	}

	public static String toXml(FuiPage page) {
		return getXstream().toXML(page);
	}

	public static FuiPage getPage(String xml) {
		return ((FuiPage) getXstream().fromXML(xml));
	}

	private static XStream getXstream() {
		XStream xs = new XStream(new DomDriver());

		xs.alias("fui-page", FuiPage.class);
		xs.aliasAttribute(FuiPage.class, "ref", "ref");
		xs.alias("ui-datas", UIDatas.class);
		xs.addImplicitCollection(UIDatas.class, "datas");
		xs.addImplicitCollection(UIScripts.class, "scripts");

		xs.alias("def", PageDef.class);
		xs.aliasAttribute(PageDef.class, "title", "title");
		xs.aliasAttribute(PageDef.class, "keyword", "keyword");
		xs.aliasAttribute(PageDef.class, "desc", "desc");

		setLayout(xs);
		setUIData(xs);
		setBootstrap(xs);
		setMirris(xs);
		setDragonChart(xs);
		setScripts(xs);
		setEvents(xs);

		setDataProvider(xs);

		return xs;
	}

	public static void setDataProvider(XStream xs) {
		xs.alias("data-provider", DataProvider.class);

		xs.alias("mysql-provider", MysqlDataProvider.class);
		xs.aliasAttribute(MysqlDataProvider.class, "sql", "sql");
		xs.aliasAttribute(MysqlDataProvider.class, "single", "single");

		xs.alias("mysql-execute-provider", MysqlExecuteDataProvider.class);
		xs.aliasAttribute(MysqlExecuteDataProvider.class, "sql", "sql");
		xs.aliasAttribute(MysqlExecuteDataProvider.class, "datasource", "datasource");

		xs.addImplicitCollection(SiteArticlesProvider.class, "oneDataProviders");
		xs.aliasField("one-data-providers", SiteArticlesProvider.class, "oneDataProviders");

		xs.addImplicitCollection(SiteCatRelatedArticlesProvider.class, "oneDataProviders");
		xs.aliasField("one-data-providers", SiteCatRelatedArticlesProvider.class, "oneDataProviders");

		xs.addImplicitCollection(SiteArticlesRandomProvider.class, "oneDataProviders");
		xs.aliasField("one-data-providers", SiteArticlesRandomProvider.class, "oneDataProviders");

		xs.addImplicitCollection(SiteArticleProvider.class, "oneDataProviders");
		xs.aliasField("one-data-providers", SiteArticleProvider.class, "oneDataProviders");

		xs.addImplicitCollection(SiteHomeProvider.class, "oneDataProviders");
		xs.aliasField("one-data-providers", SiteHomeProvider.class, "oneDataProviders");
	}

	private static void setEvents(XStream xs) {
		xs.addImplicitCollection(UIEvents.class, "events");

		xs.alias("event", UIEvent.class);
		xs.aliasAttribute(UIEvent.class, "key", "key");
		xs.aliasAttribute(UIEvent.class, "name", "name");
		xs.aliasAttribute(UIEvent.class, "event", "event");
	}

	private static void setScripts(XStream xs) {
		// xs.alias("script", IUIChart.class);

		xs.alias("function", ScriptFunction.class);
		xs.aliasAttribute(ScriptFunction.class, "name", "name");
		xs.aliasAttribute(ScriptFunction.class, "vars", "vars");
		xs.aliasAttribute(ScriptFunction.class, "callback", "callback");
		xs.addImplicitCollection(ScriptFunction.class, "lines");

		xs.alias("jquery-get", JQueryGet.class);
		xs.aliasAttribute(JQueryGet.class, "url", "url");
		xs.aliasAttribute(JQueryGet.class, "urlVar", "url-var");
		xs.aliasAttribute(JQueryGet.class, "consts", "consts");
		xs.aliasAttribute(JQueryGet.class, "vars", "vars");

		xs.alias("jquery-datepicker", JQueryDatePicker.class);
		xs.aliasAttribute(JQueryDatePicker.class, "id", "id");
		xs.aliasAttribute(JQueryDatePicker.class, "format", "format");

		xs.alias("parse-json", ScriptParseJson.class);
		xs.aliasAttribute(ScriptParseJson.class, "in", "in");
		xs.aliasAttribute(ScriptParseJson.class, "out", "out");

		xs.alias("simple-script", ScriptSimple.class);
		xs.aliasAttribute(ScriptSimple.class, "script", "script");

		xs.alias("change-html", ScriptChangeHtml.class);
		xs.aliasAttribute(ScriptChangeHtml.class, "id", "id");
		xs.aliasAttribute(ScriptChangeHtml.class, "content", "content");
		xs.aliasAttribute(ScriptChangeHtml.class, "var", "var");

		xs.alias("para-mapping", ScriptParaMapping.class);
		xs.addImplicitCollection(ScriptParaMapping.class, "mappings");
		xs.alias("para-map", ParaMapping.class);
		xs.aliasAttribute(ParaMapping.class, "from", "from");
		xs.aliasAttribute(ParaMapping.class, "to", "to");

		xs.alias("assign", ScriptAssign.class);
		xs.aliasAttribute(ScriptAssign.class, "name", "name");
		xs.aliasAttribute(ScriptAssign.class, "value", "value");
		xs.aliasAttribute(ScriptAssign.class, "first", "first");
		xs.aliasAttribute(ScriptAssign.class, "quote", "quote");

		xs.alias("switch", ScriptSwitch.class);
		xs.addImplicitCollection(ScriptSwitch.class, "conditions");

		xs.alias("condition", ScriptCondition.class);
		xs.addImplicitCollection(ScriptCondition.class, "scripts");
		xs.aliasAttribute(ScriptCondition.class, "con", "con");
	}

	private static void setDragonChart(XStream xs) {
		xs.aliasAttribute(AbstractDragonChart.class, "id", "id");
		xs.aliasAttribute(AbstractDragonChart.class, "click", "click");

		xs.alias("dragon-pie", DragonPie.class);
		xs.aliasAttribute(DragonPie.class, "suffix", "suffix");

		xs.alias("dragon-line", DragonLine.class);
		xs.aliasAttribute(DragonLine.class, "suffix", "suffix");
	}

	private static void setMirris(XStream xs) {
		xs.aliasAttribute(AbstractMirrisChart.class, "id", "id");
		xs.aliasAttribute(AbstractMirrisChart.class, "keyVar", "key-var");
		xs.aliasAttribute(AbstractMirrisChart.class, "labelVar", "label-var");
		xs.aliasAttribute(AbstractMirrisChart.class, "valueVar", "value-var");

		xs.alias("mirris-line", MirrisLine.class);
		xs.alias("mirris-bar", MirrisBar.class);
		xs.alias("mirris-area", MirrisArea.class);
		xs.alias("mirris-donut", MirrisDonut.class);

		xs.alias("chart-simple-provider", ChartSimpleProvider.class);
		xs.aliasAttribute(ChartSimpleProvider.class, "dataVar", "data-var");
		xs.aliasAttribute(ChartSimpleProvider.class, "label", "label");
		xs.aliasAttribute(ChartSimpleProvider.class, "keyName", "key-name");
		xs.aliasAttribute(ChartSimpleProvider.class, "valueNames", "value-names");

		xs.alias("chart-text-provider", ChartTextProvider.class);
		xs.aliasAttribute(ChartTextProvider.class, "key", "key");
		xs.aliasAttribute(ChartTextProvider.class, "value", "value");
		xs.aliasAttribute(ChartTextProvider.class, "label", "label");
	}

	private static void setBootstrap(XStream xs) {
		xs.aliasAttribute(AbstractUIComponent.class, "styles", "styles");
		xs.aliasAttribute(AbstractUIComponent.class, "hidden", "hidden");
		xs.aliasAttribute(AbstractUIComponent.class, "attrs", "attrs");
		xs.aliasAttribute(AbstractUIComponent.class, "className", "class-name");

		xs.alias("boot-badge", BootBadge.class);
		xs.alias("boot-bread", BootBreadCrumb.class);

		xs.alias("boot-navi", BootNavi.class);
		xs.aliasAttribute(BootNavi.class, "pos", "pos");
		xs.aliasAttribute(BootNavi.class, "fluid", "fluid");
		xs.aliasAttribute(BootNavi.class, "inverse", "inverse");
		xs.aliasAttribute(BootNavi.class, "vertical", "vertical");
		xs.aliasAttribute(BootNavi.class, "haslogon", "haslogon");

		xs.alias("boot-panel", BootPanel.class);
		xs.aliasAttribute(BootPanel.class, "head", "use-head");
		xs.aliasAttribute(BootPanel.class, "tail", "use-tail");
		xs.aliasAttribute(BootPanel.class, "style", "style");

		xs.alias("boot-buttons", BootButtons.class);
		xs.aliasAttribute(BootButtons.class, "multi", "multi");
		xs.aliasAttribute(BootButtons.class, "dialog", "open-dialog");
		xs.aliasAttribute(BootButtons.class, "dialogIn", "in-dialog");
		xs.aliasAttribute(BootButtons.class, "type", "btn-type");

		xs.alias("simple-radio", SimpleRadio.class);
		xs.aliasAttribute(SimpleRadio.class, "click", "click");
		xs.aliasAttribute(SimpleRadio.class, "ml", "ml");

		xs.alias("boot-chart", BootChart.class);

		xs.alias("boot-block1", BootBlock1.class);

		xs.alias("simple-list", SimpleList.class);
		xs.aliasAttribute(SimpleList.class, "row", "row");
		xs.aliasAttribute(SimpleList.class, "column", "column");
		xs.aliasAttribute(SimpleList.class, "hrStyle", "hr-style");
		xs.aliasAttribute(SimpleList.class, "wrapNode", "wrap-node");
		xs.aliasAttribute(SimpleList.class, "wrapNodeAttrs", "wrap-node-attrs");
		xs.aliasAttribute(SimpleList.class, "wrapNodeContent", "wrap-node-content");

		xs.alias("alternate-list", AlternateList.class);
		xs.aliasAttribute(AlternateList.class, "row", "row");
		xs.aliasAttribute(AlternateList.class, "column", "column");
		xs.aliasAttribute(AlternateList.class, "hrStyle", "hr-style");
		xs.aliasAttribute(AlternateList.class, "wrapNode", "wrap-node");
		xs.aliasAttribute(AlternateList.class, "wrapNodeAttrs", "wrap-node-attrs");

		xs.alias("head-element-list", HeadElementList.class);
		xs.aliasAttribute(HeadElementList.class, "row", "row");
		xs.aliasAttribute(HeadElementList.class, "column", "column");
		xs.aliasAttribute(HeadElementList.class, "hrStyle", "hr-style");
		xs.aliasAttribute(HeadElementList.class, "wrapNode", "wrap-node");
		xs.aliasAttribute(HeadElementList.class, "wrapNodeAttrs", "wrap-node-attrs");

		xs.alias("simple-url", SimpleUrl.class);
		xs.aliasAttribute(SimpleUrl.class, "wrapNode", "wrap-node");
		xs.aliasAttribute(SimpleUrl.class, "wrapNodeAttrs", "wrap-node-attrs");
		xs.aliasAttribute(SimpleUrl.class, "wrapNodeContent", "wrap-node-content");

		xs.alias("simple-img", SimpleImg.class);

		xs.alias("simple-text", SimpleText.class);
		xs.aliasAttribute(SimpleText.class, "multi", "multi");
		xs.aliasAttribute(SimpleText.class, "tag", "tag");

		xs.alias("boot-header", BootHeader.class);

		xs.alias("simple-div", SimpleDiv.class);
		xs.aliasAttribute(SimpleDiv.class, "styles", "styles");
		xs.aliasAttribute(SimpleDiv.class, "attrs", "attrs");

		xs.alias("titled-group", TitledGroup.class);
		xs.aliasAttribute(TitledGroup.class, "headStyle", "head-style");
		xs.aliasAttribute(TitledGroup.class, "hrStyle", "hr-style");

		xs.alias("simple-hr", SimpleHr.class);

		xs.alias("boot-page", BootPage.class);
		xs.aliasAttribute(BootPage.class, "count", "count");
		xs.aliasAttribute(BootPage.class, "max", "max");

		xs.alias("boot-page-nowrap", BootPageNoWrap.class);
		xs.aliasAttribute(BootPageNoWrap.class, "count", "count");
		xs.aliasAttribute(BootPageNoWrap.class, "max", "max");

		xs.alias("boot-progress", BootProgress.class);

		xs.alias("simple-listview", SimpleListView.class);
		xs.aliasAttribute(SimpleListView.class, "width", "width");
		xs.aliasAttribute(SimpleListView.class, "headerStyle", "header-style");
		xs.aliasAttribute(SimpleListView.class, "columnStyle", "column-style");
		xs.aliasAttribute(SimpleListView.class, "hasCheckBox", "hasCheckBox");
		xs.aliasAttribute(SimpleListView.class, "checkboxKey", "checkboxKey");

		xs.alias("condition-column", ListViewConditionColumn.class);
		xs.aliasAttribute(ListViewConditionColumn.class, "field", "field");
		xs.aliasAttribute(ListViewConditionColumn.class, "values", "values");
		xs.aliasAttribute(ListViewConditionColumn.class, "colors", "colors");

		xs.alias("simple-filter", SimpleFilter.class);
		xs.aliasAttribute(SimpleFilter.class, "check", "check");
		xs.aliasAttribute(SimpleFilter.class, "row", "row");
		xs.aliasAttribute(SimpleFilter.class, "column", "column");

		xs.alias("boot-dialog", BootDialog.class);

		xs.alias("simple-navi", SimpleNavi.class);
		xs.alias("simple-node", SimpleNode.class);
		xs.aliasAttribute(SimpleNode.class, "node", "node");
		xs.aliasAttribute(SimpleNode.class, "content", "content");
		xs.aliasAttribute(SimpleNode.class, "wrapNode", "wrap-node");
		xs.aliasAttribute(SimpleNode.class, "wrapNodeAttrs", "wrap-node-attrs");
		xs.aliasAttribute(SimpleNode.class, "wrapNodeContent", "wrap-node-content");

		xs.alias("simple-input", SimpleInput.class);
		xs.aliasAttribute(SimpleInput.class, "twoline", "two-line");
		xs.aliasAttribute(SimpleInput.class, "type", "type");
		xs.aliasAttribute(SimpleInput.class, "bold", "bold");
		xs.aliasAttribute(SimpleInput.class, "value", "value");
		xs.aliasAttribute(SimpleInput.class, "readonly", "readonly");
		xs.aliasAttribute(SimpleInput.class, "percent", "percent");

		xs.alias("boot-button", BootButton.class);
		xs.aliasAttribute(BootButton.class, "type", "type");
		xs.aliasAttribute(BootButton.class, "style", "style");

		xs.alias("boot-tab", BootTab.class);
		xs.aliasAttribute(BootTab.class, "pill", "pill");

		xs.alias("simple-label", SimpleLabel.class);
		xs.aliasAttribute(SimpleLabel.class, "bold", "bold");
		xs.aliasAttribute(SimpleLabel.class, "twoline", "two-line");

		xs.alias("simple-iframe", SimpleIFrame.class);

		xs.alias("simple-select", SimpleSelect.class);
		xs.aliasAttribute(SimpleSelect.class, "bold", "bold");
		xs.aliasAttribute(SimpleSelect.class, "twoline", "twoline");
		xs.aliasAttribute(SimpleSelect.class, "percent", "percent");

		xs.alias("boot-notification", BootNotification.class);
		xs.aliasAttribute(BootNotification.class, "head", "use-head");
		xs.aliasAttribute(BootNotification.class, "style", "style");
		xs.aliasAttribute(BootNotification.class, "displaylength", "displaylength");

		xs.alias("boot-footer", BootFooter.class);
		xs.aliasAttribute(BootFooter.class, "textfield", "text-field");
		xs.aliasAttribute(BootFooter.class, "mongokey", "mongo-key");
		xs.aliasAttribute(BootFooter.class, "mongodb", "mongo-db-name");
		xs.aliasAttribute(BootFooter.class, "mysqlkey", "mysql-key");
		xs.aliasAttribute(BootFooter.class, "tablename", "table-name");

	}

	private static void setUIData(XStream xs) {
		xs.aliasField("component", AbstractUIData.class, "component");
		xs.aliasAttribute(AbstractUIData.class, "key", "key");

		xs.alias("data-navi", DataNavi.class);
		xs.aliasAttribute(DataNavi.class, "url", "url");
		xs.aliasAttribute(DataNavi.class, "parent", "parent");
		xs.aliasAttribute(DataNavi.class, "selected", "selected");
		xs.aliasAttribute(DataNavi.class, "head", "head");
		xs.aliasAttribute(DataNavi.class, "right", "right");

		xs.alias("data-panel", DataPanel.class);
		xs.aliasAttribute(DataPanel.class, "head", "head");
		xs.aliasAttribute(DataPanel.class, "tail", "tail");
		xs.addImplicitCollection(DataPanel.class, "children");

		xs.alias("data-badge", DataBadge.class);
		xs.aliasAttribute(DataBadge.class, "name", "name");
		xs.aliasAttribute(DataBadge.class, "url", "url");
		xs.aliasAttribute(DataBadge.class, "count", "count");

		xs.alias("data-group", DataButtonGroup.class);
		xs.aliasAttribute(DataButtonGroup.class, "keyLabels", "key-labels");
		xs.aliasAttribute(DataButtonGroup.class, "keyDescs", "key-descs");
		xs.aliasAttribute(DataButtonGroup.class, "keyUrls", "key-urls");
		xs.aliasAttribute(DataButtonGroup.class, "group", "group");
		xs.aliasAttribute(DataButtonGroup.class, "selected", "selected");

		xs.alias("data-chart", DataChart.class);

		xs.alias("data-block1", DataBlock1.class);
		xs.aliasAttribute(DataBlock1.class, "img", "img");
		xs.aliasAttribute(DataBlock1.class, "title", "title");
		xs.aliasAttribute(DataBlock1.class, "desc", "desc");
		xs.aliasAttribute(DataBlock1.class, "url", "url");
		xs.aliasAttribute(DataBlock1.class, "buttons", "buttons");

		xs.alias("data-list", DataList.class);
		xs.alias("alternate-data-list", AlternateDataList.class);
		xs.aliasField("alter-data", AlternateDataList.class, "alterData");

		xs.alias("data-set", DataSet.class);
		xs.aliasAttribute(DataSet.class, "tag", "tag");
		xs.aliasAttribute(DataSet.class, "attrs", "attrs");
		xs.aliasAttribute(DataSet.class, "content", "content");
		xs.addImplicitCollection(DataSet.class, "set");

		xs.alias("data-text", DataText.class);
		xs.aliasAttribute(DataText.class, "text", "text");

		xs.alias("data-url", DataUrl.class);
		xs.aliasAttribute(DataUrl.class, "text", "text");
		xs.aliasAttribute(DataUrl.class, "url", "url");

		xs.alias("data-img", DataImg.class);
		xs.aliasAttribute(DataImg.class, "alt", "alt");
		xs.aliasAttribute(DataImg.class, "url", "url");
		xs.aliasAttribute(DataImg.class, "img", "img");

		xs.alias("data-header", DataHeader.class);
		xs.aliasAttribute(DataHeader.class, "title", "title");
		xs.aliasAttribute(DataHeader.class, "desc", "desc");
		xs.aliasAttribute(DataHeader.class, "backPic", "back-pic");

		xs.alias("data-div", DataDiv.class);

		xs.alias("data-hr", DataHr.class);

		xs.alias("data-page", DataPage.class);
		xs.aliasAttribute(DataPage.class, "url", "url");
		xs.aliasAttribute(DataPage.class, "selected", "selected");

		xs.alias("data-progress", DataProgress.class);
		xs.aliasAttribute(DataProgress.class, "percent", "percent");
		xs.aliasAttribute(DataProgress.class, "count", "count");

		xs.alias("data-listview", DataListView.class);
		xs.aliasAttribute(DataListView.class, "header", "header");
		xs.aliasAttribute(DataListView.class, "columns", "column");
		xs.aliasAttribute(DataListView.class, "title", "title");

		xs.alias("data-filter", DataFilter.class);
		xs.aliasAttribute(DataFilter.class, "name", "name");
		xs.aliasAttribute(DataFilter.class, "filter", "filter");

		xs.alias("data-dialog", DataDialog.class);
		xs.aliasAttribute(DataDialog.class, "header", "header");
		xs.aliasAttribute(DataDialog.class, "id", "id");

		xs.alias("data-input", DataInput.class);
		xs.aliasAttribute(DataInput.class, "label", "label");
		xs.aliasAttribute(DataInput.class, "value", "value");
		xs.aliasAttribute(DataInput.class, "submit", "submit");

		xs.alias("data-label", DataLabel.class);
		xs.aliasAttribute(DataLabel.class, "label", "label");
		xs.aliasAttribute(DataLabel.class, "text", "text");

		xs.alias("data-form", DataForm.class);
		xs.addImplicitCollection(DataForm.class, "set");
		xs.aliasAttribute(DataForm.class, "id", "id");
		xs.aliasAttribute(DataForm.class, "action", "action");
		xs.aliasAttribute(DataForm.class, "method", "method");

		xs.alias("data-iframe", DataIFrame.class);
		xs.aliasAttribute(DataIFrame.class, "src", "src");

		xs.alias("data-ref", DataRef.class);
		xs.aliasAttribute(DataRef.class, "ref", "ref");

		xs.alias("data-notification", DataNotification.class);
		xs.aliasAttribute(DataNotification.class, "head", "head");
		xs.aliasAttribute(DataNotification.class, "datakey", "datakey");
		xs.aliasAttribute(DataNotification.class, "cat", "cat");
		xs.aliasAttribute(DataNotification.class, "title", "title");
		xs.aliasAttribute(DataNotification.class, "content", "content");

		xs.alias("data-footer", DataFooter.class);
		xs.aliasAttribute(DataFooter.class, "keyLabels", "key-labels");
		xs.aliasAttribute(DataFooter.class, "keyUrls", "key-urls");
		xs.aliasAttribute(DataFooter.class, "keyLabelTypes", "key-label-types");
		xs.aliasAttribute(DataFooter.class, "keyUrlTypes", "key-url-types");
	}

	private static void setLayout(XStream xs) {
		xs.alias("simple-layout", SimpleLayout.class);
		xs.aliasAttribute(SimpleLayout.class, "htmlAttrs", "html-attrs");
		xs.aliasAttribute(SimpleLayout.class, "bodyAttrs", "body-attrs");
		xs.aliasField("value-set", SimpleLayout.class, "valueSet");

		xs.alias("css-ref", CssRef.class);
		xs.aliasAttribute(CssRef.class, "href", "href");
		xs.alias("forge-layout", ForgeLayout.class);
		xs.addImplicitCollection(ForgeLayout.class, "refs");
		xs.aliasField("refs", ForgeLayout.class, "refs");
		xs.aliasAttribute(ForgeLayout.class, "htmlAttrs", "html-attrs");
		xs.aliasAttribute(ForgeLayout.class, "bodyAttrs", "body-attrs");
		xs.aliasAttribute(ForgeLayout.class, "pageAttrs", "page-attrs");
		xs.aliasAttribute(ForgeLayout.class, "refs", "refs");
		xs.aliasField("value-set", ForgeLayout.class, "valueSet");

		xs.addImplicitCollection(LayoutValueSet.class, "values");
		xs.aliasAttribute(LayoutValueSet.class, "width", "width");
		xs.alias("layout-value", LayoutValue.class);

		xs.aliasAttribute(LayoutValue.class, "key", "key");
		xs.aliasAttribute(LayoutValue.class, "tag", "tag");
		xs.aliasAttribute(LayoutValue.class, "parent", "parent");
		xs.aliasAttribute(LayoutValue.class, "row", "row");
		xs.aliasAttribute(LayoutValue.class, "column", "column");
		xs.aliasAttribute(LayoutValue.class, "width", "width");
		xs.aliasAttribute(LayoutValue.class, "height", "height");
		xs.aliasAttribute(LayoutValue.class, "ml", "ml");
		xs.aliasAttribute(LayoutValue.class, "mr", "mr");
		xs.aliasAttribute(LayoutValue.class, "mt", "mt");
		xs.aliasAttribute(LayoutValue.class, "mb", "mb");
		xs.aliasAttribute(LayoutValue.class, "pos", "pos");
		xs.aliasAttribute(LayoutValue.class, "styles", "styles");
		xs.aliasAttribute(LayoutValue.class, "attrs", "attrs");
		xs.aliasAttribute(LayoutValue.class, "noWidth", "no-width");
	}

	public static void main(String[] args) {
		DataList dataList = new DataList();
		SiteHomeProvider obj = new SiteHomeProvider();
		ArrayList oneDataProviders = new ArrayList();
		oneDataProviders.add(new SiteOneCatProvider());
		oneDataProviders.add(new SiteOneCatProvider());
		oneDataProviders.add(new SiteOneCatProvider());

		obj.setOneDataProviders(oneDataProviders);
		dataList.setProvider(obj);
		System.out.println(getXstream().toXML(dataList));
	}
}
