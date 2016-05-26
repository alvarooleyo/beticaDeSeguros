/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author android-0174654321
 */
public class Modelo extends Database{
    
  /** Constructor de clase */
    public Modelo(){      
    }
    
  
    
    /** verifica el usuario y clave introducido:
     * @param usuario String
     * @param clave String
     * @return int */
    public int verificarClave(String usuario, String clave){
        int res=0;
        //se arma la consulta
        String q = "select rango as rango FROM comercial WHERE  usuario='" + usuario + "' and clave='"+clave+"'" ;
        //se ejecuta la consulta
        System.out.println(usuario+clave);
         try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            ResultSet resultado=pstm.executeQuery();
            resultado.next();
            //Condición de entrada
            if(resultado.getString("rango").equals("administrador")){
                res=1;
            }else if(resultado.getString("rango").equals("comercial")){
                res=2;                
            }
            pstm.close();
            resultado.close();
                
            System.out.println("consulta realizada");
         }catch(SQLException e){
            System.err.println( e.getMessage() );
        }
        return res;       
        
    }
    
   
    
}
