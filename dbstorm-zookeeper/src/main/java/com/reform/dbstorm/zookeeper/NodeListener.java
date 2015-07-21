package com.reform.dbstorm.zookeeper;

/**
 * 节点监听器.
 * 
 * @author huaiyu.du@opi-corp.com 2012-1-29 下午4:46:33
 */
public interface NodeListener {

	/**
	 * 当数据库配置发生改变后回调.
	 */
	void onNodeChange();

	/**
	 * 获取znode path.
	 * 
	 * @return
	 */
	String getPath();
}
