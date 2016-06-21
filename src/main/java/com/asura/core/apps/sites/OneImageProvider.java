import com.asura.fui.apps.sites;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cpkf.yyjd.tools.data.DataRecord;
import com.asura.core.service.data.IOneDataProvider;
import com.asura.core.service.dispatch.FuiUrl;
import com.asura.fui.FrontData;

public class OneImageProvider implements IOneDataProvider {
	private static final Random RANDOM = new Random();

	private static final Pattern PATTERN = Pattern.compile("<img.*?src=\"(.*?)\"");

	private static final Pattern PATTERN_SRC = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)");

	public void build(FrontData data, DataRecord record) {
		FuiUrl url = (FuiUrl) data.getValue("fui-url");
		List<String> list = getImageUrl(record.getFieldValue("content"));

		if (list.size() > 0) {
			int i = 1;
			for (String str : list) {
				record.AddField("image" + i, str);
				++i;
			}
		} else {
			record.AddField("image1", url.toUrlBase() + getRandomBigImage());
		}
	}

	private static List<String> getImageUrl(String content) {
		Matcher m = PATTERN.matcher(content);
		List list = new ArrayList();
		while (m.find()) {
			String str = m.group();
			Matcher m1 = PATTERN_SRC.matcher(str);
			if (m1.find()) {
				list.add(m1.group(1));
			}
		}

		System.out.println(list);
		return list;
	}

	public static String getRandomBigImage() {
		return "/images/random/big/big" + (1 + RANDOM.nextInt(9)) + ".jpg";
	}

	public static String getRandomSmallImage() {
		return "/images/random/small/tb" + (1 + RANDOM.nextInt(19)) + ".jpg";
	}

	public static void main(String[] args) {
		String str = "规划<img class=\"alignnone\" alt=\"\" src=\"http://www.baidu.com/img/baidu_aaa.gif\" width=\"270\" height=\"129\" />法规<img class=\"alignnone\" alt=\"\" src=\"http://www.baidu.com/img/baidu_sylogo1.gif\" width=\"270\" height=\"129\" />";
		getImageUrl(str);
		System.out.println(getRandomBigImage());
		System.out.println(getRandomSmallImage());
	}
}
