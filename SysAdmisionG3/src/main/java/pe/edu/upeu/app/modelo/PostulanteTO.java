/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.modelo;

import lombok.Data;

/**
 *
 * @author Data_Science
 */
@Data
public class PostulanteTO {
    public int idPostulante, idCarrera, idPeriodo;
    public String dni, nombre, apellidoPat, apellidoMat, modalidad, estado;
    //no data base
    public String nombreModalidad, nombrePeriodo, nombreCarrera;

}
