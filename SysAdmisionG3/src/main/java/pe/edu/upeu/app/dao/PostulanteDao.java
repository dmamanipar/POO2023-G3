/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.edu.upeu.app.conexion.ConnS;
import pe.edu.upeu.app.modelo.PostulanteTO;
import pe.edu.upeu.app.util.ErrorLogger;

/**
 *
 * @author Data_Science
 */
public class PostulanteDao implements PostulanteDaoI{

    ConnS instance=ConnS.getInstance();
    Connection connection=instance.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    
    ErrorLogger log=new ErrorLogger(PostulanteDao.class.getName());
    
    @Override
    public int create(PostulanteTO p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(PostulanteTO p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<PostulanteTO> listarTodo() {
        List<PostulanteTO> listar=new ArrayList<>();
        String sql=" SELECT p.*, x.nombre as nombreperiodo, c.nombrecarrera "
                + "from postulante p, periodo x, carrera c "
                + "where p.id_periodo=x.id_periodo and p.id_carrera=c.id_carrera; ";        
        try {
            ps=connection.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                PostulanteTO e=new PostulanteTO();
                e.setDni(rs.getString("dni"));
                System.out.println("DNI:"+e.getDni());
                listar.add(e);
            }
        } catch (Exception e) {
        }
        
        return listar;
    }
    
    public static void main(String[] args) {
        PostulanteDao dao=new PostulanteDao();
        dao.listarTodo();        
    }
    
    
}
