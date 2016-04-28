/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio1oches;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.StringTokenizer;

/**
 *
 * @author Usuario
 */
public class conexionBD {

    private static void printSQLException(SQLException e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param args the command line arguments
     */
    private String bd = "aa361";
    private String host="localhost:3306";
    private String user = "root";
    private String pass = "nikiekany";
    private String server = "jdbc:mysql://"+host+"/"+bd;
    
    public Connection conectarte (){
    Connection connection = null;
     try {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(server, user, pass);
        System.out.println("Conectado");
     } catch (ClassNotFoundException ex){
         System.out.println("Otro error");
     } catch (SQLException ex) {
            ex.getSQLState();
     
     }
     return connection;
    }
}