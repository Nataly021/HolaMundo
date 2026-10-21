/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.holamundo;
/**
 *
 * @author susyo
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionDB {
   private static final String URL = "jdbc:sqlserver://Nataly:1433;"
            + "databaseName=AvanzadaII;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "Avanzada";

    private static Connection conexion = null;

    public static Connection getConnection() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("¡Conexion Exitosa a SQL!!");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR. No se encontro el Driver" + e.getMessage());
        } catch (SQLException e) {
            System.err.println("ERROR AL CONECTARSE CON SQL " + e.getMessage());
        }
        return conexion;
    }

    public static void cerrarConexion(){
        try{
            if(conexion != null && !conexion.isClosed()){
                conexion.close();
                System.out.println("->La conexion con SQL fue Cerrada");
            }
        }catch(SQLException e){
            System.err.println("Error al cerrar la conexion " + e.getMessage());
        }
    }
}