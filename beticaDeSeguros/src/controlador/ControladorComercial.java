/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import reportes.Reportes;
import vista.Interfaz;

/**
 *
 * @author alvaro
 */
public class ControladorComercial extends Controlador implements ActionListener,MouseListener{

    public ControladorComercial(Interfaz vista) {
        /** instancia a nuestra interfaz de usuario*/
        super(vista);
        
    }
 
   
  
    
    public void iniciarComercial(){
         //cargamos el panel de comercial
                this.panelComercial();
                this.vista.tablaCaptaciones.setModel(this.modeloComercial.getTablaCliente());
                this.vista.tablaRenovaciones.setModel(this.modeloComercial.getTablaClienteRenov());
                this.vista.tablaRenovaciones.setName("tablaRenovaciones");
                this.vista.tablaRenovaciones.addMouseListener(this);
                
                this.vista.tablaCaptaciones.addMouseListener(this);
                this.vista.tablaCaptaciones.setName("tablaCaptaciones");
                
                this.vista.btnCaptEliminar.setActionCommand( "btnCaptEliminar" );
                this.vista.btnCaptEliminar.addActionListener(this);
                this.vista.btnCaptAgregar.addActionListener(this);
                this.vista.btnCaptAgregar.setActionCommand( "btnCaptAgregar" );
                this.vista.btnCaptVer.setActionCommand("btnCaptVer");
                this.vista.btnCaptVer.addActionListener(this);
                this.vista.cmbClientesTipoProd.setModel(this.modeloComercial.rellenarComboProductos());
                this.vista.comboClientesPro2.setModel(this.modeloComercial.rellenarComboProductos());
                //boton hacer contrato
                this.vista.btnHacerContrato.addActionListener(this);
                this.vista.btnHacerContrato.setActionCommand("btnHacerContrato");
                
                
                                
                               

    }

    public enum MouseMVC {
         tablaCaptaciones,
         tablaRenovaciones     
 
    }
    
    public enum ActionMVC {
        btnCaptAgregar,
        btnCaptEliminar,
        btnCaptVer,
        btnHacerContrato
    }
    
    String pepe;
    
    @Override
    public void mouseClicked(MouseEvent e) {
                  
        int fila;
        switch (ControladorComercial.MouseMVC.valueOf(e.getComponent().getName())){           
        
            case tablaCaptaciones  :
             fila = this.vista.tablaCaptaciones.rowAtPoint(e.getPoint());
            if (fila > -1){                
                this.vista.txtClienteId.setText( String.valueOf( this.vista.tablaCaptaciones.getValueAt(fila, 0) ));
                this.vista.txtClienteNombre.setText( String.valueOf( this.vista.tablaCaptaciones.getValueAt(fila, 1) ));
                this.vista.txtClienteTelefono.setText( String.valueOf( this.vista.tablaCaptaciones.getValueAt(fila, 2) ));
                pepe = String.valueOf( this.vista.tablaCaptaciones.getValueAt(fila, 0) );
               
             }
            break;
      
            case tablaRenovaciones:
                
              fila = this.vista.tablaRenovaciones.rowAtPoint(e.getPoint());
             if (fila > -1){                
                this.vista.txtClienteId.setText( String.valueOf( this.vista.tablaRenovaciones.getValueAt(fila, 0) ));
                this.vista.txtClienteNombre.setText( String.valueOf( this.vista.tablaRenovaciones.getValueAt(fila, 1) ));
                this.vista.txtClienteTelefono.setText( String.valueOf( this.vista.tablaRenovaciones.getValueAt(fila, 2) ));
                
               
             }
            break;
            
             
                
             
             }
        
    }
    
    
     @Override
    public void actionPerformed(ActionEvent e) {
         switch ( ControladorComercial.ActionMVC.valueOf( e.getActionCommand() ) ){
             case btnCaptAgregar:
                
                  this.vista.btnAgregarCliente.setEnabled(true);
                  this.vista.btnEditarCliente.setEnabled(false);
                  this.vista.comercial.setSelectedIndex(1);
                  
                  this.vista.txtClienteNombre.setText(" ");
                  this.vista.txtClienteId.setText(" ");
                  this.vista.txtClienteDni.setText(" ");
                  this.vista.txtClienteTelefono.setText(" ");
                 System.out.println("pulsando agregar");
                 break;
             case  btnCaptEliminar:
                 
                 
                 break;
                 
                 
            case btnCaptVer:    
                this.vista.btnEditarCliente.setEnabled(true);
                this.vista.btnAgregarCliente.setEnabled(false);
                this.vista.comercial.setSelectedIndex(1);
                System.out.println("Se ha pulsado el boton");
                String i = pepe;
         
                System.out.println("llamando metodo");
                this.vista.txtClienteDni.setText(this.modeloComercial.verCliente(i)[0]);
                this.vista.txtEstablNombre.setText(this.modeloComercial.verCliente(i)[1]);
                this.vista.txtEstablZona.setText(this.modeloComercial.verCliente(i)[2]);
                this.vista.txtEstablIdZona.setText(this.modeloComercial.verCliente(i)[3]);
         
                
                
                
                    
                    
                break;
            case btnHacerContrato:
                 try{
                    Reportes reporte= new Reportes();
                    reporte.reporteHacerContratao();               
                    
                } catch (SQLException | JRException ex) {
                    Logger.getLogger(ControladorAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            
        
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
