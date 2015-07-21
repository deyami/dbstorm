package com.reform.dbstorm.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 查询列表.
 *
 * @author huaiyu.du@opi-corp.com
 * 2012-2-13 上午11:49:28
 * @param <T>
 */
public abstract class OpList<T> extends Op {

	private List<T>	collect;

	/**
	 * 此构造器用于业务不散库的情况<br>
	 * @param sql sql语句
	 * @param bizName 业务名
	 */
	public OpList(String sql, String bizName) {
		this(sql, bizName, -1);
	}

	/**
	 * 此构造器用于业务散库的情况<br>
	 * @param sql sql语句
	 * @param bizName 业务名
	 * @param tableSuffix 散库库名的后缀，如gossip_2,应该传入 2
	 */
	public OpList(String sql, String bizName, int tableSuffix) {
		this.sql = sql;
		collect = new ArrayList<T>();
		this.bizName = bizName;
		this.tableSuffix = tableSuffix;
	}

	public final List<T> getResult() {
		return collect;
	}

	public abstract void setParam(PreparedStatement ps) throws SQLException;

	public abstract T parse(ResultSet rs) throws SQLException;

	public final void add(T ob) {
		collect.add(ob);
	}

}
