/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.app.dao;

import java.util.List;
import pe.edu.upeu.app.modelo.PostulanteTO;

/**
 *
 * @author Data_Science
 */
public interface PostulanteDaoI {
    
    public int create(PostulanteTO p);
    
    public int update(PostulanteTO p);
    
    public int delete(String id) throws Exception;
    
    public List<PostulanteTO> listarTodo();
    
}
