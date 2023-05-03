/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import pe.com.syscenterlife.autocomp.ModeloDataAutocomplet;
import pe.edu.upeu.app.conexion.ConnS;
import pe.edu.upeu.app.modelo.ComboBoxOption;
import pe.edu.upeu.app.modelo.PostulanteTO;
import pe.edu.upeu.app.util.ErrorLogger;

/**
 *
 * @author Data_Science
 */
public class PostulanteDao implements PostulanteDaoI {

    ConnS instance = ConnS.getInstance();
    Connection connection = instance.getConnection();
    PreparedStatement ps;
    ResultSet rs;

    ErrorLogger log = new ErrorLogger(PostulanteDao.class.getName());

    @Override
    public int create(PostulanteTO d) {
        int rsId = 0;
        String[] returns = {"dni"};
        String sql = "INSERT INTO postulante(id_carrera, id_periodo, dni, "
                + "nombre, apellido_pat, apellido_mat, modalidad, estado) "
                + " values(?, ?, ?, ?, ?, ?, ?, ?);";
        int i = 0;
        try {
            ps = connection.prepareStatement(sql, returns);
            ps.setInt(++i, d.getIdCarrera());
            ps.setInt(++i, d.getIdPeriodo());
            ps.setString(++i, d.getDni());
            ps.setString(++i, d.getNombre());
            ps.setString(++i, d.getApellidoPat());
            ps.setString(++i, d.getApellidoMat());
            ps.setString(++i, d.getModalidad());
            ps.setString(++i, d.getEstado());
            rsId = ps.executeUpdate();// 0 no o 1 si commit
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    rsId = rs.getInt(1);
                }
                rs.close();
            }
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "create", ex);
        }
        return rsId;
    }

    @Override
    public int update(PostulanteTO d) {
        System.out.println("actualizar d.getDniruc: " + d.getDni());
        int comit = 0;
        String sql = "UPDATE postulante SET "
                + "nombre=?, "
                + "apellido_pat=?, "
                + "apellido_mat=?, "
                + "modalidad=?, "
                + "estado=?, "
                + "id_periodo=?, "
                + "id_carrera=? "
                + "WHERE dni=?";
        int i = 0;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(++i, d.getNombre());
            ps.setString(++i, d.getApellidoPat());
            ps.setString(++i, d.getApellidoMat());
            ps.setString(++i, d.getModalidad());
            ps.setString(++i, d.getEstado());
            ps.setInt(++i, d.getIdPeriodo());
            ps.setInt(++i, d.getIdCarrera());
            ps.setString(++i, d.getDni());
            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "update", ex);
        }
        return comit;
    }

    @Override
    public int delete(String id) throws Exception {
        int comit = 0;
        String sql = "DELETE FROM postulante WHERE dni = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "delete", ex);
            // System.err.println("NO del " + ex.toString());
            throw new Exception("Detalle:" + ex.getMessage());
        }
        return comit;
    }

    @Override
    public List<PostulanteTO> listarTodo() {
        List<PostulanteTO> listarEntidad = new ArrayList();
        String sql = "SELECT po.*, p.nombre as nombreperiodo, c.nombrecarrera "
                + "FROM postulante po, periodo p, carrera c "
                + "WHERE p.id_periodo = po.id_periodo and po.id_carrera = c.id_carrera";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                PostulanteTO cli = new PostulanteTO();
                cli.setDni(rs.getString("dni"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApellidoPat(rs.getString("apellido_pat"));
                cli.setApellidoMat(rs.getString("apellido_mat"));
                cli.setModalidad(rs.getString("modalidad"));
                cli.setEstado(rs.getString("estado"));
                cli.setIdCarrera(rs.getInt("id_carrera"));
                cli.setIdPeriodo(rs.getInt("id_periodo"));
                cli.setNombrePeriodo(rs.getString("nombreperiodo"));
                cli.setNombreCarrera(rs.getString("nombrecarrera"));
                cli.setNombreModalidad(buscarModalidadExamen(rs.getString("modalidad")));
                listarEntidad.add(cli);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listarEntidad;
    }

    public static void main(String[] args) {
        PostulanteDao dao = new PostulanteDao();

        PostulanteTO d = new PostulanteTO();
        d.setDni("01436319");
        d.setNombre("David Dario");
        d.setApellidoPat("Mamani");
        d.setApellidoMat("Mendoza");
        d.setModalidad("Examen");
        d.setEstado("Activo");
        d.setIdCarrera(1);
        d.setIdPeriodo(1);

        //dao.create(d);
        //dao.update(d);
        try {
            dao.delete("01436319");
        } catch (Exception e) {
        }

        System.out.println("DNI\t Nombre\t A.Paterno");
        for (PostulanteTO po : dao.listarTodo()) {
            System.out.println(po.getDni() + "\t" + po.getNombre() + "\t" + po.getApellidoPat());
        }

        int i = 0;
        System.out.println("i=" + (++i));
        //id_carrera, id_periodo

    }

    @Override
    public List<PostulanteTO> listCmb(String filter) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PostulanteTO buscarEntidad(String dni) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ModeloDataAutocomplet> listAutoComplet(String filter) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ComboBoxOption> listaModalidadExamen() {
        List<ComboBoxOption> dd = new ArrayList<>();
        dd.add(new ComboBoxOption("EG", "Examen General"));
        dd.add(new ComboBoxOption("PP", "Primeros Puesto"));
        dd.add(new ComboBoxOption("PR", "Profesionales"));
        dd.add(new ComboBoxOption("ES", "Especial"));
        return dd;
    }

    @Override
    public List<ComboBoxOption> listarPeriodo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ModeloDataAutocomplet> listAutoCompletCarrera(String filter) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String buscarModalidadExamen(String id) {
        List<ComboBoxOption> dd = listaModalidadExamen();
        String nombre = "";
        for (ComboBoxOption comboBoxOption : dd) {
            if (comboBoxOption.getKey().equals(id)) {
                nombre = comboBoxOption.getValue();
            }
        }
        return nombre;
    }

}
