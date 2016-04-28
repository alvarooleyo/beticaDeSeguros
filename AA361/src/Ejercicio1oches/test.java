/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio1oches;

import java.sql.SQLException;

/**
 *
 * @author UsuarioPrueba
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
       conexionBD con = new conexionBD();
       funciones f = new funciones();
       
       con.conectarte();
      f.creaTablaCoches(con.conectarte(), "aa361");
      f.cargaTablacoches(con.conectarte(),"aa361","ejercicio1.txt");
    }
    
}
