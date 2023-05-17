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
//import java.util.Collection;
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

    ErrorLogger log = new ErrorLogger(UsuarioDao.class.getName());

    @Override
    public int crearUsuario(UsuarioTO d) {
        int rsId = 0;
        String[] returns = {"user"};
        String sql = "INSERT INTO usuario( "
                + "user, clave, perfil, estado )"
                + " values(?, ?, ?, ? );";
        int i = 0;
        try {
            ps = connection.prepareStatement(sql, returns);
            ps.setString(++i, d.getUser());
            ps.setString(++i, d.getClave());
            ps.setString(++i, d.getPerfil());
            ps.setString(++i, d.getEstado());
            rsId = ps.executeUpdate();
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
    public int actualizarUsuario(UsuarioTO d) {
        System.out.println("actualizar d.getDniruc: " + d.getIdUsuario());
        int comit = 0;
        String sql = "UPDATE usuario SET "
                + "user=?, "
                + "clave=?, "
                + "perfil=?, "
                + "estado=? "
                + "WHERE id_usuario=?";
        int i = 0;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(++i, d.getUser());
            ps.setString(++i, d.getClave());
            ps.setString(++i, d.getPerfil());
            ps.setString(++i, d.getEstado());
            ps.setInt(++i, d.getIdUsuario());

            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "update", ex);
        }

        return comit;

    }

    @Override
    public int eliminarUsuario(String usuario) throws Exception {
        int comit = 0;
        String sql = "DELETE FROM usuario WHERE user = ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, usuario);
            comit = ps.executeUpdate();
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "delete", ex);

            throw new Exception("Detalle:" + ex.getMessage());
        }

        return 0;

    }

    public void listarUsuarios(List<UsuarioTO> lista) {
        System.out.println("User\t\t Clave\t\t Perfil\t\t Estado");
        for (UsuarioTO po : lista) {
            System.out.println(po.getUser() + "\t\t" + po.getClave() + "\t\t" + po.getPerfil() + "\t\t" + po.getEstado());
        }
    }

    @Override
    public List<UsuarioTO> listarTodo() {
        List<UsuarioTO> listarEntidad = new ArrayList();
        String sql = "SELECT * "
                + "FROM usuario ";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                UsuarioTO cli = new UsuarioTO();
                cli.setIdUsuario(rs.getInt("id_usuario"));
                cli.setUser(rs.getString("user"));
                cli.setClave(rs.getString("clave"));
                cli.setEstado(rs.getString("estado"));
                cli.setPerfil(rs.getString("perfil"));
                listarEntidad.add(cli);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listarEntidad;
    }

    public static void main(String[] args) {
        UsuarioDao dao = new UsuarioDao();
        Scanner sc = new Scanner(System.in);
        String opc = "R";
        System.out.println("****************Bienvenido al Sistemas****************");
        String msg = "Opciones de Menú:\nC=Crear\nR=Reportar\nU=Actualizar\nD=Eliminar";
        UsuarioTO d = null;
        do {
            switch (opc) {
                case "C" -> {
                    d = new UsuarioTO();
                    System.out.println("Ingrese user :");
                    d.setUser(sc.next());
                    System.out.println("Ingrese clave :");
                    d.setClave(sc.next());
                    System.out.println("Ingrese perfil :");
                    d.setPerfil(sc.next());
                    System.out.println("Ingrese Estado :");
                    d.setEstado(sc.next());
                    dao.crearUsuario(d);
                    dao.listarUsuarios(dao.listarTodo());
                }
                case "R" -> {
                    dao.listarUsuarios(dao.listarTodo());
                }
                case "U" -> {
                    System.out.println("Ingrese el user  del registro que desea modificar:");
                    int IdUsuario = sc.nextInt();
                    d = dao.buscarEntidad(IdUsuario);
                    System.out.println("Ingrese el nuevo user:");
                    d.setUser(sc.next());
                    System.out.println("Ingrese la nueva clave :");
                    d.setClave(sc.next());
                    System.out.println("Ingrese el nuevo perfil :");
                    d.setPerfil(sc.next());
                    System.out.println("Ingrese el nuevo estado :");
                    d.setEstado(sc.next());
                    dao.actualizarUsuario(d);
                    dao.listarUsuarios(dao.listarTodo());
                }

                case "D" -> {
                    System.out.println("Ingrese el usario que desea Eliminar:");
                    try {
                        dao.eliminarUsuario(sc.next());
                    } catch (Exception e) {
                    }
                    dao.listarUsuarios(dao.listarTodo());
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
    public UsuarioTO buscarEntidad(int idUsuario) {
        UsuarioTO entidad = new UsuarioTO();
        String sql = "SELECT * "
                + "FROM usuario "
                + "WHERE id_usuario=?";
        try {
            //connection = new Conn().connectSQLite();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                entidad.setUser(rs.getString("user"));
                entidad.setClave(rs.getString("clave"));
                entidad.setPerfil(rs.getString("perfil"));
                entidad.setEstado(rs.getString("estado"));
                entidad.setIdUsuario(rs.getInt("id_usuario"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return entidad;
    }

}
