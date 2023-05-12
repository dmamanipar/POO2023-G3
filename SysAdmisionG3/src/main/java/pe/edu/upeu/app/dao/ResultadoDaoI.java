/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.app.dao;

/**
 *
 * @author Alex
 */
public interface ResultadoDaoI {

    int getIdResultado();

    int getIdPregunta();

    int getIdPostulante();

    String getRespuesta();

    double getPunto();

    int getIdAreaPeriodo();
}
