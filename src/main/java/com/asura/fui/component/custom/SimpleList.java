package com.asura.fui.component.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.cpkf.yyjd.tools.util.StringUtil;
import com.asura.fui.component.AbstractUIComponent;
import com.asura.fui.component.data.DataList;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.html.SimpleHtml;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;

public class SimpleList extends AbstractUIComponent {
	private String wrapNode;
	private String wrapNodeAttrs;
	private String wrapNodeContent;
	private int row;
	private int column;
	private String hrStyle;

	public int getRow() {
		return this.row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return this.column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String getHrStyle() {
		return this.hrStyle;
	}

	public void setHrStyle(String hrStyle) {
		this.hrStyle = hrStyle;
	}

	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataList list = (DataList) data;
		IHtmlElement div = null;
		if (StringUtil.isNullOrEmpty(this.wrapNode))
			div = new HtmlDiv(paras);
		else {
			div = new SimpleHtml(this.wrapNode, this.wrapNodeContent, paras);
		}

		if (!(StringUtil.isNullOrEmpty(this.wrapNodeAttrs))) {
			HashMap<String,String> sMap = ParamterUtil.convert(this.wrapNodeAttrs);
			for (String key : sMap.keySet()) {
				if (StringUtil.isNullOrEmpty((String) sMap.get(key)))
					div.addAttr(key, null);
				else {
					div.addAttr(key, (String) sMap.get(key));
				}
			}

		}

		List els = new ArrayList();

		for (DataRecord dr : list.getProvider().build(paras)) {
			IUIData d = list.getData();
			FrontData newParas = paras.clone();
			for (String f : dr.getAllFields()) {
				newParas.AddField(f, dr.getFieldValue(f));
			}
			els.add(d.toElement(layout, newParas));
		}

		layout.handleList(div, list.getKey(), els, list.getData().getKey(), this.row, this.column, paras, this.hrStyle);

		return div;
	}

	public String getWrapNodeContent() {
		return this.wrapNodeContent;
	}

	public void setWrapNodeContent(String wrapNodeContent) {
		this.wrapNodeContent = wrapNodeContent;
	}
}