package com.atos.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.springframework.dao.DataAccessException;

import com.atos.hibernate.dao.RolesDAO;
import com.atos.hibernate.dto.Roles;
import com.atos.hibernate.dto.Tareas;
import com.atos.hibernate.dto.Usuarios;
import com.atos.hibernate.modelo.IGestion_Roles;
import com.atos.hibernate.modelo.IGestion_Usuarios;
import com.atos.util.IUtilidades;
import com.atos.util.Utilidades;

/**
 * 
 * @author Guillermo Cermeño
 *
 *         09 ago. 2018
 */

@ManagedBean(name = "usuarios_bean")
@ViewScoped
public class Usuarios_Bean {
	private String das;
	private String nombre;
	private String apellido;
	private String estado;
	private Integer inicio;
	private String password;
	private Usuarios usuario;
	private boolean visible;
	private List<Roles> listaRoles;
	public Integer codigoRol;
	public Roles rol;

	@ManagedProperty("#{gestionUsuarios}")
	private IGestion_Usuarios gestionUsuarios;

	@ManagedProperty("#{gestion_roles}")
	private IGestion_Roles gestionRoles;
	
	@ManagedProperty("#{utilidades}")
	private Utilidades util;

	@PostConstruct
	public void valores_Iniciales() {
		usuario = new Usuarios();
		rol = new Roles();
		das = "";
		nombre = "";
		apellido = "";
		estado = "";
		inicio = 0;
		password = "";
		visible = true;
		listaRoles = gestionRoles.consultar_Todos();
		util = new Utilidades();
	}

	// EVENTOS
	public void alta_Usuario(ActionEvent evento) {
		try {
			usuario.setRoles(rol);
			//Le pongo una contraseña por defecto al nuevo usuario
			usuario.setPassword(util.randomPassword());
			gestionUsuarios.alta_Usuario(usuario);
			System.out.println("alta correcta");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public void baja_Usuario(ActionEvent evento) {
		try {
			// NO SE TIENE QUE ELIMINAR EL USUARIO SINO CAMBIAR EL CAMPO ESTADO A NO ACTIVO
			gestionUsuarios.baja_Usuario(usuario);
			System.out.println("baja correcta");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public void modificacion_Usuario(ActionEvent evento) {
		try {
			gestionUsuarios.modificacion_Usuario(usuario);
			System.out.println("modificación correcta");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public void rolChange(ValueChangeEvent event) {
		codigoRol = (Integer) event.getNewValue();
		rol.setCodigoRol(codigoRol);
		rol = gestionRoles.consultar_PorCodigoRol(rol);
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public String getDas() {
		return das;
	}

	public void setDas(String das) {
		this.das = das;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getInicio() {
		return inicio;
	}

	public void setInicio(Integer inicio) {
		this.inicio = inicio;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public List<Roles> getListaRoles() {
		return listaRoles;
	}

	public void setListaRoles(List<Roles> listaRoles) {
		this.listaRoles = listaRoles;
	}

	public Integer getCodigoRol() {
		return codigoRol;
	}

	public void setCodigoRol(Integer codigoRol) {
		this.codigoRol = codigoRol;
	}

	// ACCESORES PARA SPRING
	public void setGestionUsuarios(IGestion_Usuarios gestionUsuarios) {
		this.gestionUsuarios = gestionUsuarios;
	}

	public void setGestionRoles(IGestion_Roles gestionRoles) {
		this.gestionRoles = gestionRoles;
	}

	public void setUtil(Utilidades util) {
		this.util = util;
	}
}
