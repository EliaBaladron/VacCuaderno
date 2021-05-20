package com.example.ClasesVO.Animales;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Elia Baladrón Peral
 */
public class Vaca implements Serializable {

    private static final long serialVersionUID = 1L;

    long id;
    String crotal;
    Date fechaEmbarazo;
    ArrayList<String> terneros;

    Animal animal;

    /**
     * Constructor vacío. Utilizado para añadir vacas con datos por defecto
     */
    public Vaca() {
        this.crotal = "ES 4321 4321 4321";
        this.fechaEmbarazo = new Date(System.currentTimeMillis());
        this.terneros = new ArrayList<>();

        animal = new Animal();
    }

    /**
     * Constructor utilizado en la obtención de datos de la Base de Datos
     * @param id                ID del objeto en la BD
     * @param crotal            Crotal o número identificativo oficial
     * @param fechaEmbarazo     Última fecha de embarazo registrada
     * @param terneros          Listado de los terneros
     */
    public Vaca(long id, String crotal, String fechaEmbarazo, String terneros) {
        this.id = id;
        this.crotal = crotal;
        this.fechaEmbarazo = Date.valueOf(fechaEmbarazo);

        String[] t = terneros.split(";");
        this.terneros = new ArrayList<>();
        Collections.addAll(this.terneros, t);
    }

    /**
     * Constructor utilizado para la creación de objetos que no están en la BD, por lo que no tienen un ID
     * @param crotal            Crotal o número identificativo oficial
     * @param fechaEmbarazo     Última fecha de embarazo registrada
     * @param terneros          Listado de los terneros
     */
    public Vaca(String crotal, String fechaEmbarazo, ArrayList<String> terneros) {
        this.crotal = crotal;
        this.fechaEmbarazo = Date.valueOf(fechaEmbarazo);
        this.terneros = terneros;
    }

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
    public String getFechaEmbarazoString() {
        return fechaEmbarazo.toString();
    }
    public ArrayList<String> getTerneros() {
        return terneros;
    }
    public String getTernerosString(){
        StringBuilder sb = new StringBuilder();
        for(byte i = 0; i<terneros.size(); i++){
            sb.append(terneros.get(i));
        }
        return sb.toString();
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
    public void setFechaEmbarazo(String fechaEmbarazo){
        this.fechaEmbarazo = Date.valueOf(fechaEmbarazo);
    }
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "Vaca{" +
                "id=" + id +
                ", crotal='" + crotal + '\'' +
                ", fechaEmbarazo=" + fechaEmbarazo +
                ", terneros=" + terneros +
                ", animal=" + animal +
                '}';
    }
}
