package com.example.ClasesVO.CompraVenta;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Elia Baladrón Peral
 */
public class CompraVenta implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String crotal;
    private Double precio;
    private Date fecha;

    /**
     * Constructor vacío. Utilizado para añadir compras o ventas con datos por defecto
     */
    public CompraVenta() {
        this.crotal = "ES 4321 4321 4321";
        this.precio = 1000d;
        this.fecha = new Date(System.currentTimeMillis());
    }
    /*public CompraVenta(String crotal, Double precio, Date fecha) {
        this.crotal = crotal;
        this.precio = precio;
        this.fecha = fecha;
    }*/

    /**
     *Constructor utilizado para la creación de objetos que no están en la BD, por lo que no tienen un ID
     * @param crotal    Crotal o número identificativo oficial
     * @param precio    Precio de la compra
     * @param fecha     Fecha de la compra
     */
    public CompraVenta(String crotal, Double precio, String fecha) {
        this.crotal = crotal;
        this.precio = precio;
        this.fecha = Date.valueOf(fecha);
    }
    /*public CompraVenta(long id, String crotal, Double precio, Date fecha) {
        this.id = id;
        this.crotal = crotal;
        this.precio = precio;
        this.fecha = fecha;
    }*/

    /**
     * Constructor utilizado en la obtención de datos de la Base de Datos
     * @param id        ID del objeto en la BD
     * @param crotal    Crotal o número identificativo oficial
     * @param precio    Precio de la compra
     * @param fecha     Fecha de la compra
     */
    public CompraVenta(long id, String crotal, Double precio, String fecha) {
        this.id = id;
        this.crotal = crotal;
        this.precio = precio;
        this.fecha = Date.valueOf(fecha);
    }

    public long getId() {
        return id;
    }
    public String getIdString() {
        return Long.toString(id);
    }
    public String getCrotal() {
        return crotal;
    }
    public Double getPrecio() {
        return precio;
    }
    public String getPrecioString() {
        return precio.toString();
    }
    public Date getFecha() {
        return fecha;
    }
    public String getFechaString() {
        return fecha.toString();
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setCrotal(String crotal) {
        this.crotal = crotal;
    }
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public void setPrecio(String precio) {
        this.precio = Double.parseDouble(precio);
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = Date.valueOf(fecha);
    }

    @Override
    public String toString() {
        return "CompraVenta{" +
                "id=" + id +
                ", crotal='" + crotal + '\'' +
                ", precio=" + precio +
                ", fecha=" + fecha +
                '}';
    }
}
