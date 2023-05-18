/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.modelo;

import lombok.Data;

/**
 *
 * @author usuario
 */
@Data
public class ResultadoFinalTO {

    public int idResultFinal, idPostulante, idPeriodo, idCarrera;
    public String dni;
    public double puntoConocimiento, puntoEntrevista;
    public int evalPsicologica;
    //no data base
    public String  nombreCarrera,nombrePeriodo,nombrePostulante ;
}
