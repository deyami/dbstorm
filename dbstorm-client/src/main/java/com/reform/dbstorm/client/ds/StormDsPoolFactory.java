package com.reform.dbstorm.client.ds;

import com.reform.dbstorm.client.DbAgent;
import com.reform.dbstorm.xml.DbInstanceConfig;

/**
 * dbstorm数据源工厂，根据配置类型来创建路由数据源或单实例数据源。
 * 
 * @author huaiyu.du@opi-corp.com 2012-1-16 下午5:18:27
 */
public class StormDsPoolFactory {

	public static StormDataSourcePool createStormDs(final DbAgent agent,
		final DbInstanceConfig config) {
		StormDataSourcePool ds = null;
		switch (config.getType()) {
		case CDbInstanceSingler:
			ds = new SinglerDsPool(config);
			break;
		case CDbInstanceRouter:
			ds = new RouterDsPool(agent, config);
			break;
		default:
			throw new IllegalArgumentException("unknown instance type:" + config.getType());
		}
		return ds;
	}
}
