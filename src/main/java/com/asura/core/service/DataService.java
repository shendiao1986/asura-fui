import com.asura.fui.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asura.core.service.data.DataProvider;
import com.asura.core.service.data.DataProviderCache;
import com.asura.core.util.JsonUtil;
import com.asura.core.util.ServiceUtil;
import com.asura.fui.DataConverter;
import com.asura.fui.FrontData;


public class DataService extends HttpServlet {
	private static final long serialVersionUID = -5164872685520450152L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf8");

		FrontData data = DataConverter.fromParameter(request);

		String site = request.getServerName();
		String category = data.getValueString("cat");
		String key = data.getValueString("key");

		System.out.println(data);

		DataProvider dp = DataProviderCache.getBuilder(site, category, key);
		if (dp == null) {
			dp = DataProviderCache.getBuilder(category, key);
		}

		if (dp != null)
			ServiceUtil.output(JsonUtil.toJson(dp.getProvider().build(data)), response);
		else
			ServiceUtil.output("", response);
	}
}
