package DAO.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//TEST 1

//From https://stackoverflow.com/questions/34497253/how-to-call-multiple-dao-functions-in-a-transaction


public class ConnectionWrapper {


    private DB dataSource;

    private Connection connection;


    public ConnectionWrapper() {
        this.dataSource = new DB();
        this.connection = dataSource.getConnection();
    }


    public void destroy() /*throws SQLException*/ {
        try{
            this.connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        };
    }

    public void begin() /*throws SQLException*/ {
        try{
            this.connection.setAutoCommit(false);
        }catch (SQLException e){
            e.printStackTrace();
    };
    }

    public void commit() /*throws SQLException*/ {
        try {
            this.connection.commit();
            this.connection.setAutoCommit(true);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void rollback() /*throws SQLException*/ {
        try {
            this.connection.rollback();
            this.connection.setAutoCommit(true);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void setAutoCommit() /*throws SQLException*/{
        try {
            this.connection.setAutoCommit(true);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
