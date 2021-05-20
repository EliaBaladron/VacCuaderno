package com.example.ClasesVO.CompraVenta;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author Elia Baladrón Peral
 */
public class Venta extends CompraVenta implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Constructor vacío. Utilizado para añadir ventas con datos por defecto
     */
    public Venta(){
        super();
    }


    /**
     * Constructor utilizado para la creación de objetos que no están en la BD, por lo que no tienen un ID
     * @param crotal    Crotal o número identificativo oficial del animal vendido
     * @param precio    Precio de la venta
     * @param fecha     Fecha de la venta
     */
    public Venta(String crotal, Double precio, String fecha) {
        super(crotal, precio, fecha);
    }


    /**
     * Constructor utilizado en la obtención de datos de la Base de Datos
     * @param id        ID del objeto en la BD
     * @param crotal    Crotal o número identificativo oficial del animal vendido
     * @param precio    Precio de la venta
     * @param fecha     Fecha de la venta
     */
    public Venta(long id, String crotal, Double precio, String fecha) {
        super(id, crotal, precio, fecha);
    }


}
