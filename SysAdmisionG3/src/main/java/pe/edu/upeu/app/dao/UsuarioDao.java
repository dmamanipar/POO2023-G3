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
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import pe.edu.upeu.app.conexion.ConnS;
import pe.edu.upeu.app.modelo.UsuarioTO;
import pe.edu.upeu.app.util.ErrorLogger;

/**
 *
 * @author Lenovo
 */
public class UsuarioDao implements UsuarioDaoI {

    ConnS instance = ConnS.getInstance();
    Connection connection = instance.getConnection();
    PreparedStatement ps;
    ResultSet rs;

    ErrorLogger log = new ErrorLogger(PostulanteDao.class.getName());
    private Iterable<UsuarioDao> usuario;

    @Override
    public int crearUsuario(UsuarioTO d) {
        int rsId = 0;
        String[] returns = {"clave"};
        String sql = "INSERT INTO postulante(id_carrera, "
                + "clave,nombre, apellido, Perfil, Estado) "
                + " values(?, ?, ?, ?, ?,?,);";
        int i = 0;
        try {
            ps = connection.prepareStatement(sql, returns);
            ps.setInt(++i, d.getIdUsuario());
            ps.setString(++i, d.getclave());
            ps.setString(++i, d.getNombre());
            ps.setString(++i, d.getApellido());
            ps.setString(++i, d.getPerfil());
            ps.setString(++i, d.getEstado());
            rsId = ps.executeUpdate();
            try ( ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    rsId = rs.getInt(1);
                }
                rs.close();
            }
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "create", ex);
        }
        return 0;

    }

    @Override
    public int actualizarUsuario(UsuarioTO d) {
        System.out.println("actualizar d.getDniruc: " + d.getClave());
        int comit = 0;
        String sql = "UPDATE postulante SET "
                + "nombre=?, "
                + "apellido=?, "
                + "perfil=?, "
                + "estado=?, "
                + "WHERE clave=?";
        int i = 0;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(++i, d.getNombre());
            ps.setString(++i, d.getApellido());
            ps.setString(++i, d.getPerfil());
            ps.setString(++i, d.getEstado());
            ps.setString(++i, d.getClave());
            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "update", ex);
        }

        return 0;

    }

    @Override
    public int eliminarUsuario(String clave) throws Exception {
        int comit = 0;
        String sql = "DELETE FROM postulante WHERE clave = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, clave);
            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "delete", ex);

            throw new Exception("Detalle:" + ex.getMessage());
        }

        return 0;

    }
  
    @Override
    public List<UsuarioTO> listarUsuarios(String filter) {
        List<UsuarioTO> ls = new ArrayList();
        ls.add(new UsuarioTO());
        ls.addAll(listarTodo());
         return null;
         }

    private Collection<? extends UsuarioTO> listarTodo() {
        List<UsuarioTO> listarEntidad = new ArrayList();
        String sql = "SELECT po.*, p.nombre as nombreperiodo, c.nombrecarrera "
                + "FROM postulante po, periodo p, carrera c "
                + "WHERE p.id_periodo = po.id_periodo and po.id_carrera = c.id_carrera";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                UsuarioTO cli = new UsuarioTO();
                cli.setClave(rs.getString("clave"));
                cli.setNombre(rs.getString("nombre"));
                cli.setApellido(rs.getString("apellido"));
                cli.setPerfil(rs.getString("modalidad"));
                cli.setEstado(rs.getString("estado"));
                listarEntidad.add(cli);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
         return listarEntidad ;
         }

 
public static void main(String[] args) {
        PostulanteDao dao = new PostulanteDao();
        Scanner sc = new Scanner(System.in);
        String opc = "R";
        System.out.println("****************Bienvenido al Sistemas****************");
        String msg = "Opciones de Menú:\nC=Crear\nR=Reportar\nU=Actualizar\nD=Eliminar";
        UsuarioTO d = null;
        do {
            switch (opc) {
                case "C" -> {
                    d = new UsuarioTO();
                    System.out.println("Ingrese DNI:");
                    d.setClave(sc.next());
                    System.out.println("Ingrese Nombre:");
                    d.setNombre(sc.next());
                    System.out.println("Ingrese A. Paterno:");
                    d.setApellido(sc.next());
                    System.out.println("Ingrese perfil:");
                    d.setPerfil(sc.next());
                    System.out.println("Ingrese Estado:");
                    d.setEstado(sc.next());
                    dao.create(d);
                    dao.listarPostulantes(dao.listarTodo());
                }
                case "R" -> {
                    dao.listarUsuarios(dao.listarTodo());
                }
                case "U" -> {
                    System.out.println("Ingrese la clave  del registro que desea modificar:");
                    String clave = sc.next();
                    System.out.println("Ingrese el nuevo Nombre:");
                    d.setNombre(sc.next());
                    System.out.println("Ingrese el nuevo A. Paterno:");
                    d.setApellido(sc.next());
                    dao.update(d);
                    dao.listarPostulantes(dao.listarTodo());
                }

                case "D" -> {
                    System.out.println("Ingrese el usario que desea Eliminar:");
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

    @Override
    public UsuarioTO buscarEntidad(String dni) {
        UsuarioTO entidad = new UsuarioTO();
        String sql = "SELECT po.*, p.nombre as nombreperfil, c.nombreestado "
                + "FROM usuario us, perfil p, estado e "
                + "WHERE us.clave =  ? and u.id_usuario = us.id_usuario ";
        try {
            //connection = new Conn().connectSQLite();
            ps = connection.prepareStatement(sql);
            ps.setString(1, dni);
            rs = ps.executeQuery();
            if (rs.next()) {
                entidad.setClave(rs.getString("clave"));
                entidad.setNombre(rs.getString("nombre"));
                entidad.setApellido(rs.getString("apellido_pat"));
                entidad.setPerfil(rs.getString("modalidad"));
                entidad.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
         return entidad;
         }
    }


