package com.asura.fui.component.layout;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cpkf.yyjd.tools.util.StringUtil;
import com.asura.fui.apps.sites.AttachmentCaches;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.data.UIDatas;
import com.asura.fui.config.CssRef;
import com.asura.fui.html.FuiHtml;
import com.asura.fui.html.HtmlBody;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.HtmlText;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.html.SimpleHtml;
import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;

public class ForgeLayout implements IUILayout {
	private LayoutValueSet valueSet;
	private HashSet<String> used;
	private String htmlAttrs;
	private String bodyAttrs;
	private String pageAttrs;
	private List<CssRef> refs;

	public IHtmlElement toHtml(UIDatas datas, FrontData paras) {
		this.used = new HashSet();
		FuiHtml html = new FuiHtml();
		if (!(StringUtil.isNullOrEmpty(this.htmlAttrs))) {
			Map kToV = ParamterUtil.convert(this.htmlAttrs);
			Iterator keys = kToV.keySet().iterator();
			while (keys.hasNext()) {
				String name = (String) keys.next();
				html.addAttr(name, (String) kToV.get(name));
			}
		}

		FuiUrl url = (FuiUrl) paras.getValue("fui-url");

		for (CssRef ref : this.refs) {
			html.getHead().addCssRef(url.toUrlBase() + ref.getHref());
		}

		html.getHead().addJSRef(url.toUrlBase() + "/js/jquery.min.js");
		html.getHead().addJSRef(url.toUrlBase() + "/js/common.js");
		html.getHead().addJSRef(url.toUrlBase() + "/js/bootstrap.min.js");
		html.getHead().addJSRef(url.toUrlBase() + "/js/dragon/excanvas.js");
		html.getHead().addJSRef(url.toUrlBase() + "/js/dragon/dragonchart.core.min.js");
		html.getHead().addJSRef(url.toUrlBase() + "/js/dragon/dragonchart.pie3d.min.js");
		html.getHead().addJSRef("http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js");
		html.getHead().addJSRef(url.toUrlBase() + "/js/morris-0.4.3.min.js");

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
		html.getBody()
				.addChild(
						new HtmlText(
								AttachmentCaches
										.getAttachment(((FuiUrl) paras.getValue("fui-url")).getServer(),
												"ad", paras
														.getValueString(
																"pageName"))
										.getFieldValue(
												"content")
										+ AttachmentCaches
												.getAttachment(((FuiUrl) paras.getValue("fui-url")).getServer(), "calc",
														paras.getValueString("pageName"))
												.getFieldValue("content")));

		return html;
	}

	public void toHtml(IHtmlElement parent, UIDatas datas, FrontData paras, boolean unique) {
		handleKey(parent, datas, paras, unique);
	}

	public void handleList(IHtmlElement parent, String parentKey, List<IHtmlElement> children, String childKey, int row,
			int column, FrontData paras, String hrStyle) {
		if (children.size() <= 0)
			return;
		if ((row < 0) && (column < 0))
			parent.addChild((IHtmlElement) children.get(0));
		else
			for (IHtmlElement el : children)
				parent.addChild(el);
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

			if (ud == null) {
				if (!("page".equals(key))) {
					div.addAttr("id", key);
				} else if (!(StringUtil.isNullOrEmpty(this.pageAttrs))) {
					Map kToV = ParamterUtil.convert(this.pageAttrs);
					Iterator keys = kToV.keySet().iterator();
					while (keys.hasNext()) {
						String name = (String) keys.next();
						div.addAttr(name, (String) kToV.get(name));
					}
				}

			}

			HashMap<String, String> m = ParamterUtil.convert(this.valueSet.getAttrs(key));
			for (String a : m.keySet()) {
				if (!(StringUtil.isNullOrEmpty(ParamterUtil.getValue((String) m.get(a), paras))))
					div.addAttr(a, ParamterUtil.getValue((String) m.get(a), paras));
				else {
					div.addAttr(a, null);
				}

			}

			if ((!("page".equals(key))) && (this.valueSet.getLayoutValue(key) == null)) {
				div = parent;
			} else {
				current = div;
				parent.addChild(div);
			}

			if (ud != null) {
				IHtmlElement el = ud.toElement(this, paras);
				div.addChild(el);
				current = el;
			}
			Integer[] arrayOfInteger;
			int str1 = (arrayOfInteger = this.valueSet.getRows(key)).length;
			for (int name = 0; name < str1; ++name) {
				Integer row = arrayOfInteger[name];
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

	public List<CssRef> getRefs() {
		return this.refs;
	}

	public void setRefs(List<CssRef> refs) {
		this.refs = refs;
	}

	public String getPageAttrs() {
		return this.pageAttrs;
	}

	public void setPageAttrs(String pageAttrs) {
		this.pageAttrs = pageAttrs;
	}

	public void merge(IUILayout layout) {
	}
}
