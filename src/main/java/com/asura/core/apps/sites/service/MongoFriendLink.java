import com.asura.fui.apps.sites.service;

import com.cpkf.yyjd.tools.data.DataRecord;

public class MongoFriendLink extends AbstractSitebean {
	private String sub;
	private String type;
	private String key;
	private String links;

	public MongoFriendLink() {
	}

	public MongoFriendLink(String sub, String type, String key, String links) {
		this.sub = sub;
		this.type = type;
		this.key = key;
		this.links = links;
	}

	public String getSub() {
		return this.sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLinks() {
		return this.links;
	}

	public void setLinks(String links) {
		this.links = links;
	}

	public DataRecord toDataRecord() {
		DataRecord dr = new DataRecord();
		dr.AddField("sub", this.sub);
		dr.AddField("type", this.type);
		dr.AddField("key", this.key);
		dr.AddField("links", this.links);

		return dr;
	}

	public String getTableName() {
		return "f_link";
	}

	public String[] getIndexes() {
		return new String[] { "sub", "type", "key" };
	}

	public String[] getKeys() {
		return new String[] { "sub", "type", "key" };
	}
}
