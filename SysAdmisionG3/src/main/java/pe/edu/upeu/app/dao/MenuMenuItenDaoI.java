/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.app.dao;

import java.util.List;
import java.util.Properties;
import pe.edu.upeu.app.modelo.MenuMenuItenTO;

/**
 *
 * @author Data_Science
 */
public interface MenuMenuItenDaoI {
   public List<MenuMenuItenTO> listaAccesos(String perfil, Properties idioma);
 
}
