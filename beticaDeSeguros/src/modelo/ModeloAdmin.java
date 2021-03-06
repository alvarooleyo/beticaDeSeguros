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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author android-0174654321
 */
public class ModeloAdmin extends Database{

    
    public ModeloAdmin(){
    
}
     /** Obtiene registros de la tabla Comerciales y los devuelve en un DefaultTableMode
     * @return DefaultTableModel*/
    public DefaultTableModel getTablaComerciales() {
        
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"ID","Nombre","D.N.I.","Puesto","Usuario","Clave","Zona"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM comercial where rango='comercial'");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][7];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM comercial where rango='comercial'");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "id" );
                data[i][1] = res.getString( "nombre" );
                data[i][2] = res.getString( "dni" );
                data[i][3] = res.getString( "rango" );
                data[i][4] = res.getString( "usuario" );
                data[i][5] = res.getString( "clave" );
                data[i][6] = res.getString( "zona" );
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
     
    /** Cargar datos en el comboBox de comerciales
     * @return DefaultComboBoxModel**/
     public DefaultComboBoxModel rellenarComboBajasC(){
        DefaultComboBoxModel vector=new DefaultComboBoxModel();
         int total=0;
         /**Obtenemos la cantidad de elementos que contendra el ComboBox de comerciales**/
         try{
             //se arma la consulta
             PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM comercial where rango='comercial'");
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
         String q = "select nombre FROM comercial where rango='comercial'" ;       
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
     
     
    /** Cargar datos en el comboBox de zonas
    * @return DefaultComboBoxModel**/
    public DefaultComboBoxModel rellenarComboBajasZ(){
        DefaultComboBoxModel vector=new DefaultComboBoxModel();
         int total=0;
         /**Obtenemos la cantidad de elementos que contendra el ComboBox de comerciales**/
         try{
             //se arma la consulta
             PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM zona");
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
         String q = "select nombre FROM zona" ;       
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
     
     /** Obtiene registros de la tabla Cliente y los devuelve en un DefaultTableMode
     * @return DefaultTableModel*/
    public DefaultTableModel rellenarTablaClientes() {
        
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"ID","Nombre","D.N.I.","Telefono","Establecimiento","Zona"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM cliente");
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
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM cliente");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "id" );
                data[i][1] = res.getString( "nombre" );
                data[i][2] = res.getString( "dni" );
                data[i][3] = res.getString( "telefono" );
                data[i][4] = res.getString( "establecimiento" );
                data[i][5] = res.getString( "idZona" );               
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
    /** Obtiene registros de la tabla zona y los devuelve en un DefaultTableMode
     * @return DefaultTableModel*/
     public DefaultTableModel rellenarTablaZona() {
        
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"ID","Nombre","Habitantes","Nº de establecimientos"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM zona");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][4];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM zona");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "id" );
                data[i][1] = res.getString( "nombre" );
                data[i][2] = res.getString( "poblacion" );
                data[i][3] = res.getString( "establecimientos" );
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
     
     /** Obtiene registros de la tabla productos y los devuelve en un DefaultTableMode
     * @return DefaultTableModel*/
       public DefaultTableModel rellenarTablaProductos() {
        
      DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"ID","Nombre","Descripción","Precio"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM productos");
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.err.println( e.getMessage() );
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][4];
      try{
          //realizamos la consulta sql y llenamos los datos en la matriz "Object[][] data"
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM productos");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "id" );
                data[i][1] = res.getString( "nombre" );
                data[i][2] = res.getString( "descripcion" );
                data[i][3] = res.getString( "precio" );
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
    
    /**Método para dar de baja a los comerciales
     * 
     * @param dni 
     */    
    public void BajaComerciales(String dni){
        String q="delete from comercial where dni='"+dni+"'";
         try{
             PreparedStatement pstm = this.getConexion().prepareStatement(q);
             pstm.execute();
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
                 System.err.println( e.getMessage() );
                 }
    }
    
    /**Método para modificar los datos de los comerciales
     * 
     * @param usuario
     * @param clave
     * @param dni 
     */
    
    public void MoificarDatosComerciales(String usuario,String clave,String dni){
        String q="update comercial set usuario='"+usuario+"',clave='"+clave+"' where dni='"+dni+"'";
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
    
    /** Obtiene registros de la tabla Comerciales selecionando solo los que son administradores y los devuelve en un DefaultTableMode
     * @return DefaultTableModel*/
      public DefaultTableModel rellenarTablaAdministradores() {
        
        DefaultTableModel tablemodel = new DefaultTableModel();
      int registros = 0;
      String[] columNames = {"ID","Nombre","D.N.I.","Puesto","Clave","usuario"};
      //obtenemos la cantidad de registros existentes en la tabla y se almacena en la variable "registros"
      //para formar la matriz de datos
      try{
         PreparedStatement pstm = this.getConexion().prepareStatement( "SELECT count(*) as total FROM comercial");
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
         PreparedStatement pstm = this.getConexion().prepareStatement("SELECT * FROM comercial where rango='administrador'");
         ResultSet res = pstm.executeQuery();
         int i=0;
         while(res.next()){
                data[i][0] = res.getString( "id" );
                data[i][1] = res.getString( "nombre" );
                data[i][2] = res.getString( "dni" );
                data[i][3] = res.getString( "rango" );
                data[i][4] = res.getString( "clave" );
                data[i][5] = res.getString( "usuario" );
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
      
     /**Método para añadir comerciales de tipo administador
      * 
      * @param nom
      * @param dni
      * @param admin
      * @param clave
      * @param usr 
      */ 
      public void AñadirAdministrador(String nom,String dni,String admin,String clave,String usr){
           String q="insert into comercial values (null,'"+nom+"','"+dni+"','"+admin+"',null,'"+clave+"','"+usr+"')";
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
      
      /**Metodo para asignar zonas a los comerciales
       * @param nombre
       * @param zona
       */
      public void reasignarZonas(String nombre,String zona){
          int id=0;
          String z="select id as id from zona where nombre='"+zona+"'";
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
          String q="update comercial set zona ='"+id+"' where nombre='"+nombre+"'";
           System.out.println(q);
           try{
             PreparedStatement pstm = this.getConexion().prepareStatement(q);             
             pstm.execute();                        
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
                 System.err.println( e.getMessage() );
                 }
          
      }
      
      /** Metodo para dar de alta a un comercial
       * @param nombre
       * @param dni
       * @param usuario
       * @param clave
       * @param zona
       */
      public void altaComercial(String nombre,String dni,String usuario,String clave,String zona){
          String z="insert into comercial values (null,'"+nombre+"','"+dni+"','comercial','"+zona+"','"+clave+"','"+usuario+"')";
                     System.out.println(z);

          try{
             PreparedStatement pstm = this.getConexion().prepareStatement(z);             
             pstm.execute();                           
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
                 JOptionPane.showMessageDialog(null,"Error: Los datos son incorrectos.\nReviselos y vuelva a intentarlo");
                 System.err.println( e.getMessage() );
                 }
      }
      
       /** Metodo para dar de alta a un comercial
       * @param nombre
       * @param habitantes
       * @param establecimientos       
       */
      public void altaZona(String nombre,String habitantes,String establecimientos){
          String z="insert into zona values (null,'"+nombre+"','"+habitantes+"','"+establecimientos+"')";
                     System.out.println(z);

          try{
             PreparedStatement pstm = this.getConexion().prepareStatement(z);             
             pstm.execute();                           
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
                 JOptionPane.showMessageDialog(null,"Error: Los datos son incorrectos.\nReviselos y vuelva a intentarlo");
                 System.err.println( e.getMessage() );
                 }
      }
      /**Método que nos devuelve el número de comerciales por zona
       * 
       * @param nombreZona
       * @return 
       */
      public String comercialesZona(String nombreZona){
           String z="select count(*) as numero from comercial where rango='comercial' and zona=(select id from zona where nombre='"+nombreZona+"')";
                     System.out.println(z);
                     String numero=null;

          try{
             PreparedStatement pstm = this.getConexion().prepareStatement(z);             
             ResultSet res=pstm.executeQuery();
             res.next();
             numero=res.getString("numero");
             res.close();
             pstm.close();
             
             }catch(SQLException e){
                 System.err.println( e.getMessage() );
                 }
          return numero;
      }
      
      /**Método para eliminar zonas
       * 
       * @param nombre 
       */
      public void eliminarZona(String nombre){
        String q="delete from zona where nombre='"+nombre+"'";
         try{
             PreparedStatement pstm = this.getConexion().prepareStatement(q);
             pstm.execute();
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
                 System.err.println( e.getMessage() );
                 JOptionPane.showMessageDialog(null,"No se puede realizar la operación:\nZona actualmente activa");
                 }
    }
      
      /**Método para editar los datos de una zona
       * 
       * @param nombre
       * @param habitantes
       * @param establecimientos 
       */
        public void editarZonas(String nombre,String habitantes,String establecimientos){
          
          String q="update zona set poblacion="+habitantes+", establecimientos='"+establecimientos+"' where nombre='"+nombre+"'";
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
        
         /**Método para añadir clientes
          * 
          * @param nom
          * @param dni
          * @param telefono
          * @param establecimiento
          * @param idzona 
          */      
      public void AñadirClientes(String nom,String dni,String telefono,String establecimiento,String idzona){
           String q="insert into cliente values (null,'"+nom+"','"+dni+"','"+telefono+"','"+establecimiento+"','"+idzona+"',0)";
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
      /**Método para eliminar clientes
       * 
       * @param nombre 
       */
      public void eliminarCliente(String dni){
        String q="delete from cliente where dni='"+dni+"'";
         try{
             PreparedStatement pstm = this.getConexion().prepareStatement(q);
             pstm.execute();
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
                 System.err.println( e.getMessage() );
                 JOptionPane.showMessageDialog(null,"No se puede realizar la operación:\nCliente activo");
                 }
    }
        
        
        
        
        
        
        /**Método para eliminar un comercial de tipo administrador
         * 
         * @param dni 
         */
        public void eliminarAdmin(String dni){
        String q="delete from comercial where dni='"+dni+"'";
        try{
             PreparedStatement pstm = this.getConexion().prepareStatement(q);
             pstm.execute();
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
                 System.err.println( e.getMessage() );
                 }
    }
        
        /**Método para editar la información de los productos
         * 
         * @param id
         * @param nombre
         * @param descripcion
         * @param precio 
         */
        public void editarproductos(String id,String nombre,String descripcion,String precio){
          
          String q="update productos set descripcion='"+descripcion+"', precio='"+precio+"', nombre='"+nombre+"' where id='"+id+"'";
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
        
        /**Método para añadir productos
         * 
         * @param nombre
         * @param descripcion
         * @param precio 
         */
         public void AñadirProducto(String nombre,String descripcion,String precio){
           String q="insert into productos values (null,'"+nombre+"','"+descripcion+"','"+precio+"')";
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
         
        public void eliminarProducto(String id){
        String q="delete from productos where id='"+id+"'";
        try{
             PreparedStatement pstm = this.getConexion().prepareStatement(q);
             pstm.execute();
             pstm.close();
             JOptionPane.showMessageDialog(null,"Operación Realizada");
             }catch(SQLException e){
                 JOptionPane.showMessageDialog(null,"El producto no puede ser eliminado\nya que actualmente hay clientes que lo tiene contratado.");
                 System.err.println( e.getMessage() );
                 }
    }
      
      
}
   
    

