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
public class PreguntasTO {

    private int idPregunta;
    private int idBp;
    private int idAreaPeriodo;
    private int numero;
    private String resultado;

    // Constructor
    public PreguntasTO(int idPregunta, int idBp, int idAreaPeriodo, int numero, String resultado) {
        this.idPregunta = idPregunta;
        this.idBp = idBp;
        this.idAreaPeriodo = idAreaPeriodo;
        this.numero = numero;
        this.resultado = resultado;
    }

    // Getters y Setters
    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public int getIdBp() {
        return idBp;
    }

}
