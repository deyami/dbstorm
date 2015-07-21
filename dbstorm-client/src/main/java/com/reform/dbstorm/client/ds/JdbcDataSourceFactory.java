package com.reform.dbstorm.client.ds;

import javax.sql.DataSource;

import com.reform.dbstorm.xml.DbServerConfig;

/**
 * jdbc数据源工厂.
 *
 * @author huaiyu.du@opi-corp.com
 * 2012-2-8 下午5:12:49
 */
public interface JdbcDataSourceFactory {

	/**
	 * 创建一个数据源.
	 * @param server 数据库服务器配置
	 * @return
	 */
	DataSource createDataSource(final DbServerConfig server);

	/**
	 * 回收一个数据源.
	 * 数据源被回收后将不能再被使用.
	 * @param ds 将要被回收的数据源
	 */
	void closeDataSource(final DataSource ds);

	/**
	 * 获取一个数据源的描述信息.
	 * @param ds
	 * @return
	 */
	String describeDataSource(final DataSource ds);

}
