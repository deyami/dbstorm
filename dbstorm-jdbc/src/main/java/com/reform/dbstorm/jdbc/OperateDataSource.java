package com.reform.dbstorm.jdbc;

import java.util.List;

/**
 * DataAccesser工具提供的对外功能接口.
 *
 * @author huaiyu.du@opi-corp.com 2011-12-19 下午5:50:09
 */
public interface OperateDataSource {

    /**
     * 插入数据并返回最新自增主键.
     *
     * @param op
     * @return
     */
    public long insertReturnAutogenId(final OpUpdate op);

    /**
     * 向数据库中插入数据.
     *
     * @param op
     * @return 成功true，失败false
     * @throws DataAccessException
     */
    public boolean insert(final OpUpdate op);

    /**
     * 查询数据库是否存在指定数据.
     *
     * @param op
     * @return 存在true，不存在false
     * @throws DataAccessException
     */
    @SuppressWarnings("rawtypes")
    public boolean queryExist(final OpUniq op);

    //	/**
    //	 * 查询数据库是否存在指定数据.
    //	 *
    //	 * @param op
    //	 * @return 存在true，不存在false
    //	 * @throws DataAccessException
    //	 */
    //	@SuppressWarnings("rawtypes")
    //	public int queryId(OpUniq op);

    //	@SuppressWarnings("rawtypes")
    //	public long getQueryId(OpUniq op);

    /**
     * 在从库中查询一个整数值.
     *
     * @param op
     * @return
     * @throws DataAccessException
     */
    @SuppressWarnings("rawtypes")
    public int queryInt(final OpUniq op);

    /**
     * 查询一个整数值.
     *
     * @param op
     * @param master true表示主库，false表示从库
     * @return
     * @throws DataAccessException
     */
    @SuppressWarnings("rawtypes")
    public int queryInt(final OpUniq op, final boolean master);

    /**
     * 在从库中查询一个long值.
     *
     * @param op
     * @return
     * @throws DataAccessException
     */
    @SuppressWarnings("rawtypes")
    public long queryLong(final OpUniq op);

    /**
     * 在从库中查询一个记录序列.
     *
     * @param op
     * @return
     * @throws DataAccessException
     */
    public <T> List<T> queryList(final OpList<T> op);

    /**
     * 在从库中查询一条记录.
     *
     * @param op
     * @return
     * @throws DataAccessException
     */
    public <T> T queryUnique(final OpUniq<T> op);

    /**
     * 查询一条记录.
     *
     * @param op
     * @param master true代表主库，false代表从库.
     * @return
     * @throws DataAccessException
     */
    public <T> T queryUnique(final OpUniq<T> op, final boolean master);

    /**
     * 更新一条记录.
     *
     * @param op
     * @return
     * @throws DataAccessException
     */
    public int update(final OpUpdate op);

    /**
     * 执行批量的sql更新，返回第一条sql执行后的id
     * @param ops 必须是OpBatchUpdate类型的列表
     * @param bizName 业务名，必须输入
     * @throws DataAccessException
     */
    //	public void insertBatchReturnFirstSqlId(final List<OpBatchUpdate> ops);

    /**
     * 将得到的查询结果集封装在map中返回
     * @param op
     * @param keyFieldName 用那个字段的值作为map的key
     * @return
     * @throws DataAccessException
     */
    //    public <T> Map<String, T> queryMap(final OpMap<T> op, final String keyFieldName);

    /**
     * 将得到的查询结果集封装在map中返回
     * @param op
     * @param keyFieldNames 用多个字段的值联合作为map的key，字段名之间用_分隔
     * @return
     * @throws DataAccessException
     */
    //    public <T> Map<String, T> queryMap(final OpMap<T> op, final String[] keyFieldNames);

}
