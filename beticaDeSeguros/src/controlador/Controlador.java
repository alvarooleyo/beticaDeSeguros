/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Modelo;
import vista.Interfaz;


/**
 *
 * @author android-0174654321
 */
public class Controlador implements ActionListener,MouseListener{
 /** instancia a nuestra interfaz de usuario*/
    Interfaz vista ;
    
    /** instancia a nuestro modelo */
    Modelo modelo = new Modelo();
    
    
    
    /** Constrcutor de clase
     * @param vista Instancia de clase interfaz
     */
    public Controlador( Interfaz vista)
    {
        this.vista = vista;
        
        System.out.println("Construyendo controlador");
    }

    
     public enum AccionMVC {
        btnEntrar
    }
    
    
    
    
    
     public void iniciar()
    {
        System.out.println("ejecutando iniciar");
        //iniciamos el panel principal        
        this.vista.setVisible(true);
        this.vista.login.setVisible(true);
        this.vista.administrador.setVisible(false);
        this.vista.comercial.setVisible(false);        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.btnEntrar.setActionCommand( "btnEntrar" );
        this.vista.btnEntrar.addActionListener(this);
        
        //añade e inicia el jtable con un DefaultTableModel vacio
       
        
      
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch ( AccionMVC.valueOf( e.getActionCommand() ) ){
            
            case btnEntrar:
                System.out.println("Pulsando entrar");
                String Usuario=this.vista.txtUsuario.getText();
                String clave=this.vista.txtPassword.getText();
                if (modelo.verificarClave(Usuario, clave)){
                    System.out.println("abriendo administrador");
                    
                    this.vista.setVisible(true);
                    this.vista.login.setVisible(false);
                    this.vista.administrador.setVisible(true);
                    this.vista.comercial.setVisible(false);  
                    
                    
                    this.vista.tablaACoCo.setModel( new DefaultTableModel() );
                    //Se añaden valores a las tablas
                    this.vista.tablaACoCo.setModel( this.modelo.getTablaComerciales() );
                    this.vista.tablaACoBajas.setModel( this.modelo.getTablaComerciales() );
                                        //Se añaden valores a las tablas
                                        
                    this.vista.comboCoCo.setModel(this.modelo.rellenarComboBajasC());
                    
                    this.vista.comboCoZona.setModel(this.modelo.rellenarComboBajasZ());
                    
                    this.vista.tablaACoBajas.setModel( this.modelo.getTablaComerciales() );
                    
                    this.vista.tablaAClientes.addMouseListener(this);
                    this.vista.tablaAClientes.setModel(this.modelo.rellenarTablaClientes());
                    
                    this.vista.tablaAZonas.setModel(this.modelo.rellenarTablaZona());
                    
                    this.vista.tablaAPro.addMouseListener(this);
                    this.vista.tablaAPro.setModel(this.modelo.rellenarTablaProductos());
                    
                }else{
                    JOptionPane.showMessageDialog(vista,"Error: Los datos son incorrectos.");
                }
            break;
            
        
        }
        
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if( e.getButton()== 1)//boton izquierdo
        {
             int fila = this.vista.tablaAClientes.rowAtPoint(e.getPoint());
             if (fila > -1){                
                this.vista.txtACNombre.setText( String.valueOf( this.vista.tablaAClientes.getValueAt(fila, 1) ));
                this.vista.txtACDni.setText( String.valueOf( this.vista.tablaAClientes.getValueAt(fila, 2) ));
                this.vista.txtACTelefono.setText( String.valueOf( this.vista.tablaAClientes.getValueAt(fila, 3) ));
                this.vista.txtACEst.setText( String.valueOf( this.vista.tablaAClientes.getValueAt(fila, 4) ));
                this.vista.txtACZona.setText( String.valueOf( this.vista.tablaAClientes.getValueAt(fila, 5) ));
               
             }
        }
        
        if( e.getButton()== 1)//boton izquierdo
        {
             int fila = this.vista.tablaAPro.rowAtPoint(e.getPoint());
             if (fila > -1){                
                this.vista.txtAPNombre.setText( String.valueOf( this.vista.tablaAPro.getValueAt(fila, 1) ));
                this.vista.txtAPDes.setText( String.valueOf( this.vista.tablaAPro.getValueAt(fila, 2) ));
                this.vista.txtAPPrecio.setText( String.valueOf( this.vista.tablaAPro.getValueAt(fila, 3) ));
                
               
             }
        }
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
     
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
   
    }
    
}


