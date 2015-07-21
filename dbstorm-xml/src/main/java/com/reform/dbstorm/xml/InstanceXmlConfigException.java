package com.reform.dbstorm.xml;

/**
 * xml配置文件格式或字符异常,此异常可由<code>StromXmlConfigException</code>引发.
 * 
 * @author huaiyu.du@opi-corp.com  
 * 2012-2-8 下午5:42:22
 */
public class InstanceXmlConfigException extends Exception {

	private static final long	serialVersionUID	= 1L;

	public InstanceXmlConfigException(final String xml) {
		super("xml config error [" + xml + "]");
	}

	public InstanceXmlConfigException(final String xml, Throwable cause) {
		super("xml config error [" + xml + "]");
		initCause(cause);
	}
}
