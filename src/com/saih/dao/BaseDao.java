package com.saih.dao;

import com.saih.util.ConnectionPool;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class BaseDao {


//    // 传统连接，获取数据库连接的方式
//    protected Connection getConnection() throws SQLException {
//        try {
//            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
//            Properties properties = new Properties();
//            properties.load(inputStream);
//            // jdbc4.0之后不需要显式的去加载驱动，如果驱动包符合 SPI 模式就会自动加载
//            // String driver = properties.getProperty("driver");
//            String url = properties.getProperty("url");
//            String user = properties.getProperty("user");
//            String password = properties.getProperty("password");
//            // Class.forName(driver);
//            return DriverManager.getConnection(url, user, password);
//        } catch (IOException e) {
//            System.out.println(e);
//            return null;
//        }
//    }

    // 设置参数
    private void setParameters(PreparedStatement statement, Object... params) throws SQLException {
        if(params != null) {
            for(int i = 0; i < params.length; i++) statement.setObject(i + 1, params[i]);
        }
    }

    // 将 ResultSet 转换为实体对象的方法
    private <T> T mapResultSetToEntity(ResultSet rs, Class<T> clazz) throws SQLException {
        try {
            T entity = clazz.getDeclaredConstructor().newInstance();
            for(Field field: clazz.getDeclaredFields()) {
                field.setAccessible(true); // 解除私有属性封装
                field.set(entity, rs.getObject(field.getName()));
            }
            return entity;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println(e);
            return null;
        }
    }

    // 数据库更新操作
    public int update(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            setParameters(statement, params);
            return statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return -1;
        } finally {
            try {
                ConnectionPool.release();
                assert statement != null;
                statement.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    // 数据库查询操作
    public <T> List<T> query(Class<T> clazz, String sql, Object... params) {
        List<T> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getConnection();
            statement = connection.prepareStatement(sql);
            setParameters(statement, params);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) resultList.add(mapResultSetToEntity(rs, clazz));
            return resultList;
        } catch (SQLException e) {
            System.out.println(e);
            return resultList;
        } finally {
            try {
                ConnectionPool.release();
                assert statement != null;
                statement.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
