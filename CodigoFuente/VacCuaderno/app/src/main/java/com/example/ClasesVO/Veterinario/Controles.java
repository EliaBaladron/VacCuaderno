package com.example.ClasesVO.Veterinario;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;

/**
 * @author Elia Baladrón Peral
 */
public class Controles extends Veterinario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String numRegistro;

    /**
     * Constructor vacío. Utilizado para añadir controles veterinarios con datos por defecto
     */
    public Controles() {
        super();
        this.numRegistro = "1234 1234";
    }

    /**
     *
     * @param id            ID del objeto en la BD
     * @param titulo        Titulo de la visita
     * @param descripcion   Descripción de la visita
     * @param fecha         Fecha en la que se realizo la visita
     * @param animales      Animales incluidos en la visita
     * @param numRegistro   Número oficial del control
     */
    public Controles(long id, String titulo, String descripcion, String fecha, String animales, String numRegistro) {
        super(id, titulo, descripcion, fecha, animales);
        this.numRegistro = numRegistro;
    }

    /**
     * Constructor utilizado para la creación de objetos que no están en la BD, por lo que no tienen un ID
     * @param titulo        Titulo de la visita
     * @param descripcion   Descripción de la visita
     * @param fecha         Fecha en la que se realizo la visita
     * @param animales     Animales incluidos en la visita
     * @param numRegistro   Número oficial del control
     */
    public Controles(String titulo, String descripcion, String fecha, ArrayList<String> animales, String numRegistro) {
        super(titulo, descripcion, fecha, animales);
        this.numRegistro = numRegistro;
    }

    /**
     * Constructor utilizado para la creación de objetos que no están en la BD, por lo que no tienen un ID
     * @param titulo        Titulo de la visita
     * @param descripcion   Descripción de la visita
     * @param fecha         Fecha en la que se realizo la visita
     * @param animales     Animales incluidos en la visita
     * @param numRegistro   Número oficial del control
     */
    public Controles(String titulo, String descripcion, String fecha, String animales, String numRegistro) {
        super(titulo, descripcion, fecha, animales);
        this.numRegistro = numRegistro;
    }

    public String getNumRegistro() {
        return numRegistro;
    }

    public void setNumRegistro(String numRegistro) {
        this.numRegistro = numRegistro;
    }

    @Override
    public String toString() {
        return "Veterinario{" +
                "id=" + getId() +
                ", titulo='" + getTitulo() + '\'' +
                ", descripcion='" + getDescripcion() + '\'' +
                ", fecha=" + getFecha().toString() +
                ", animales=" + getAnimalesString() +
                ", numero de registro=" + getNumRegistro() +
                '}';
    }
}
