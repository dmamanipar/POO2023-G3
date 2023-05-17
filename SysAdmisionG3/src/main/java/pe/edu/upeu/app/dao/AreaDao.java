package pe.edu.upeu.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import pe.edu.upeu.app.conexion.ConnS;
import pe.edu.upeu.app.modelo.AreasTO;

import pe.edu.upeu.app.util.ErrorLogger;


/**
 
 * @author Data_Science
 */
public class AreaDao implements AreasDaoI {

    ConnS instance = ConnS.getInstance();
    Connection connection = instance.getConnection();
    PreparedStatement ps;
    ResultSet rs;

    ErrorLogger log = new ErrorLogger(AreaDao.class.getName());

    @Override
    public int create(AreasTO d) {
        int rsId = 0;
        String[] returnStringss = {"dni"};
        String sql = "INSERT INTO area(id_area, id_nombrearea, siglas ";
                 
        int i = 0;
        try {

        } catch (Exception e) {
        }
        {
            ps = connection.prepareStatement(sql, returns);
            ps.setInt(++i, d.getIdArea());
            ps.setInt(++i, d.getnombrearea());
            ps.setString(++i, d.getsiglas());

            rsId = ps.executeUpdate();// 0 no o 1 si commit

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    rsId = rs.getInt(1);
                }
                rs.close();
            }
        }catch (SQLDataExceptionex) {
            log.log(Level.SEVERE, "create", ex);
        }
        return rsId;
    }

    @Override
    public int update(AreasTO d) {
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
    public List<AreasTO> listarTodo() {
        List<AreasTO> listarEntidad = new ArrayList();
        String sql = "SELECT po.*, p.nombre as nombreperiodo, c.nombrecarrera "
                + "FROM postulante po, periodo p, carrera c "
                + "WHERE p.id_periodo = po.id_periodo and po.id_carrera = c.id_carrera";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                AreaDao cli = new AreaDao();
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
                
                listarEntidad.add(cli);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listarEntidad;
    }

    public static void main(String[] args) {
        AreasTO tO = new AreasTO();
        Scanner sc = new Scanner(System.in);
        String opc = "R";
        System.out.println("****************Bienvenido al Sistemas****************");
        String msg = "Opciones de Menú:\nC=Crear\nR=Reportar\nU=Actualizar\nD=Eliminar";
        AreaDao d;
        do {
            switch (opc) {
                case "C" -> {
                    d = new AreaDao();
                    System.out.println("Ingrese DNI:");
                    d.setDni(sc.next());
                    System.out.println("Ingrese Nombre:");
                    d.setNombre(sc.next());
                    System.out.println("Ingrese A. Paterno:");
                    d.setApellidoPat(sc.next());
                    System.out.println("Ingrese A. Materno:");
                    d.setApellidoMat(sc.next());
                    System.out.println("Ingrese Modalidad:");
                    d.setModalidad(sc.next());
                    System.out.println("Ingrese Estado:");
                    d.setEstado(sc.next());
                    System.out.println("Ingrese Carrera (1=Sistemas, 2=Contabilidad):");
                    d.setIdCarrera(sc.nextInt());
                    System.out.println("Ingrese Periodo (1=2023-1):");
                    d.setIdPeriodo(sc.nextInt());
                    dao.create(d);
                    dao.listarPostulantes(dao.listarTodo());
                }
                case "R" -> {
                    dao.listarPostulantes(dao.listarTodo());
                }
                case "U" -> {
                    System.out.println("Ingrese el DNI del registro que desea modificar:");
                    String dni = sc.next();
                    d = dao.buscarEntidad(dni);
                    System.out.println("Ingrese el nuevo Nombre:");
                    d.setNombre(sc.next());
                    System.out.println("Ingrese el nuevo A. Paterno:");
                    d.setApellidoPat(sc.next());
                    dao.update(d);
                    dao.listarPostulantes(dao.listarTodo());
                }
                case "D" -> {
                    System.out.println("Ingrese el DNI que desea Eliminar:");
                    try {
                        dao.delete(sc.next());
                    } catch (Exception e) {
                    }
                    dao.listarPostulantes(dao.listarTodo());
                }
                default -> {
                    System.out.println("Opción no valida intente otra vez!");
                }
            }
            System.out.println("Que desea hacer?\n" + msg);
            opc = sc.next();
        } while (!opc.toUpperCase().equals("X"));
    }

    public void listarAreas(List<AreaDao> lista) {
        System.out.println("DNI\t\t Nombre\t\t A.Paterno");
        for (AreaTO po : lista) {
            System.out.println(po.getDni() + "\t\t" + po.getNombre() + "\t\t" + po.getApellidoPat());
        }
    }

    @Override
    public List<AreaTO> listCmb(String filter) {
        List<AreaTO> ls = new ArrayList();
        ls.add(new AreasTO());
        ls.addAll(listarTodo());
        return ls;
    }

    @Override
    public AreasTO buscarEntidad(String dni) {
        AreasTO entidad = new AreasTO();
        String sql = "SELECT po.*, p.nombre as nombreperiodo, c.nombrecarrera "
                + "FROM postulante po, periodo p, carrera c "
                + "WHERE po.dni =  ? and p.id_periodo = po.id_periodo and po.id_carrera = c.id_carrera";
        try {
            //connection = new Conn().connectSQLite();
            ps = connection.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            if (rs.next()) {
                entidad.setDni(rs.getString("dni"));
                entidad.setNombre(rs.getString("nombre"));
                entidad.setApellidoPat(rs.getString("apellido_pat"));
                entidad.setApellidoMat(rs.getString("apellido_mat"));
                entidad.setModalidad(rs.getString("modalidad"));
                entidad.setEstado(rs.getString("estado"));
                entidad.setIdCarrera(rs.getInt("id_carrera"));
                entidad.setIdPeriodo(rs.getInt("id_periodo"));
                entidad.setNombrePeriodo(rs.getString("nombreperiodo"));
                entidad.setNombreCarrera(rs.getString("nombrecarrera"));
                
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return entidad;
    }


}
