package com.reform.dbstorm.zookeeper;

import com.reform.dbstorm.zookeeper.exception.ZKDataDeserializeException;

/**
 * 对象反序列化.
 * 
 * @author huaiyu.du@opi-corp.com  
 * 2012-1-29 下午4:52:31
 * @param <T>
 */
public interface DataDeserializer<T> {

	/**
	 * 反序列化.
	 * @param data
	 * @return
	 * @throws com.reform.dbstorm.zookeeper.exception.ZKDataDeserializeException
	 */
	T deserialize(byte[] data) throws ZKDataDeserializeException;
}
