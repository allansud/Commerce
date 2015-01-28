package br.com.commerce.util;

public class UtilException extends Exception {

	private static final long serialVersionUID = -3062610310823005332L;

	public UtilException() {
		super();
	}

	public UtilException(String message, Throwable cause) {
		super(message, cause);
	}

	public UtilException(String message) {
		super(message);
	}

	public UtilException(Throwable cause) {
		super(cause);
	}
}
