package com.atos.hibernate.dao;

import org.springframework.stereotype.Repository;

import com.atos.hibernate.dto.Tareas;
import com.atos.util.IUtilidades;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

@Repository("tareas_dao")
@Scope("prototype")
public class TareasDAO {
	// modificar esto en cuanto tengamos acceso a las variables
	public static final String ID_TAREA = "id";
	public static final String NOMBRE_TAREA = "nombre";
	public static final String DESCRIPCION_TAREA = "descripcion";
	public static final String ESTADO = "estado";

	private SessionFactory sessionFactory;
	private IUtilidades util;

	// Spring iniciará el SessionFactory
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	// Guardar un objeto de tipo Tareas en la tabla Roles
	public void save(Tareas transientInstance) {
		try {
			getCurrentSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	// Eliminar un objeto tarias
	public void delete(Tareas persistentInstance) {
		Integer codigoTarea = null;
		try {
			if (util.consultarCodigoTarea(persistentInstance) != null) {
				Query qRoles_Tareas = getCurrentSession().createQuery("DELETE FROM ROLES_TAREAS WHERE CODIGO_TAREAS = ?");
				Query qTareas = getCurrentSession().createQuery("DELETE FROM TAREA WHERE CODIGO_TAREA = ?");
				
				qRoles_Tareas.setInteger(0, persistentInstance.getId_Tarea());
				codigoTarea = (Integer) qRoles_Tareas.uniqueResult();
				
				qTareas.setInteger(0, persistentInstance.getId_Tarea());
				codigoTarea = (Integer) qTareas.uniqueResult();
				
				getCurrentSession().delete(persistentInstance);
			}else
				System.out.println("No existe ese id de tarea");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	// Recuperar un objeto de tipo Tareas a partir de un Id
	public Tareas findById(java.lang.Integer id) {
		try {
			Tareas instance = (Tareas) getCurrentSession().get("com.atos.hibernate.dto.Tareas", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	// Encontrar todos los objetos de tipo Tareas que tengan una cierta propiedad
	public List findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from Tareas as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	// Recuperar objetos de tipo tareas a partir de un nombre
	public List<Tareas> findByNombreTareas(Object nombre) {
		return findByProperty(NOMBRE_TAREA, nombre);
	}

	// Recuperar objetos de tipo tareas a partir de una descripcion
	public List<Tareas> findByDescripcionTareas(Object descripcion) {
		return findByProperty(DESCRIPCION_TAREA, descripcion);
	}

	// Recuperar objetos de tipo tareas a partir de un estado
	public List<Tareas> findByEstadoTareas(Object estado) {
		return findByProperty(ESTADO, estado);
	}

	// Recuperar todos los objetos de tipo tareas
	public List findAll() {
		try {
			String queryString = "from Tareas";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	// Hace una consulta para actualizar un objeto de tipo Tareas desde la base de
	// datos a nuestra aplicación
	public Tareas merge(Tareas detachedInstance) {
		try {
			Tareas result = (Tareas) getCurrentSession().merge(detachedInstance);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	// Hace un update para actualizar un objeto de tipo Tareas desde nuestra
	// aplicación a la base de datos (e intenta bloquear)
	public void attachDirty(Tareas instance) {
		try {
			getCurrentSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	// Hace un update para actualizar un objeto de tipo Tareas desde nuestra
	// aplicación a la base de datos (sin intentar bloquear)
	public void attachClean(Tareas instance) {
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	// Devuelve un valor Tareas de ApplicationContext
	public static Tareas getFromApplicationContext(ApplicationContext ctx) {
		return (Tareas) ctx.getBean("TareasDAO");
	}

}
