/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.app.dao;

import java.util.List;
import pe.com.syscenterlife.autocomp.ModeloDataAutocomplet;
import pe.edu.upeu.app.modelo.ComboBoxOption;
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

    public List<ModeloDataAutocomplet> listAutoComplet(String filter);

    public List<ComboBoxOption> listaModalidadExamen();

    public List<ComboBoxOption> listarPeriodo();

    public List<ModeloDataAutocomplet> listAutoCompletCarrera(String filter);

    public String buscarModalidadExamen(String id);

}
