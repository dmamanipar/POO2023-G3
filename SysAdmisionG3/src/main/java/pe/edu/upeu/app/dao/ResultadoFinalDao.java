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
import java.util.Scanner;
import java.util.logging.Level;
import pe.edu.upeu.app.conexion.ConnS;
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
        String sql = "INSERT INTO resultado_final(id_postulante,id_periodo,id_carrera, "
                + "dni,punto_conocimiento,punto_entrevista,eval_psicologica ) "
                + " values(?, ?, ?, ?, ?, ?, ?);";
        int i = 0;

        try {
            ps = connection.prepareStatement(sql, returns);
            ps.setInt(++i, d.getIdPostulante());
            ps.setInt(++i, d.getIdPeriodo());
            ps.setInt(++i, d.getIdCarrera());
            ps.setString(++i, d.getDni());
            ps.setDouble(++i, d.getPuntoConocimiento());
            ps.setDouble(++i, d.getPuntoEntrevista());
            ps.setInt(++i, d.getEvalPsicologica());
            rsId = ps.executeUpdate();// 0 no o 1 si commit
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    rsId = rs.getInt(1);
                }
                rs.close();
            }
        } catch (SQLException ex) {
            ErrorLogger.log(Level.SEVERE, "create", ex);
        }
        return rsId;
    }

    @Override
    public int update(ResultadoFinalTO d) {
        System.out.println("actualizar d.getDniruc: " + d.getDni());
        int comit = 0;
        String sql = "UPDATE resultado_final SET "
                + "nombrecarrera=?, "
                + "periodo=?, "
                + "postulante=?, "
                + "PuntoConocimiento=?, "
                + "PuntoEntrevista=?, "
                + "Evaluación Psicologica=?, "
                + "WHERE dni=?";
        int i = 0;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(++i, d.getIdPostulante());
            ps.setInt(++i, d.getIdPeriodo());
            ps.setInt(++i, d.getIdCarrera());
            ps.setString(++i, d.getDni());
            ps.setDouble(++i, d.getPuntoConocimiento());
            ps.setDouble(++i, d.getPuntoEntrevista());
            ps.setInt(++i, d.getEvalPsicologica());
            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            ErrorLogger.log(Level.SEVERE, "update", ex);
        }
        return comit;
    }

    @Override
    public int delete(String id) throws Exception {
        int comit = 0;
        String sql = "DELETE FROM resultado_final WHERE dni = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            ErrorLogger.log(Level.SEVERE, "delete", ex);
// System.err.println("NO del " + ex.toString());
            throw new Exception("Detalle:" + ex.getMessage());

        }
        return comit;
    }

    @Override
    public List<ResultadoFinalTO> listarTodo() {
        List<ResultadoFinalTO> listarEntidad = new ArrayList();
        String sql = "SELECT * from resultado_final";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ResultadoFinalTO cli = new ResultadoFinalTO();
                cli.setDni(rs.getString("dni"));
                cli.setIdCarrera(rs.getInt("id_carrera"));
                cli.setIdPeriodo(rs.getInt("id_periodo"));
                cli.setIdPostulante(rs.getInt("id_postulante"));
                cli.setPuntoConocimiento(rs.getDouble("punto_conocimiento"));
                cli.setPuntoEntrevista(rs.getDouble("punto_entrevista"));
                cli.setEvalPsicologica(rs.getInt("eval_psicologica"));
                listarEntidad.add(cli);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listarEntidad;
    }

    public static void main(String[] args) {
        ResultadoFinalDao dao = new ResultadoFinalDao();
        Scanner sc = new Scanner(System.in);
        String opc = "R";
        System.out.println("****************Bienvenido al Sistemas****************");
        String msg = "Opciones de Menú:\nC=Crear\nR=Reportar\nU=Actualizar\nD=Eliminar";
        ResultadoFinalTO d;
        do {
            switch (opc) {
                case "C" -> {
                    d = new ResultadoFinalTO();
                    System.out.println("Ingrese DNI:");
                    d.setDni(sc.next());
                    System.out.println("Ingrese Carrera (1=Sistemas, 2=Contabilidad):");
                    d.setIdCarrera(sc.nextInt());
                    System.out.println("Ingrese Periodo: ");
                    d.setIdPeriodo(sc.nextInt());
                    System.out.println("Ingrese codigo postulante:");
                    d.setIdPostulante(sc.nextInt());
                    System.out.println("Ingrese el punto de conocimiento:");
                    d.setPuntoConocimiento(sc.nextDouble());
                    System.out.println("Ingrese el punto de la entrevista:");
                    d.setPuntoEntrevista(sc.nextDouble());
                    System.out.println("Ingrese el puntaje de Evaluación Psicologica:");
                    d.setEvalPsicologica(sc.nextInt());
                    dao.create(d);
                    dao.ListarResultadoFinal(dao.listarTodo());
                }
                case "R" -> {
                    dao.ListarResultadoFinal(dao.listarTodo());
                }
                case "U" -> {
                    System.out.println("Ingrese el DNI del registro que desea modificar:");
                    String dni = sc.next();
                    d = dao.buscarEntidad(dni);
                    System.out.println("Ingrese la nueva carrera: ");
                    d.setIdCarrera(sc.nextInt());
                    System.out.println("Ingrese el codigo al que ddesea ccambia: ");
                    d.setIdPostulante(sc.nextInt());
                    dao.update(d);
                    dao.ListarResultadoFinal(dao.listarTodo());
                }
                case "D" -> {
                    System.out.println("Ingrese el DNI que desea Eliminar:");
                    try {
                        dao.delete(sc.next());
                    } catch (Exception e) {
                    }
                    dao.ListarResultadoFinal(dao.listarTodo());
                }
                default -> {
                    System.out.println("Opción no valida intente otra vez!");
                }
            }
            System.out.println("Que desea hacer?\n" + msg);
            opc = sc.next();
        } while (!opc.toUpperCase().equals("X"));
    }

    @Override
    public List<ResultadoFinalTO> listCmb(String filter) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResultadoFinalTO buscarEntidad(String dni) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void ListarResultadoFinal(List<ResultadoFinalTO> listarTodo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
