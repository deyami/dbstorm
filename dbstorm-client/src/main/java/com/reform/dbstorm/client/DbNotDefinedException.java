package com.reform.dbstorm.client;

/**
 * 数据库未定义异常.
 * 
 * @author huaiyu.du@opi-corp.com  
 * 2012-2-8 下午5:08:58
 */
public class DbNotDefinedException extends RuntimeException {

	private static final long	serialVersionUID	= 1L;

	public DbNotDefinedException(final String db) {
		super("config for [" + db + "] is not be defined properly");
	}

	public DbNotDefinedException(final String db, final Throwable cause) {
		super("config for [" + db + "] is not be defined properly");
		initCause(cause);
	}

}
