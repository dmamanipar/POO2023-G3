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
import pe.edu.upeu.app.modelo.ResultadoFinalTO;
import pe.edu.upeu.app.util.ErrorLogger;

/**
 *
 * @author usuario
 */
public class ResultadoFinalDao implements ResultadoFinalDaoI {

    ConnS instance = ConnS.getInstance();

    Connection connection = instance.getConnection();
    PreparedStatement ps;
    ResultSet rs;

    ErrorLogger log = new ErrorLogger(ResultadoFinalDao.class.getName());

    @Override
    public int create(ResultadoFinalTO d) {
        int rsId = 0;
        String[] returns = {"dni"};
        String sql = "INSERT INTO ResultadoFinal(id_postulante,id_periodo,id_carrera,"
                + "Dni,punto_de_conocimiento,punto_de_entrevista,eval_psicologica ) "
                + " values(?, ?, ?, ?, ?, ?, ?);";
        int i = 0;

        try {
            ps = connection.prepareStatement(sql, returns);
            ps.setInt(++i, d.getIdPostulante());
            ps.setInt(++i, d.getIdPeriodo());
            ps.setInt(++i, d.getIdCarrera());
            ps.setString(++i, d.getDni());
            ps.setDouble(++i, d.getPuntoDeConocimiento());
            ps.setDouble(++i, d.getPuntoDeEntrevista());
            ps.setInt(++i, d.getEvalPsicologica());
            rsId = ps.executeUpdate();// 0 no o 1 si commit
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    rsId = rs.getInt(1);
                }
                rs.close();
            }
        } catch (SQLException ex) {
            log.log(Level.ALL.SEVERE, "create", ex);
        }
        return rsId;
    }

    @Override
    public int update(ResultadoFinalTO p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<ResultadoFinalTO> ListarTodo() {

        List<ResultadoFinalTO> Listar = new ArrayList<>();
        String sql = "SELECT r.*, c.nombrecarrera, p.nombre as nombreperiodo, f.nombre as nombrepostulante "
                + "from resultado_final r, carrera c, periodo p, postulante f "
                + "where r.id_carrera=c.id_carrera and id_periodo=p.id_periodo and id_postulante=f.id_postulante;";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ResultadoFinalTO e = new ResultadoFinalTO();
                e.setDni(rs.getString("DNI"));

                System.out.println("DNI: " + e.getDni());

                Listar.add(e);

            }
        } catch (Exception e) {
        }

        return Listar;
    }

    public static void main(String[] args) {
        ResultadoFinalDao dao = new ResultadoFinalDao();
        ResultadoFinalTO d = new ResultadoFinalTO();
        d.setDni("75073576");
        d.setPuntoDeConocimiento(15);
        d.setPuntoDeEntrevista(20);
        d.setPuntoDeEntrevista(20);
        d.setPuntoDeEntrevista(20);
        dao.ListarTodo(); 
        
    }

    @Override
    public List<ResultadoFinalTO> listCmb(String filter) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ResultadoFinalTO> listarTodo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResultadoFinalTO buscarEntidad(String dni) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ModeloDataAutocomplet> listAutoComplet(String filter) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ComboBoxOption> listaModalidadExamen() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
