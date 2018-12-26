package com.atos.hibernate.modelo;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.atos.hibernate.dto.Tareas;


public interface IGestion_Tareas {

	void modificacion_Tarea(Tareas tarea);

	void baja_Tarea(Tareas tarea);

	void alta_Tarea(Tareas tarea);

	List<Tareas> consultar_Todos();

	Tareas consultar_PorCodigoTarea(Tareas tarea);

}