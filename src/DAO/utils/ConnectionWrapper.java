package DAO.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//From https://stackoverflow.com/questions/34497253/how-to-call-multiple-dao-functions-in-a-transaction


public class ConnectionWrapper {


    private DB dataSource;

    private Connection connection;


    public ConnectionWrapper() {
        this.connection = dataSource.getConexion();
    }


    public void destroy() throws SQLException {
        this.connection.close();
    }

    public void begin() throws SQLException {
        this.connection.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        this.connection.commit();
        this.connection.setAutoCommit(true);
    }

    public void rollback() throws SQLException {
        this.connection.rollback();
        this.connection.setAutoCommit(true);
    }

    public Connection getConnection() {
        return connection;
    }
}
