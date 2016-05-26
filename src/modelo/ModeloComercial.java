/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import vista.Interfaz;

/**
 *
 * @author android-0174654321
 */
public class ModeloComercial extends Database{
    
    /**
     *
     */
    public ModeloComercial(){
        
    }
    
       //Nuevo metodo para rellenar clientes en el panel de comercial
   
    /**
     *
     * @return
     */
    public DefaultTableModel getTablaCliente(){
          DefaultTableModel tablemodel = new DefaultTableModel();
          int registros = 0;
          String [] columNames = {"id", "nombre", "telefono"};
          try{
              String sql ="select count(*) as total from cliente where contratos = 0";
              PreparedStatement pstm = this.getConexion().prepareStatement(sql);
              ResultSet res = pstm.executeQuery();
              res.next();
              registros = res.getInt("total");
              res.close();
          } catch (SQLException e) {
              System.err.println( e.getMessage() );
          }
          // se crea una matriz con tanta filas y columnas como se necesiten
          Object[][] data = new String[registros][3];
            try {
              //se realiza la consulta sql y llenamos los datos en la matriz "Object[][]" data
              String sql2 = "select id, nombre, telefono from cliente where contratos = 0";
              PreparedStatement pstm = this.getConexion().prepareStatement(sql2);
              ResultSet res = pstm.executeQuery();
              int i = 0;
              while(res.next()){
                  data[i][0] = res.getString("id");
                  data[i][1] = res.getString("nombre");
                  data[i][2] = res.getString("telefono");
                i++;
              }
              res.close();
              //se añade la matriz de datos en el DefaultTableModel
              tablemodel.setDataVector(data, columNames);
                System.out.println("tabla cliente cargada");
          } catch (SQLException e) {
              System.err.println( e.getMessage() );
          }
                    return tablemodel;
        
        
      }
      
    /**
     *
     * @return
     */
    public DefaultTableModel getTablaClienteRenov(){
          DefaultTableModel tablemodel = new DefaultTableModel();
          int registros = 0;
          String [] columNames = {"id", "nombre", "telefono"};
          try{
              String sql ="select count(*) as total from cliente where contratos = 1";
              PreparedStatement pstm = this.getConexion().prepareStatement(sql);
              ResultSet res = pstm.executeQuery();
              res.next();
              registros = res.getInt("total");
              res.close();
          } catch (SQLException e) {
              System.err.println( e.getMessage() );
          }
          // se crea una matriz con tanta filas y columnas como se necesiten
          Object[][] data = new String[registros][3];
            try {
              //se realiza la consulta sql y llenamos los datos en la matriz "Object[][]" data
              String sql2 = "select id, nombre, telefono from cliente where contratos = 1";
              PreparedStatement pstm = this.getConexion().prepareStatement(sql2);
              ResultSet res = pstm.executeQuery();
              int i = 0;
              while(res.next()){
                  data[i][0] = res.getString("id");
                  data[i][1] = res.getString("nombre");
                  data[i][2] = res.getString("telefono");
                i++;
              }
              res.close();
              //se añade la matriz de datos en el DefaultTableModel
              tablemodel.setDataVector(data, columNames);
                System.out.println("tabla cliente cargada");
          } catch (SQLException e) {
              System.err.println( e.getMessage() );
          }
                    return tablemodel;
        
        
      }
      
    /**
     *
     * @param id
     * @return
     */
    public boolean eliminarCliente(int id){
            boolean res = false;
            System.out.println("Id del cliente a borrar: "+id);
            String q = "DELETE FROM cliente WHERE id='"+id+"'";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            System.out.println("Borrando");
            pstm.close();
            res=true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
            return res;
        }
    
    /**
     *
     * @param id
     * @return
     */
    public String[] verCliente(String id){
        
        
        String a[] = new String[4];
        String verCliente = "select cliente.dni, cliente.establecimiento, zona.nombre, zona.id from cliente join zona where zona.id=cliente.idZona and cliente.id = '"+id+"'";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(verCliente);
            ResultSet res = pstm.executeQuery();
            while(res.next()){
               System.out.println("Consulta realizada.");
               a[0] = res.getString("dni");
               a[1] = res.getString("nombre");
               a[2] = res.getString("establecimiento");
               a[3] = res.getString("id");
        
            }
            
        res.close();
        pstm.close();
        }catch(SQLException e){
            e.getMessage();
        }
        
      return a;  
    }
    
    
    
     /** Cargar datos en el comboBox de zonas
    * @return DefaultComboBoxModel**/
    public DefaultComboBoxModel rellenarComboProductos(){
        DefaultComboBoxModel vector=new DefaultComboBoxModel();
         int total=0;
         /**Obtenemos la cantidad de elementos que contendra el ComboBox de comerciales**/
         try{
             //se arma la consulta
             PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM productos");
             //se ejecuta la consulta
             ResultSet res1 = pstm.executeQuery();
             res1.next();
             total= res1.getInt("total");
             res1.close();
      }catch(SQLException e){
          System.err.println( e.getMessage() );
      }         
         int i=0;
         Object[] data = new String[total];       
         String q = "select nombre FROM productos" ;       
         try {
             //se arma la consulta
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            //se ejecuta la consulta
            ResultSet resultado=pstm.executeQuery();
            while(resultado.next()){
                data[i]=resultado.getString("nombre");
                vector.addElement(data[i].toString());
                i++;
            }           
            pstm.close();
            resultado.close();            
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }         
        return vector;          
    }
    
     /** Obtiene registros de la tabla Comerciales y los devuelve en un DefaultTableMode
     * @param Usuario
     * @return DefaultTableModel*/
    public String[] datosComerciales(String Usuario) {
        
      
    //se crea una matriz con tantas filas y columnas que necesite
    String[] data = new String[7];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM comercial where usuario='"+Usuario+"'");
         ResultSet res = pstm.executeQuery();
         res.next();
                data[0] = res.getString( "id" );
                data[1] = res.getString( "nombre" );
                data[2] = res.getString( "dni" );
                data[3] = res.getString( "rango" );
                data[4] = res.getString( "usuario" );
                data[5] = res.getString( "clave" );
                data[6] = res.getString( "zona" );
            
         
         res.close();
        
         }catch(SQLException e){             
            System.err.println( e.getMessage() );
        }
        return data;
    }
    
    /**Método para crear un contrato
     * 
     * @param idcliente
     * @param idComercial
     * @param producto
     * @param fechainicio
     * @param fechafin 
     */
    public void contratar(String idcliente,String idComercial,String producto,String fechainicio,String fechafin){
        int id=0;
          String z="select id as id from productos where nombre='"+producto+"'";
                     System.out.println(z);

          try{
             PreparedStatement pstm = this.getConexion().prepareStatement(z);             
             ResultSet res = pstm.executeQuery();
             res.next();
             id=res.getInt("id");              
             pstm.close();
             }catch(SQLException e){
                 System.err.println( e.getMessage() );
                 }
        String q="insert into contrato values (null,'"+fechainicio+"','"+fechafin+"','"+idComercial+"','"+idcliente+"','"+id+"')";
           System.out.println(q);
         try{
             PreparedStatement pstm = this.getConexion().prepareStatement(q);
             pstm.execute();
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
                 JOptionPane.showMessageDialog(null,"Error: Los datos son incorrectos.\nReviselos y vuelva a intentarlo");
                 System.err.println( e.getMessage() );
                 }
        
    }
    //Agregar cliente a la base de datos

    /**
     *
     * @param nom
     * @param dni
     * @param telefono
     * @param establecimiento
     * @param idZona
     */
    public void agregarCliente(String nom,String dni,String telefono,String establecimiento,int idZona){
        String q="insert into cliente values (null,'"+nom+"','"+dni+"',"+telefono+",'"+establecimiento+"',"+idZona+",0)";
           System.out.println(q);
         try{
             PreparedStatement pstm = this.getConexion().prepareStatement(q);
             pstm.execute();
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
                 JOptionPane.showMessageDialog(null,"Error: Los datos son incorrectos.\nReviselos y vuelva a intentarlo");
                 System.err.println( e.getMessage() );
                 }
    }     
    //Al darle al boton agregar de la pestaña objetivos del dia se pone en la pestaña cliente el id de zona y el nombre de la zona

    /**
     *
     * @param usuario
     * @return
     */
    public String[] ponerZonaCliente(String usuario){
        String a[] = new String[2];
        String q = "select  zona.nombre, zona.id from zona join comercial where zona.id = comercial.zona and comercial.usuario = '"+usuario+"'";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            while(res.next()){
               System.out.println("Consulta realizada.");
               a[0] = res.getString("nombre");
               a[1] = res.getString("id");
               
        
            }
            
        res.close();
        pstm.close();
        }catch(SQLException e){
            e.getMessage();
        }
        
      return a;  
      
      
    }
    
    //Actualizar cliente en la base de datos

    /**
     *
     * @param id
     * @param nom
     * @param telefono
     * @param establecimiento
     * @param idZona
     */

    public void MoificarDatosCliente(int id, String nom,String telefono,String establecimiento,int idZona){
        String q="update cliente set nombre='"+nom+"',telefono="+telefono+",establecimiento='"+establecimiento+"',idZona="+idZona+" where id='"+id+"'";
         try{
             PreparedStatement pstm = this.getConexion().prepareStatement(q);
             pstm.execute();
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
                 JOptionPane.showMessageDialog(null,"Error: Los datos son incorrectos.\nReviselos y vuelva a intentarlo");
                 System.err.println( e.getMessage() );
                 }
        
    }
    
    
}
