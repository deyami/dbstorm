package com.reform.dbstorm.zookeeper.exception;

/**
 * zookeeper异常.
 * 
 * @author huaiyu.du@opi-corp.com  
 * 2012-2-8 下午5:33:26
 */
public class ZKKeeperException extends RuntimeException {

	private static final long	serialVersionUID	= 1L;

	public ZKKeeperException(final Throwable cause) {
		super();
		initCause(cause);
	}

}
