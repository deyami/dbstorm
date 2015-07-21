package com.reform.dbstorm.zookeeper.exception;

/**
 * 对象反序列化异常.
 * 
 * @author huaiyu.du@opi-corp.com 2012-1-19 下午3:41:38
 */
public class ZKDataDeserializeException extends RuntimeException {

	private static final long	serialVersionUID	= 1L;

	public ZKDataDeserializeException(final Throwable cause) {
		super();
		initCause(cause);
	}

}
