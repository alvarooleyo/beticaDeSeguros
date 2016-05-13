/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import vista.Interfaz;

/**
 *
 * @author alvaro
 */
public class ControladorComercial extends Controlador implements ActionListener,MouseListener{

    public ControladorComercial(Interfaz vista) {
        super(vista);
        
    }
 /** instancia a nuestra interfaz de usuario*/
   
  
    
    public void iniciarComercial(){
         //cargamos el panel de administrador
                this.panelComercial();
                this.vista.tablaCaptaciones.setModel(this.modelo.getTablaCliente());
                this.vista.tablaRenovaciones.setModel(this.modelo.getTablaCliente());
                
                this.vista.btnCaptEliminar.setActionCommand( "btnCaptEliminar" );
                this.vista.btnCaptEliminar.addActionListener(this);
    }

    public enum MouseMVC {
         tablaCaptaciones,
         tablaRenovaciones,
         btnCaptEliminar;
         
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
                  
        int fila;
        switch (ControladorComercial.MouseMVC.valueOf(e.getComponent().getName())){           
        
            case tablaCaptaciones  :
             fila = this.vista.tablaAClientes.rowAtPoint(e.getPoint());
            if (fila > -1){                
                this.vista.txtACNombre.setText( String.valueOf( this.vista.tablaAClientes.getValueAt(fila, 1) ));
                this.vista.txtACDni.setText( String.valueOf( this.vista.tablaAClientes.getValueAt(fila, 2) ));
                this.vista.txtACTelefono.setText( String.valueOf( this.vista.tablaAClientes.getValueAt(fila, 3) ));
                this.vista.txtACEst.setText( String.valueOf( this.vista.tablaAClientes.getValueAt(fila, 4) ));
                this.vista.txtACZona.setText( String.valueOf( this.vista.tablaAClientes.getValueAt(fila, 5) ));
               
             }
      
            case tablaRenovaciones:
                
              fila = this.vista.tablaRenovaciones.rowAtPoint(e.getPoint());
             if (fila > -1){                
                this.vista.txtAPNombre.setText( String.valueOf( this.vista.tablaAPro.getValueAt(fila, 1) ));
                this.vista.txtAPDes.setText( String.valueOf( this.vista.tablaAPro.getValueAt(fila, 2) ));
                this.vista.txtAPPrecio.setText( String.valueOf( this.vista.tablaAPro.getValueAt(fila, 3) ));
                
               
             }
            
            case btnCaptEliminar:
                if(modelo.eliminarCliente(Integer.parseInt(vista.txtClienteId.getText()))){
                    this.vista.tablaCaptaciones.setModel( this.modelo.getTablaCliente());
                    JOptionPane.showMessageDialog(vista,"Exito: Registro eliminado.");
                    this.vista.txtClienteNombre.setText("");
                    this.vista.txtClienteId.setText("") ;
                    this.vista.txtClienteDni.setText("");
                    this.vista.txtClienteTelefono.setText("") ;

                }
                
             
             }
        
    }
    
    
    
    
    
    private void panelComercial() {
        super.vista.panelComercial.setVisible(true);
        super.vista.panelAdmin.setVisible(false);
        super.vista.login.setVisible(false);
        System.out.println("Comercial cargado");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
