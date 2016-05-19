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
    
    public ModeloComercial(){
        
    }
    
       //Nuevo metodo para rellenar clientes en el panel de comercial
   
    
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
    
    public void ObtenerLista () throws ClassNotFoundException, SQLException{
        Connection cn;

        Interfaz i = new Interfaz();
        String sql = "SELECT nombre from productos";
        Database con=new Database();
        cn=con.getConexion();
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            i.cmbClientesTipoProd.removeAllItems();
            while (rs.next()) {
                i.cmbClientesTipoProd.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al llamar la BD");
            System.out.println("Coneccion incorrecta: "+ ex);
        }
        
    }
    
    public void MostrarDatos(final JComboBox combobox){
        combobox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
           @Override 
            public void keyReleased(KeyEvent evt) {
                String cadenaEscrita = combobox.getEditor().getItem().toString();

                if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || evt.getKeyCode() >= 96 && evt.getKeyCode() <= 105 || evt.getKeyCode() == 8) {
                    try {  
                        combobox.setModel(ObtenerAutocompletar(cadenaEscrita));
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (combobox.getItemCount() > 0) { 
                        combobox.showPopup();
                        
                        if(evt.getKeyCode()!=8){
                            ((JTextComponent)combobox.getEditor().getEditorComponent()).select(cadenaEscrita.length(), combobox.getEditor().getItem().toString().length());
                            
                        }else{
                            combobox.getEditor().setItem(cadenaEscrita);
                        }

                    } else {
                        combobox.addItem(cadenaEscrita);
                    }
                }
            } 
        });
    }
}
