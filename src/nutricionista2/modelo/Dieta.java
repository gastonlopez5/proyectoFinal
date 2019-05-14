/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista2.modelo;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author gaston
 */
public class Dieta {
    private int id = -1;
    private Paciente paciente;
    private List<Comida> comidas;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private double pesoInicial;
    private double pesoBuscado;

    public Dieta(int id, Paciente paciente, LocalDate fechaInicial, LocalDate fechaFinal, double pesoInicial, double pesoBuscado, List<Comida> comidas) {
        this.id = id;
        this.paciente = paciente;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.pesoInicial = pesoInicial;
        this.pesoBuscado = pesoBuscado;
        this.comidas = comidas;
    }

    public Dieta(Paciente paciente, LocalDate fechaInicial, LocalDate fechaFinal, double pesoInicial, double pesoBuscado, List<Comida> comidas) {
        this.paciente = paciente;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.pesoInicial = pesoInicial;
        this.pesoBuscado = pesoBuscado;
        this.comidas = comidas;
    }

    public Dieta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public double getPesoInicial() {
        return pesoInicial;
    }

    public void setPesoInicial(double pesoInicial) {
        this.pesoInicial = pesoInicial;
    }

    public double getPesoBuscado() {
        return pesoBuscado;
    }

    public void setPesoBuscado(double pesoBuscado) {
        this.pesoBuscado = pesoBuscado;
    }

    public List<Comida> getComidas() {
        return comidas;
    }

    public void setComidas(List<Comida> comidas) {
        this.comidas = comidas;
    }
    
    
}
