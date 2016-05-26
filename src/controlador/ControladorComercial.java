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
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Database;
import net.sf.jasperreports.engine.JRException;
import reportes.Reportes;
import vista.Interfaz;

/**
 *
 * @author alvaro
 */
public class ControladorComercial extends Controlador implements ActionListener,MouseListener{

    /**
     *
     * @param vista Interfaz
     */
    public ControladorComercial(Interfaz vista) {
        /** instancia a nuestra interfaz de usuario*/
        super(vista);
        
    }
 
   
  Database a=new Database();
    
    /**
     *
     */
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
                //Boton agregar cliente a base de datos
                this.vista.btnAgregarCliente.setActionCommand("btnAgregarCliente");
                this.vista.btnAgregarCliente.addActionListener(this);
                //boton editar = actualizar cliente en la base de datos
                this.vista.btnEditarCliente.addActionListener(this);
                this.vista.btnEditarCliente.setActionCommand("btnEditarCliente");
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
                //botn eliminar cliente
                this.vista.btnRenovEliminar.addActionListener(this);
                this.vista.btnRenovEliminar.setActionCommand("btnRenovEliminar");
                //boton Nuevo contrato en renovaciones
                this.vista.btnNuevoContrato.addActionListener(this);
                this.vista.btnNuevoContrato.setActionCommand("btnNuevoContrato");
                
                
                
                
                
                                
                               

    }

    /**
     *
     */
    public enum MouseMVC {

        /**
         *
         */
        tablaCaptaciones,
     
        /**
         *
         */
        tablaRenovaciones     
 
    }
    
    /**
     *
     */
    public enum ActionMVC {

        /**
         *
         */
        btnCaptAgregar,

        /**
         *
         */
        btnCaptEliminar,

        /**
         *
         */
        btnCaptVer,
        
        /**
         *
         */
        btnRenovAgregar,

        /**
         *
         */
        btnRenovEliminar,

        /**
         *
         */
        btnRenovVer,
        
        /**
         *
         */
        btnAgregarCliente,

        /**
         *
         */
        btnEditarCliente,
        
        /**
         *
         */
        btnContratar,

        /**
         *
         */
        btnImprimirContrato,

        /**
         *
         */
        btnHacerContrato,
        
        btnNuevoContrato
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
                this.vista.txtIdCliente.setText(String.valueOf( this.vista.tablaRenovaciones.getValueAt(fila, 0) ));
                j = String.valueOf( this.vista.tablaRenovaciones.getValueAt(fila, 0) );
               
             }
            break;
            
             
                
             
             }
        
    }
    
    
     @Override
    public void actionPerformed(ActionEvent e) {
         switch ( ControladorComercial.ActionMVC.valueOf( e.getActionCommand() ) ){
             //boton para ir a panel para agregar cliente
             case btnCaptAgregar:
                  
                  this.vista.btnAgregarCliente.setEnabled(true);
                  
                  this.vista.btnEditarCliente.setEnabled(false);
                  this.vista.comercial.setSelectedIndex(1);
                  
                  
                  this.vista.txtClienteNombre.setText("");
                  this.vista.txtClienteId.setText("");
                  this.vista.txtClienteDni.setText("");
                  this.vista.txtClienteTelefono.setText("");
                  this.vista.txtEstablNombre.setText("");
                  this.vista.txtEstablZona.setText("");
                  this.vista.txtEstablIdZona.setText("");
                  String usuario = this.vista.nombreUsuario.getText();                 
                  this.vista.txtEstablIdZona.setText(this.modeloComercial.ponerZonaCliente(usuario)[1]);
                  this.vista.txtEstablNombre.setText(this.modeloComercial.ponerZonaCliente(usuario)[0]);
                 System.out.println("pulsando agregar");
                 
                 break;
             
             //Boton para eliminar cliente de la tabla captaciones
             case  btnCaptEliminar:                
                this.modeloComercial.eliminarCliente(Integer.parseInt(this.vista.txtClienteId.getText()));
                this.vista.tablaCaptaciones.setModel(this.modeloComercial.getTablaCliente());
                 break;
            
            //boton agregar cliente a la base de datos   
             case btnAgregarCliente:
                 String nombre = this.vista.txtClienteNombre.getText();
                 String dni = this.vista.txtClienteDni.getText();
                 String telefono = this.vista.txtClienteTelefono.getText();
                 String establecimiento = this.vista.txtEstablZona.getText();
                 int idZona = Integer.parseInt(this.vista.txtEstablIdZona.getText());
                 this.modeloComercial.agregarCliente(nombre, dni, telefono, establecimiento, idZona);
                 this.vista.tablaCaptaciones.setModel(this.modeloComercial.getTablaCliente());
                break;  
            //Boton actualizar cliente en la base de datos
             case btnEditarCliente:
                 String n = this.vista.txtClienteNombre.getText();
                 String d = this.vista.txtClienteDni.getText();
                 String t = this.vista.txtClienteTelefono.getText();
                 String est = this.vista.txtEstablZona.getText();
                 int ide = Integer.parseInt(this.vista.txtClienteId.getText());
                 int iZ = Integer.parseInt(this.vista.txtEstablIdZona.getText());
                 this.modeloComercial.MoificarDatosCliente(ide, n, t, est, iZ);
                 this.vista.tablaCaptaciones.setModel(this.modeloComercial.getTablaCliente());
                 this.vista.tablaRenovaciones.setModel(this.modeloComercial.getTablaClienteRenov());
                 System.out.println("Actualizado");
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
            case btnRenovEliminar:
                this.modeloComercial.eliminarCliente(Integer.parseInt(this.vista.txtClienteId.getText()));
                this.vista.tablaCaptaciones.setModel(this.modeloComercial.getTablaCliente());
                break;
                
                
                
                
            // Ver detalles del cliente de la tabla renovaciones
            case btnRenovVer:    
                this.vista.btnEditarCliente.setEnabled(true);
                this.vista.panelDatosProducto.setVisible(true);
                this.vista.cmbClientesTipoProd.setModel(this.modeloComercial.rellenarComboProductosRenovacion(this.vista.txtClienteId.getText()));
                this.vista.btnAgregarCliente.setEnabled(false);
                this.vista.comercial.setSelectedIndex(1);
                String ie = j;
         
                this.vista.txtClienteDni.setText(this.modeloComercial.verCliente(ie)[0]);
                this.vista.txtEstablNombre.setText(this.modeloComercial.verCliente(ie)[1]);
                this.vista.txtEstablZona.setText(this.modeloComercial.verCliente(ie)[2]);
                this.vista.txtEstablIdZona.setText(this.modeloComercial.verCliente(ie)[3]);
                
                   
                break;    
                
            case btnContratar:
                this.vista.comercial.setSelectedIndex(2);
                this.vista.txtIdComercial.setText(this.modeloComercial.datosComerciales(this.vista.txtUsuario.getText())[0]);
                //Intentando poner el primer jcalendar con la fecha actual ya seleccionada
                //this.vista.jDateChooser1.setDate(date);
         
                           
                    
                    
                break;
                
                
            case btnImprimirContrato:
                 try{
                    Reportes reporte= new Reportes();
                    reporte.reporteHacerContratao(a.getConexion());
                } catch (SQLException | JRException ex) {
                    Logger.getLogger(ControladorAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case btnHacerContrato:
                String idcliente=this.vista.txtIdCliente.getText();
                String idcomercial=this.vista.txtIdComercial.getText();
                //falla a la hora de seleccionar la fecha del jcalendar hay qu investigar
                int dia =this.vista.jDateChooser1.getCalendar().get(Calendar.DAY_OF_MONTH);
                int mes=this.vista.jDateChooser1.getCalendar().get(Calendar.MONTH);
                int ano=this.vista.jDateChooser1.getCalendar().get(Calendar.YEAR);
                String fechaalta=ano+"-"+mes+"-"+dia;
                int dia2 =this.vista.jDateChooser2.getCalendar().get(Calendar.DAY_OF_MONTH);
                int mes2=this.vista.jDateChooser2.getCalendar().get(Calendar.MONTH);
                int ano2=this.vista.jDateChooser2.getCalendar().get(Calendar.YEAR);
                String fechabaja=ano2+"-"+mes2+"-"+dia2;
                               
                String producto=this.vista.comboClientesPro2.getSelectedItem().toString();
                System.out.println(this.vista.comboClientesPro2.getSelectedItem().toString());
                this.modeloComercial.contratar(idcliente, idcomercial, producto, fechaalta, fechabaja);
                this.vista.tablaCaptaciones.setModel(this.modeloComercial.getTablaCliente());
                this.vista.tablaRenovaciones.setModel(this.modeloComercial.getTablaClienteRenov());
                
                break;
            case btnNuevoContrato: 
                this.vista.comercial.setSelectedIndex(2);
                this.vista.txtIdComercial.setText(this.modeloComercial.datosComerciales(this.vista.txtUsuario.getText())[0]);
                //Intentando poner el primer jcalendar con la fecha actual ya seleccionada
                //this.vista.jDateChooser1.setDate(date);
         
                           
                    
                    
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
