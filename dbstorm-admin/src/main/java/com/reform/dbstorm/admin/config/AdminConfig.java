package com.reform.dbstorm.admin.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 管理台配置.
 * 
 * @author huaiyu.du@opi-corp.com  
 * 2012-2-8 下午5:01:28
 */
public final class AdminConfig {

	/**
	 * 配置文件名称.
	 */
	public static final String	CONF_FILE	= "dbstorm.properties";

	private final Configuration	conf;

	private AdminConfig() {
		try {
			conf = new PropertiesConfiguration(CONF_FILE);
		} catch (ConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	public String getEndpoints() {
		return conf.getString("endpoints");
	}

	public static AdminConfig getConfig() {
		return InstanceHolder.instance;
	}

	private static class InstanceHolder {

		public static final AdminConfig	instance	= new AdminConfig();
	}
}
