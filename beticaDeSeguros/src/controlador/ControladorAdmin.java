/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.table.DefaultTableModel;
import vista.Interfaz;

/**
 *
 * @author Usuario
 */
public class ControladorAdmin extends Controlador implements ActionListener,MouseListener{

    public ControladorAdmin(Interfaz vista) {
        super(vista);
        
    }
 /** instancia a nuestra interfaz de usuario*/
   
  
    
    public void iniciarAdmin(){
         //cargamos el panel de administrador
                this.panelAdministrador();
                
    }

    @Override
    public void mouseClicked(MouseEvent e) {
                  
        int fila;
        switch (ControladorAdmin.MouseMVC.valueOf(e.getComponent().getName())){           
        
            case   tablaAClientes:
             fila = this.vista.tablaAClientes.rowAtPoint(e.getPoint());
            if (fila > -1){                
                this.vista.txtACNombre.setText( String.valueOf( this.vista.tablaAClientes.getValueAt(fila, 1) ));
                this.vista.txtACDni.setText( String.valueOf( this.vista.tablaAClientes.getValueAt(fila, 2) ));
                this.vista.txtACTelefono.setText( String.valueOf( this.vista.tablaAClientes.getValueAt(fila, 3) ));
                this.vista.txtACEst.setText( String.valueOf( this.vista.tablaAClientes.getValueAt(fila, 4) ));
                this.vista.txtACZona.setText( String.valueOf( this.vista.tablaAClientes.getValueAt(fila, 5) ));
               
             }
      
            case tablaAPro:
                
              fila = this.vista.tablaAPro.rowAtPoint(e.getPoint());
             if (fila > -1){                
                this.vista.txtAPNombre.setText( String.valueOf( this.vista.tablaAPro.getValueAt(fila, 1) ));
                this.vista.txtAPDes.setText( String.valueOf( this.vista.tablaAPro.getValueAt(fila, 2) ));
                this.vista.txtAPPrecio.setText( String.valueOf( this.vista.tablaAPro.getValueAt(fila, 3) ));
                
               
             }
            case tablaACoCo:
              fila = this.vista.tablaACoCo.rowAtPoint(e.getPoint());
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
    
    
     public enum AccionMVC {
        
        btnACCBaja,
        btnACCModificar,
        btnAADA
        
    }
     public enum MouseMVC {
         tablaACoCo,
         tablaAClientes,
         tablaAZonas,
         tablaAPro,
         tablaAAdmi;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
         switch ( ControladorAdmin.AccionMVC.valueOf( e.getActionCommand() ) ){
            
            case btnACCBaja:
                
                this.modelo.BajaComerciales(this.vista.txtACCDni.getText());
                this.vista.tablaACoCo.setModel( this.modelo.getTablaComerciales());
                break;
            case btnACCModificar:
                this.modelo.MoificarDatosComerciales(this.vista.txtACCUsuario.getText(),this.vista.txtACCClave.getText(),this.vista.txtACCDni.getText());
                this.vista.tablaACoCo.setModel( this.modelo.getTablaComerciales());
                break;
            case btnAADA:
                this.modelo.AñadirAdministrador(this.vista.txtAANombre.getText()+" "+this.vista.txtAAApellidos.getText(),this.vista.txtAADni.getText(),"administrador",this.vista.txtAAClave.getText(),this.vista.txtAANU.getText());
                this.vista.tablaAAdmi.setModel( this.modelo.rellenarTablaAdministradores());
                break;
            
            
                    
                
            
        
        }




    }
    public void panelAdministrador(){
        this.vista.setVisible(true);
                    this.vista.login.setVisible(false);
                    this.vista.panelComercial.setVisible(false);
                    this.vista.panelAdmin.setVisible(true);  
                    
                    //se añaden la tabla comerciales y se cargan los datos de la base de dtos
                    this.vista.tablaACoCo.setModel( new DefaultTableModel() );                   
                    this.vista.tablaACoCo.setModel( this.modelo.getTablaComerciales() );
                    //se añade un mouselistener a la tabla comerciales
                    this.vista.tablaACoCo.addMouseListener(this);
                    this.vista.tablaACoCo.setName("tablaACoCo");
              
                    //Se rellenan los combobox de comerciales y zonas                    
                    this.vista.comboCoCo.setModel(this.modelo.rellenarComboBajasC());                    
                    this.vista.comboCoZona.setModel(this.modelo.rellenarComboBajasZ());                  
                   
                    //se cargan los datos de la base de datos en la tabla clientes
                    this.vista.tablaAClientes.setModel(this.modelo.rellenarTablaClientes());
                    
                    this.vista.tablaAClientes.addMouseListener(this);
                    this.vista.tablaAClientes.setName("tablaAClientes");
                   
                    //se cargan los datos de la base de datos en la tabla zonas
                    this.vista.tablaAZonas.setModel(this.modelo.rellenarTablaZona());
                    
                    //se cargan los datos de la base de datos en la tabla productos
                    this.vista.tablaAPro.addMouseListener(this);
                    this.vista.tablaAPro.setModel(this.modelo.rellenarTablaProductos());
                    this.vista.tablaAPro.setName("tablaAPro");
                    
                    this.vista.btnACCBaja.setActionCommand("btnACCBaja");
                    this.vista.btnACCBaja.addActionListener(this);
                    
                     this.vista.btnACCModificar.setActionCommand("btnACCModificar");
                    this.vista.btnACCModificar.addActionListener(this);
                    
                    this.vista.tablaAAdmi.setModel(this.modelo.rellenarTablaAdministradores());
                    
                    this.vista.tablaAAdmi.addMouseListener(this);
                    this.vista.tablaAAdmi.setName("tablaAAdmi");
                    
                    this.vista.btnAADA.setActionCommand("btnAADA");
                    this.vista.btnAADA.addActionListener(this);
    }
    
    public void panelComercial(){
        this.vista.setVisible(true);
        this.vista.login.setVisible(false);
        this.vista.panelComercial.setVisible(true);
        this.vista.panelAdmin.setVisible(false);  
    }
}

 

