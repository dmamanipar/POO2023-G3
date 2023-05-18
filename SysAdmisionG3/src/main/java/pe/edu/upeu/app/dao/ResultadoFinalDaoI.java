/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.app.dao;

import java.util.List;
import pe.edu.upeu.app.modelo.ResultadoFinalTO;

/**
 *
 * @author usuario
 */
public interface ResultadoFinalDaoI {

    public int create(ResultadoFinalTO p);

    public int update(ResultadoFinalTO p);

    public int delete(String id) throws Exception;

    public List<ResultadoFinalTO> listCmb(String filter);

    public List<ResultadoFinalTO> listarTodo();

    public ResultadoFinalTO buscarEntidad(String dni);
    
    public static class ListarResultadoFinal {

        public ListarResultadoFinal() {
        }
    }
}
