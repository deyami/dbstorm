package com.reform.dbstorm.zookeeper;

/**
 * zookeeper事件.
 * 
 * @author huaiyu.du@opi-corp.com 2012-1-29 下午4:42:30
 */
public abstract class ZKEvent {

	/**
	 * 处理事件.
	 * @throws Exception 
	 */
	abstract void run() throws Exception;
}
