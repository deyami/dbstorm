package com.reform.dbstorm.client.ds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reform.dbstorm.client.config.DbStormConfig;

/**
 * jdbc数据源管理器.
 * @author huaiyu.du@opi-corp.com
 * 2012-2-6 下午2:28:53
 */
public class JdbcDsFactoryManager {

	public static final Logger	log						= LoggerFactory
															.getLogger(JdbcDsFactoryManager.class);
	/**
	 * 默认工厂实现.
	 */
	public static final String	DEFAULT_FACTORY_CLASS	= "DefaultJdbcDataSourceFactory";
	/**
	 * 指定工厂类属性.
	 */
	public static final String	FACTORY_KEY				= "dsfactory";

	/**
	 * 获取一个工厂实例.
	 * @return 工厂实例
	 */
	public static JdbcDataSourceFactory getFactory() {
		return FactoryHolder.factory;
	}

	private static class FactoryHolder {

		public static final JdbcDataSourceFactory	factory	= createFactory();

		/**
		 * 加载工厂类并创建实例.
		 *
		 * @return 工厂实例
		 */
		private static JdbcDataSourceFactory createFactory() {
			String factoryClazz = getFactoryClazz();
			Class<?> clz = null;
			try {
				ClassLoader loader = Thread.currentThread().getContextClassLoader();//使用contextloader加载
				clz = loader.loadClass(factoryClazz);
				return (JdbcDataSourceFactory) clz.newInstance();
			} catch (Exception e) {
				log.error(" jdbcdsfactory class [{}] not found ,use default factory", factoryClazz);
				return new DefaultJdbcDataSourceFactory();
			}
		}
	}

	/**
	 * 取得工厂类信息.
	 *
	 * @return 工厂实现类名
	 */
	private static String getFactoryClazz() {
		String factoryClass = DEFAULT_FACTORY_CLASS;
		DbStormConfig config = DbStormConfig.getConfig();
		String clz = config.getDsFactoryClz();
		if (clz != null) factoryClass = clz;
		log.info(" use [{}] as default datasource factory", factoryClass);
		return factoryClass;
	}
}
