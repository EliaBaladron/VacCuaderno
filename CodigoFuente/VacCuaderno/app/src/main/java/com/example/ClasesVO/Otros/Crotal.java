package com.example.ClasesVO.Otros;

import java.io.Serializable;

/**
 * @author Elia Baladrón Peral
 */
public class Crotal implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String crotal;
    private int unidades;

    /**
     * Constructor vacío. Utilizado para añadir crotales con datos por defecto
     */
    public Crotal() {
        this.crotal = "ES 4321 4321 4321";
        this.unidades = 2;
    }

    /**
     * Constructor utilizado en la obtención de datos de la Base de Datos
     * @param id        ID del objeto en la BD
     * @param crotal    Número del crotal
     * @param unidades  Unidades del crotal al que se hace referencia
     */
    public Crotal(long id, String crotal, int unidades) {
        this.id = id;
        this.crotal = crotal;
        this.unidades = unidades;
    }
    /**
     * Constructor utilizado para la creación de objetos que no están en la BD, por lo que no tienen un ID
     * @param crotal    Número del crotal
     * @param unidades  Unidades del crotal al que se hace referencia
     */
    public Crotal(String crotal, int unidades) {
        this.crotal = crotal;
        this.unidades = unidades;
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
    public int getUnidades() {
        return unidades;
    }
    public String getUnidadesString() {
        return Integer.toString(unidades);
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setCrotal(String crotal) {
        this.crotal = crotal;
    }
    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }
    public void setUnidades(String unidades) {
        this.unidades = Integer.parseInt(unidades);
    }

    @Override
    public String toString() {
        return "Crotal{" +
                "id=" + id +
                ", crotal='" + crotal + '\'' +
                ", unidades=" + unidades +
                '}';
    }
}
