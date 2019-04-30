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
public class Paciente {
    private int id = -1;
    private String nombre;
    private String dni;
    private String domicilio;
    private String celular;
    private double pesoActual;

    public Paciente(int id, String nombre, String dni, String domicilio, String celular, double pesoActual) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.celular = celular;
        this.pesoActual = pesoActual;
    }

    public Paciente(String nombre, String dni, String domicilio, String celular, double pesoActual) {
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.celular = celular;
        this.pesoActual = pesoActual;
    }

    public Paciente() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public double getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(double pesoActual) {
        this.pesoActual = pesoActual;
    }
    
     public String toString(){
    
        return id+"-"+nombre;
    }    
}

