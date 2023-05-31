/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.dao;

import pe.edu.upeu.app.modelo.PeriodoTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import pe.edu.upeu.app.conexion.ConnS;
import pe.edu.upeu.app.util.ErrorLogger;

public class PeriodoDao implements PeriodoDaoI {

    ConnS instance = ConnS.getInstance();
    Connection connection = instance.getConnection();
    PreparedStatement ps;
    ResultSet rs;

    ErrorLogger log = new ErrorLogger(PeriodoDao.class.getName());

    @Override
    public int create(PeriodoTO periodo) {
        int result = 0;
        String query = "INSERT INTO periodo (nombre) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, periodo.getNombre());
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al crear el periodo: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int update(PeriodoTO periodo) {
        int result = 0;
        String query = "UPDATE periodo SET nombre = ? WHERE id_periodo = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, periodo.getNombre());
            pstmt.setInt(2, periodo.idPeriodo);
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al actualizar el periodo: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int delete(int idPeriodo) {
        int result = 0;
        String query = "DELETE FROM periodo WHERE id_periodo = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, idPeriodo);
            result = pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al eliminar el periodo: " + e.getMessage());
        }
        return result;
    }

    @Override
    public List<PeriodoTO> listarTodo() {
        List<PeriodoTO> periodos = new ArrayList<>();
        String query = "SELECT * FROM periodo";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PeriodoTO periodo = new PeriodoTO();
                periodo.setIdPeriodo(rs.getInt("id_periodo"));
                periodo.setNombre(rs.getString("nombre"));
                periodos.add(periodo);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la lista de periodos: " + e.getMessage());
        }
        return periodos;
    }

    public static void main(String[] args) {
        PeriodoDao dao = new PeriodoDao();
        List<PeriodoTO> periodos = dao.listarTodo();
        Scanner sc = new Scanner(System.in);
        String opc = "R";
        System.out.println("*****Bienvenido al Sistema*****");
        String msg = "Opciones de Menú:\nC=Crear\nR=Reportar\nU=Actualizar\nD=Eliminar";
        PeriodoTO periodo;
        do {
            switch (opc) {
                case "C" -> {
                    periodo = new PeriodoTO();
                    System.out.println("Ingrese Nombre del periodo:");
                    periodo.setNombre(sc.next());
                    dao.create(periodo);
                    dao.listarTodo();
                }
                case "R" -> {
                    dao.listarTodo();
                }
                case "U" -> {
                    System.out.println("Ingrese el ID del periodo que desea modificar:");
                    int idPeriodo = sc.nextInt();
                    periodo = dao.buscarPorId(idPeriodo);
                    System.out.println("Ingrese el nuevo Nombre del periodo:");
                    periodo.setNombre(sc.next());
                    dao.update(periodo);
                    dao.listarTodo();
                }
                case "D" -> {
                    System.out.println("Ingrese el ID del periodo que desea eliminar:");
                    try {
                        int idPeriodo = sc.nextInt();
                        dao.delete(idPeriodo);
                    } catch (Exception e) {
                    }
                    dao.listarTodo();
                }
                default -> {
                    System.out.println("Opción no válida. ¡Intente otra vez!");
                }
            }
            System.out.println("¿Qué desea hacer?\n" + msg);
            opc = sc.next();
        } while (!opc.toUpperCase().equals("X"));
    }

    @Override
    public PeriodoTO buscarPorId(int idPeriodo) {
        PeriodoTO periodo = null;
        String query = "SELECT * FROM periodo WHERE id_periodo = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, idPeriodo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                periodo = new PeriodoTO();
                periodo.setIdPeriodo(rs.getInt("id_periodo"));
                periodo.setNombre(rs.getString("nombre"));
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el periodo por ID: " + e.getMessage());
        }
        return periodo;
    }

    @Override
    public List<PeriodoTO> buscarPorNombre(String nombre) {
        List<PeriodoTO> periodos = new ArrayList<>();
        String query = "SELECT * FROM periodo WHERE nombre LIKE ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, "%" + nombre + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PeriodoTO periodo = new PeriodoTO();
                periodo.setIdPeriodo(rs.getInt("id_periodo"));
                periodo.setNombre(rs.getString("nombre"));
                periodos.add(periodo);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar periodos por nombre: " + e.getMessage());
        }
        return periodos;
    }

    @Override
    public List<PeriodoTO> buscarPorFecha(String fechaInicio, String fechaFin) {
        List<PeriodoTO> periodos = new ArrayList<>();
        String query = "SELECT * FROM periodo WHERE fecha_inicio >= ? AND fecha_fin <= ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, fechaInicio);
            pstmt.setString(2, fechaFin);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                PeriodoTO periodo = new PeriodoTO();
                periodo.setIdPeriodo(rs.getInt("id_periodo"));
                periodo.setNombre(rs.getString("nombre"));
                periodos.add(periodo);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar periodos por fecha: " + e.getMessage());
        }
        return periodos;
    }
}
