package com.asura.fui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cpkf.yyjd.tools.util.StringUtil;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.data.UIDatas;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.FuiHtml;
import com.asura.fui.html.HtmlMeta;
import com.asura.fui.html.HtmlScript;
import com.asura.fui.html.SimpleHtml;
import com.asura.fui.script.IUIScript;
import com.asura.fui.script.UIScripts;
import com.asura.fui.service.dispatch.PageCache;


public class FuiPage {
	private PageDef def;
	private IUILayout layout;
	private UIDatas datas;
	private UIScripts scripts;
	private String ref;
	private String css;
	private String js;

	public FuiPage() {
		this.scripts = new UIScripts();
		this.datas = new UIDatas();
	}

	public IUILayout getLayout() {
		return this.layout;
	}

	public void setLayout(IUILayout layout) {
		this.layout = layout;
	}

	public UIDatas getDatas() {
		return this.datas;
	}

	public void setDatas(UIDatas datas) {
		this.datas = datas;
	}

	public UIScripts getScripts() {
		return this.scripts;
	}

	public void setScripts(UIScripts scripts) {
		this.scripts = scripts;
	}

	public PageDef getDef() {
		return this.def;
	}

	public void setDef(PageDef def) {
		this.def = def;
	}

	public void replace(IUIData data) {
		this.datas.replace(data);
	}

	public String toHtml(FrontData paras) {
		if (!(StringUtil.isNullOrEmpty(this.ref))) {
			String[] ss = StringUtil.split(this.ref, ",");
			if (ss.length == 2) {
				FuiPage base = PageCache.getPage(ss[0].trim(), ss[1].trim()).clone();
				if (this.datas != null){
					Iterator localIterator = this.datas.getDatas().iterator();
					while (true) {
						IUIData data = (IUIData) localIterator.next();
						base.replace(data);

						if (!(localIterator.hasNext())) {
							return base.toHtml(paras);
						}
					}
				}
			}
			return getHtml(paras).toHtml();
		}

		return getHtml(paras).toHtml();
	}

	private FuiHtml getHtml(FrontData paras) {
		FuiHtml html = (FuiHtml) this.layout.toHtml(this.datas, paras);

		if (this.def != null) {
			if (!(StringUtil.isNullOrEmpty(this.def.getTitle()))) {
				html.getHead().addChild(new SimpleHtml("title", this.def.getTitle(), paras));
			}
			if (!(StringUtil.isNullOrEmpty(this.def.getKeyword()))) {
				html.getHead().addChild(new HtmlMeta("Keywords", this.def.getKeyword(), paras));
			}
			if (!(StringUtil.isNullOrEmpty(this.def.getDesc()))) {
				html.getHead().addChild(new HtmlMeta("Description", this.def.getDesc(), paras));
			}
		}

		HtmlScript hs = new HtmlScript("text/javascript", "");

		List list = new ArrayList();

		if ((this.scripts != null) && (this.scripts.getScripts() != null)) {
			for (IUIScript script : this.scripts.getScripts()) {
				list.add(script.toScript(paras));
			}
		}

		hs.setContent(StringUtil.getStringFromStrings(list, "\n\n"));

		html.addChild(hs);

		return html;
	}

	public FuiPage clone() {
		FuiPage fp = new FuiPage();
		fp.def = this.def;
		fp.ref = this.ref;
		fp.css = this.css;
		fp.js = this.js;

		fp.setLayout(this.layout);

		if ((this.datas != null) && (this.datas.getDatas() != null)) {
			for (IUIData d : this.datas.getDatas()) {
				fp.datas.addUIData(d);
			}
		}

		if ((this.scripts != null) && (this.scripts.getScripts() != null)) {
			for (IUIScript d : this.scripts.getScripts()) {
				fp.scripts.addScript(d);
			}
		}

		return fp;
	}

	public String getCss() {
		return this.css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getJs() {
		return this.js;
	}

	public void setJs(String js) {
		this.js = js;
	}
}
