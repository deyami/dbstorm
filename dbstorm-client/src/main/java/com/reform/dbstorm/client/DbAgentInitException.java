package com.reform.dbstorm.client;

/**
 * DbAgent初始化异常.
 * 
 * @author huaiyu.du@opi-corp.com  
 * 2012-2-8 下午5:08:26
 */
public class DbAgentInitException extends RuntimeException {

	private static final long	serialVersionUID	= 1L;

	public DbAgentInitException(final String message) {
		super(message);
	}

	public DbAgentInitException(final Throwable cause) {
		super();
		initCause(cause);
	}

	public DbAgentInitException(final String message, final Throwable cause) {
		super(message);
		initCause(cause);
	}

}
