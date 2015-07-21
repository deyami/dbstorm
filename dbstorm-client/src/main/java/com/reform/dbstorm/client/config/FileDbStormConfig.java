package com.reform.dbstorm.client.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * dbstorm系统全局配置.
 *
 * @author huaiyu.du@opi-corp.com
 * 2012-2-8 下午5:10:06
 */
public class FileDbStormConfig extends DbStormConfig {

    /**
     * 配置文件名称.
     */
    public static final String CONF_FILE = "dbstorm.properties";

    private Configuration      conf;
    private DbStormConfig      defaultConfig;

    protected FileDbStormConfig(DbStormConfig defaultConfig) {
        this.defaultConfig = defaultConfig;
        try {
            conf = new PropertiesConfiguration(CONF_FILE);
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEndpoints() {
        return conf.getString("endpoints", defaultConfig.getEndpoints());
    }

    public String getDsFactoryClz() {
        return conf.getString("dsfactory", defaultConfig.getDsFactoryClz());
    }

}
