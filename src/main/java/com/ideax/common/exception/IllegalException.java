package com.ideax.common.exception;

/**
 * base runtime exception
 * @author <a href="xuxin03@baidu.com">xuxin03</a>
 */
public class IllegalException extends IllegalStateException {

	private static final long serialVersionUID = 1L;

	private int errorCode;

	public IllegalException(int errorCode) {
		this(errorCode, errorCode + "");
	}

	public IllegalException(int errorCode, String msg) {
		super(msg);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

}
