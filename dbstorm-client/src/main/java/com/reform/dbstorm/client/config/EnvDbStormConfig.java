package com.reform.dbstorm.client.config;

import com.reform.dbstorm.client.util.IPAddress;
import org.apache.commons.configuration.BaseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnvDbStormConfig extends DbStormConfig {

    private static final Logger log             = LoggerFactory.getLogger(EnvDbStormConfig.class);

    private static final String PROD_ENDPOINTS  = "10.6.39.111:2181,10.6.39.50:2181,10.6.39.141:2181";
    private static final String DEV_ENDPOINTS   = "10.249.7.4:2181,10.249.7.5:2181";
    private static final String DEFAULT_FACTORY = "DefaultJdbcDataSourceFactory";

    public EnvDbStormConfig() {
        String endpoints = isDevEnv() ? DEV_ENDPOINTS : PROD_ENDPOINTS;
        this.conf = new BaseConfiguration();
        this.conf.addProperty("endpoints", endpoints);
        this.conf.addProperty("dsfactory", DEFAULT_FACTORY);
    }

    private boolean isDevEnv() {
        boolean isDev = true;

        String mode = System.getenv("DBSTORM_MODE"); //系统环境变量
        if (mode == null) mode = System.getProperty("DBSTORM_MODE"); //jvm环境变量
        if (mode == null) {
            /**
             * 自动根据网段选择
             */
            if (isProdIp()) {
                isDev = false;
            }
        } else if (mode.equalsIgnoreCase("dev")) {
            isDev = true;
        } else if (mode.equalsIgnoreCase("prod")) {
            isDev = false;
        }

        log.info("DBSTORM_MODE dev is {}", isDev);
        return isDev;
    }

    private boolean isProdIp() {
        String localIp = IPAddress.getLocalAddress();
        return localIp.startsWith("10.3.") || localIp.startsWith("10.6.")
            || localIp.startsWith("10.31.") || localIp.startsWith("10.10.");
    }

}
