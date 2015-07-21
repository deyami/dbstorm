package com.reform.dbstorm.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 更新记录.
 *
 * @author huaiyu.du@opi-corp.com
 * 2012-2-13 上午11:48:40
 */
public abstract class OpUpdate extends Op {

    /**
     * 此构造器用于业务不散库的情况<br>
     * @param sql sql语句
     * @param bizName 业务名
     */
    public OpUpdate(String sql, String bizName) {
        this.sql = sql;
        this.bizName = bizName;
    }

    /**
     * 此构造器用于业务散库的情况<br>
     * @param sql sql语句
     * @param bizName 业务名
     * @param tableSuffix 散库库名的后缀，如gossip_2,应该传入 2
     */
    public OpUpdate(String sql, String bizName, int tableSuffix) {
        this.sql = sql;
        this.bizName = bizName;
        this.tableSuffix = tableSuffix;
    }

    abstract public void setParam(PreparedStatement ps) throws SQLException;
}
