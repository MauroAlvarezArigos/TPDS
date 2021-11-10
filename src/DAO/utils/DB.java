package DAO.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	String url =" ";
	String user=" ";
	String pass=" ";
	private static Boolean conexion = true;

    private static Connection crearConexion() {

        String url =  "jdbc:postgresql://localhost:5432/TP6DisenioSistemas";
        String user = "postgres";
        String pass = "0000";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            System.out.println("Error al conectarse al servidor");
            e.printStackTrace();
        }
        conexion = true;
        return conn;
    }
    public static  Connection getConexion() {

        if(conexion) return crearConexion();
        return null;
    }
}
