package com.reform.dbstorm.zookeeper;

import com.reform.dbstorm.zookeeper.exception.ZKDataSerializeException;

/**
 * 对象序列化。
 * 
 * @author huaiyu.du@opi-corp.com  
 * 2012-1-29 下午4:53:04
 * @param <T>
 */
public interface DataSerializer<T> {

	/**
	 * 序列化.
	 * @param obj
	 * @return
	 * @throws ZKDataSerializeException
	 */
	byte[] serialize(final T obj) throws ZKDataSerializeException;

}
