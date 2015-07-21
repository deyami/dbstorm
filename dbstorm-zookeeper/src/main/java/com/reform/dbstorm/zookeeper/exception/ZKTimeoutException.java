package com.reform.dbstorm.zookeeper.exception;

/**
 * zookeeper连接超时引发的异常。此超时是ZKClient自行引入的定义。
 * 
 * @author huaiyu.du@opi-corp.com 2012-1-19 下午3:43:17
 * 
 *
 */
public class ZKTimeoutException extends RuntimeException {

	private static final long	serialVersionUID	= 1L;

	public ZKTimeoutException(String endpoints, long timeout) {
		super("unable to connect to zk-server [" + endpoints + "] for timeout [" + timeout + "]");
	}

}
