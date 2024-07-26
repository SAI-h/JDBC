package com.saih.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {
    private static final DataSource dataSource;
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    // 项目启动时，即创建连接池对象，赋值给dataSource
    static {
        try {
            Properties properties = new Properties();
            InputStream inputStream = ConnectionPool.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 获取连接
    public static Connection getConnection() throws SQLException {
        Connection connection = threadLocal.get();
        if(connection == null) {
            connection = dataSource.getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }

    // 释放连接
    public static void release() throws SQLException {
        Connection connection = threadLocal.get();
        if(connection != null) {
            threadLocal.remove();
            connection.close();
        }
    }
}
