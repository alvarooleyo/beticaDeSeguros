/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import vista.Interfaz;


/**
 *
 * @author android-0174654321
 */
public class Main {    
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //ejecuta el controlador y este la vista
        new Controlador( new Interfaz() ).iniciar(); ;
    }

}
    

