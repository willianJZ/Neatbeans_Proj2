/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author JHONIER ARIAS
 */
public class Producto {
    private int id;
    private String nombre;
    private int precio;
    private int idCategoria;

    public Producto() {
    }

    public Producto(int id) {
        this.id = id;
    }

    public Producto(String nombre, int precio, int idCategoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.idCategoria = idCategoria;
    }

    public Producto(int id, String nombre, int precio, int idCategoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.idCategoria = idCategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    
    
    
    
    
}
