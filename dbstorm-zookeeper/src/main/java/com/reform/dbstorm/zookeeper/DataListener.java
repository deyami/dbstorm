package com.reform.dbstorm.zookeeper;

/**
 * 数据监听器.
 * 
 * @author huaiyu.du@opi-corp.com 2012-1-29 下午4:46:23
 */
public interface DataListener {

	/**
	 * 获取znode path.
	 * @return
	 */
	String getPath();

	/**
	 * 数据发生改变
	 */
	void onDataChange();
}
