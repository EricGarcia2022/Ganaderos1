
package com.mycompany.ganaderiaapp;
import java.sql.CallableStatement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.List;
import  java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;



public class UsuariosG {
       int codigo;
    String nombres;
    String apellidopaterno;
    String apellidomaterno;
    String correo;
    String contraseña;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidopaterno() {
        return apellidopaterno;
    }

    public void setApellidopaterno(String apellidopaterno) {
        this.apellidopaterno = apellidopaterno;
    }

    public String getApellidomaterno() {
        return apellidomaterno;
    }

    public void setApellidomaterno(String apellidomaterno) {
        this.apellidomaterno = apellidomaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        Conexion objetoConexion = new Conexion();
        Connection conexion = objetoConexion.estableceConexion();
        
        String consulta = "SELECT * FROM usuarios";

        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("IDUsuario"));
                usuario.setNombres(rs.getString("Nombres"));
                usuario.setApellidoPaterno(rs.getString("ApellidoPaterno"));
                usuario.setApellidoMaterno(rs.getString("ApellidoMaterno"));
                usuario.setCorreo(rs.getString("Correo"));
                usuario.setContraseña(rs.getString("Contraseña"));

                listaUsuarios.add(usuario);
            }

            rs.close();
            stmt.close();
            conexion.close();
            

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al obtener la lista de usuarios: " + e.toString());
        }
        return listaUsuarios;
    }
    
    public void SeleccionarUsuario(JTable paramTablaUsuario, JTextField paramid, JTextField paramNombres, JTextField Apellidopaterno, JTextField Apellidomaterno, JTextField Correo,JTextField Contraseña){
        
        try{
            int fila = paramTablaUsuario.getSelectedRow();
            
            if (fila >=0){
                paramid.setText(paramTablaUsuario.getValueAt(fila,0).toString());
                paramNombres.setText(paramTablaUsuario.getValueAt(fila,1).toString());
                Apellidopaterno.setText(paramTablaUsuario.getValueAt(fila,2).toString());
                Apellidomaterno.setText(paramTablaUsuario.getValueAt(fila,3).toString());
                Correo.setText(paramTablaUsuario.getValueAt(fila,4).toString());
                Contraseña.setText(paramTablaUsuario.getValueAt(fila,5).toString());
                
            }
            else{
                
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
                    
        }catch(Exception e){
            
                JOptionPane.showMessageDialog(null,"Error de seleccón: error"+ e.toString());
            
        }
    }
    public void insercionUsuario(JTextField paramNombres, JTextField Apellidopaterno, JTextField Apellidomaterno, JTextField Correo, JTextField Contraseña){
        //setNombres(paramNombres.getText());
        
        setNombres(paramNombres.getText());
        setApellidopaterno(Apellidopaterno.getText());
        setApellidomaterno(Apellidomaterno.getText());
        setCorreo(Correo.getText());     
        setContraseña(Contraseña.getText());
        
        Conexion objetoConexion = new Conexion();
        
        String consulta = "insert into usuarios (nombres,ApellidoPaterno,ApellidoMaterno,Correo,Contraseña) values (?,?,?,?,?);";
        
        
        try{
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1,getNombres());
            cs.setString(2,getApellidopaterno());
            cs.setString(3,getApellidomaterno());
            cs.setString(4,getCorreo());
            cs.setString(5,getContraseña());
            
            cs.execute();
            
            JOptionPane.showInternalMessageDialog(null,"Se inserto correctamente al usuario");
            
        
        }catch(Exception e){
            JOptionPane.showInternalConfirmDialog(null,"No se inserto al usuario correctamente"+e.toString());
            
        }
    }
    
    public void modificarUsuarioG(JTextField paramid, JTextField paramNombres, JTextField Apellidopaterno, JTextField Apellidomaterno, JTextField Correo, JTextField Contraseña){
    
        setid(Integer.parseInt(paramid.getText()));
        setNombres(paramNombres.getText());
        setApellidopaterno(Apellidopaterno.getText());
        setApellidomaterno(Apellidomaterno.getText());
        setCorreo(Correo.getText());     
        setContraseña(Contraseña.getText());
        
        
        Conexion objetoConexion = new Conexion();
        
        String Consulta = "update usuarios \n" + "set \n" + "usuarios.Nombres = ?,\n" + "usuarios.ApellidoPaterno = ?,\n"+"usuarios.ApellidoMaterno = ?,\n" +"usuarios.Correo = ?,\n" +"usuarios.Contraseña = ?\n" +"where\n" +"usuarios.IDUsuario = ?;";
        try{
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(Consulta);
            
            cs.setString(1,getNombres());
            cs.setString(2,getApellidopaterno());
            cs.setString(3,getApellidomaterno());
            cs.setString(4,getCorreo());
            cs.setString(5,getContraseña());
            cs.setInt(6, getid());
                        
            cs.execute();
            
            JOptionPane.showInternalMessageDialog(null,"Modificación exitosa");
            
        
        }catch(SQLException e){
            JOptionPane.showInternalConfirmDialog(null,"No se modifico el parametro: error"+e.toString());
            
        }
    }
    
    private void setid(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     public void eliminarUsuarioG(JTextField paramid, JTextField paramNombres, JTextField Apellidopaterno, JTextField Apellidomaterno, JTextField Correo, JTextField Contraseña){
    
        setid(Integer.parseInt(paramid.getText()));
               
        Conexion objetoConexion = new Conexion();
        
        String Consulta = "delete from usuarios\n" +"where\n" +"usuarios.IDUsuario = ?;";
        try{
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(Consulta);
            cs.setInt(1, getid());
            cs.execute();
            
            JOptionPane.showInternalMessageDialog(null,"Se elimino correctamente");
            
        
        }catch(SQLException e){
            JOptionPane.showInternalConfirmDialog(null,"No se pudo eliminar: error"+e.toString());
            
        }
    }

    private int getid() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

         
}
