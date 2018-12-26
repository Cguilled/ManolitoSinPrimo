package com.atos.hibernate.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.atos.hibernate.dto.Roles;

@Repository("roles_dao")
@Scope("prototype")
public class RolesDAO {
	// modificar esto en cuanto tengamos acceso a las variables
	public static final String CODIGO_ROL = "codigo_rol";
	public static final String DESCRIPCION_ROL = "descripcion";

	private SessionFactory sessionFactory;
	
	//Spring iniciará el SessionFactory
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//Guardar un objeto de tipo Roles en la tabla Roles
	public void save(Roles transientInstance) {
		try {
			getCurrentSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//Borrar un valor de roles de la tabla que sea identico a nuestro objeto roles
	public void delete(Roles persistentInstance) {
		try {
			getCurrentSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//Recuperar un objeto de tipo roles a partir de un Id
	public Roles findByCodigoRol(java.lang.Integer id) {
		try {
			Roles instance = (Roles) getCurrentSession().get(
					"com.atos.hibernate.dto.Roles", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//Encontrar todos los objetos de tipo roles que tengan una cierta propiedad
	public List findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from Roles as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//Recuperar objetos de tipo roles a partir de una descripcion
	public List<Roles> findByDescripcion(Object nombre) {
		return findByProperty(DESCRIPCION_ROL, nombre);
	}
	
	//Recuperar todos los objetos de tipo roles
	public List<Roles> findAll() {
		try {
			String queryString = "from Roles";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//Hace una consulta para actualizar un objeto de tipo roles desde la base de datos a nuestra aplicación
	public Roles merge(Roles detachedInstance) {
		try {
			Roles result = (Roles) getCurrentSession().merge(
					detachedInstance);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//Hace un update para actualizar un objeto de tipo roles desde nuestra aplicación a la base de datos (e intenta bloquear)
	public void attachDirty(Roles instance) {
		try {
			getCurrentSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//Hace un update para actualizar un objeto de tipo roles desde nuestra aplicación a la base de datos (sin intentar bloquear)
	public void attachClean(Roles instance) {
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//Devuelve un valor Roles de ApplicationContext
	public static Roles getFromApplicationContext(ApplicationContext ctx) {
		return (Roles) ctx.getBean("RolesDAO");
	}

}
