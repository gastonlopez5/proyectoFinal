/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nutricionista2;

import java.util.List;

/**
 *
 * @author gaston
 */
public class DietaComida {
    private int id = -1;
    private int idDieta;
    private int idComida;

    public DietaComida(int id, int idDieta, int idComida) {
        this.id = id;
        this.idDieta = idDieta;
        this.idComida = idComida;
    }

    public DietaComida(int idDieta, int idComida) {
        this.idDieta = idDieta;
        this.idComida = idComida;
    }

    public DietaComida() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdDieta() {
        return idDieta;
    }

    public void setIdDieta(int idDieta) {
        this.idDieta = idDieta;
    }

    public int getIdComida() {
        return idComida;
    }

    public void setIdComida(int idComida) {
        this.idComida = idComida;
    }

    
}
