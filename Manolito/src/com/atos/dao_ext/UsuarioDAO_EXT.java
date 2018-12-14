package com.atos.dao_ext;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.atos.hibernate.Usuarios;
import com.atos.dao.UsuariosDAO;

@Repository("usuariosdao")
@Scope("prototype")
public class UsuarioDAO_EXT extends UsuariosDAO {
	
	
	
	public Usuarios consultar_ConRol(String das) {
		// APERTURA DE CRITERIA PARA LA CONSULTA
		Criteria consulta = getCurrentSession().createCriteria(Usuarios.class);
		// MODO DE RESOLUCION DE CARGA VAGA
		consulta.setFetchMode("roles", FetchMode.JOIN);
		// CONDICIONES DE LA CONSULTA
		consulta.add(Restrictions.idEq(das));
		// SE EJECUTA LA CONSULTA
		Usuarios usuario = (Usuarios) consulta.uniqueResult();
		return usuario;
	}
	
	public List<Usuarios> consultar_all() {
		// APERTURA DE CRITERIA PARA LA CONSULTA
		Criteria consulta = getCurrentSession().createCriteria(Usuarios.class);
		// MODO DE RESOLUCION DE CARGA VAGA
		consulta.setFetchMode("roles", FetchMode.JOIN);

		// SE EJECUTA LA CONSULTA
		
		List<Usuarios> usuarios = (List<Usuarios>) consulta.list();
		return usuarios;
	}
	 
	 

}
