package com.example.ClasesVO.Otros;

import java.io.Serializable;

/**
 * @author Elia Baladrón Peral
 */
public class Rebano implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String nombre;

    /**
     * Constructor vacío. Utilizado para añadir rebaños con datos por defecto
     */
    public Rebano() {
        this.nombre = "Campo";
    }

    /**
     *
     * Constructor utilizado en la obtención de datos de la Base de Datos
     * @param id        ID del objeto en la BD
     * @param nombre    Nombre o titulo que identifica el Rebaño
     */
    public Rebano(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Constructor utilizado para la creación de objetos que no están en la BD, por lo que no tienen un ID
     * @param nombre    Nombre o titulo que identifica el Rebaño
     */
    public Rebano(String nombre) {
        this.id = -1;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }
    public String getIdString() {
        return Long.toString(id);
    }
    public String getNombre() {
        return nombre;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setId(String id) {
        this.id = Long.getLong(id);
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Rebaño{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
