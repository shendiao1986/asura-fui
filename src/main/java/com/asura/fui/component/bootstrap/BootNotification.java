package com.asura.fui.component.bootstrap;

import com.asura.fui.FrontData;
import com.asura.fui.component.IUIComponent;
import com.asura.fui.component.data.DataNotification;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.HtmlNavi;
import com.asura.fui.html.HtmlScript;
import com.asura.fui.html.HtmlUL;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.html.SimpleHtml;

public class BootNotification implements IUIComponent {
	private boolean head;
	private String style;
	private String displaylength = "80";

	public boolean isHead() {
		return this.head;
	}

	public void setHead(boolean head) {
		this.head = head;
	}


	public String getStyle() {
		return this.style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	

	public String getDisplaylength() {
		return displaylength;
	}

	public void setDisplaylength(String displaylength) {
		this.displaylength = displaylength;
	}

	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataNotification p = (DataNotification) data;

		HtmlNavi panel = new HtmlNavi(paras);

		String s = this.style;
		try {
			s = PanelType.valueOf(s).name().toLowerCase();
		} catch (Exception e) {
			s = "default";
		}
		panel.addAttr("class", "panel panel-" + s);
        panel.addStyle("border", "0px");
        
		if (this.head) {
			HtmlDiv ph = new HtmlDiv(paras);

			ph.addAttr("class", "panel-heading");

			SimpleHtml h = new SimpleHtml("h3", paras);
			h.setContent(p.getHead());
			h.setClass("panel-title");

			ph.addChild(h);

			panel.addChild(ph);
		}

		HtmlDiv body = new HtmlDiv(paras);
		body.addAttr("class", "panel-body");
		body.addStyle("padding", "0px");
		HtmlUL content = new HtmlUL(paras);
		content.addAttr("id", "notification_ul");
		content.addAttr("class", "list-inline");
		content.addStyle("margin", "0px");
		body.addChild(content);
		panel.addChild(body);

		handleLoginScript(p, body, paras);
		
		return panel;
	}
	
	private void handleLoginScript(DataNotification notification, HtmlDiv div, FrontData paras){
		/*JQueryGet get = new JQueryGet();
		get.setUrl("$server$/data");
		get.setVars("cat='"+ notification.getCat()+"',key='" + notification.getDatakey() + "'");
		
		ScriptFunction callback = new ScriptFunction("notify","notifications",true);
		ScriptParseJson pj = new ScriptParseJson("notifications","lists");
		callback.addLine(pj);
		ScriptSimple alert = new ScriptSimple();
		alert.setScript("alert(lists);");
		callback.addLine(alert);
		get.setCallback(callback);*/
		StringBuffer sb = new StringBuffer();
		sb.append("$.get(\"$server$/data?cat=\"+encodeURI('"+notification.getCat()+"')+\"&key=\"+encodeURI('"+notification.getDatakey()+"'),");
		sb.append("function(notifications){\nvar lists = $.parseJSON(notifications);\n");
		sb.append("for(var l in lists){\nif(lists[l]."+notification.getContent()+"&&!getCookie(lists[l]."+notification.getTitle()+"))\n$('#notification_ul').append("+getLiElement(notification)+")\n}");
		sb.append("\n});");
		sb.append("function closeNotify(li_title){\n$('#'+li_title).hide()\ndocument.cookie=li_title+'='+true;}\n");
		sb.append("function getCookie(name){\nvar arr,reg=new RegExp(\"(^| )\"+name+\"=([^;]*)(;|$)\");\nif(arr=document.cookie.match(reg))\nreturn unescape(arr[2]);else\nreturn null;}\n");
		sb.append("function showAll(li_title){\nalert($('#'+li_title).attr('title'))}\n");
		HtmlScript hs = new HtmlScript("text/javascript", getValue(sb.toString(), paras));
		div.addChild(hs);
	}
	
	private String getLiElement(DataNotification notification){
		StringBuffer sb = new StringBuffer();
		sb.append("'<li style=\"width:100%;background-color:#d0e4fe;\" title=\"'+lists[l]."+notification.getContent()+"+'\" id=\"'+lists[l]."+notification.getTitle()+"+'\">'+");
		sb.append("(lists[l]."+notification.getContent()+".length>" + displaylength + "?lists[l]."+notification.getContent()+".substring(0,"+displaylength+")+'<a href=javascript:showAll(\"'+lists[l]."+notification.getTitle()+"+'\")>&gt&gt&gt</a>':lists[l]."+notification.getContent()+")+");
		sb.append("'<button type=\"button\" class=\"close\" data-dismiss=\"modal\" onclick=closeNotify(\"'+lists[l]."+notification.getTitle()+"+'\")>×</button></li>'");
		return sb.toString();
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

	public static enum PanelType {
		primary, success, info, warning, danger, Default;
	}
}
