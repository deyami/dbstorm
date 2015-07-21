package com.reform.dbstorm.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 查询唯一记录.
 *
 * @author huaiyu.du@opi-corp.com
 * 2012-2-13 上午11:46:43
 * @param <T>
 */
public abstract class OpUniq<T> extends Op {

    public T result;

    /**
     * 此构造器用于业务不散库的情况<br>
     * @param sql sql语句
     * @param bizName 业务名
     */
    public OpUniq(String sql, String bizName) {
        this.sql = sql;
        result = null;
        this.bizName = bizName;
    }

    /**
     * 此构造器用于业务散库的情况<br>
     * @param sql sql语句
     * @param bizName 业务名
     * @param tableSuffix 散库库名的后缀，如gossip_2,应该传入 2
     */
    public OpUniq(String sql, String bizName, int tableSuffix) {
        this.sql = sql;
        result = null;
        this.bizName = bizName;
        this.tableSuffix = tableSuffix;
    }

    abstract public void setParam(PreparedStatement ps) throws SQLException;

    abstract public T parse(ResultSet rs) throws SQLException;

    public final T getResult() {
        return result;
    }

    public final void add(T ob) {
        result = ob;
    }

}
