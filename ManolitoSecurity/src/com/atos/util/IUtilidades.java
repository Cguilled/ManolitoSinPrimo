package com.atos.util;

import com.atos.hibernate.dto.Tareas;

public interface IUtilidades {

	String randomPassword();

	// Consultar del codigo de la tarea de la tabla Roles_Tareas
	Integer consultarCodigoTarea(Tareas transientInstance);

}