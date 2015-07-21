package com.reform.dbstorm.zookeeper;

/**
 * 断言工具类.
 * 
 * @author huaiyu.du@opi-corp.com  
 * 2012-2-6 下午11:40:24
 */
public final class Assertion {

	/**
	 * 断言当前线程是否和目标不是同一线程.
	 * 
	 * @param zookeeperEventThread
	 */
	public static void isNotInEventThread(Thread zookeeperEventThread) {
		if (zookeeperEventThread != null && Thread.currentThread() == zookeeperEventThread) {
			throw new IllegalArgumentException("Must not be done in the zookeeper event thread.");
		}
	}
}
