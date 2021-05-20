package com.example.ClasesVO.Datos;

import java.sql.Date;

/**
 * @author Elia Baladrón Peral
 */
public class Destete {

    private String crotal;
    private int diasDiferencia;
    private Date fechaNacimiento;
    private Date fechaDestete;

    
    public Destete(String crotal, int diasDiferencia, Date fechaNacimiento, Date fechaDestete) {
        this.crotal = crotal;
        this.diasDiferencia = diasDiferencia;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaDestete = fechaDestete;
    }

    public String getCrotal() {
        return crotal;
    }
    public void setCrotal(String crotal) {
        this.crotal = crotal;
    }

    public int getDiasDiferencia() {
        return diasDiferencia;
    }

    public String getFechaNacimientoString() {
        return fechaNacimiento.toString();
    }

    public String getFechaDesteteStrng() {
        return fechaDestete.toString();
    }

    @Override
    public String toString() {
        return "Destete{" +
                "crotal='" + crotal + '\'' +
                ", diasDiferencia=" + diasDiferencia +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaDestete=" + fechaDestete +
                '}';
    }
}
