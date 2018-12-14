package com.atos.hibernate.modelo;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.atos.dao.RolesDAO;
import com.atos.hibernate.Roles;

@Component("gestion_roles")
@Scope("prototype")
public class Gestion_Roles implements IGestion_Roles {

	private RolesDAO rolesdao;

	@Override
	@Transactional(readOnly = true)
	public List<Roles> consultar_Roles() {
		return rolesdao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Roles consultar_ID(Integer id) {
		return rolesdao.findById(id);
	}
	
	// ACCESORS PARA SPRING
	public void setRolesdao(RolesDAO rolesdao) {
		this.rolesdao = rolesdao;
	}

	
}
