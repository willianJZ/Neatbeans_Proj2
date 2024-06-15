package MODELO;

import CONEXION.ConexionDB;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductoDao {
    private final Connection conexion;
    private final ConexionDB conexionDB;


    public ProductoDao(){
        conexionDB = new ConexionDB();
        conexion = ConexionDB.getConexion();
    }


    public void crearProducto( Producto producto) {
        String sql = "INSERT INTO productos (nombre, precio, idCategoria) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setInt(2, producto.getPrecio());
            preparedStatement.setInt(3, producto.getIdCategoria());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("error" + e.getMessage());
        }
        conexionDB.closeConexion(conexion);
    }


    // se modificó el metodo con el int id para luego llamarlo  como clic en el parametro del controlador actulizar
    public void actualizarProducto(Producto producto, int id) {
        String sql = "UPDATE productos SET nombre = ?, precio = ?, idCategoria = ? WHERE id = "+id+" ";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.setString(1, producto.getNombre());
            preparedStatement.setInt(2, producto.getPrecio());
            preparedStatement.setInt(3, producto.getIdCategoria());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR: "+e.getMessage());
        }
        conexionDB.closeConexion(conexion);
    }

    public void eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = "+id+" ";
        try (PreparedStatement preparedStatement = conexion.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar los datos: " + e.getMessage());
        } conexionDB.closeConexion(conexion);

    }


 }


