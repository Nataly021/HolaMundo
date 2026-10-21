/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.holamundo;

/**
 *
 * @author susyo
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class HolaMundo {

    public static void main(String[] args) throws SQLException {
       System.out.println("Ejecutando operaciones en tabla Alumnos");
       //obtener la conexion
       Connection cn = ConexionDB.getConnection();
       if (cn == null){
           System.out.println("no se puede establecer la conexion " );
           return;
       }
       //declarar los objetos de sql para nuestra base
       PreparedStatement psInsertar = null;
       PreparedStatement psSeleccionar = null;
       PreparedStatement psActualizar = null;
       PreparedStatement psEliminar = null;
       ResultSet rs = null;
       
       try{
           System.out.println("\n 1. intentandio insertar nuevo alumno...");
           String psInsert = "INSERT INTO Alumnos(nombre, correo) VALUES (?,?)";
           psInsertar = cn.prepareStatement(psInsert);
           
           //asignar valores a los paramentros 
           psInsertar.setString(1, "Nataly");
           psInsertar.setString(2, "nata@uth.hn");
           
           //ejecutar la incersion 
           int filasAfectadas = psInsertar.executeUpdate();
           if (filasAfectadas > 0){
               System.out.println("alumno insertado con exito");
           } 
           
           //parte b seleccionar y moestrar datos en pantalla
           System.out.println("\n consulta de lista de alumnos en la base de datos");
           
           String sqlSelect = "SELECT id, nombre, correo FROM Alumnos";
            psSeleccionar = cn.prepareStatement(sqlSelect);
            rs = psSeleccionar.executeQuery();
                                  
          System.out.println("\n ===================================================");
          System.out.println("ID    | Nombre          |Correo ");
          System.out.println("\n ===================================================");
           while(rs.next()){
           int id = rs.getInt("id");
          String nombre = rs.getString("nombre");
          String correo = rs.getString("correo");
           // Imprimimos la tabla virtual
           System.out.printf("%-3d | %-20s |%s\n  ", id, nombre, correo);       
           }
         System.out.println("\n ===================================================");
         //actualizar
         System.out.println("\n Intentando actualizar alumno");
         String sqlActualizar =
                 "UPDATE Alumnos SET nombre = ?, correo = ? WHERE id = ?";
         
         psActualizar = cn.prepareStatement(sqlActualizar);
         psActualizar.setString(1, "Nataly Ordonez");
         psActualizar.setString(2, "nataly.ord@uth.hn");
         psActualizar.setInt(3, 3);
         
         int filasActualizadas = psActualizar.executeUpdate();
         
         if (filasActualizadas > 0){
          System.out.println("alumnos actualizado con exito");
          
         }else{
             System.out.println("no se encontro el alumno");
         }
         //eliminar
         System.out.println("\n intentando eliminar alumno");
         String sqlEliminar = "DELETE FROM Alumnos WHERE id = ?";
         psEliminar=cn.prepareStatement(sqlEliminar);
         psEliminar.setInt(1, 20);
         int filasEliminadas = psEliminar.executeUpdate();
         if (filasEliminadas>0){
             System.out.println("alumno eliminado con exito");
         }else{
             System.out.println("no se encontro el alumno");
         }

         
       } catch (SQLException e){
           System.err.println("ocurrio un error con la base de datos"+ e.getMessage());
       }finally{
           //cerrar la conexion de manera obligatoria
           if (rs != null){
               rs.close();
           }
           if (psInsertar != null)psInsertar.close();
           if (psSeleccionar !=null)psSeleccionar.close();
           if (psActualizar !=null)psActualizar.close();
           if (psEliminar !=null)psEliminar.close();
           ConexionDB.cerrarConexion();
       }
    }
}
