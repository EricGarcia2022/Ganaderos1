
package com.mycompany.ganaderiaapp;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class Conexion {
    
    Connection conectar = null;
    
    String usuario = "root";
    String contrasenia = "";
    String bd = "ganadeandoapp";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    
    public Connection estableceConexion(){
    
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contrasenia);
            //JOptionPane.showMessageDialog(null,"Se ha conectado correctamente a la base de datos");
        
        }catch (Exception e){
            //JOptionPane.showMessageDialog(null,"Error al conectarse a la base de datos: error"+e.toString());
        
        }
        return conectar;
    }
    
}
