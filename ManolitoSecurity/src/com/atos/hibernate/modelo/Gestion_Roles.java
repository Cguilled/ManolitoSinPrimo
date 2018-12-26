package com.atos.hibernate.modelo;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.atos.hibernate.dao.RolesDAO;
import com.atos.hibernate.dto.Roles;

/**
 * @author Guillermo Cermeño Puertas
 *
 * 27 ago. 2018
 */

@Component("gestion_roles")
@Scope("prototype")
public class Gestion_Roles implements IGestion_Roles {

private RolesDAO roles_dao;
	
	@Override
	@Transactional(readOnly = true)
	public Roles consultar_PorCodigoRol(Roles rol) {
		return roles_dao.findByCodigoRol(rol.getCodigoRol());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Roles> consultar_Todos() {
		return roles_dao.findAll();
	}
	
	// ************ CRUD
		@Override
		@Transactional
		public void alta_Rol(Roles rol) {
			roles_dao.save(rol);
		}

		@Override
		@Transactional
		public void baja_Rol(Roles rol) {
			roles_dao.delete(rol);
		}

		@Override
		@Transactional
		public void modificacion_Rol(Roles rol) {
			roles_dao.attachDirty(rol);
		}

	// ACCESORES PARA SPRING
	public void setRoles_dao(RolesDAO rol_dao) {
		this.roles_dao = rol_dao;
	}

}
