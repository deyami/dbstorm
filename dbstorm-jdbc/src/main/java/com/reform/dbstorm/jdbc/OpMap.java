package com.reform.dbstorm.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 查询一个集合.
 *
 * @author huaiyu.du@opi-corp.com
 * 2012-2-13 上午11:47:45
 * @param <T>
 */
public abstract class OpMap<T> extends Op {

    private Map<String, T> collect;

    /**
     * 此构造器用于业务不散库的情况<br>
     * @param sql sql语句
     * @param bizName 业务名
     */
    public OpMap(String sql, String bizName) {
        this.sql = sql;
        collect = new HashMap<String, T>();
        this.bizName = bizName;
    }

    /**
     * 此构造器用于业务散库的情况<br>
     * @param sql sql语句
     * @param bizName 业务名
     * @param tableSuffix 散库库名的后缀，如gossip_2,应该传入 2
     */
    public OpMap(String sql, String bizName, int tableSuffix) {
        this.sql = sql;
        collect = new HashMap<String, T>();
        this.bizName = bizName;
        this.tableSuffix = tableSuffix;
    }

    public final Map<String, T> getResult() {
        return collect;
    }

    abstract public void setParam(PreparedStatement ps) throws SQLException;

    abstract public T parse(ResultSet rs) throws SQLException;

    public final void add(String keyFieldName, T ob) {
        collect.put(keyFieldName, ob);
    }

}
