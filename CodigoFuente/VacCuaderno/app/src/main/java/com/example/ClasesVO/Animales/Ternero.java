package com.example.ClasesVO.Animales;

import java.io.Serializable;
import java.sql.Date;

public class Ternero implements Serializable {

    private static final long serialVersionUID = 1L;

    long id;
    String crotal;
    Date fechaDestete;
    String proposito;

    Animal animal;

    /**
     * Constructor vacío. Utilizado para añadir terneros con datos por defecto
     */
    public Ternero(){
        this.crotal = "ES 4321 4321 4321";
        this.fechaDestete = new Date(System.currentTimeMillis());
        this.proposito = "proposito";

        animal = new Animal();
    }

    /**
     * Constructor utilizado en la obtención de datos de la Base de Datos
     * @param id            ID del objeto en la BD
     * @param crotal        Crotal o número identificativo oficial
     * @param fechaDestete  Fecha de destete
     * @param proposito     Propósito del ternero
     */
    public Ternero(long id, String crotal, String fechaDestete, String proposito) {
        this.id = id;
        this.crotal = crotal;
        if(fechaDestete.equals(""))
            this.fechaDestete = null;
        else
            this.fechaDestete = Date.valueOf(fechaDestete);
        this.proposito = proposito;
    }
    /*public Ternero(long id, String crotal, Date fechaDestete, String proposito) {
        this.id = id;
        this.crotal = crotal;
        this.fechaDestete = fechaDestete;
        this.proposito = proposito;
    }*/

    /**
     * Constructor utilizado para la creación de objetos que no están en la BD, por lo que no tienen un ID
     * @param crotal        Crotal o número identificativo oficial
     * @param fechaDestete  Fecha de destete
     * @param proposito     Propósito del ternero
     */
    public Ternero(String crotal, String fechaDestete, String proposito) {
        this.crotal = crotal;
        this.fechaDestete = Date.valueOf(fechaDestete);
        this.proposito = proposito;
    }
    /*public Ternero(String crotal, Date fechaDestete, String proposito) {
        this.crotal = crotal;
        this.fechaDestete = fechaDestete;
        this.proposito = proposito;
    }*/

    //Getters
    public long getId() {
        return id;
    }
    public String getIdString() {
        return Long.toString(id);
    }
    public String getCrotal() {
        return crotal;
    }
    public Date getFechaDestete() {
        return fechaDestete;
    }
    public String getProposito() {
        return proposito;
    }
    public Animal getAnimal() {
        return animal;
    }


    //Setters
    public void setId(long id) {
        this.id = id;
    }
    public void setCrotal(String crotal) {
        this.crotal = crotal;
    }
    /*public void setFechaDestete(Date fechaDestete) {
        this.fechaDestete = fechaDestete;
    }*/
    public void setFechaDestete(String fechaDestete) {
        if(fechaDestete.equals(""))
            this.fechaDestete = null;
        else
            this.fechaDestete = Date.valueOf(fechaDestete);
    }
    public void setProposito(String proposito) {
        this.proposito = proposito;
    }
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "Ternero{" +
                "id=" + id +
                ", crotal='" + crotal + '\'' +
                ", fechaDestete=" + fechaDestete +
                ", proposito='" + proposito + '\'' +
                ", animal=" + animal +
                '}';
    }
}
