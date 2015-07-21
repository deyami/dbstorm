package com.reform.dbstorm.client.config;

import org.apache.commons.configuration.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DbStormConfig {

    private static final Logger        log           = LoggerFactory.getLogger(DbStormConfig.class);

    private static final DbStormConfig defaultConfig = new EnvDbStormConfig();

    protected Configuration            conf;

    public String getEndpoints() {
        return conf.getString("endpoints");
    }

    public String getDsFactoryClz() {
        return conf.getString("dsfactory");
    }

    public static DbStormConfig getConfig() {
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {

        public static final DbStormConfig instance = createConfig();

        private static DbStormConfig createConfig() {
            DbStormConfig config = null;
            try {
                config = new FileDbStormConfig(defaultConfig);
            } catch (Throwable e) {
                config = defaultConfig;
                log.info("fail to read config file, use default set ");
            }
            return config;
        }
    }

}
