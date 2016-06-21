import com.asura.fui.component.custom;

import java.util.HashMap;
import java.util.Map;

import com.cpkf.yyjd.tools.util.StringUtil;
import com.asura.core.component.AbstractUIComponent;
import com.asura.core.component.data.IUIData;
import com.asura.core.component.data.basic.DataUrl;
import com.asura.core.component.layout.IUILayout;
import com.asura.core.html.HtmlA;
import com.asura.core.html.IHtmlElement;
import com.asura.core.html.SimpleHtml;
import com.asura.core.util.ParamterUtil;
import com.asura.fui.FrontData;


public class SimpleUrl extends AbstractUIComponent {
	private String wrapNode;
	private String wrapNodeAttrs;
	private String wrapNodeContent;

	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataUrl url = (DataUrl) data;

		IHtmlElement parent = null;
		if (!(StringUtil.isNullOrEmpty(this.wrapNode))) {
			if (!(StringUtil.isNullOrEmpty(this.wrapNodeContent)))
				parent = new SimpleHtml(this.wrapNode, this.wrapNodeContent);
			else {
				parent = new SimpleHtml(this.wrapNode);
			}

			if (!(StringUtil.isNullOrEmpty(this.wrapNodeAttrs))) {
				HashMap<String,String> sMap = ParamterUtil.convert(this.wrapNodeAttrs);
				for (String key : sMap.keySet()) {
					if ("".equals(sMap.get(key)))
						parent.addAttr(key, null);
					else {
						parent.addAttr(key, (String) sMap.get(key));
					}
				}

			}

		}

		HtmlA a = new HtmlA(paras);

		a.setUrl(url.getUrl());
		a.setContent(url.getText());

		if (!(StringUtil.isNullOrEmpty(this.attrs))) {
			Map<String,String> sMap = ParamterUtil.convert(this.attrs);
			for (String key : sMap.keySet()) {
				if ("".equals(sMap.get(key)))
					a.addAttr(key, null);
				else {
					a.addAttr(key, (String) sMap.get(key));
				}
			}

		}

		addStyle(a, paras);

		if (parent != null) {
			parent.addChild(a);
			return parent;
		}

		return a;
	}

	public String getWrapNode() {
		return this.wrapNode;
	}

	public void setWrapNode(String wrapNode) {
		this.wrapNode = wrapNode;
	}

	public String getWrapNodeAttrs() {
		return this.wrapNodeAttrs;
	}

	public void setWrapNodeAttrs(String wrapNodeAttrs) {
		this.wrapNodeAttrs = wrapNodeAttrs;
	}

	public String getWrapNodeContent() {
		return this.wrapNodeContent;
	}

	public void setWrapNodeContent(String wrapNodeContent) {
		this.wrapNodeContent = wrapNodeContent;
	}
}
