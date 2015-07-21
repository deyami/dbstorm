package com.reform.dbstorm.jdbc;

/**
 * jdbc api访问异常.
 * 
 * @author huaiyu.du@opi-corp.com  
 * 2012-2-8 下午5:22:59
 */
public class DataAccessException extends RuntimeException {

	private static final long	serialVersionUID	= 1L;

	public DataAccessException(final String msg) {
		super(msg);
	}

	public DataAccessException(final Throwable cause) {
		super();
		initCause(cause);
	}

	public DataAccessException(final String msg, final Throwable cause) {
		super(msg);
		initCause(cause);
	}

}
