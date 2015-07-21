package com.reform.dbstorm.zookeeper.exception;

/**
 * 对象序列化异常.
 * 
 * @author huaiyu.du@opi-corp.com 2012-1-19 下午3:41:55
 */
public class ZKDataSerializeException extends RuntimeException {

	private static final long	serialVersionUID	= 1L;

	public ZKDataSerializeException(final Throwable cause) {
		super();
		initCause(cause);
	}
}
