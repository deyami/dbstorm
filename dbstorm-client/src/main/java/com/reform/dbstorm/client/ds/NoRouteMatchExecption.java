package com.reform.dbstorm.client.ds;

/**
 * 路由不匹配引发的异常.
 * 
 * @author huaiyu.du@opi-corp.com 2012-1-23 下午6:37:31
 */
public class NoRouteMatchExecption extends RuntimeException {

	private static final long	serialVersionUID	= 1L;

	public NoRouteMatchExecption(final String pattern, final String name) {
		super("[" + pattern + "] does not have a match for db [" + name + "]");
	}

	public NoRouteMatchExecption(final Throwable cause) {
		super();
		initCause(cause);
	}

	public NoRouteMatchExecption(final String pattern, final String name, final Throwable cause) {
		super("[" + pattern + "] does not have a match for db [" + name + "]");
		initCause(cause);
	}

}
