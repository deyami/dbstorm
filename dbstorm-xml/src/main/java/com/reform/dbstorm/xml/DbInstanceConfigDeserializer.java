package com.reform.dbstorm.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reform.dbstorm.zookeeper.DataDeserializer;
import com.reform.dbstorm.zookeeper.exception.ZKDataDeserializeException;

/**
 * 将配置信息反序列化为DbInstanceConfig实例.
 * 
 * @author huaiyu.du@opi-corp.com  
 * 2012-2-8 下午5:39:15
 */
public class DbInstanceConfigDeserializer implements DataDeserializer<DbInstanceConfig> {

	public static final Logger	log	= LoggerFactory.getLogger(DbInstanceConfigDeserializer.class);

	public DbInstanceConfig deserialize(final byte[] data) {
		if (data == null || data.length == 0) {
			return null;
		}
		try {
			String xml = new String(data, "utf-8");//xml配置必须为utf-8字符集编写
			log.debug("xml config: {}", xml);
			DbXmlParser xmlConfig = new DbXmlParser(xml);
			return xmlConfig.getDbInstanceConfig();
		} catch (Exception e) {
			throw new ZKDataDeserializeException(e);
		}
	}
}
