package com.asura.fui;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;


public class DataConverter {
	
	public static FrontData fromParameter(HttpServletRequest request) {
		return fromParameter(request, true);
	}
	
	public static FrontData fromParameter(HttpServletRequest request, boolean encoder){
		FrontData data=new FrontData();
		for(Iterator localIterator=request.getParameterMap().keySet().iterator();localIterator.hasNext();){
			Object name =localIterator.next();
			if(encoder){
				try {
					data.AddField((String)name, new String(request.getParameter((String)name).getBytes("ISO-8859-1"), "UTF8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}else{
				 data.AddField((String)name, request.getParameter((String)name));
			}
		}
		
		Enumeration en = request.getSession().getAttributeNames();
		while (en.hasMoreElements()) {
			String name = (String) en.nextElement();
			data.AddField(name, request.getSession().getAttribute(name));
		}
		return data;
	}
	
	public static FrontData fromOnlyParameter(HttpServletRequest request) {
		FrontData data=new FrontData();
		for (Iterator localIterator = request.getParameterMap().keySet().iterator(); localIterator.hasNext();) {
			Object name = localIterator.next();
			try {
				data.AddField((String) name,
						new String(request.getParameter((String) name).getBytes("ISO-8859-1"), "UTF8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return data;
	}

}
