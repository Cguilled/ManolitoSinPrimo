package com.atos.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.atos.hibernate.Usuarios;

public class UsuariosDAO {
	private static final Logger log = LoggerFactory.getLogger(UsuariosDAO.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(Usuarios transientInstance) {
		log.debug("saving Usuarios instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Usuarios persistentInstance) {
		log.debug("deleting Usuarios instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Usuarios findById(java.lang.String id) {
		log.debug("getting Usuarios instance with id: " + id);
		try {
			Usuarios instance = (Usuarios) getCurrentSession().get("com.atos.hibernate.Usuarios", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public boolean findByProperty(String username, String dir) {
		log.debug("finding Usuarios instance with property: das, value: " + username + " OR property: correo, value: "
				+ dir);
		try {
			String queryString = "from Usuarios as model where model.das = :das or model.correo = :correo";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter("das", username);
			queryObject.setParameter("correo", dir);
			Usuarios usu = (Usuarios) queryObject.uniqueResult();
			if (usu != null )
				return true;
			else
				return false;
		} catch (RuntimeException re) {
			log.error("find by property das or correo failed", re);
			throw re;
		}
	}

	public List<Usuarios> findByExample(Usuarios instance) {
		log.debug("finding Usuarios instance by example");
		try {
			List<Usuarios> results = (List<Usuarios>) getCurrentSession().createCriteria("com.atos.hibernate.Usuarios")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	public Usuarios findByEmail(String correo) {

		try {
			String queryString = "from Usuarios as model where model.correo = :correo";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter("correo", correo);
			return (Usuarios) queryObject.uniqueResult();

		} catch (RuntimeException re) {
			
			throw re;
		}
	}


	public List findAll() {
		log.debug("finding all Usuarios instances");
		try {
			String queryString = "from Usuarios";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Usuarios merge(Usuarios detachedInstance) {
		log.debug("merging Usuarios instance");
		try {
			Usuarios result = (Usuarios) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Usuarios instance) {
		log.debug("attaching dirty Usuarios instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Usuarios instance) {
		log.debug("attaching clean Usuarios instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UsuariosDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UsuariosDAO) ctx.getBean("UsuariosDAO");
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}