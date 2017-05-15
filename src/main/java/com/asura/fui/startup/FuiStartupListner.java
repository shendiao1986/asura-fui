package com.asura.fui.startup;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.asura.fui.service.StaticCacheService;
import com.asura.fui.util.FuiMysqlHandler;

public class FuiStartupListner implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String fuiDbConfig = StaticCacheService.getSettingProperty("fuiDbConfig") == null ? "connection.xml"
				: StaticCacheService.getSettingProperty("fuiDbConfig");

		System.out.println("fui db config file name is " + fuiDbConfig);

		FuiMysqlHandler.setConfigFile(fuiDbConfig);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// do nothing

	}

}
