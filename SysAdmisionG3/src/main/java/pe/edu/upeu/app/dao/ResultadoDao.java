/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.dao;

/**
 *
 * @author Alex
 */
public class ResultadoDao {

    private int idResultado;
    private int idPregunta;
    private int idPostulante;
    private String respuesta;
    private int punto;
    private int idAreaPeriodo;

    // Constructor
    public ResultadoDao(int idResultado, int idPregunta, int idPostulante, String respuesta, int punto, int idAreaPeriodo) {
        this.idResultado = idResultado;
        this.idPregunta = idPregunta;
        this.idPostulante = idPostulante;
        this.respuesta = respuesta;
        this.punto = punto;
        this.idAreaPeriodo = idAreaPeriodo;
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

    public String getRespuesta() {
        return respuesta;
    }
}
