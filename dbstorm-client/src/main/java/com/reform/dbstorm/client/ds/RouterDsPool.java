package com.reform.dbstorm.client.ds;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reform.dbstorm.client.DbAgent;
import com.reform.dbstorm.xml.DbInstanceConfig;
import com.reform.dbstorm.xml.RouteConfig;

/**
 * 可路由数据源.
 *
 * @author huaiyu.du@opi-corp.com 2012-1-16 下午5:18:07
 */
public class RouterDsPool implements StormDataSourcePool {

	public static Logger			log	= LoggerFactory.getLogger(RouterDsPool.class);

	private final DbAgent			agent;
	private final DbInstanceConfig	config;

	public RouterDsPool(final DbAgent agent, final DbInstanceConfig config) {
		this.agent = agent;
		this.config = config;
	}

	public DataSource getReadableDs(final String pattern) {
		return findDataSource(pattern).getReadableDs(pattern);
	}

	public DataSource getWriteableDs(final String pattern) {
		return findDataSource(pattern).getWriteableDs(pattern);
	}

	/**
	 * 按表达式找到匹配数据源.
	 * @param pattern 匹配模式
	 * @return
	 */
	protected StormDataSourcePool findDataSource(final String pattern) {
		for (RouteConfig route : config.getRoutes()) {
			log.debug("Comparing {} aginst {}", pattern, route.getExpression());
			if (pattern.matches(route.getExpression())) {
				return agent.getDsPool(route.getInstance());
			}
		}
		throw new NoRouteMatchExecption(pattern, config.getName());
	}

	public long getTimeStamp() {
		return config.getTimestamp();
	}

	public void close() {
		log.debug("close route {}", config.getName());
		//什么也不做
	}

	@Override
	public String toString() {
		return "RouterInstance: " + config.getName();
	}
}
