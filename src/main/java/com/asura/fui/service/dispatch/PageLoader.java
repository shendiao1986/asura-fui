package com.asura.fui.service.dispatch;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.asura.fui.util.FuiMysqlHandler;
import com.asura.tools.data.DataRecord;
import com.asura.tools.sql.SelectSQL;
import com.asura.tools.util.FileUtil;
import com.asura.tools.util.StringUtil;
import com.google.common.base.Charsets;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.io.Files;

public class PageLoader {

	private static Cache<String, List<String>> cache = null;

	private static String page_directory = "/pages";

	public static void init(String pageDirectory, int timeout) {
		page_directory = pageDirectory;
		cache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(timeout, TimeUnit.SECONDS).build();
	}

	public static String getConfigFromFile(final String key) {
		if (cache == null) {
			init(page_directory, 10);
		}
		List<String> value = new ArrayList<String>();
		try {
			value = cache.get(key, new Callable<List<String>>() {
				@Override
				public List<String> call() throws IOException {
					System.out.println("Cache timeout for the key: " + key);
					System.out.println("Loading from page directory: " + page_directory);
					// URL page_url =
					// this.getClass().getClassLoader().getResource(page_directory+
					// "/" + key + ".xml");
					String page_url = page_directory + "/" + key + ".xml";
					File page = new File(page_url);
					List<String> lines = new ArrayList<String>();
					if (page.exists()) {
						lines = Files.readLines(page, Charsets.UTF_8);
					} else {
						URL source_page_url = this.getClass().getClassLoader().getResource("pages/" + key + ".xml");
						if (source_page_url != null) {
							// lines = Files.readLines(new
							// File(source_page_url.getFile()), Charsets.UTF_8);
							lines = Arrays.asList(FileUtil.getContentByLine(source_page_url.openStream(), "UTF-8"));
						}
					}
					return lines;
				}
			});
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return StringUtil.getStringFromStrings(value, "	");
	}

	public static void loadConfigFromDirectory(String page_directory) {
		try {
			// URL page_url =
			// PageLoader.class.getClassLoader().getResource(page_directory);
			// String[] page_files = FileUtil.getAllFileNames(page_directory);
			File pageDirectory = new File(page_directory);
			if (pageDirectory.exists() && pageDirectory.isDirectory()) {
				cache.cleanUp();
				for (File page : pageDirectory.listFiles()) {
					if (page.getName().endsWith(".xml")) {
						String key = page.getName().substring(0, page.getName().length() - 4);
						System.out.println("Loading page key: " + key);
						List<String> lines = Files.readLines(page, Charsets.UTF_8);
						System.out.println("Loading page value: " + lines);
						cache.put(key, lines);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getConfigFromDB(String server, String name) {
		SelectSQL sql = new SelectSQL("page");
		if (!(StringUtil.isNullOrEmpty(server))) {
			sql.addWhereCondition("site", server);
		}
		sql.addWhereCondition("name", name);
		List list = FuiMysqlHandler.getFuiMysqlHandler().selectList(sql);
		if (list.size() == 0) {
			sql = new SelectSQL("page");
			sql.addWhereCondition("name", name);
			list = FuiMysqlHandler.getFuiMysqlHandler().selectList(sql);
		}
		if (list.size() > 0) {
			return ((DataRecord) list.get(0)).getFieldValue("page");
		}

		return "";
	}

	public static void invalidCache(String key) {
		System.out.println("PageLoader - invalid cache for key: " + key);
		cache.invalidate(key);
	}

	public static void updateCache(String key) {
		System.out.println("PageLoader - update cache for key: " + key);
		cache.invalidate(key);
		getConfigFromFile(key);
	}

}
