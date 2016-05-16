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
            break;
      
            case tablaAPro:
                
              fila = this.vista.tablaAPro.rowAtPoint(e.getPoint());
             if (fila > -1){                
                this.vista.txtAPNombre.setText( String.valueOf( this.vista.tablaAPro.getValueAt(fila, 1) ));
                this.vista.txtAPDes.setText( String.valueOf( this.vista.tablaAPro.getValueAt(fila, 2) ));
                this.vista.txtAPPrecio.setText( String.valueOf( this.vista.tablaAPro.getValueAt(fila, 3) ));
                
               
             }
            break;
            
            case tablaACoCo:
              fila = this.vista.tablaACoCo.rowAtPoint(e.getPoint());
             if (fila > -1){                
                this.vista.txtACCNombre.setText( String.valueOf( this.vista.tablaACoCo.getValueAt(fila, 1) ));
                this.vista.txtACCDni.setText( String.valueOf( this.vista.tablaACoCo.getValueAt(fila, 2) ));
                this.vista.txtACCUsuario.setText( String.valueOf( this.vista.tablaACoCo.getValueAt(fila, 5) ));
                this.vista.txtACCClave.setText( String.valueOf( this.vista.tablaACoCo.getValueAt(fila, 4) ));
                
             }
            break;
            case tablaAZonas:
                fila = this.vista.tablaAZonas.rowAtPoint(e.getPoint());
             if (fila > -1){                
                this.vista.txtAZoNombre.setText( String.valueOf( this.vista.tablaAZonas.getValueAt(fila, 1) ));
                this.vista.txtAZoHabitantes.setText( String.valueOf( this.vista.tablaAZonas.getValueAt(fila, 2) ));
                this.vista.txtAZoEst.setText( String.valueOf( this.vista.tablaAZonas.getValueAt(fila, 3) ));
                String nombrezona=this.vista.txtAZoNombre.getText();                
                this.vista.txtAZoCo.setText(this.modeloAdmin.comercialesZona(nombrezona));
             }
            break;
             case tablaAAdmi:
                fila = this.vista.tablaAAdmi.rowAtPoint(e.getPoint());
             if (fila > -1){                
                this.vista.txtAANombre.setText( String.valueOf( this.vista.tablaAAdmi.getValueAt(fila, 1) ));
                this.vista.txtAADni.setText( String.valueOf( this.vista.tablaAAdmi.getValueAt(fila, 2) ));
                this.vista.txtAANU.setText( String.valueOf( this.vista.tablaAAdmi.getValueAt(fila, 5) ));
                this.vista.txtAAClave.setText( String.valueOf( this.vista.tablaAAdmi.getValueAt(fila, 4) ));
                
             }
             break;
        
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
        btnAADA,
        btnCoReasignar,
        btnACoAlta,
        btnAZoA,
        btnAZoE,
        btnAZoEli,
        btnAADB,
        btnACAnadir,
        btnACEliminar
    }
     public enum MouseMVC {
         tablaACoCo,
         tablaAClientes,
         tablaAZonas,
         tablaAPro,
         tablaAAdmi

    }

    @Override
    public void actionPerformed(ActionEvent e) {
         switch ( ControladorAdmin.AccionMVC.valueOf( e.getActionCommand() ) ){
            
            case btnACCBaja:
                
                this.modeloAdmin.BajaComerciales(this.vista.txtACCDni.getText());
                this.vista.tablaACoCo.setModel( this.modeloAdmin.getTablaComerciales());
                break;
            case btnACCModificar:
                this.modeloAdmin.MoificarDatosComerciales(this.vista.txtACCUsuario.getText(),this.vista.txtACCClave.getText(),this.vista.txtACCDni.getText());
                this.vista.tablaACoCo.setModel( this.modeloAdmin.getTablaComerciales());
                break;
            case btnAADA:
                this.modeloAdmin.AñadirAdministrador(this.vista.txtAANombre.getText(),this.vista.txtAADni.getText(),"administrador",this.vista.txtAAClave.getText(),this.vista.txtAANU.getText());
                this.vista.tablaAAdmi.setModel( this.modeloAdmin.rellenarTablaAdministradores());
                break;
                
            case btnCoReasignar:
                String nombre=this.vista.comboCoCo.getSelectedItem().toString();
                String zona=this.vista.comboCoZona.getSelectedItem().toString();
                this.modeloAdmin.reasignarZonas(nombre, zona);
                this.vista.tablaACoCo.setModel( this.modeloAdmin.getTablaComerciales());
                break;
            case btnACoAlta:
                String nombre1=this.vista.txtACoNombre.getText()+" "+this.vista.txtACoApellidos.getText();
                String dni=this.vista.txtACoDni.getText();
                String usuario=this.vista.txtACoNusu.getText();
                String clave=this.vista.txtACoClave.getText();
                String zona1=this.vista.txtACoZona.getText();
                this.modeloAdmin.altaComercial(nombre1, dni, usuario, clave, zona1);
                this.vista.tablaACoCo.setModel( this.modeloAdmin.getTablaComerciales());
                //Se rellenan los combobox de comerciales y zonas                    
                    this.vista.comboCoCo.setModel(this.modeloAdmin.rellenarComboBajasC());              
                    
                break;
            case btnAZoA:
                String nombre2=this.vista.txtAZoNombre.getText();
                String habitantes=this.vista.txtAZoHabitantes.getText();
                String establecimientos=this.vista.txtAZoEst.getText();
                this.modeloAdmin.altaZona(nombre2, habitantes, establecimientos);
                this.vista.tablaAZonas.setModel(this.modeloAdmin.rellenarTablaZona());
                this.vista.comboCoZona.setModel(this.modeloAdmin.rellenarComboBajasZ());
                break;
                
            case btnAZoE:
                String nombre3=this.vista.txtAZoNombre.getText();
                String habitantes3=this.vista.txtAZoHabitantes.getText();
                String establecimientos3=this.vista.txtAZoEst.getText();
                this.modeloAdmin.editarZonas(nombre3, habitantes3, establecimientos3);
                this.vista.tablaAZonas.setModel(this.modeloAdmin.rellenarTablaZona());
                this.vista.comboCoZona.setModel(this.modeloAdmin.rellenarComboBajasZ());
                break;  
            case btnAZoEli:
                String nombre4=this.vista.txtAZoNombre.getText();
                this.modeloAdmin.eliminarZona(nombre4);
                this.vista.tablaAZonas.setModel(this.modeloAdmin.rellenarTablaZona());
                this.vista.comboCoZona.setModel(this.modeloAdmin.rellenarComboBajasZ());
                break;
                
            case btnAADB:
                String dni2=this.vista.txtAADni.getText();
                this.modeloAdmin.eliminarAdmin(dni2);
                this.vista.tablaAAdmi.setModel(this.modeloAdmin.rellenarTablaAdministradores());
                break;
            case btnACAnadir:
                String nom=this.vista.txtACNombre.getText();
                String dni3=this.vista.txtACDni.getText();
                String telefono=this.vista.txtACTelefono.getText();
                String establecimiento=this.vista.txtACEst.getText();
                String idzona=this.vista.txtACZona.getText();
                this.modeloAdmin.AñadirClientes(nom, dni3, telefono, establecimiento, idzona);
                //se cargan los datos de la base de datos en la tabla clientes
                this.vista.tablaAClientes.setModel(this.modeloAdmin.rellenarTablaClientes());
                break;
            case btnACEliminar:
                String dni4=this.vista.txtACDni.getText();
                this.modeloAdmin.eliminarCliente(dni4);
                this.vista.tablaAClientes.setModel(this.modeloAdmin.rellenarTablaClientes());
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
                    this.vista.tablaACoCo.setModel( this.modeloAdmin.getTablaComerciales() );
                    //se añade un mouselistener a la tabla comerciales
                    this.vista.tablaACoCo.addMouseListener(this);
                    this.vista.tablaACoCo.setName("tablaACoCo");
              
                    //Se rellenan los combobox de comerciales y zonas                    
                    this.vista.comboCoCo.setModel(this.modeloAdmin.rellenarComboBajasC());                    
                    this.vista.comboCoZona.setModel(this.modeloAdmin.rellenarComboBajasZ());                  
                   
                    //se cargan los datos de la base de datos en la tabla clientes
                    this.vista.tablaAClientes.setModel(this.modeloAdmin.rellenarTablaClientes());
                    
                    this.vista.tablaAClientes.addMouseListener(this);
                    this.vista.tablaAClientes.setName("tablaAClientes");
                   
                    //se cargan los datos de la base de datos en la tabla zonas
                    this.vista.tablaAZonas.setModel(this.modeloAdmin.rellenarTablaZona());
                    this.vista.tablaAZonas.addMouseListener(this);
                    this.vista.tablaAZonas.setName("tablaAZonas");
                    
                    //se cargan los datos de la base de datos en la tabla productos
                    this.vista.tablaAPro.addMouseListener(this);
                    this.vista.tablaAPro.setModel(this.modeloAdmin.rellenarTablaProductos());
                    this.vista.tablaAPro.setName("tablaAPro");
                    
                    this.vista.btnACCBaja.setActionCommand("btnACCBaja");
                    this.vista.btnACCBaja.addActionListener(this);
                    
                     this.vista.btnACCModificar.setActionCommand("btnACCModificar");
                    this.vista.btnACCModificar.addActionListener(this);
                    
                    this.vista.tablaAAdmi.setModel(this.modeloAdmin.rellenarTablaAdministradores());
                    
                    this.vista.tablaAAdmi.addMouseListener(this);
                    this.vista.tablaAAdmi.setName("tablaAAdmi");
                    
                    this.vista.btnAADA.setActionCommand("btnAADA");
                    this.vista.btnAADA.addActionListener(this);
                    
                    this.vista.btnCoReasignar.setActionCommand("btnCoReasignar");
                    this.vista.btnCoReasignar.addActionListener(this);
                    
                    this.vista.btnACoAlta.setActionCommand("btnACoAlta");
                    this.vista.btnACoAlta.addActionListener(this);
                    //boton de añadir zonas
                    this.vista.btnAZoA.addActionListener(this);
                    this.vista.btnAZoA.setActionCommand("btnAZoA");
                    //boton editar zonas
                    this.vista.btnAZoE.addActionListener(this);
                    this.vista.btnAZoE.setActionCommand("btnAZoE");
                    //boton eliminar zona
                    this.vista.btnAZoEli.addActionListener(this);
                    this.vista.btnAZoEli.setActionCommand("btnAZoEli");  
                    //botno eliminar administrador
                    this.vista.btnAADB.addActionListener(this);
                    this.vista.btnAADB.setActionCommand("btnAADB");
                    //botón añadir clientes
                    this.vista.btnACAnadir.addActionListener(this);
                    this.vista.btnACAnadir.setActionCommand("btnACAnadir");
                    //botón eliminar clientes
                    this.vista.btnACEliminar.addActionListener(this);
                    this.vista.btnACEliminar.setActionCommand("btnACEliminar");
                    
                    
    }
    
   
}

 

