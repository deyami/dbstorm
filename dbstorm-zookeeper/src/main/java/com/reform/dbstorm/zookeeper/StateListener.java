package com.reform.dbstorm.zookeeper;

/**
 * 状态监听器.
 * 
 * @author huaiyu.du@opi-corp.com 2012-1-29 下午4:45:03
 */
public interface StateListener {

	/**
	 * 新连接建立或重连.
	 */
	void onNewSession();

	/**
	 * 状态发生改变.
	 */
	void onStateChange();
}
