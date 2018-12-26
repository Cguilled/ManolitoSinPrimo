package com.atos.hibernate.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.atos.hibernate.dto.Usuarios;

@Repository("usuarios_dao")
@Scope("prototype")
public class UsuariosDAO {
	
	// modificar esto en cuanto tengamos acceso a las variables
	public static final String DAS = "DAS";
	public static final String NOMBRE = "nombre";
	public static final String APELLIDO = "apellido";
	public static final String PASSWORD = "password";
	public static final String ESTADO = "estado";
	public static final String INICIO = "inicio";
	public static final String CODIGO_ROL = "codigo_rol";

	private SessionFactory sessionFactory;
	
	
	//Spring iniciará el SessionFactory
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//Guardar un objeto de tipo Usuarios en la tabla Roles
	public void save(Usuarios transientInstance) {
		try {
			getCurrentSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//Borrar un valor de Usuarios de la tabla que sea identico a nuestro objeto roles
	public void delete(Usuarios persistentInstance) {
		try {
			getCurrentSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	//Recuperar un objeto de tipo Usuarios a partir de un Id
	public Usuarios findById(java.lang.Integer id) {
		try {
			Usuarios usuario = (Usuarios) getCurrentSession().get(
					"com.atos.hibernate.dto.Usuarios", id);
			return usuario;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//Encontrar todos los objetos de tipo Usuarios que tengan una cierta propiedad
	public List findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from Usuarios as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//Encontrar todos los objetos de tipo Usuarios que tengan ciertas propiedades
	/*public List findByProperty(List<String> propertiesNames, List<Object> values) {
		
		log.debug("finding Usuarios instance with properties");
		try {
			
			String queryString = "from Usuarios as model where mode"; 
			String varArgs="";
			int count=0;
			
			Query queryObject = getCurrentSession().createQuery(queryString);
			
			for (String propertyName : propertiesNames) {
			
				if(count<(propertiesNames.size()-1))
					varArgs+="model."+ propertyName + "= ? and ";
				else
					varArgs+="model."+ propertyName + "= ?";
					
				queryObject.setParameter(count, values.get(count++));
			
			}
			
			return queryObject.list();
			
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
		
	}*/
	
	public Usuarios findByDasAndPass(String das, String pass) {
		try {
			
			String queryString = "from Usuarios as model where model.das = ? and model.password = ?";
			
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, das);
			queryObject.setParameter(1, pass);
			
			return (Usuarios) queryObject.uniqueResult();
			
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List findByProperty(List<String> propertiesNames, List<Object> values) {
		
		try {
			
			String queryString = "from Usuarios as model where "; 
			String varArgs="";
			int count=0;
			
			//Query queryObject = getCurrentSession().createQuery(queryString);
			
			for (String propertyName : propertiesNames) {
			
				if(count<(propertiesNames.size()-1))
					varArgs+="model."+ propertyName + "= ? and ";
				else
					varArgs+="model."+ propertyName + "= ?";
				
				count++;
			}
			
			Query queryObject = getCurrentSession().createQuery(queryString + varArgs);
			count = 0;
			
			for (Object value : values) {
					
				queryObject.setParameter(count, value);
				
				count++;
			}
			
			return queryObject.list();
			
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//Recuperar objetos de tipo Usuarios a partir de un das
	public Usuarios findByDas(String das) {
		try {
			Usuarios instance = (Usuarios) getCurrentSession().get(
					"com.atos.hibernate.dto.Usuarios", das);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//Recuperar objetos de tipo Usuarios a partir de un nombre
	public List<Usuarios> findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}
	
	//Recuperar objetos de tipo Usuarios a partir de un apellido
	public List<Usuarios> findByApellido(Object descripcion) {
		return findByProperty(APELLIDO, descripcion);
	}

	//Recuperar objetos de tipo Usuarios a partir de un estado
	public List<Usuarios> findByEstado(Object descripcion) {
		return findByProperty(ESTADO, descripcion);
	}
	
	//Recuperar objetos de tipo Usuarios a partir de si ha iniciado sesion
	public List<Usuarios> findByInicio(Object descripcion) {
		return findByProperty(INICIO, descripcion);
	}
	
	//Recuperar objetos de tipo Usuarios a partir de un rol
	public List<Usuarios> findByRol(Object descripcion) {
		return findByProperty(CODIGO_ROL, descripcion);
	}

	//Recuperar todos los objetos de tipo Usuarios
	public List findAll() {
		try {
			String queryString = "from Usuarios";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	//Hace una consulta para actualizar un objeto de tipo Usuarios desde la base de datos a nuestra aplicación
	public Usuarios merge(Usuarios detachedInstance) {
		try {
			Usuarios result = (Usuarios) getCurrentSession().merge(
					detachedInstance);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	//Hace un update para actualizar un objeto de tipo Usuarios desde nuestra aplicación a la base de datos (e intenta bloquear)
	public void attachDirty(Usuarios instance) {
		try {
			getCurrentSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	//Hace un update para actualizar un objeto de tipo Usuarios desde nuestra aplicación a la base de datos (sin intentar bloquear)
	public void attachClean(Usuarios instance) {
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//Devuelve un valor Usuarios de ApplicationContext
	public static Usuarios getFromApplicationContext(ApplicationContext ctx) {
		return (Usuarios) ctx.getBean("UsuariosDAO");
	}
}
