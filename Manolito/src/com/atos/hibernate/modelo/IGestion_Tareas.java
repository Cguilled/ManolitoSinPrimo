package com.atos.hibernate.modelo;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.atos.dao.TareasDAO;
import com.atos.hibernate.Tareas;


public interface IGestion_Tareas {

	public List<Tareas> consultar_Todos();
	public Tareas consultar_Codigo(Integer codigo);
	public void alta_Tarea(Tareas tarea) throws DataAccessException;
	public void baja_Tarea(Tareas tarea) throws DataAccessException;
	public void modificacion_Tarea(Tareas tarea);
	public Tareas consultar_Nombre(String nombre);

}