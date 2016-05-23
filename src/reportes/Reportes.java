/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author android-0174654321
 */
public class Reportes {
    
    /**
     *
     * @param a
     * @throws SQLException
     * @throws JRException
     */
    public void reporteContratos(Connection a) throws SQLException, JRException{
    JasperReport contratos=null;
        System.out.println("1");
    contratos=(JasperReport) JRLoader.loadObjectFromFile("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\beticaDeSeguros\\beticaDeSeguros\\src\\reportes\\AdminContratos.jasper");
        System.out.println("2");
    JasperPrint print=JasperFillManager.fillReport(contratos, null, a);
        System.out.println("3");
    JasperViewer ver= new JasperViewer(print);
        System.out.println("4");
    ver.setTitle("Contratos");
    ver.setVisible(true);
    }
    
    /**
     *
     * @param a
     * @throws SQLException
     * @throws JRException
     */
    public void reporteContratosComerciales(Connection a) throws SQLException, JRException{
    
    a=DriverManager.getConnection("jdbc:mysql://localhost/BeticaSeguros");
    JasperReport contratos=null;
    contratos=(JasperReport) JRLoader.loadObjectFromFile("/home/android-0174654321/NetBeansProjects/beticaDeSeguros/beticaDeSeguros/src/reportes/AdminComerciales.jasper");
    JasperPrint print=JasperFillManager.fillReport(contratos, null, a);
    JasperViewer ver= new JasperViewer(print);
    ver.setTitle("Comerciales que han realizado contratos");
    ver.setVisible(true);
    }
    
    /**
     *
     * @param a
     * @throws SQLException
     * @throws JRException
     */
    public void reporteContratosZonas(Connection a) throws SQLException, JRException{
 
    a=DriverManager.getConnection("jdbc:mysql://localhost/BeticaSeguros");
    JasperReport contratos=null;
    contratos=(JasperReport) JRLoader.loadObjectFromFile("/home/android-0174654321/NetBeansProjects/beticaDeSeguros/beticaDeSeguros/src/reportes/AdminZonas.jasper");
    JasperPrint print=JasperFillManager.fillReport(contratos, null, a);
    JasperViewer ver= new JasperViewer(print);
    ver.setTitle("Contratos por Zonas");
    ver.setVisible(true);
    }
    
    /**
     *
     * @param a
     * @throws SQLException
     * @throws JRException
     */
    public void reporteHacerContratao(Connection a) throws SQLException, JRException{
    
    a=DriverManager.getConnection("jdbc:mysql://localhost/BeticaSeguros");
    JasperReport contratos=null;
    contratos=(JasperReport) JRLoader.loadObjectFromFile("/home/android-0174654321/NetBeansProjects/beticaDeSeguros/beticaDeSeguros/src/reportes/ComercialContrato.jasper");
    JasperPrint print=JasperFillManager.fillReport(contratos, null, a);
    JasperViewer ver= new JasperViewer(print);
    ver.setTitle("Bética de Seguros");
    ver.setVisible(true);
    }
}
