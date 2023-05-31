/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.upeu.app.dao;

import java.util.List;
import pe.edu.upeu.app.modelo.PeriodoTO;

/**
 *
 * @author Data_Science
 */
public interface PeriodoDaoI {
    
    public int create(PeriodoTO periodo);
    
    public int update(PeriodoTO periodo);
    
    public int delete(int idPeriodo);
    
    public List<PeriodoTO> listarTodo();
    
    public PeriodoTO buscarPorId(int idPeriodo);
    
    public List<PeriodoTO> buscarPorNombre(String nombre);
    
    public List<PeriodoTO> buscarPorFecha(String fechaInicio, String fechaFin);
    
}
