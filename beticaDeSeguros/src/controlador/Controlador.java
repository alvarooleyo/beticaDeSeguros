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
import modelo.ModeloAdmin;
import modelo.ModeloComercial;
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
    ModeloComercial modeloComercial = new ModeloComercial();
    ModeloAdmin modeloAdmin= new ModeloAdmin();
       
    
    
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
        
        
        
        txtPassword
    }
    
    
    
    
    
     public void iniciar(){
         
        
        //iniciamos el panel principal        
        this.vista.setVisible(true);
        this.vista.login.setVisible(true); // cambiado para que no salte siempre el usuario contraseña
        this.vista.panelAdmin.setVisible(false);
        this.vista.panelComercial.setVisible(false);
        //declara una acción y añade un escucha al evento producido por el componente:
        //boton de entrar
        this.vista.btnEntrar.setActionCommand( "btnEntrar" );
        this.vista.btnEntrar.addActionListener(this);
        
        this.vista.txtPassword.setActionCommand("txtPassword");
        this.vista.txtPassword.addActionListener(this);
        this.vista.txtPassword.addMouseListener(this);
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
               this.cargaPaneles();
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
           
              
        
        }
        
    
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
    
    public void cargaPaneles(){
         System.out.println("Pulsando entrar");
                String Usuario=this.vista.txtUsuario.getText();
                String clave=this.vista.txtPassword.getText();
                // se verifica la clave
                if (modelo.verificarClave(Usuario, clave)==1){
                    ControladorAdmin admin=new ControladorAdmin(this.vista);
                    admin.iniciarAdmin();
                    
                   
                    
                }else if(modelo.verificarClave(Usuario, clave)==2){
                    ControladorComercial comerc = new ControladorComercial(this.vista);
                    comerc.iniciarComercial();
                    
                }else{    
                    JOptionPane.showMessageDialog(vista,"Error: Los datos son incorrectos.");
                }
    }
    
    
}


