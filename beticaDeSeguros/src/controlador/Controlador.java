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
    public Controlador( Interfaz vista )
    {
        this.vista = vista;
        System.out.println("Construyendo controlador");
    }

     public void iniciar()
    {
        vista.setVisible(true);
        vista.login.setVisible(true);
        System.out.println("ejecutando iniciar");
        //declara una acción y añade un escucha al evento producido por el componente
        
        //añade e inicia el jtable con un DefaultTableModel vacio
         this.vista.tablaACoCo.setModel( this.modelo.getTablaComerciales() );
      
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
    
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
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
