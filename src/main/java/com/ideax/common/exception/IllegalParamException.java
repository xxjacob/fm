package com.ideax.common.exception;

/**
 * 
 * @author <a href="xuxin03@baidu.com">xuxin03</a>
 */
public class IllegalParamException extends IllegalException {

	private static final long serialVersionUID = 1L;

	public IllegalParamException(int errorCode) {
		this(errorCode, errorCode + "");
	}

	public IllegalParamException(int errorCode, String msg) {
		super(errorCode, msg);
	}

}
