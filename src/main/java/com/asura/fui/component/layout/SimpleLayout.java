package com.asura.fui.component.layout;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cpkf.yyjd.tools.util.StringUtil;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.data.UIDatas;
import com.asura.fui.html.FuiHtml;
import com.asura.fui.html.HtmlBody;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.html.SimpleHtml;
import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;
import com.asura.fui.FuiCss;

public class SimpleLayout implements IUILayout {
	private LayoutValueSet valueSet;
	private HashSet<String> used;
	private String htmlAttrs;
	private String bodyAttrs;

	public void merge(IUILayout layout) {
		SimpleLayout sl = (SimpleLayout) layout;
		for (LayoutValue lv : sl.getValueSet().getValues())
			this.valueSet.addLayoutVaue(lv);
	}

	public IHtmlElement toHtml(UIDatas datas, FrontData paras) {
		this.used = new HashSet();
		FuiHtml html = new FuiHtml();

		FuiUrl url = (FuiUrl) paras.getValue("fui-url");

		html.getHead().addCssRef(url.toUrlBase() + "/css/morris-0.4.3.min.css");
		html.getHead().addCssRef(url.toUrlBase() + "/css/bootstrap.min.css");

		html.getHead().addJSRef(url.toUrlBase() + "/js/jquery.min.js");
		html.getHead().addJSRef(url.toUrlBase() + "/js/common.js");
		html.getHead().addJSRef(url.toUrlBase() + "/js/bootstrap.min.js");
		html.getHead().addJSRef(url.toUrlBase() + "/js/dragon/excanvas.js");
		html.getHead().addJSRef(url.toUrlBase() + "/js/dragon/dragonchart.skins.min.js");
		html.getHead().addJSRef(url.toUrlBase() + "/js/dragon/dragonchart.core.min.js");
		html.getHead().addJSRef(url.toUrlBase() + "/js/dragon/dragonchart.pie3d.min.js");
		html.getHead().addJSRef(url.toUrlBase() + "/js/dragon/dragonchart.line.min.js");
		html.getHead().addJSRef(url.toUrlBase() + "/js/raphael-min.js");
		html.getHead().addJSRef(url.toUrlBase() + "/js/morris-0.4.3.min.js");
		html.getHead().addJSRef("http://code.jquery.com/ui/1.10.4/jquery-ui.js");
		html.getHead().addCssRef("http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css");

		if (!(StringUtil.isNullOrEmpty(this.htmlAttrs))) {
			Map kToV = ParamterUtil.convert(this.htmlAttrs);
			Iterator keys = kToV.keySet().iterator();
			while (keys.hasNext()) {
				String name = (String) keys.next();
				html.addAttr(name, (String) kToV.get(name));
			}
		}

		if (!(StringUtil.isNullOrEmpty(this.bodyAttrs))) {
			HtmlBody body = new HtmlBody();

			Map kToV = ParamterUtil.convert(this.bodyAttrs);

			Iterator keys = kToV.keySet().iterator();
			while (keys.hasNext()) {
				String name = (String) keys.next();
				body.addAttr(name, (String) kToV.get(name));
			}

			html.setBody(body);
		}

		handleKey(html.getBody(), datas, "page", paras);

		return html;
	}

	public void toHtml(IHtmlElement parent, UIDatas datas, FrontData paras, boolean unique) {
		handleKey(parent, datas, paras, unique);
	}

	public void handleList(IHtmlElement parent, String parentKey, List<IHtmlElement> children, String childKey, int row,
			int column, FrontData paras, String hrStyle) {
		if (children.size() > 0) {
			FuiCss css = this.valueSet.getStyle(parentKey);
			int width = css.getWidth();

			LayoutValue lv = this.valueSet.getLayoutValue(childKey);

			int other = (column - 1) * 20;
			int ml = 20;
			if (lv != null) {
				if (lv.getMl() != 0) {
					ml = lv.getMl();
				}
				other = (column - 1) * (ml + lv.getMr());
			}

			int w = (width - other) / column;

			int i = 0;

			for (IHtmlElement el : children) {
				if (el.getAttr("style") == null || !"".equals(el.getAttr("style"))) {
					FuiCss c = this.valueSet.getStyle(childKey);
					if (c != null) {
						for (String key : c.getFields()) {
							el.addStyle(key, c.getValue(key));
						}
					}
					if (i % column != 0) {
						el.addStyle("margin-left", ml + "px");
					} else if (i != 0 && !StringUtil.isNullOrEmpty(hrStyle)) {
						HashMap<String, String> m = ParamterUtil.convert(hrStyle);
						HtmlDiv div = new HtmlDiv();
						div.addAttr("style",
								"border-top: 1px solid;color:#ccc; height: 1px; width: " + w + "px;float:left;");
						for (Iterator<String> localIterator = m.keySet().iterator(); localIterator.hasNext();) {
							String n = localIterator.next();
							if (!(n.equals("margin-left"))) {
								div.addStyle(n, (String) m.get(n));
							}
						}
						parent.addChild(div);

					}
					el.addStyle("width", w + "px");
					el.addStyle("float", "left");
				}
				parent.addChild(el);
				++i;
			}
		}
	}

	private void handleKey(IHtmlElement parent, UIDatas datas, FrontData paras, boolean unique) {
		for (IUIData data : datas.getDatas())
			if (!(StringUtil.isNullOrEmpty(data.getKey()))) {
				handleKey(parent, datas, data.getKey(), paras);
				if (unique)
					this.used.add(data.getKey());
			} else {
				IHtmlElement el = data.toElement(this, paras);
				if (el != null)
					parent.addChild(el);
			}
	}

	private void handleKey(IHtmlElement parent, UIDatas datas, String key, FrontData paras) {
		if (!(this.used.contains(key))) {
			IUIData ud = datas.getUIData(key);
			IHtmlElement current = null;

			IHtmlElement div = null;
			if (StringUtil.isNullOrEmpty(this.valueSet.getTag(key)))
				div = new HtmlDiv();
			else {
				div = new SimpleHtml(this.valueSet.getTag(key));
			}

			FuiCss css = this.valueSet.getStyle(key);

			if (ud == null) {
				div.addAttr("id", key);
			}
			if (key.equals("page")) {
				css.add("margin", "0 auto");
			} else
				if ((this.valueSet.getLayoutValue(key) != null) && (this.valueSet.getLayoutValue(key).getPos() == 0)) {
				css.addIgnore("float", "left");
			}

			if (!(StringUtil.isNullOrEmpty(css.toStyle(paras)))) {
				div.addAttr("style", css.toStyle(paras));
			}

			HashMap<String, String> m = ParamterUtil.convert(this.valueSet.getAttrs(key));
			for (String a : m.keySet()) {
				if (!(StringUtil.isNullOrEmpty(ParamterUtil.getValue((String) m.get(a), paras))))
					div.addAttr(a, ParamterUtil.getValue((String) m.get(a), paras));
				else {
					div.addAttr(a, null);
				}

			}

			current = div;
			parent.addChild(div);

			if (ud != null) {
				IHtmlElement el = ud.toElement(this, paras);
				div.addChild(el);
				current = el;
			}

			for (Integer row : this.valueSet.getRows(key)) {
				String[] subs = this.valueSet.getKeys(key, row.intValue());
				for (String sub : subs)
					if (current != null)
						handleKey(current, datas, sub, paras);
			}
		}
	}

	public LayoutValueSet getValueSet() {
		return this.valueSet;
	}

	public void setValueSet(LayoutValueSet valueSet) {
		this.valueSet = valueSet;
	}

	public String getHtmlAttrs() {
		return this.htmlAttrs;
	}

	public void setHtmlAttrs(String htmlAttrs) {
		this.htmlAttrs = htmlAttrs;
	}

	public String getBodyAttrs() {
		return this.bodyAttrs;
	}

	public void setBodyAttrs(String bodyAttrs) {
		this.bodyAttrs = bodyAttrs;
	}
}