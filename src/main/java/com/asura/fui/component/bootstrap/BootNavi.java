package com.asura.fui.component.bootstrap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cpkf.yyjd.tools.util.StringUtil;
import com.asura.fui.component.IUIComponent;
import com.asura.fui.component.data.DataNavi;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlA;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.HtmlLi;
import com.asura.fui.html.HtmlNavi;
import com.asura.fui.html.HtmlUL;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.html.SimpleHtml;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;

public class BootNavi implements IUIComponent {
	private NaviPos pos;
	private boolean fluid;
	private boolean inverse;
	private boolean vertical;

	public boolean isFluid() {
		return this.fluid;
	}

	public void setFluid(boolean fluid) {
		this.fluid = fluid;
	}

	public NaviPos getPos() {
		return this.pos;
	}

	public void setPos(NaviPos pos) {
		this.pos = pos;
	}

	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataNavi navi = (DataNavi) data;

		HtmlDiv out = new HtmlDiv(paras);
		out.setClass("sidebar-nav");

		HtmlNavi ul = new HtmlNavi(paras);

		String type = "default";
		if (this.inverse) {
			type = "inverse";
		}
		if (this.pos == NaviPos.Bottom)
			ul.addAttr("class", "navbar navbar-" + type + " navbar-fixed-bottom");
		else if (this.pos == NaviPos.Top)
			ul.addAttr("class", "navbar navbar-" + type + " navbar-fixed-top");
		else {
			ul.addAttr("class", "navbar navbar-" + type);
		}

		HtmlDiv div = new HtmlDiv(paras);

		if (this.fluid)
			div.addAttr("class", "container-fluid");
		else {
			div.addAttr("class", "container");
		}

		ul.addChild(div);

		handleHeader(navi, div, paras);

		handleMiddle(navi, div, paras);

		if (this.vertical) {
			out.addChild(ul);
			return out;
		}
		return ul;
	}

	private void handleHeader(DataNavi navi, HtmlDiv div, FrontData paras) {
		if (!(StringUtil.isNullOrEmpty(navi.getHead()))) {
			HtmlDiv head = new HtmlDiv(paras);
			head.addAttr("class", "navbar-header");

			HashMap m = ParamterUtil.convert(getValue(navi.getHead(), paras));

			HtmlA a = new HtmlA(paras);
			a.addAttr("class", "navbar-brand");

			if (m.size() == 1) {
				a.addAttr("href", (String) m.values().iterator().next());
				a.setContent((String) m.keySet().iterator().next());
			} else {
				a.setContent(navi.getHead());
			}

			head.addChild(a);

			div.addChild(head);
		}
	}

	private void handleMiddle(DataNavi navi, HtmlDiv div, FrontData paras) {
		HtmlDiv middle = new HtmlDiv(paras);
		middle.addAttr("class", "collapse navbar-collapse");

		div.addChild(middle);

		HtmlUL content = new HtmlUL(paras);
		content.addAttr("class", "nav navbar-nav");

		middle.addChild(content);

		String url = getValue(navi.getUrl(), paras);

		HashMap<String,String> m = ParamterUtil.convert(url);

		HashMap<String,String> pM = ParamterUtil.convert(getValue(navi.getParent(), paras));

		String right = getValue(navi.getRight(), paras);

		for (String n : m.keySet()) {
			if ((pM.containsKey(n)) || ((!(StringUtil.isNullOrEmpty(right))) && !(right.contains(n))))
				continue;
			String u = (String) m.get(n);
			HtmlLi li = new HtmlLi(paras);

			HtmlA a = new HtmlA(paras);
			a.addAttr("href", u);
			a.setContent(n);
			li.addChild(a);

			if (!(StringUtil.isNullOrEmpty(navi.getSelected()))) {
				String se = ParamterUtil.getValue(navi.getSelected(), paras);

				if (n.equals(se)) {
					li.addAttr("class", "active");
				}
			}

			handleChild(n, pM, m, paras, li, a);

			content.addChild(li);
		}

		content = new HtmlUL(paras);
		content.addAttr("class", "nav navbar-nav navbar-right");

		middle.addChild(content);

		/*for (String n : m.keySet()) {
			if ((pM.containsKey(n)) || (StringUtil.isNullOrEmpty(right)) || (!(right.contains(n))))
				continue;
			String u = (String) m.get(n);
			HtmlLi li = new HtmlLi(paras);

			HtmlA a = new HtmlA(paras);
			a.addAttr("href", u);
			a.setContent(n);
			li.addChild(a);

			if (!(StringUtil.isNullOrEmpty(navi.getSelected()))) {
				String se = ParamterUtil.getValue(navi.getSelected(), paras);

				if (n.equals(se)) {
					li.addAttr("class", "active");
				}
			}

			handleChild(n, pM, m, paras, li, a);

			content.addChild(li);
		}*/
	}

	private void handleChild(String n, HashMap<String, String> pM, HashMap<String, String> m, FrontData paras,
			HtmlLi li, HtmlA a) {
		String[] cs = getChildren(pM, n);
		if (cs.length > 0) {
			li.setClass("dropdown");
			a.addAttr("class", "dropdown-toggle");
			a.addAttr("data-toggle", "dropdown");
			SimpleHtml b = new SimpleHtml("b");
			b.setClass("caret");
			a.addChild(b);

			HtmlUL drop = new HtmlUL(paras);
			drop.setClass("dropdown-menu");

			for (String c : cs) {
				HtmlLi sli = new HtmlLi(paras);

				HtmlA sa = new HtmlA(paras);
				sa.addAttr("href", (String) m.get(c));
				sa.setContent(c);
				sli.addChild(sa);

				drop.addChild(sli);
			}

			li.addChild(drop);
		}
	}

	private String[] getChildren(HashMap<String, String> map, String name) {
		List list = new ArrayList();

		for (String key : map.keySet()) {
			if (((String) map.get(key)).equals(name)) {
				list.add(key);
			}
		}

		return ((String[]) list.toArray(new String[0]));
	}

	private String getValue(String value, FrontData paras) {
		if (value == null) {
			value = "";
		}
		String t = value;
		for (String p : paras.getAllFields()) {
			t = t.replace("$" + p + "$", paras.getValueString(p));
		}
		return t;
	}

	public static enum NaviPos {
		Top, Bottom, Default;
	}

}
