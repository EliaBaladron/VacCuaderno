package com.example.ClasesVO.Veterinario;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Arrays;

/**
 * @author Elia Baladrón Peral
 */
class Veterinario implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String titulo;
    private String descripcion;
    private Date fecha;
    private ArrayList<String> animales;

    /**
     * Constructor vacío. Utilizado para añadir datos veterinarios con datos por defecto
     */
    public Veterinario() {
        this.titulo = "Titulo";
        this.descripcion = "Descripción del control o visita";
        this.fecha = new Date(System.currentTimeMillis());
        this.animales = new ArrayList<>();
    }

    /**
     * Constructor utilizado en la obtención de datos de la Base de Datos
     * @param id            ID del objeto en la BD
     * @param titulo        Titulo de la visita
     * @param descripcion   Descripción de la visita
     * @param fecha         Fecha en la que se realizo la visita
     * @param animales      Animales incluidos en la visita
     */
    public Veterinario(long id, String titulo, String descripcion, String fecha, String animales) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = Date.valueOf(fecha);

        String[] t = animales.split(";");
        this.animales = new ArrayList<>();
        this.animales.addAll(Arrays.asList(t));
    }
    /**
     * Constructor utilizado para la creación de objetos que no están en la BD, por lo que no tienen un ID
     * @param titulo        Titulo de la visita
     * @param descripcion   Descripción de la visita
     * @param fecha         Fecha en la que se realizo la visita
     * @param animales      Animales incluidos en la visita
     */
    public Veterinario(String titulo, String descripcion, String fecha, ArrayList<String> animales) {
        this.id = -1;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = Date.valueOf(fecha);
        this.animales = animales;
    }
    /**
     * Constructor utilizado para la creación de objetos que no están en la BD, por lo que no tienen un ID
     * @param titulo        Titulo de la visita
     * @param descripcion   Descripción de la visita
     * @param fecha         Fecha en la que se realizo la visita
     * @param animales      Animales incluidos en la visita
     */
    public Veterinario(String titulo, String descripcion, String fecha, String animales) {
        this.id = -1;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = Date.valueOf(fecha);

        String[] t = animales.split(";");
        this.animales = new ArrayList<>();
        this.animales.addAll(Arrays.asList(t));
    }

    public long getId() {
        return id;
    }
    public String getIdString() {
        return Long.toString(id);
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFecha() {
        return fecha;
    }
    public String getFechaString() {
        return fecha.toString();
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = Date.valueOf(fecha);
    }
    public ArrayList<String> getAnimales() {
        return animales;
    }
    public String getAnimalesString(){
        StringBuilder sb = new StringBuilder();
        for(byte i = 0; i<animales.size(); i++){
            sb.append(animales.get(i));
        }
        return sb.toString();
    }
    public void setAnimales(ArrayList<String> animales) {
        this.animales = animales;
    }

    @Override
    public String toString() {
        return "Veterinario{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha.toString() +
                ", animales=" + getAnimalesString() +
                '}';
    }
}
