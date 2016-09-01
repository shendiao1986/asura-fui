package com.asura.fui.component.bootstrap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.asura.fui.FrontData;
import com.asura.fui.component.IUIComponent;
import com.asura.fui.component.data.DataFooter;
import com.asura.fui.component.data.IUIData;
import com.asura.fui.component.layout.IUILayout;
import com.asura.fui.html.HtmlA;
import com.asura.fui.html.HtmlDiv;
import com.asura.fui.html.HtmlFooter;
import com.asura.fui.html.HtmlLi;
import com.asura.fui.html.HtmlSpan;
import com.asura.fui.html.HtmlUL;
import com.asura.fui.html.IHtmlElement;
import com.asura.fui.html.SimpleHtml;
import com.asura.fui.service.StaticCacheService;
import com.asura.fui.service.data.DataSourceProvider;
import com.asura.fui.util.ParamterUtil;
import com.asura.tools.data.DataIterator;
import com.asura.tools.data.DataRecord;
import com.asura.tools.data.mongo.MongoHandler;
import com.asura.tools.sql.SelectSQL;
import com.asura.tools.util.StringUtil;
import com.asura.tools.util.cache.SimpleCache;
import com.mongodb.DBCollection;

public class BootFooter implements IUIComponent {
	private static SimpleCache<String, Object> cache = new SimpleCache<String, Object>(1000000);
	private String textfield;
	private String mongokey;
	private String mongodb;
	private String mysqlkey;
    private String tablename;
    

	public String getTextfield() {
		if(StringUtil.isNullOrEmpty(textfield)){
			return "text";
		}else{
			return textfield;	
		}
	}


	public void setTextfield(String textfield) {
		this.textfield = textfield;
	}


	public String getMongokey() {
		if(StringUtil.isNullOrEmpty(mongokey)){
			return "app-report";
		}else{
			return mongokey;	
		}
	}


	public void setMongokey(String mongokey) {
		this.mongokey = mongokey;
	}


	public String getMongodb() {
		if(StringUtil.isNullOrEmpty(mongodb)){
			return "midas";
		}else{
			return mongodb;	
		}
	}


	public void setMongodb(String mongodb) {
		this.mongodb = mongodb;
	}


	public String getMysqlkey() {
		if(StringUtil.isNullOrEmpty(mysqlkey)){
			return "app";
		}else{
			return mysqlkey;	
		}
	}


	public void setMysqlkey(String mysqlkey) {
		this.mysqlkey = mysqlkey;
	}

	

	public String getTablename() {
		if(StringUtil.isNullOrEmpty(tablename)){
			return "log";
		}else{
			return tablename;	
		}
	}


	public void setTablename(String tablename) {
		this.tablename = tablename;
	}


	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataFooter df = (DataFooter) data;

		HashMap<String, String> lmap = convert(getValue(df.getKeyLabels(), paras));

		HashMap<String, String> uMap = convert(getValue(df.getKeyUrls(),paras));

		HashMap<String, String> ltMap = convert(getValue(df.getKeyLabelTypes(),paras));
		
		HashMap<String, String> utMap = convert(getValue(df.getKeyUrlTypes(),paras));
		
		for (String key : lmap.keySet()) {
			if ((ltMap.containsKey(key)) && ((ltMap.get(key)).equals("property"))){
				lmap.put(key, StaticCacheService.getSettingProperty(lmap.get(key)));
			}else if((ltMap.containsKey(key)) && ((ltMap.get(key)).equals("mysql"))){
				lmap.put(key, (getTextFromDB(key,lmap.get(key))));
			}else if((ltMap.containsKey(key)) && ((ltMap.get(key)).equals("mongodb"))){
				getContentForKey(key,lmap.get(key),lmap,uMap,utMap);
			}
		}
		
		HtmlFooter footerbar = new HtmlFooter(paras);
//		footerbar.setClass("btn-toolbar");
//		footerbar.addStyle("background-color", "#373f48");
//		footerbar.addAttr("text-align", "center");
//		footerbar.addStyle("font-size", "13px");
//		footerbar.addStyle("line-height", "24px");
//	    footerbar.addStyle("color", "#878B91");
	    footerbar.addAttr("class", "navbar navbar-inverse");
	    
	    HtmlDiv div_out = new HtmlDiv(paras);
	    div_out.addAttr("class", "container-fluid");
		footerbar.addChild(div_out);
		
		HtmlDiv middle = new HtmlDiv(paras);
		middle.addAttr("class", "collapse navbar-collapse");

		div_out.addChild(middle);
		
		HtmlUL content = new HtmlUL(paras);
		content.addAttr("class", "nav navbar-nav navbar-left");
		content.addStyle("margin-left", "200px");
		middle.addChild(content);
		
		
		for (String key : lmap.keySet()) {
			HtmlLi li = new HtmlLi(paras);
			//div.setClass("btn-group");

			IHtmlElement input = null;
			if ((utMap.containsKey(key)) && ((utMap.get(key)).equals("button"))){
				input = new HtmlA(paras);
				input.addAttr("class", "btn btn-info");
				input.addAttr("onclick", "javascript:window.open('" + (uMap.get(key)) + "');");
			}else if((utMap.containsKey(key)) && ((utMap.get(key)).equals("button-js"))){
				input = new SimpleHtml("button", paras);
				input.addAttr("type", "button");
				input.addAttr("onclick", (String) uMap.get(key));
			}else if((utMap.containsKey(key)) && ((utMap.get(key)).equals("link"))){
				input = new HtmlA(paras);
				input.addAttr("href", uMap.get(key));
			}else{
				input = new HtmlSpan();
			}
			input.setContent(lmap.get(key));
			li.addChild(input);
			content.addChild(li);
		}
		return footerbar;
	}
	

	public String getTextFromDB(String key,String textSql) {
		if(!cache.iscached(key)){
			SelectSQL sql = new SelectSQL();
			sql.setSql(textSql);
			DataIterator<DataRecord> di = DataSourceProvider.getMysql("", getMysqlkey()).select(sql,1);
			if(di.hasNext()){
				cache.cache(key, ((DataRecord) di.next()).getFieldValue(getTextfield()), 3600);
			}else{
				cache.cache(key, "", 3600);
			}
		}
        return (String)cache.get(key);		
	}
	
	public List<String> getTypebyColumn(String column) {
		if(!cache.iscached("mongo-types")){
			MongoHandler mongoHandler = DataSourceProvider.getMongo("", getMongokey());
			DBCollection table = mongoHandler.getCollection(getMongodb(), getTablename());
			List<String> types = table.distinct(column);
			cache.cache("mongo-types", types, 3600);
		}
        return (List<String>)cache.get("mongo-types");		
	}
	
	public void getContentForKey(String mapkey,String column,Map<String, String> lmap, Map<String, String> uMap, Map<String, String> utMap){
		List<String> types = getTypebyColumn(column);
		for(String type : types){
			String key = mapkey+"_"+type;
			if(!cache.iscached(key)){
				SelectSQL sql = new SelectSQL(getTablename());
				sql.addWhereCondition(column, type);
				long count = DataSourceProvider.getMongo("", getMongokey()).getCount(getMongodb(), sql);
				cache.cache(key, String.valueOf(count), 3600);
			}
			//keyContent.put(key, StaticCacheService.getSettingProperty(type)==null?type:StaticCacheService.getSettingProperty(type)+":"+cache.get(key));
			lmap.put(key, StaticCacheService.getSettingProperty(type)==null?type:StaticCacheService.getSettingProperty(type)+":"+cache.get(key));
			if(uMap.containsKey(mapkey)){
				uMap.put(key, uMap.get(mapkey).indexOf("?")!=-1?uMap.get(mapkey)+"&"+column+"="+type:uMap.get(mapkey)+"?"+column+"="+type);
				//uMap.put(key, uMap.get(mapkey)+"/"+type);
				//uMap.remove(mapkey);
			}
			if(utMap.containsKey(mapkey)){
				utMap.put(key, utMap.get(mapkey));
				//utMap.remove(mapkey);
			}
		}
		//lmap.putAll(keyContent);
		lmap.remove(mapkey);
		uMap.remove(mapkey);
		utMap.remove(mapkey);
		
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
	
	public HashMap<String, String> convert(String ec) {
		    if (ec == null) {
			    ec = "";
		    }
			HashMap map = new LinkedHashMap();
			String[] ss = StringUtil.split(ec.trim(), ",");
			for (String s : ss) {
				int index = s.indexOf("=");
				if (index > 0) {
					map.put(s.substring(0, index).trim(),s.substring(index + 1).trim());
				}
			}
		    return map;
	}
	
}
