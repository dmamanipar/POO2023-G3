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
public class AreaPeriodoResultTO {

    private int idAreaPeriodoResult;
    private int idAreaPeriodo;
    private int idAreaExamen;
    private double porcentaje;

    // Constructor
    public AreaPeriodoResultTO(int idAreaPeriodoResult, int idAreaPeriodo, int idAreaExamen, double porcentaje) {
        this.idAreaPeriodoResult = idAreaPeriodoResult;
        this.idAreaPeriodo = idAreaPeriodo;
        this.idAreaExamen = idAreaExamen;
        this.porcentaje = porcentaje;
    }

    // Getters y Setters
    public int getIdAreaPeriodoResult() {
        return idAreaPeriodoResult;
    }

    public void setIdAreaPeriodoResult(int idAreaPeriodoResult) {
        this.idAreaPeriodoResult = idAreaPeriodoResult;
    }

    public int getIdAreaPeriodo() {
        return idAreaPeriodo;
    }

    public void setIdAreaPeriodo(int idAreaPeriodo) {
        this.idAreaPeriodo = idAreaPeriodo;
    }

    public int getIdAreaExamen() {
        return idAreaExamen;
    }

    public void setIdAreaExamen(int idAreaExamen) {
        this.idAreaExamen = idAreaExamen;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
}
