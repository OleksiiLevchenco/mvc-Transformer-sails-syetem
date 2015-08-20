package com.levchenko.transformerShop;

import com.mchange.v2.c3p0.ComboPooledDataSource;


import java.beans.PropertyVetoException;
import java.sql.SQLException;

/**
 * @author Alexey Levchenko
 */
public class C3p0Test {


    public static void main(String[] args) throws PropertyVetoException, SQLException {
        ComboPooledDataSource source = new ComboPooledDataSource();

        source.setDriverClass("com.mysql.jdbc.Driver");
        source.setJdbcUrl("jdbc:mysql://localhost:3306/study");
        source.setUser("root");
        source.setPassword("serverPassword");



        System.out.println(source.getConnection());
    }


}
