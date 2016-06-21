import com.asura.fui.service.dispatch;

import java.util.HashSet;


public class UrlFieldMeeter implements IUrlMeeter {
	private String sql;
	private String key;
	private HashSet<String> set;

	public String getSql() {
		return this.sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public HashSet<String> getSet() {
		return this.set;
	}

	public void setSet(HashSet<String> set) {
		this.set = set;
	}

	public synchronized boolean meet(String url) {
		if (this.set == null) {
			this.set = new HashSet();
		}

		return false;
	}
}
