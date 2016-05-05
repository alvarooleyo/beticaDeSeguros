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

/**
 *
 * @author android-0174654321
 */
public class Modelo extends Database{
    
    /** Constructor de clase */
    public Modelo(){      
    }
    
     /** Obtiene registros de la tabla PRODUCTO y los devuelve en un DefaultTableModel*/
    public DefaultTableModel getTablaComerciales() {
        
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"ID","Nombre","D.N.I.","Puesto","Clave","Zona"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM Comercial");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][6];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM Comercial");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "id" );
                data[i][1] = res.getString( "nombre" );
                data[i][2] = res.getString( "dni" );
                data[i][3] = res.getString( "rango" );
                data[i][4] = res.getString( "clave" );
                data[i][5] = res.getString( "zona" );
            i++;
         }
         res.close();
         //se añade la matriz de datos en el DefaultTableModel
         tablemodel.setDataVector(data, columNames );
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return tablemodel;
    }
    
    
    
}
