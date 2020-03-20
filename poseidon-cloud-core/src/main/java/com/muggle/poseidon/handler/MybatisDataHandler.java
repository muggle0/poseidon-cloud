package com.muggle.poseidon.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * @program: poseidon-cloud-core
 * @description:
 * @author: muggle
 * @create: 2020-03-10 16:25
 */

@MappedTypes({LocalDateTime.class})
@MappedJdbcTypes(JdbcType.TIMESTAMP)
public class MybatisDataHandler extends BaseTypeHandler<LocalDateTime> {

    /**
     * 将时间戳字符串存入数据库
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime parameter, JdbcType jdbcType) throws SQLException {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = parameter.atZone(zone).toInstant();
        long l = instant.toEpochMilli();
        ps.setLong(i,l);
    }

    /**
     * 把时间戳类型的字符串取出转换为Date
     */
    @Override
    public LocalDateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Timestamp timestamp = rs.getTimestamp(columnName);
        Instant instant = Instant.ofEpochMilli(timestamp.getTime());
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        Timestamp timestamp = rs.getTimestamp(columnIndex);
        Instant instant = Instant.ofEpochMilli(timestamp.getTime());
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        Timestamp timestamp = cs.getTimestamp(columnIndex);
        Instant instant = Instant.ofEpochMilli(timestamp.getTime());
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }
}
