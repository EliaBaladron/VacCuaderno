package com.example.ClasesVO.Datos;

import java.sql.Date;

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
    public void setDiasDiferencia(int diasDiferencia) {
        this.diasDiferencia = diasDiferencia;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaDestete() {
        return fechaDestete;
    }
    public void setFechaDestete(Date fechaDestete) {
        this.fechaDestete = fechaDestete;
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
