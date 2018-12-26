package com.atos.managedbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

import org.icefaces.ace.model.DefaultMenuModel;

import org.springframework.dao.DataAccessException;

@ManagedBean(name = "menubar_bean")
@ViewScoped
public class MenuBar_Bean implements Serializable, ActionListener {
	public DefaultMenuModel modelo;
	private String siguiente;
//	MenuItem usuarios;
//	MenuItem tareas;
//	MenuItem roles;
//	MenuItem consultar;
//	MenuItem finSesion;

	private boolean visible_roles;
	private boolean visible_usuarios;
	private boolean visible_tareas;

	/*
	 * public void buildMenuModel() { modelo = new DefaultMenuModel(); usuarios =
	 * new MenuItem(); tareas = new MenuItem(); roles = new MenuItem(); consultar =
	 * new MenuItem(); finSesion = new MenuItem();
	 * 
	 * usuarios.setId("itemUsuarios"); usuarios.setValue("Usuarios");
	 * usuarios.addActionListener(this);
	 * 
	 * tareas.setId("itemTareas"); tareas.setValue("Tareas");
	 * tareas.addActionListener(this);
	 * 
	 * roles.setId("itemRoles"); roles.setValue("Roles");
	 * roles.addActionListener(this);
	 * 
	 * consultar.setId("itemConsultar"); consultar.setValue("Consultar");
	 * consultar.addActionListener(this);
	 * 
	 * finSesion.setId("itemFinSesion"); finSesion.setValue("FinSesion");
	 * finSesion.addActionListener(this); }
	 */

	@PostConstruct
	public void valores_Iniciales() {
		siguiente = "";
		visible_roles = false;
		visible_usuarios = false;
		visible_tareas = false;
	}

	@Override
	public void processAction(ActionEvent arg0) throws AbortProcessingException {
		// TODO Auto-generated method stub

	}

	public void fragmentUsuarios(ActionEvent evento) {
		try {
			visible_usuarios = true;
			formUsuarios();
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public void fragmentTareas(ActionEvent evento) {
		try {
			visible_tareas = true;
			formTareas();
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public void fragmentRoles(ActionEvent evento) {
		try {
			visible_roles = true;
			formRoles();
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public String formUsuarios() {
		System.out.println("Redirigiendo a Usuarios.xhtml");
		siguiente = "/admin/Usuarios.xhtml";
		return "";
	}

	public String formTareas() {
		System.out.println("Redirigiendo a altaTareas.xhtml");
		siguiente = "/admin/altaTareas.xhtml";
		return siguiente;
	}

	public String formRoles() {
		System.out.println("Redirigiendo a Roles.xhtml");
		siguiente = "/admin/Roles.xhtml";
		return siguiente;
	}

	public String getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(String siguiente) {
		this.siguiente = siguiente;
	}

	public boolean isVisible_roles() {
		return visible_roles;
	}

	public boolean isVisible_usuarios() {
		return visible_usuarios;
	}

	public boolean isVisible_tareas() {
		return visible_tareas;
	}

	public void setVisible_roles(boolean visible_roles) {
		this.visible_roles = visible_roles;
	}

	public void setVisible_usuarios(boolean visible_usuarios) {
		this.visible_usuarios = visible_usuarios;
	}

	public void setVisible_tareas(boolean visible_tareas) {
		this.visible_tareas = visible_tareas;
	}
}
