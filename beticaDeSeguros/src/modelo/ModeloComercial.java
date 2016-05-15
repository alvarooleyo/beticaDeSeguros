/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import vista.Interfaz;

/**
 *
 * @author android-0174654321
 */
public class ModeloComercial extends Database{
    
    public ModeloComercial(){
        
    }
    
       //Nuevo metodo para rellenar clientes en el panel de comercial
    Interfaz v = new Interfaz();  
    
      public DefaultTableModel getTablaCliente(){
          DefaultTableModel tablemodel = new DefaultTableModel();
          int registros = 0;
          String [] columNames = {"id", "nombre", "telefono"};
          try{
              String sql ="select count(*) as total from cliente";
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
              String sql2 = "select id, nombre, telefono from cliente";
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
      
    public boolean eliminarCliente(int id){
            boolean res = false;
            
            String q = "DELETE FROM cliente WHERE id='"+id+"'";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            res=true;
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
            return res;
        }
    
    public void verCliente(String id) throws SQLException{
        
        
        
        String verCliente = "select cliente.dni, cliente.establecimiento, zona.nombre, zona.id from cliente join zona where zona.id=cliente.idZona and cliente.id = "+id+"";
        try{
            PreparedStatement pstm = this.getConexion().prepareStatement(verCliente);             
        ResultSet res = pstm.executeQuery();
            System.out.println("Consulta realizada.");
        String a = (res.getString("cliente.98dni"));
            System.out.println("el dni del cliente es: "+a);
        v.txtClienteDni.setText(a);
        res.close();
        pstm.close();
        }catch(SQLException e){
            e.getMessage();
        }
        
    }
    
}