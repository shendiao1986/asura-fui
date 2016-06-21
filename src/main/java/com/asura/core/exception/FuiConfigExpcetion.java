import com.asura.fui.exception;

public class FuiConfigExpcetion extends RuntimeException {
	private static final long serialVersionUID = -8575424562135763998L;

	public FuiConfigExpcetion(String msg) {
		super(msg);
	}

	public FuiConfigExpcetion(Exception e) {
		super(e);
	}
}