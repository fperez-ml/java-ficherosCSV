/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.dam2627.accesodatos.javaficheroscsv.modelo;

/**
 *
 * @author Fperez
 */
public class Producto {
    private String idProducto;
    private double precioUnidad;
    private String descripcion;

    public Producto() {
    }

    public Producto(String idProducto, double precioUnidad, String descripcion) {
        this.idProducto = idProducto;
        this.precioUnidad = precioUnidad;
        this.descripcion = descripcion;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(double precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
