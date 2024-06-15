/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import MODELO.ProductoDao;
import MODELO.ListarProductos;
import MODELO.Producto;

import VISTA.VistaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import static Main.Principal.lp;

/**
 * @author JHONIER ARIAS
 */
public class ControladorPrincipal implements ActionListener, ListSelectionListener, DocumentListener {

    //El ActionListener en Java sirve para manejar eventos de acción generados por componentes
    // de interfaz de usuario, como botones, menús desplegables y elementos de lista. Cuando un
    // usuario interactúa con uno de estos componentes, como hacer clic en un botón,
    // se genera un evento de acción. El ActionListener permite capturar ese evento y realizar acciones específicas en respuesta a él.

    //ListSelectionListener en Java se utiliza para manejar eventos de selección en componentes de lista,
    // como JList o JTable. Cuando un usuario selecciona uno o más elementos en una lista,
    // se generan eventos de selección. El ListSelectionListener permite capturar estos eventos y
    // responder a ellos ejecutando código específico.


     // El DocumentListener en Java sirve para monitorear
    // cambios en un documento de texto, como un campo de texto o un
    // área de texto. Proporciona tres métodos que te permiten responder a eventos
    // cuando el texto en el documento cambia:
    //pueden ser útiles para realizar validaciones en tiempo real,
    // actualizar la interfaz de usuario o realizar otras acciones basadas en los cambios en el texto

    VistaPrincipal vista;

    public ControladorPrincipal(VistaPrincipal guardar1) {
        this.vista = guardar1;
        this.vista.btnGuardar.addActionListener(this);// inicializar el baton VistaPrincipal
        this.vista.tablaProductos.getSelectionModel().addListSelectionListener(this);
        this.vista.btnActualizar.addActionListener(this);// inicializar el boton actualizar
        this.vista.btnCancelar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.txtBuscar.getDocument().addDocumentListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) { // controlador que modifica los botones y le da órdenes

        if (e.getSource()== vista.btnEliminar){
            int id = Integer.parseInt(vista.txtID.getText());
            ProductoDao eliminar = new ProductoDao();
            eliminar.eliminarProducto(id);


            ListarProductos lp= new ListarProductos();// recrezca la tabla
            lp.MostrarTable(vista.tablaProductos);

            vista.btnGuardar.setEnabled(false);
            vista.btnActualizar.setEnabled(false);

            JOptionPane.showMessageDialog(null," ! Eliminado con Exito ¡");
        }

        //boton VistaPrincipal
        if (e.getSource() == vista.btnGuardar) {

            vista.txtID.setEnabled(false);
            vista.btnActualizar.setEnabled(false);

            String nombre = vista.txtNombre.getText();
            int precio = Integer.parseInt(vista.txtPrecio.getText());
            int idCategoria = Integer.parseInt(vista.txtCategoria.getText());
            Producto p = new Producto(nombre, precio, idCategoria);
            ProductoDao crear1 = new ProductoDao();
            crear1.crearProducto(p);

            ListarProductos lp = new ListarProductos();
            lp.MostrarTable(vista.tablaProductos);
            JOptionPane.showMessageDialog(null, " PRODUCTO GUARDADO");
            limpiarEntradas();
        }

        //boton actualizar
        if (e.getSource()== vista.btnActualizar){
                int id = Integer.parseInt(vista.txtID.getText());
                String nombre = vista.txtNombre.getText();
                int precio = Integer.parseInt(vista.txtPrecio.getText());
                int idCategoria = Integer.parseInt(vista.txtCategoria.getText());

                Producto actualizarUnProducto = new Producto(id, nombre, precio, idCategoria);
                ProductoDao productoDao = new ProductoDao();
                productoDao.actualizarProducto(actualizarUnProducto, id);

                JOptionPane.showMessageDialog(null,"! Actualizado ¡");

                ListarProductos lp= new ListarProductos();
                lp.MostrarTable(vista.tablaProductos);

                 limpiarEntradas();//una vez actualizado limpia los campos
        }
        if (e.getSource()==vista.btnCancelar){
            vista.txtID.setEnabled(true);
            vista.txtNombre.setEnabled(true);
            vista.txtPrecio.setEnabled(true);
            vista.txtCategoria.setEnabled(true);
            vista.btnActualizar.setEnabled(true);
            vista.btnGuardar.setEnabled(true);
            limpiarEntradas();
        }

    }
    private void limpiarEntradas() {//Metodo para que se borren los campos del panel
        //se llaman todos los txt y se le da el valor null.. Cuando se llame en otro método él borra los campos
        // una vez ya se corre el metodo.
        vista.txtID.setText("");
        vista.txtNombre.setText("");
        vista.txtPrecio.setText("");
        vista.txtCategoria.setText("");
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        //  este metodo sirve para seleccionar una fila de la tabla y poderla eliminar o actualizar
        if (!e.getValueIsAdjusting()) {
            if (e.getSource() == vista.tablaProductos.getSelectionModel()) {
                int filaObtenidad = vista.tablaProductos.getSelectedRow();

                if (filaObtenidad >= 0) {
                    TableModel modelo = vista.tablaProductos.getModel();

                    Object id = modelo.getValueAt(filaObtenidad, 0);
                    Object nombre = modelo.getValueAt(filaObtenidad, 1);
                    Object precio = modelo.getValueAt(filaObtenidad, 2);
                    Object idCategoria = modelo.getValueAt(filaObtenidad, 3);

                    vista.txtID.setText(id.toString());
                    vista.txtNombre.setText(nombre.toString());
                    vista.txtPrecio.setText(precio.toString());
                    vista.txtCategoria.setText(idCategoria.toString());


                    vista.btnGuardar.setEnabled(false);
                    vista.btnActualizar.setEnabled(true);
                }
                System.out.println("fila: " + filaObtenidad);
            }
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        buscar();
    }
    @Override
    public void removeUpdate(DocumentEvent e) {
        buscar();
    }
    @Override
    public void changedUpdate(DocumentEvent e) {
        buscar();
    }

    public void buscar(){
            String valor = vista.txtBuscar.getText();
            lp.Buscar(valor,vista.tablaProductos);
    }


}
