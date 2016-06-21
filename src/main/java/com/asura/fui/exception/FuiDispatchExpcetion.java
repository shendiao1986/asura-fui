package com.asura.fui.exception;

public class FuiDispatchExpcetion extends RuntimeException {
	private static final long serialVersionUID = -8575424562135763998L;

	public FuiDispatchExpcetion(String msg) {
		super(msg);
	}

	public FuiDispatchExpcetion(Exception e) {
		super(e);
	}
}
