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
                //Eliminar cliente de la parte captaciones
                this.vista.btnCaptEliminar.setActionCommand( "btnCaptEliminar" );
                this.vista.btnCaptEliminar.addActionListener(this);
                
                this.vista.btnCaptAgregar.addActionListener(this);
                this.vista.btnCaptAgregar.setActionCommand( "btnCaptAgregar" );
                this.vista.btnCaptVer.setActionCommand("btnCaptVer");
                this.vista.btnCaptVer.addActionListener(this);
                this.vista.cmbClientesTipoProd.setModel(this.modeloComercial.rellenarComboProductos());
                this.vista.comboClientesPro2.setModel(this.modeloComercial.rellenarComboProductos());
                //boton verRenovaciones
                this.vista.btnRenovVer.setActionCommand("btnRenovVer");
                this.vista.btnRenovVer.addActionListener(this);
                //boton contratar
                this.vista.btnContratar.addActionListener(this);
                this.vista.btnContratar.setActionCommand("btnContratar");
                //boton hacer controtato
                this.vista.btnHacerContrato.addActionListener(this);
                this.vista.btnHacerContrato.setActionCommand("btnHacerContrato");
                //boton imprimir contrato
                this.vista.btnImprimirContrato.addActionListener(this);
                this.vista.btnImprimirContrato.setActionCommand("btnImprimirContrato");
                
                //boton añadir captacion
                
                this.vista.btnAgregarCliente.addActionListener(this);
                this.vista.btnAgregarCliente.setActionCommand("btnAgregarCliente");
                
                
                
                                
                               

    }

    public enum MouseMVC {
         tablaCaptaciones,
         tablaRenovaciones     
 
    }
    
    public enum ActionMVC {
        btnCaptAgregar,
        btnCaptEliminar,
        btnCaptVer,
        btnAgregarCliente,
        
        btnRenovAgregar,
        btnRenovEliminar,
        btnRenovVer,
        
        btnContratar,
        btnImprimirContrato,
        btnHacerContrato
    }
    
    String p,j;
    
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
                this.vista.txtIdCliente.setText(String.valueOf( this.vista.tablaCaptaciones.getValueAt(fila, 0) ));
                p = String.valueOf( this.vista.tablaCaptaciones.getValueAt(fila, 0) );
               
             }
            break;
      
            case tablaRenovaciones:
                
              fila = this.vista.tablaRenovaciones.rowAtPoint(e.getPoint());
             if (fila > -1){                
                this.vista.txtClienteId.setText( String.valueOf( this.vista.tablaRenovaciones.getValueAt(fila, 0) ));
                this.vista.txtClienteNombre.setText( String.valueOf( this.vista.tablaRenovaciones.getValueAt(fila, 1) ));
                this.vista.txtClienteTelefono.setText( String.valueOf( this.vista.tablaRenovaciones.getValueAt(fila, 2) ));
                j = String.valueOf( this.vista.tablaRenovaciones.getValueAt(fila, 0) );
               
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
                  this.vista.txtEstablNombre.setText(" ");
                  this.vista.txtEstablZona.setText(" ");
                  this.vista.txtEstablIdZona.setText("");
                 System.out.println("pulsando agregar");
                 
                 
                 break;
                 
             case btnAgregarCliente:
                 
                 this.modeloComercial.AñadirCaptaciones(Integer.parseInt(vista.txtIdCliente.getText()), vista.txtClienteNombre.getText(), vista.txtClienteDni.getText(), vista.txtClienteTelefono.getText(), vista.txtEstablZona.getText(), Integer.parseInt(vista.txtEstablIdZona.getText()));
                 this.vista.tablaRenovaciones.setModel(this.modeloComercial.getTablaClienteRenov());
                 
             
             //Boton para eliminar cliente de la tabla captaciones
             case  btnCaptEliminar:
                 System.out.println("Se ha pulsado eliminar captacion");
                this.modeloComercial.eliminarCliente(Integer.parseInt(this.vista.txtClienteId.getText()));
                
                this.vista.tablaCaptaciones.setModel(this.modeloComercial.getTablaCliente());
                
                break;
                 
            // Ver detalles del cliente de la tabla captaciones
            case btnCaptVer:    
                this.vista.btnEditarCliente.setEnabled(true);
                this.vista.panelDatosProducto.setVisible(false);
                this.vista.btnAgregarCliente.setEnabled(false);
                this.vista.comercial.setSelectedIndex(1);
                System.out.println("Se ha pulsado el boton");
                String i = p;
         
                System.out.println("llamando metodo");
                this.vista.txtClienteDni.setText(this.modeloComercial.verCliente(i)[0]);
                this.vista.txtEstablNombre.setText(this.modeloComercial.verCliente(i)[1]);
                this.vista.txtEstablZona.setText(this.modeloComercial.verCliente(i)[2]);
                this.vista.txtEstablIdZona.setText(this.modeloComercial.verCliente(i)[3]);               
                   
                break;
            // Ver detalles del cliente de la tabla renovaciones
            case btnRenovVer:    
                this.vista.btnEditarCliente.setEnabled(true);
                this.vista.panelDatosProducto.setVisible(true);
                this.vista.cmbClientesTipoProd.setEnabled(false);
                this.vista.btnAgregarCliente.setEnabled(false);
                this.vista.comercial.setSelectedIndex(1);
                System.out.println("Se ha pulsado el boton");
                String ie = j;
         
                System.out.println("llamando metodo");
                this.vista.txtClienteDni.setText(this.modeloComercial.verCliente(ie)[0]);
                this.vista.txtEstablNombre.setText(this.modeloComercial.verCliente(ie)[1]);
                this.vista.txtEstablZona.setText(this.modeloComercial.verCliente(ie)[2]);
                this.vista.txtEstablIdZona.setText(this.modeloComercial.verCliente(ie)[3]);               
                   
                break;    
                
            case btnContratar:
                this.vista.comercial.setSelectedIndex(2);
                System.out.println("Se ha pulsado el boton contratar");
                this.vista.txtIdComercial.setText(this.modeloComercial.datosComerciales(this.vista.txtUsuario.getText())[0]);
                //Intentando poner el primer jcalendar con la fecha actual ya seleccionada
                //this.vista.jDateChooser1.setDate(date);
         
                           
                    
                    
                break;
                
                
            case btnImprimirContrato:
                 try{
                    Reportes reporte= new Reportes();
                    reporte.reporteHacerContratao();               
                    
                } catch (SQLException | JRException ex) {
                    Logger.getLogger(ControladorAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case btnHacerContrato:
                String idcliente=this.vista.txtIdCliente.getText();
                String idcomercial=this.vista.txtIdComercial.getText();
                //falla a la hora de seleccionar la fecha del jcalendar hay qu investigar
                String fechaInicio="this.vista.jDateChooser1.getDate().getDay()-"+"this.vista.jDateChooser1.getDate().getDay()";
               
                System.out.println(this.vista.jDateChooser1.getDate().getDay());
                String fechaFin=this.vista.jDateChooser1.getDateFormatString();
                String producto=this.vista.comboClientesPro2.getSelectedItem().toString();
                System.out.println(this.vista.comboClientesPro2.getSelectedItem().toString());
                this.modeloComercial.contratar(idcliente, idcomercial, producto, fechaInicio, fechaFin);
                
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
