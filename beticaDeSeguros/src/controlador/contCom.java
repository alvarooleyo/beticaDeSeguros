/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.table.DefaultTableModel;
import modelo.Modelo;
import vista.Interfaz;

/**
 *
 * @author Manuel
 */
public class contCom extends Controlador implements ActionListener,MouseListener{
    
    public contCom(Interfaz vista){
        super(vista);
    }
    
    Interfaz vista = new Interfaz();
    Modelo mod = new Modelo();
    
    public enum AccionMVC {
        _btnCaptVer,
        _btnCaptEliminar,
        _btnCaptAgregar,
        
        _btnRenovVer,
        _btnRenovEliminar,
        _btnRenovAgregar,
        
        _btnAgregarCliente,
        _btnEditarClientes
    }
    
    public enum MouseMVC {
        _tablaCaptaciones,
        _tablaRenovaciones
    }
    
    public void panelComercial(){
        super.vista.panelComercial.setVisible(true);
        super.vista.panelAdmin.setVisible(false);
        super.vista.login.setVisible(false);
        System.out.println("Comercial cargado");
        
        //se añade la tabla cliente y se cargan los datos de la base de datos
        this.vista.tablaRenovaciones.setModel(new DefaultTableModel());
        this.vista.tablaRenovaciones.setModel(this.modelo.getTablaCliente());
        
        //se añade un mouselistener a la tabla comerciales
        this.vista.tablaRenovaciones.addMouseListener(this);
        this.vista.tablaRenovaciones.setName("tablaRenovaciones");
    }
}
