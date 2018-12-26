package com.atos.hibernate.modelo;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.atos.hibernate.dao.TareasDAO;
import com.atos.hibernate.dto.Tareas;

/**
 * 
 * @author Guillermo Cermeño Puertas
 *
 * 27 ago. 2018
 */

@Component("gestion_tareas")
@Scope("prototype")
public class Gestion_Tareas implements IGestion_Tareas {

	private TareasDAO tareas_dao;

	// ***************** CONSULTAS
	@Override
	@Transactional(readOnly = true)
	public Tareas consultar_PorCodigoTarea(Tareas tarea) {
		return tareas_dao.findById(tarea.getId_Tarea());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tareas> consultar_Todos() {
		return tareas_dao.findAll();
	}
	
	// ************ CRUD
	@Override
	@Transactional
	public void alta_Tarea(Tareas tarea) {
		tareas_dao.save(tarea);
	}

	@Override
	@Transactional
	public void baja_Tarea(Tareas tarea) {
		tareas_dao.delete(tarea);
	}

	@Override
	@Transactional
	public void modificacion_Tarea(Tareas tarea) {
		tareas_dao.attachDirty(tarea);
	}

	// ACCESORES DE SPRING
	public void setTareas_dao(TareasDAO tarea_dao) {
		this.tareas_dao = tarea_dao;
	}

}
