/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import CONTROLADOR.ControladorPrincipal;
import MODELO.ListarProductos;
import VISTA.VistaPrincipal;


/**
 *
 * @author JHONIER ARIAS
 */
public class Principal {

    /**
     */
    
    public static VistaPrincipal guardar;
    public static CONTROLADOR.ControladorPrincipal controlador;
    public static ListarProductos lp;

    public static void main(String[] args) {

        guardar = new VistaPrincipal();
        guardar.setVisible(true);
        guardar.setLocationRelativeTo(null);

        controlador= new ControladorPrincipal(guardar);// se le une el controlador  la vista para sincronizar

        lp= new ListarProductos();//mostrar la lista permanente
        lp.MostrarTable(guardar.tablaProductos);
       
        
        
        
        
        
       




    






        
        
        }
    
}
