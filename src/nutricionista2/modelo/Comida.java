/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista2.modelo;

/**
 *
 * @author gaston
 */
public class Comida {
    private int id = -1;
    private String nombre;
    private String detalle;
    private double calorias;

    public Comida(int id, String nombre, String detalle, double calorias) {
        this.id = id;
        this.nombre = nombre;
        this.detalle = detalle;
        this.calorias = calorias;
    }

    public Comida(String nombre, String detalle, double calorias) {
        this.nombre = nombre;
        this.detalle = detalle;
        this.calorias = calorias;
    }

    public Comida() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public double getCalorias() {
        return calorias;
    }

    public void setCalorias(double calorias) {
        this.calorias = calorias;
    }
    
}
