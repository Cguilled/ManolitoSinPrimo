package com.atos.dao;

import java.util.List;
import java.util.Set;

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

import com.atos.hibernate.Tareas;



public class TareasDAO {
	private static final Logger log = LoggerFactory.getLogger(TareasDAO.class);
	// property constants

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Tareas transientInstance) {
		log.debug("saving Tareas instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tareas tarea) {
		log.debug("deleting Tareas instance");
		try {
			getCurrentSession().delete(tarea);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tareas findById(Integer id) {
		log.debug("getting Tareas instance with id: " + id);
		try {
			Tareas instance = (Tareas) getCurrentSession().get("com.atos.hibernate.Tareas", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Tareas> findByExample(Tareas instance) {
		log.debug("finding Tareas instance by example");
		try {
			List<Tareas> results = (List<Tareas>) getCurrentSession().createCriteria("com.atos.hibernate.Tareas")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public Tareas findByProperty(String nombre) {
		log.debug("finding Tareas instance with value: "+nombre);
		try {
			String queryString = "from Tareas as model where model.nombre_tarea= :tarea";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter("tarea", nombre);
			return (Tareas) queryObject.uniqueResult();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}


	public List findAll() {
		log.debug("finding all Tareas instances");
		try {
			String queryString = "from Tareas";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tareas merge(Tareas detachedInstance) {
		log.debug("merging Tareas instance");
		try {
			Tareas result = (Tareas) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tareas instance) {
		log.debug("attaching dirty Tareas instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tareas instance) {
		log.debug("attaching clean Tareas instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TareasDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TareasDAO) ctx.getBean("TareasDAO");
	}
}