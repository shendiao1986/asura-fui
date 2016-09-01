package com.asura.fui.component.bootstrap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.asura.tools.data.DataRecord;
import com.asura.tools.util.StringUtil;
import com.asura.fui.component.IUIComponent;
import com.asura.fui.component.data.DataDialog;
import com.asura.fui.component.data.DataNavi;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.data.UIDatas;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlA;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.HtmlLi;
import com.asura.fui.html.HtmlNavi;
import com.asura.fui.html.HtmlScript;
import com.asura.fui.html.HtmlSpan;
import com.asura.fui.html.HtmlUL;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.html.SimpleHtml;
import com.asura.fui.script.ScriptAssign;
import com.asura.fui.script.ScriptCondition;
import com.asura.fui.script.ScriptFunction;
import com.asura.fui.script.ScriptSimple;
import com.asura.fui.script.ScriptSwitch;
import com.asura.fui.service.dispatch.FuiUrl;
import com.asura.fui.util.ParamterUtil;
import com.asura.fui.FrontData;

public class BootNavi implements IUIComponent {
	private NaviPos pos;
	private boolean fluid;
	private boolean inverse;
	private boolean vertical;
	private boolean haslogon;

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
		
		if(haslogon){
		
		  handleLoginDialog(navi, div, paras);
		
		  handleLoginScript(navi, div, paras);
		
		}

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

	if(haslogon){
		content = new HtmlUL(paras);
		content.addAttr("class", "nav navbar-nav navbar-right");

		middle.addChild(content);

		if (right.contains("登录")){
			HtmlLi li = new HtmlLi(paras);
			HtmlA a = new HtmlA(paras);
			a.addAttr("href", "javascript:login(true);");
			a.setContent("登录");
			li.addChild(a);
			content.addChild(li);
		}else if(right.contains("退出")){
			HtmlLi li_user = new HtmlLi(paras);
			HtmlA span = new HtmlA(paras);
			span.setContent(paras.getValueString("username"));
			span.addAttr("href", "#");
			li_user.addChild(span);
			content.addChild(li_user);
			HtmlLi li = new HtmlLi(paras);
			HtmlA a = new HtmlA(paras);
			a.addAttr("href", "javascript:login(false);");
			a.setContent("退出");
			li.addChild(a);
			content.addChild(li);
		}
	  }
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
	
	private void handleLoginScript(DataNavi navi, HtmlDiv div, FrontData paras){
		StringBuffer sb = new StringBuffer();
		sb.append("function closedialog(dialogid){\n$('#' + dialogid).modal('hide');\n}\n");
		
		sb.append("function submitlogin(loginformid){\nvar username = $('#username').val();\nvar userpassword = $('#password').val();\n");
		sb.append("if(username == ''){\nalert('请填写用户名');\n} else if (userpassword == ''){\nalert('请填写密码');}");
		sb.append("else{\n$('#' + loginformid).submit();}\n}\n");

        sb.append("function login(key){\nif(key){\nvar actionurl = $('#user-login-form').attr('action');\n$('#user-login-form').attr('action',actionurl+'?islogin=true');\n$('#loginDialog').modal('show');\n} else {\n");
		sb.append("var actionurl = $('#user-login-form').attr('action');\n$('#user-login-form').attr('action',actionurl+'?islogin=false');\n");
		sb.append("$('#' + 'user-login-form').submit();\n}\n}");
			
		HtmlScript hs = new HtmlScript("text/javascript", sb.toString());
		div.addChild(hs);
	}
	
	private void handleLoginDialog(DataNavi navi, HtmlDiv div, FrontData paras) {
		HtmlDiv dialog = new HtmlDiv(paras);
		dialog.setClass("modal fade");
		dialog.addAttr("id", "loginDialog");
		dialog.addAttr("tabindex", "-1");
		dialog.addAttr("role", "dialog");
		dialog.addAttr("aria-hidden", "true");

		HtmlDiv dia = new HtmlDiv(paras);
		dia.setClass("modal-dialog");
		dia.addStyle("margin-top", "200px");

		dialog.addChild(dia);

		HtmlDiv con = new HtmlDiv(paras);
		con.setClass("modal-content");

		dia.addChild(con);

		createDialogHead(con,paras);
		
		createDialogBody(con,paras);
		
		createDialogFooter(con,paras);
			
		div.addChild(dialog);
	
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
	
	private void createDialogHead(HtmlDiv con,FrontData paras){
		HtmlDiv head = new HtmlDiv(paras);
		head.setClass("modal-header");

		SimpleHtml bt = new SimpleHtml("button", paras);
		bt.addAttr("type", "button");
		bt.addAttr("class", "close");
		bt.addAttr("data-dismiss", "modal");
		bt.setContent("×");

		head.addChild(bt);

		SimpleHtml h4 = new SimpleHtml("h4", paras);
		h4.setContent("登录提示");

		head.addChild(h4);

		con.addChild(head);
	}
	
	private void createDialogBody(HtmlDiv con, FrontData paras){
		HtmlDiv body = new HtmlDiv(paras);
		body.setClass("modal-body");
		con.addChild(body);

		HtmlDiv body_name = new HtmlDiv(paras);
		body_name.addStyle("padding-left", "80px");
		HtmlSpan span_name = new HtmlSpan(paras);
		span_name.setContent("用户名" + ":");
		span_name.addStyle("float", "left");
		span_name.addStyle("overflow", "hidden");
		span_name.addStyle("width",  "15%");
		body_name.addChild(span_name);
		SimpleHtml html_name = new SimpleHtml("input", paras);
		html_name.addAttr("name", "username");
		html_name.addAttr("id", "username");
		html_name.addStyle("width", "50%");
		html_name.addAttr("type", "text");
		body_name.addChild(html_name);
		
		
		HtmlDiv body_password = new HtmlDiv(paras);
		body_password.addStyle("padding-left", "80px");
		body_password.addStyle("padding-top", "20px");
		HtmlSpan span_password = new HtmlSpan(paras);
		span_password.setContent("密 码" + ":");
		span_password.addStyle("float", "left");
		span_password.addStyle("overflow", "hidden");
		span_password.addStyle("width",  "15%");
		body_password.addChild(span_password);
		SimpleHtml html_password = new SimpleHtml("input", paras);
		html_password.addAttr("name", "password");
		html_password.addAttr("id", "password");
		html_password.addStyle("width", "50%");
		html_password.addAttr("type", "password");
		body_password.addChild(html_password);
		
		SimpleHtml divform = new SimpleHtml("form", paras);
		divform.addAttr("id", "user-login-form");
		FuiUrl url = (FuiUrl) paras.getValue("fui-url");
		String action = url.toUrlBase() + "/post";
		divform.addAttr("action", action);
		divform.addAttr("method", "post");
		divform.addChild(body_name);
		divform.addChild(body_password);

		SimpleHtml inputpost = new SimpleHtml("input", paras);
		inputpost.addAttr("type", "hidden");
		inputpost.addAttr("name", "post_id");
		inputpost.addAttr("value", "user-login-form");
		divform.addChild(inputpost);
		body.addChild(divform);
	}

	private void createDialogFooter(HtmlDiv con, FrontData paras){
		HtmlDiv footer = new HtmlDiv(paras);
		footer.setClass("modal-footer");
		con.addChild(footer);
		
		HtmlDiv toolbar = new HtmlDiv(paras);
		toolbar.setClass("btn-toolbar");
		footer.addChild(toolbar);
		
		HtmlDiv btngroup = new HtmlDiv(paras);
		btngroup.setClass("btn-group");
		SimpleHtml input = new SimpleHtml("button", paras);
		input.addAttr("type", "button");
		input.setClass("btn btn-default");
		input.addAttr("id", "btn1");
		input.addAttr("name", "submitbtn");
		input.setClass("btn btn-default");
		input.addAttr("onclick", "submitlogin('user-login-form');");
		input.setContent("确定");
        btngroup.addChild(input);
        toolbar.addChild(btngroup);
        
        HtmlDiv btngroup2 = new HtmlDiv(paras);
		btngroup2.setClass("btn-group");
        SimpleHtml input2 = new SimpleHtml("button", paras);
        input2.addAttr("type", "button");
        input2.setClass("btn btn-default");
        input2.addAttr("id", "btn2");
        input2.addAttr("name", "cancelbtn");
        input2.setClass("btn btn-default");
        input2.addAttr("onclick", "closedialog('loginDialog');");
        input2.setContent("取消");
        btngroup2.addChild(input2);
        toolbar.addChild(btngroup2);
	}
	
	public static enum NaviPos {
		Top, Bottom, Default;
	}

}
