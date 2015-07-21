package com.reform.dbstorm.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reform.dbstorm.client.DbAgent;

/**
 * 一个简单的jdbc模板.
 * 支持泛形.
 * 不直接抛出JDBC异常，转而抛出DataAccessException自定义异常.
 * @author huaiyu.du@opi-corp.com 2011-12-19 下午5:35:09
 */
public final class DataAccessMgr implements OperateDataSource {

    //=================== static field =========================//
    private static final Logger log = LoggerFactory.getLogger(DataAccessMgr.class);

    public static DataAccessMgr getInstance() {
        return DataAccessMgrHolder.instance;
    }

    //==================== constructor =========================//
    /**
     * 创建一个DataAccessMgr实例.
     */
    private DataAccessMgr() {}

    //======================== public ==========================//
    /**
     * 插入一条记录.固定在主库上操作.
     *
     * @param op
     * @return 操作影响的记录条数
     * @throws DataAccessException
     */
    public boolean insert(final OpUpdate op) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConn('w', op);
            ps = conn.prepareStatement(op.getSql());
            op.setParam(ps);
            int count = ps.executeUpdate();
            if (count > 0) {
                if (ps != null) {
                    ps.close();
                }
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            closeRSC(rs, ps, conn);
        }
    }

    /**
     * 批量更新.固定在主库上操作.
     * @param op
     * @return 返回一个数组，此数组内包含所有更新影响的记录条数.
     * @throws DataAccessException
     */
    public int[] batchUpdate(final OpUpdate op) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConn('w', op);
            ps = conn.prepareStatement(op.getSql());
            op.setParam(ps);
            return ps.executeBatch();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            closeRSC(rs, ps, conn);
        }
    }

    /**
     * 插入一条新记录并返回自增id.
     *
     * @return 大于0表示插入成功，0表示插入失败
     * @throws DataAccessException
     */
    public long insertReturnAutogenId(final OpUpdate op) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConn('w', op);
            ps = conn.prepareStatement(op.getSql(), Statement.RETURN_GENERATED_KEYS);
            op.setParam(ps);
            int count = ps.executeUpdate();
            if (count > 0) {
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getLong(1);
                } else {
                    return Long.valueOf(0);
                }
            } else return Long.valueOf(0);
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            closeRSC(rs, ps, conn);
        }
    }

    /**
     * 在从库上查询存在.
     *
     * @param op
     * @return true为存在，false为不存在
     * @throws DataAccessException
     */
    @SuppressWarnings("rawtypes")
    public boolean queryExist(final OpUniq op) {
        return queryExist(op, false);
    }

    /**
     * 查询存在.
     *
     * @param op
     * @param master true为在主库上查询，false为在从库查询.
     * @return  true为存在，false为不存在
     * @throws DataAccessException
     */
    @SuppressWarnings("rawtypes")
    public boolean queryExist(final OpUniq op, final boolean master) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            if (master) {
                conn = getConn('w', op);
            } else {
                conn = getConn('r', op);
            }
            ps = conn.prepareStatement(op.getSql());
            op.setParam(ps);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            closeRSC(rs, ps, conn);
        }
    }

    /**
     * 在从库上查询一个整形.
     * @param op
     * @return
     * @throws DataAccessException
     */
    @SuppressWarnings("rawtypes")
    public int queryInt(final OpUniq op) {
        return queryInt(op, false);
    }

    /**
     * 查询一个整形.
     *
     * @param op
     * @param master true为在主库上查询，false为从库
     * @return
     * @throws DataAccessException
     */
    @SuppressWarnings("rawtypes")
    public int queryInt(final OpUniq op, final boolean master) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        int result = 0;
        try {
            if (master) {
                conn = getConn('w', op);
            } else {
                conn = getConn('r', op);
            }
            ps = conn.prepareStatement(op.getSql());
            op.setParam(ps);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getInt(1);
            } else {
                return 0;
            }
            return result;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            closeRSC(rs, ps, conn);
        }
    }

    /**
     * 在从库上查询一个列表.
     * @param op
     * @return 符合条件的记录列表
     * @throws DataAccessException
     */
    public <T> List<T> queryList(final OpList<T> op) {
        return queryList(op, false);
    }

    /**
     * 查询一个列表.
     *
     * @param op
     * @param master true为在主库上查询，false为从库
     * @return 符合条件的记录列表
     * @throws DataAccessException
     */
    public <T> List<T> queryList(final OpList<T> op, final boolean master) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            if (master) {
                conn = getConn('w', op);
            } else {
                conn = getConn('r', op);
            }
            ps = conn.prepareStatement(op.getSql());
            op.setParam(ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                op.add(op.parse(rs));
            }
            return op.getResult();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            closeRSC(rs, ps, conn);
        }
    }

    /**
     * 在从库上查询长整形.
     * @param op
     * @return
     * @throws DataAccessException
     */
    @SuppressWarnings("rawtypes")
    public long queryLong(final OpUniq op) {
        return queryLong(op, false);
    }

    /**
     * 查询一个长整形.
     *
     * @param op
     * @param master  true为在主库上查询，false为从库
     * @return
     * @throws DataAccessException
     */
    @SuppressWarnings("rawtypes")
    public long queryLong(final OpUniq op, final boolean master) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        long result = 0;
        try {
            if (master) {
                conn = getConn('w', op);
            } else {
                conn = getConn('r', op);
            }
            ps = conn.prepareStatement(op.getSql());
            op.setParam(ps);
            rs = ps.executeQuery();
            if (rs.next())
                result = rs.getLong(1);
            else return 0;
            return result;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            closeRSC(rs, ps, conn);
        }
    }

    //            return op.getResult();
    //        } catch (SQLException e) {
    //            throw new DataAccessException(e);
    //        } finally {
    //            closeRSC(rs, ps, conn);
    //        }
    //    }

    /**
     * 在从库查询一个唯一值.
     */
    public <T> T queryUnique(final OpUniq<T> op) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConn('r', op);
            ps = conn.prepareStatement(op.getSql());
            op.setParam(ps);
            rs = ps.executeQuery();
            if (rs.next()) {
                op.add(op.parse(rs));
            }
            return op.getResult();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            closeRSC(rs, ps, conn);
        }
    }

    /**
     * 查询一个唯一值.
     * @param master true为在主库上操作
     */
    public <T> T queryUnique(final OpUniq<T> op, final boolean master) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            if (master) {
                conn = getConn('w', op);
            } else {
                conn = getConn('r', op);
            }
            ps = conn.prepareStatement(op.getSql());
            op.setParam(ps);
            rs = ps.executeQuery();
            if (rs.next()) {
                op.add(op.parse(rs));
            }
            return op.getResult();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            closeRSC(rs, ps, conn);
        }
    }

    /**
     * 执行更新操作.
     * @return 操作影响的记录条数.
     * @throws DataAccessException
     */
    public int update(final OpUpdate op) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = getConn('w', op);
            ps = conn.prepareStatement(op.getSql());
            op.setParam(ps);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            closeRSC(rs, ps, conn);
        }
    }

    /**
     * 没有散库的情况下，要得到一个连接，可以调用此方法
     *
     * @param bizName
     *            业务名
     * @param isReadConnection
     *            是否获取读连接
     * @return 数据库连接
     * @throws DataAccessException
     */
    public Connection getConnection(final String bizName, final boolean isReadConnection) {
        checkBizName(bizName);
        Connection conn = null;
        if (isReadConnection) {
            try {
                conn = DbAgent.getConnectionManager().getReadConnection(bizName);
            } catch (Exception e) {
                String message = "unable to get connection from \"" + bizName + "\"";
                throw new DataAccessException(message, e);
            }
        } else {
            try {
                conn = DbAgent.getConnectionManager().getWriteConnection(bizName);
            } catch (Exception e) {
                String message = "unable to get connection from \"" + bizName + "\"";
                throw new DataAccessException(message, e);
            }
        }
        return conn;
    }

    /**
     * 散库的情况下，要得到一个连接，可以调用此方法
     *
     * @param bizName
     *            业务名
     * @param tableSuffix
     *            散库名的后缀 ，如gossip_3，传入 3
     * @param isReadConnection
     *            是否获取读连接
     * @return 数据库连接
     * @throws DataAccessException
     */
    public Connection getConnection(final String bizName, final int tableSuffix,
        final boolean isReadConnection) {
        checkBizName(bizName);
        checkTableSuffix(tableSuffix);

        Connection conn = null;
        String tablePattern = getTablePattern(bizName, tableSuffix);

        try {
            if (isReadConnection) {
                conn = DbAgent.getConnectionManager().getReadConnection(bizName, tablePattern);
            } else {
                conn = DbAgent.getConnectionManager().getWriteConnection(bizName, tablePattern);
            }
        } catch (Exception e) {
            throw new DataAccessException(e);
        }

        return conn;
    }

    public void closeConnection(final Connection con) {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            throw new DataAccessException("close connection error", e);
        }
    }

    public void closeResultSet(final ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            throw new DataAccessException("close resultset error", e);
        }
    }

    public void closeRSC(final ResultSet rs, final Statement st, final Connection conn) {
        if (rs != null) try {
            rs.close();
        } catch (SQLException e) {
            log.error("close resultset error", e);
        }
        if (st != null) try {
            st.close();
        } catch (SQLException e) {
            log.error("close statement error", e);
        }
        if (conn != null) try {
            conn.close();
        } catch (SQLException e) {
            log.error("close connection error", e);
        }
    }

    public void closeStatement(final Statement st) {
        try {
            if (st != null) st.close();
        } catch (SQLException e) {
            log.error("close statement error", e);
        }
    }

    //================== private =====================//

    private Connection getConn(final char o, final Op op) {
        checkBizName(op.getBizName());
        Connection conn = null;
        if (o == 'r') {
            if (op.getTableSuffix() < 0) {
                conn = getConnection(op.getBizName(), true);
            } else {
                conn = getConnection(op.getBizName(), op.getTableSuffix(), true);
            }
        } else if (o == 'w') {
            if (op.getTableSuffix() < 0) {
                conn = getConnection(op.getBizName(), false);
            } else {
                conn = getConnection(op.getBizName(), op.getTableSuffix(), false);
            }
        } else throw new DataAccessException("没有指定是 r 还是 w");
        return conn;
    }

    private void checkBizName(String bizName) {
        if (bizName == null || bizName.length() == 0)
            throw new IllegalArgumentException("BizName 不能为null");
    }

    private void checkTableSuffix(int tableSuffix) {
        if (tableSuffix < 0) throw new IllegalArgumentException("tableSuffix 不能为null");
    }

    private String getTablePattern(final String bizName, final int tableSuffix) {
        return bizName + "_" + tableSuffix;
    }

    /**
     * instance持有者.
     *
     * @author huaiyu.du@opi-corp.com 2011-12-19 下午5:51:41
     */
    private static class DataAccessMgrHolder {

        public static final DataAccessMgr instance = new DataAccessMgr();
    }

}
