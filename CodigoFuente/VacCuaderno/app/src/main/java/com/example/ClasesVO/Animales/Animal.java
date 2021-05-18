package com.example.ClasesVO.Animales;

import java.io.Serializable;
import java.sql.Date;

public class Animal implements Serializable {

    private static final long serialVersionUID = 1L;

    long id;
    String nombre;
    String crotal;
    Date fechaNac;
    String sexo;
    String raza;
    String codMadre;
    String idReb;

    /**
     * Constructor vacío. Utilizado para añadir animales con datos por defecto
     */
    public Animal() {
        this.nombre = "nombre";
        this.crotal = "ES 4321 4321 4321";
        this.fechaNac = new Date(System.currentTimeMillis());
        this.sexo = "H";
        this.raza = "raza";
        this.codMadre = "ES 1234 1234 1234";
        this.idReb = "0";
    }
    /*public Animal(long id, String nombre, String crotal, Date fechaNac, String sexo, String raza, String codMadre, String idReb) {
        this.id = id;
        this.nombre = nombre;
        this.crotal = crotal;
        this.fechaNac = fechaNac;
        this.sexo = sexo;
        this.raza = raza;
        this.codMadre = codMadre;
        this.idReb = idReb;
    }*/

    /**
     * Constructor utilizado en la obtención de datos de la Base de Datos
     * @param id        ID del objeto en la BD
     * @param nombre    Nombre del animal
     * @param crotal    Crotal o número identificativo oficial
     * @param fechaNac  Fecha de nacimiento
     * @param sexo      Sexo del animal, indicado con H o M
     * @param raza      Raza del animal
     * @param codMadre  Crotal identificativo de la madre
     * @param idReb     ID del rebaño al que pertenece
     */
    public Animal(long id, String nombre, String crotal, String fechaNac, String sexo, String raza, String codMadre, String idReb) {
        this.id = id;
        this.nombre = nombre;
        this.crotal = crotal;
        this.fechaNac = Date.valueOf(fechaNac);
        this.sexo = sexo;
        this.raza = raza;
        this.codMadre = codMadre;
        this.idReb = idReb;
    }
    /*public Animal(String nombre, String crotal, Date fechaNac, String sexo, String raza, String codMadre, String idReb) {
        this.nombre = nombre;
        this.crotal = crotal;
        this.fechaNac = fechaNac;
        this.sexo = sexo;
        this.raza = raza;
        this.codMadre = codMadre;
        this.idReb = idReb;
    }*/

    /**
     * Constructor utilizado para la creación de objetos que no están en la BD, por lo que no tienen un ID
     * @param nombre    Nombre del animal
     * @param crotal    Crotal o número identificativo oficial
     * @param fechaNac  Fecha de nacimiento
     * @param sexo      Sexo del animal, indicado con H o M
     * @param raza      Raza del animal
     * @param codMadre  Crotal identificativo de la madre
     * @param idReb     ID del rebaño al que pertenece
     */
    public Animal(String nombre, String crotal, String fechaNac, String sexo, String raza, String codMadre, String idReb) {
        this.nombre = nombre;
        this.crotal = crotal;
        this.fechaNac = Date.valueOf(fechaNac);
        this.sexo = sexo;
        this.raza = raza;
        this.codMadre = codMadre;
        this.idReb = idReb;
    }

    //Getters
    public long getId() {
        return id;
    }
    public String getIdString() {
        return Long.toString(id);
    }
    public String getNombre(){
        return  nombre;
    }
    public String getCrotal() {
        return crotal;
    }
    /*public Date getFechaNac() {
        return fechaNac;
    }*/
    public String getFechaNacString() {
        return fechaNac.toString();
    }
    public String getSexo() {
        return sexo;
    }
    public String getRaza() {
        return raza;
    }
    public String getCodMadre() {
        return codMadre;
    }
    public String getIdReb() {
        return idReb;
    }

    //Setters
    public void setId(long id) {
        this.id = id;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setCrotal(String crotal) {
        this.crotal = crotal;
    }
    /*public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }*/
    public void setFechaNac(String fechaNac) {
        this.fechaNac = Date.valueOf(fechaNac);
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public void setRaza(String raza) {
        this.raza = raza;
    }
    public void setCodMadre(String codMadre) {
        this.codMadre = codMadre;
    }
    public void setIdReb(String idReb) {
        this.idReb = idReb;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", crotal='" + crotal + '\'' +
                ", fechaNac=" + fechaNac +
                ", sexo='" + sexo + '\'' +
                ", raza='" + raza + '\'' +
                ", codMadre='" + codMadre + '\'' +
                ", idReb='" + idReb + '\'' +
                '}';
    }
}
