package com.atos.hibernate.modelo;

import java.util.List;

import com.atos.hibernate.dto.Roles;

/**
 * @author Guillermo Cermeño Puertas
 *
 * 27 ago. 2018
 */
public interface IGestion_Roles {

	List<Roles> consultar_Todos();

	Roles consultar_PorCodigoRol(Roles rol);

	void modificacion_Rol(Roles rol);

	void baja_Rol(Roles rol);

	void alta_Rol(Roles rol);

}