/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.app.dao;

import java.util.List;
import pe.com.syscenterlife.autocomp.ModeloDataAutocomplet;
import pe.edu.upeu.app.modelo.ComboBoxOption;
import pe.edu.upeu.app.modelo.UsuarioTO;


public interface UsuarioDaoI {

    
public int create(UsuarioTO d);
public int update(UsuarioTO d);
public int delete(int id) throws Exception;
public List<UsuarioTO> listCmb(String filter);
public List<UsuarioTO> listarTodo();
public UsuarioTO buscarEntidad(int user);
public List<ModeloDataAutocomplet> listAutoComplet(String filter); 
public List<ComboBoxOption> listaModalidadExamen();
public List<ComboBoxOption> listarPeriodo();
public List<ModeloDataAutocomplet> listAutoCompletCarrera(String filter);
public String buscarModalidadExamen(String id);
}
    