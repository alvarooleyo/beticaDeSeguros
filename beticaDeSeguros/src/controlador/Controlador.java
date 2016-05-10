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
        btnEntrar,
        menuCerrar,
        menuSalir,
        btnACCBaja,
        btnACCModificar,
        btnAADA
    }
    
    
    
    
    
     public void iniciar(){
         
        
        //iniciamos el panel principal        
        this.vista.setVisible(true);
        this.vista.login.setVisible(true);
        this.vista.panelAdmin.setVisible(false);
        this.vista.panelComercial.setVisible(false);
        //declara una acción y añade un escucha al evento producido por el componente:
        //boton de entrar
        this.vista.btnEntrar.setActionCommand( "btnEntrar" );
        this.vista.btnEntrar.addActionListener(this);
        //boton de cerrar sesión
        this.vista.menuCerrar.setActionCommand( "menuCerrar" );
        this.vista.menuCerrar.addActionListener(this);
        //boton de salir
        this.vista.menuSalir.setActionCommand( "menuSalir" );
        this.vista.menuSalir.addActionListener(this);   
           
        
      
    }
    
     
     /** Acciones que se ejecutar al pulsar cada uno de los botones de la aplicacion**/

    @Override
    public void actionPerformed(ActionEvent e) {
        
        switch ( AccionMVC.valueOf( e.getActionCommand() ) ){
            //Acción sobre el botón entrar en el panel del login
            case btnEntrar:
                System.out.println("Pulsando entrar");
                String Usuario=this.vista.txtUsuario.getText();
                String clave=this.vista.txtPassword.getText();
                if (modelo.verificarClave(Usuario, clave)==1){
                    System.out.println("abriendo administrador");
                    
                    this.vista.setVisible(true);
                    this.vista.login.setVisible(false);
                    this.vista.panelComercial.setVisible(false);
                    this.vista.panelAdmin.setVisible(true);  
                    
                    
                    this.vista.tablaACoCo.setModel( new DefaultTableModel() );
                    //Se añaden valores a las tablas
                    this.vista.tablaACoCo.setModel( this.modelo.getTablaComerciales() );
                    
                    this.vista.tablaACoCo.addMouseListener(this);
                    
                    
                                        //Se añaden valores a las tablas
                                        
                    this.vista.comboCoCo.setModel(this.modelo.rellenarComboBajasC());
                    
                    this.vista.comboCoZona.setModel(this.modelo.rellenarComboBajasZ());                  
                    
                    
                    this.vista.tablaAClientes.setModel(this.modelo.rellenarTablaClientes());
                    
                    this.vista.tablaAClientes.addMouseListener(this);
                   
                    
                    this.vista.tablaAZonas.setModel(this.modelo.rellenarTablaZona());
                    
                    this.vista.tablaAPro.addMouseListener(this);
                    this.vista.tablaAPro.setModel(this.modelo.rellenarTablaProductos());
                    
                    this.vista.btnACCBaja.setActionCommand("btnACCBaja");
                    this.vista.btnACCBaja.addActionListener(this);
                    
                     this.vista.btnACCModificar.setActionCommand("btnACCModificar");
                    this.vista.btnACCModificar.addActionListener(this);
                    
                    this.vista.tablaAAdmi.setModel(this.modelo.rellenarTablaAdministradores());
                    
                    this.vista.tablaAAdmi.addMouseListener(this);
                    
                    this.vista.btnAADA.setActionCommand("btnAADA");
                    this.vista.btnAADA.addActionListener(this);
                    
                }else if(modelo.verificarClave(Usuario, clave)==2){
                    this.vista.setVisible(true);
                    this.vista.login.setVisible(false);
                    this.vista.panelComercial.setVisible(true);
                    this.vista.panelAdmin.setVisible(false);  
                    
                }else{    
                    JOptionPane.showMessageDialog(vista,"Error: Los datos son incorrectos.");
                }
            break;
            
            //accion que se ejecutará al hacer click sobre el botón Cerrar sesión en el menú superior
            case menuCerrar:
                this.vista.setVisible(true);
                this.vista.login.setVisible(true);
                this.vista.panelComercial.setVisible(false);
                this.vista.panelAdmin.setVisible(false); 
                this.vista.txtUsuario.setText("");
                this.vista.txtPassword.setText("");
            break;
            
            //accion que se ejecutará al hacer click sobre el botón Cerrar sesión en el menú superior
            case menuSalir:
                this.vista.dispose();
                break;
            case btnACCBaja:
                
                this.modelo.BajaComerciales(this.vista.txtACCDni.getText());
                this.vista.tablaACoCo.setModel( this.modelo.getTablaComerciales());
                break;
            case btnACCModificar:
                this.modelo.MoificarDatosComerciales(this.vista.txtACCUsuario.getText(),this.vista.txtACCClave.getText(),this.vista.txtACCDni.getText());
                this.vista.tablaACoCo.setModel( this.modelo.getTablaComerciales());
                break;
            case btnAADA:
                this.modelo.AñadirAdministrador(this.vista.txtAANombre.getText()+" "+this.vista.txtAANombre.getText(),this.vista.txtAADni.getText(),"administrador",this.vista.txtAAClave.getText(),this.vista.txtAANU.getText());
                this.vista.tablaAAdmi.setModel( this.modelo.rellenarTablaAdministradores());
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
        
         if( e.getButton()== 1)//boton izquierdo
        {
             int fila = this.vista.tablaAPro.rowAtPoint(e.getPoint());
             if (fila > -1){                
                this.vista.txtACCNombre.setText( String.valueOf( this.vista.tablaACoCo.getValueAt(fila, 1) ));
                this.vista.txtACCDni.setText( String.valueOf( this.vista.tablaACoCo.getValueAt(fila, 2) ));
                this.vista.txtACCUsuario.setText( String.valueOf( this.vista.tablaACoCo.getValueAt(fila, 5) ));
                this.vista.txtACCClave.setText( String.valueOf( this.vista.tablaACoCo.getValueAt(fila, 4) ));
                
               
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


