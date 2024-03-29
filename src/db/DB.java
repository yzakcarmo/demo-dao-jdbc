package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection conn = null;
    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties prop = loadProperties();
                String url = prop.getProperty("dburl");
                conn = DriverManager.getConnection(url, prop);
            }
            catch (SQLException e) {
                throw new DbException("Erro ao conectar no BD: " + e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            }
            catch (SQLException e) {
                throw new DbException("Erro ao conectar no BD: " + e.getMessage());
            }
        }
    }
    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            }
            catch (SQLException e) {
                throw new DbException("Erro ao conectar no BD: " + e.getMessage());
            }
        }
    }
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            }
            catch (SQLException e) {
                throw new DbException("Erro ao conectar no BD: " + e.getMessage());
            }
        }
    }
    private static Properties loadProperties() {
        try(FileInputStream fs = new FileInputStream("modulo21/db.properties")) {
            Properties prop = new Properties();
            prop.load(fs);
            return prop;
        }
        catch (IOException e) {
            throw new DbException("Erro ao conectar no BD: " + e.getMessage());
        }
    }
}
