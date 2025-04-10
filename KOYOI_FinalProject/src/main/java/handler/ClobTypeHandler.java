package com.koyoi.main.handler;


import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClobTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setClob(i, new javax.sql.rowset.serial.SerialClob(parameter.toCharArray()));  // CLOB 설정
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Clob clob = rs.getClob(columnName);
        return clob != null ? clob.getSubString(1, (int) clob.length()) : null;  // CLOB을 String으로 반환
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Clob clob = rs.getClob(columnIndex);
        return clob != null ? clob.getSubString(1, (int) clob.length()) : null;  // CLOB을 String으로 반환
    }

    @Override
    public String getNullableResult(java.sql.CallableStatement cs, int columnIndex) throws SQLException {
        Clob clob = cs.getClob(columnIndex);
        return clob != null ? clob.getSubString(1, (int) clob.length()) : null;  // CLOB을 String으로 반환
    }
}
