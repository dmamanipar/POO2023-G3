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
import pe.com.syscenterlife.autocomp.ModeloDataAutocomplet;
import pe.edu.upeu.app.conexion.ConnS;
import pe.edu.upeu.app.modelo.ComboBoxOption;
import pe.edu.upeu.app.modelo.PostulanteTO;
import pe.edu.upeu.app.modelo.UsuarioTO;
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
        Scanner sc = new Scanner(System.in);
        String opc = "R";
        System.out.println("****************Bienvenido al Sistemas****************");
        String msg = "Opciones de Menú:\nC=Crear\nR=Reportar\nU=Actualizar\nD=Eliminar";
        PostulanteTO d;
        do {
            switch (opc) {
                case "C" -> {
                    d = new PostulanteTO();
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

    public void listarPostulantes(List<PostulanteTO> lista) {
        System.out.println("DNI\t\t Nombre\t\t A.Paterno");
        for (PostulanteTO po : lista) {
            System.out.println(po.getDni() + "\t\t" + po.getNombre() + "\t\t" + po.getApellidoPat());
        }
    }

    @Override
    public List<PostulanteTO> listCmb(String filter) {
        List<PostulanteTO> ls = new ArrayList();
        ls.add(new PostulanteTO());
        ls.addAll(listarTodo());
        return ls;
    }

    @Override
    public PostulanteTO buscarEntidad(String dni) {
        PostulanteTO entidad = new PostulanteTO();
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
                entidad.setNombreModalidad(buscarModalidadExamen(rs.getString(
                        "modalidad")));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return entidad;
    }

    @Override
    public List<ModeloDataAutocomplet> listAutoComplet(String filter) {
        List<ModeloDataAutocomplet> listarentidad = new ArrayList();
        String sql = "SELECT * FROM postulante WHERE nombre like ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, filter + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ModeloDataAutocomplet data = new ModeloDataAutocomplet();
                //ModeloDataAutocomplet.TIPE_DISPLAY = "ID";
                data.setIdx(rs.getString("dni"));
                data.setNombreDysplay(rs.getString("nombre"));
                data.setOtherData(rs.getString("modalidad"));
                listarentidad.add(data);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listarentidad;
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
        List<ComboBoxOption> dd = new ArrayList<>();
        String sql = "SELECT * FROM periodo";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                dd.add(new ComboBoxOption(String.valueOf(rs.getInt("id_periodo")),
                        rs.getString("nombre")));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return dd;
    }

    @Override
    public List<ModeloDataAutocomplet> listAutoCompletCarrera(String filter) {
        List<ModeloDataAutocomplet> listarentidad = new ArrayList();
        String sql = "SELECT * FROM carrera WHERE nombrecarrera like ?";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, filter + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ModeloDataAutocomplet data = new ModeloDataAutocomplet();
                //ModeloDataAutocomplet.TIPE_DISPLAY = "ID";
                data.setIdx(rs.getString("id_carrera"));
                data.setNombreDysplay(rs.getString("nombrecarrera"));
                data.setOtherData(rs.getString("id_area_examen"));
                listarentidad.add(data);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listarentidad;
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

    void create(UsuarioTO d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void listarUsuarios(List<PostulanteTO> listarTodo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void update(UsuarioTO d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
