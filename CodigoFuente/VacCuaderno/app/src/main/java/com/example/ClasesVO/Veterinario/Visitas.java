package com.example.ClasesVO.Veterinario;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Elia Baladrón Peral
 */
public class Visitas extends Veterinario implements Serializable {

    private static final long serialVersionUID = 1L;

    private double precio;

    /**
     * Constructor vacío. Utilizado para añadir visitas veterinarias con datos por defecto
     */
    public Visitas() {
        super();
        this.precio = 100d;
    }
    /**
     * Constructor utilizado en la obtención de datos de la Base de Datos
     * @param id            ID del objeto en la BD
     * @param titulo        Titulo de la visita
     * @param descripcion   Descripción de la visita
     * @param fecha         Fecha en la que se realizo la visita
     * @param animales      Animales incluidos en la visita
     * @param precio        Precio por la visita
     */
    public Visitas(long id, String titulo, String descripcion, String fecha, String animales, float precio) {
        super(id, titulo, descripcion, fecha, animales);
        this.precio = precio;
    }
    /**
     * Constructor utilizado para la creación de objetos que no están en la BD, por lo que no tienen un ID
     * @param titulo        Titulo de la visita
     * @param descripcion   Descripción de la visita
     * @param fecha         Fecha en la que se realizo la visita
     * @param animales      Animales incluidos en la visita
     * @param precio        Precio por la visita
     */
    public Visitas(String titulo, String descripcion, String fecha, ArrayList<String> animales, float precio) {
        super(titulo, descripcion, fecha, animales);
        this.precio = precio;
    }
    /**
     * Constructor utilizado para la creación de objetos que no están en la BD, por lo que no tienen un ID
     * @param titulo        Titulo de la visita
     * @param descripcion   Descripción de la visita
     * @param fecha         Fecha en la que se realizo la visita
     * @param animales      Animales incluidos en la visita
     * @param precio        Precio por la visita
     */
    public Visitas(String titulo, String descripcion, String fecha, String animales, float precio) {
        super(titulo, descripcion, fecha, animales);
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }
    public String getPrecioString() {
        return Double.toString(precio);
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    @Override
    public String toString() {
        return "Veterinario{" +
                "id=" + getId() +
                ", titulo='" + getTitulo() + '\'' +
                ", descripcion='" + getDescripcion() + '\'' +
                ", fecha=" + getFecha().toString() +
                ", animales=" + getAnimalesString() +
                ", precio=" + getPrecio() +
                '}';
    }
}
