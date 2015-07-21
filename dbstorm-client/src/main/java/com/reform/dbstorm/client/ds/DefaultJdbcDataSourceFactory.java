package com.reform.dbstorm.client.ds;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.reform.dbstorm.xml.DbServerConfig;

/**
 * 默认的jdbc数据源工厂实现.
 * 使用dbcp作为数据源.
 *
 * @author huaiyu.du@opi-corp.com
 * 2012-2-8 下午5:11:30
 */
public final class DefaultJdbcDataSourceFactory implements JdbcDataSourceFactory {

    public static final String CLASSNAME_MYSQL = "com.mysql.jdbc.Driver";
    public static final String EMPTY_PATTERN   = "";

    public DataSource createDataSource(final DbServerConfig server) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(CLASSNAME_MYSQL);
        ds.setUrl("jdbc:mysql://" + server.getHost()
            + (server.getPort() == 0 ? "" : ":" + String.valueOf(server.getPort())) + "/"
            + server.getDatabase() + "?user=" + server.getUser() + "&password="
            + server.getPassword() + "&connectTimeout=100&autoReconnect=true&characterEncoding="
            + String.valueOf(server.getCharset()));
        ds.setUsername(server.getUser());
        ds.setPassword(server.getPassword());
        ds.setInitialSize(0);// This ensures no idle connection which only for
        // initialize.
        ds.setMaxActive(50);
        ds.setMinIdle(0);
        ds.setMaxIdle(ds.getMaxActive() / 10 + 1);
        ds.setMaxWait(10);
        ds.setTestOnReturn(false);
        ds.setTestWhileIdle(true);
        ds.setMinEvictableIdleTimeMillis(60 * 1000 * 20);
        ds.setTimeBetweenEvictionRunsMillis(1000 * 60);
        ds.setNumTestsPerEvictionRun(5);
        ds.setValidationQuery("SELECT 1");
        return ds;
    }

    /**
     * 关闭数据源.
     */
    public void closeDataSource(final DataSource ds) {
        if (ds instanceof BasicDataSource) {
            BasicDataSource new_name = (BasicDataSource) ds;
            try {
                new_name.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String describeDataSource(DataSource ds) {
        StringBuffer buf = new StringBuffer();
        if (ds != null) {
            buf.append(((BasicDataSource) ds).getNumActive()).append(",")
                .append(((BasicDataSource) ds).getNumIdle());
        }
        return buf.toString();
    }

}
