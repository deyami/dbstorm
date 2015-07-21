package com.reform.dbstorm.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {

    T mapRow(final ResultSet rs, final int arg1) throws SQLException;
}
