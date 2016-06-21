import com.asura.fui.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asura.core.service.post.IServicePostHandler;
import com.asura.core.service.post.ServiceHandlerCache;
import com.asura.fui.DataConverter;


public class PostService extends HttpServlet {
	private static final long serialVersionUID = -5164872685520450152L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		request.getSession().setAttribute("aaa", "bbb");

		System.out.println("post service last:" + request.getHeader("Referer"));

		String postId = request.getParameter("post_id");

		IServicePostHandler handler = ServiceHandlerCache.getServicePostHandler(request.getServerName())
				.getPostHandler(postId);

		System.out.println("post id:" + postId);
		System.out.println("post handler:" + handler);
		System.out.println("post paras:" + DataConverter.fromParameter(request));

		if (handler != null) {
			String re = handler.redirect(request, response);

			response.sendRedirect(re);
		}
	}
}
