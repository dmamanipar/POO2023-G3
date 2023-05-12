/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.modelo;

import lombok.Data;

/**
 *
 * @author Alex
 */
@Data
public class AreaPeriodoTO {

    private int idAreaPeriodo;
    private int idArea;
    private int idPeriodo;

    public AreaPeriodoTO(int idAreaPeriodo, int idArea, int idPeriodo) {
        this.idAreaPeriodo = idAreaPeriodo;
        this.idArea = idArea;
        this.idPeriodo = idPeriodo;
    }

    public int getIdAreaPeriodo() {
        return idAreaPeriodo;
    }

    public void setIdAreaPeriodo(int idAreaPeriodo) {
        this.idAreaPeriodo = idAreaPeriodo;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }
}
