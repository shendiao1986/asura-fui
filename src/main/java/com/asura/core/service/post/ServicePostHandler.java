import com.asura.fui.service.post;

import java.util.ArrayList;
import java.util.List;

public class ServicePostHandler {
	private List<IServicePostHandler> handlers;

	public ServicePostHandler() {
		this.handlers = new ArrayList();
	}

	public void addPostHandler(IServicePostHandler handler) {
		this.handlers.add(handler);
	}

	public IServicePostHandler getPostHandler(String postId) {
		for (IServicePostHandler handler : this.handlers) {
			if (handler.canHandle(postId)) {
				return handler;
			}
		}

		return null;
	}
}
