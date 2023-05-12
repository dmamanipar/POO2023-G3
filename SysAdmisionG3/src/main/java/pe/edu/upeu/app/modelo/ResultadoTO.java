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
public class ResultadoTO {

    private int idResultado;
    private int idPregunta;
    private int idPostulante;
    private int punto;
    private int idAreaPeriodo;
    private String respuesta;

    // Constructor
    public ResultadoTO(int idResultado, int idPregunta, int idPostulante, int punto, int idAreaPeriodo, String respuesta) {
        this.idResultado = idResultado;
        this.idPregunta = idPregunta;
        this.idPostulante = idPostulante;
        this.punto = punto;
        this.idAreaPeriodo = idAreaPeriodo;
        this.respuesta = respuesta;
    }

    // Getters y Setters
    public int getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public int getIdPostulante() {
        return idPostulante;
    }

    public void setIdPostulante(int idPostulante) {
        this.idPostulante = idPostulante;
    }

    public int getPunto() {
        return punto;
    }

    public void setPunto(int punto) {
        this.punto = punto;
    }

    public int getIdAreaPeriodo() {
        return idAreaPeriodo;
    }

    public void setIdAreaPeriodo(int idAreaPeriodo) {
        this.idAreaPeriodo = idAreaPeriodo;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
}
