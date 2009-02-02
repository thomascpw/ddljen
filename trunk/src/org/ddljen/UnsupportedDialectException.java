package org.ddljen;

public class UnsupportedDialectException extends DDLJenException {

	public UnsupportedDialectException() {
		super();
	}

	public UnsupportedDialectException(String s) {
		super(s);
	}

	public UnsupportedDialectException(String s, Throwable t) {
		super(s, t);
	}

	public UnsupportedDialectException(Throwable t) {
		super(t);
	}

}
