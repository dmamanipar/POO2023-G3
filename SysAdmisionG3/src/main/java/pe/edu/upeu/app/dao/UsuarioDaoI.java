/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.app.dao;

import java.util.List;
;
import pe.edu.upeu.app.modelo.UsuarioTO;

/**
 *
 * @author Lenovo
 */


public interface UsuarioDaoI {

    public int crearUsuario(UsuarioTO d);

    public int actualizarUsuario(UsuarioTO d);

    public int eliminarUsuario(String id) throws Exception;

    public List<UsuarioTO> listarUsuarios(String filter);
    public UsuarioTO buscarEntidad(String dni);

}
