package com.atos.dao_ext;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.atos.dao.RolesDAO;
import com.atos.hibernate.Roles;

@Repository("rolesdao")
@Scope("prototype")
public class RolesDAO_EXT extends RolesDAO{
	
	public List<Roles> consultar_Roles() {
		Criteria consulta = getCurrentSession().createCriteria(Roles.class);
		consulta.setFetchMode("roles", FetchMode.JOIN);
		/*consulta.setFetchMode("pedidoses.lineaPedidos", FetchMode.JOIN); PONER ESTE SI QUIERES MAS DATOS*/
		
		return (List<Roles>) consulta.list();
	}

}
