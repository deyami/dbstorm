package com.reform.dbstorm.admin;

import org.apache.commons.cli.Options;

import com.reform.dbstorm.admin.config.AdminConfig;
import com.reform.dbstorm.zookeeper.ZKClient;

/**
 * dbstorm控制台命令.
 *
 * -------------------------------
 *
 *
 *
 * @author huaiyu.du@opi-corp.com
 * 2012-2-6 下午4:13:24
 *
 *
 */
public abstract class StormCommand {

	/**
	 * zookeeper根目录
	 */
	public static final String	CHROOT	= "/dbstorm";

	/**
	 * 根据db名称构造zookeeper path.
	 *
	 * @param db
	 * @return
	 */
	protected static String cacuDbPath(final String db) {
		return CHROOT + "/" + db;
	}

	protected ZKClient createZKClient() {
		AdminConfig config = AdminConfig.getConfig();
		return new ZKClient(config.getEndpoints());
	}

	protected abstract Options createOptions();

	protected abstract void printHelp();

	protected abstract void responseCmd(String[] args);

	public static interface StormTask extends Runnable {

	}
}
