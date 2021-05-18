package com.example.ClasesVO.CompraVenta;

import java.io.Serializable;

public class Compra extends CompraVenta implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor vacío. Utilizado para añadir compras con datos por defecto
     */
    public Compra() {
        super();
    }
    /*public Compra(String crotal, Double precio, Date fecha) {
        super(crotal, precio, fecha);
    }*/

    /**
     * Constructor utilizado en la obtención de datos de la Base de Datos
     * @param id        ID del objeto en la BD
     * @param crotal    Crotal o número identificativo oficial
     * @param precio    Precio de la compra
     * @param fecha     Fecha de la compra
     */
    public Compra(long id, String crotal, Double precio, String fecha) {
        super(id, crotal, precio, fecha);
    }
    /**
     * Constructor utilizado para la creación de objetos que no están en la BD, por lo que no tienen un ID
     * @param crotal    Crotal o número identificativo oficial
     * @param precio    Precio de la compra
     * @param fecha     Fecha de la compra
     */
    public Compra(String crotal, Double precio, String fecha) {
        super(crotal, precio, fecha);
    }
    /*public Compra(long id, String crotal, Double precio, Date fecha) {
        super(id, crotal, precio, fecha);
    }*/
}
