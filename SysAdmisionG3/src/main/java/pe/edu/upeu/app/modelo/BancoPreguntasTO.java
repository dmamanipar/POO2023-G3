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
public class BancoPreguntasTO {

    private int idBp;
    private int idArea;
    private String pregunta;

    public BancoPreguntasTO(int idBp, int idArea, String pregunta) {
        this.idBp = idBp;
        this.idArea = idArea;
        this.pregunta = pregunta;
    }

    public int getIdBp() {
        return idBp;
    }

    public void setIdBp(int idBp) {
        this.idBp = idBp;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
}
