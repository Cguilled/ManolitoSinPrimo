package com.atos.managedbean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.springframework.dao.DataAccessException;

import com.atos.hibernate.dto.Roles;
import com.atos.hibernate.dto.Tareas;
import com.atos.hibernate.modelo.IGestion_Roles;
import com.atos.hibernate.modelo.IGestion_Tareas;

/**
 * 
 * @author Guillermo Cermeño
 *
 * 09 ago. 2018
 */

@ManagedBean(name = "roles_bean")
@ViewScoped
public class Roles_Bean {
	private Roles rol;
	private String nombre_rol;
	private boolean visible;
	
	@ManagedProperty("#{gestion_roles}")
	private IGestion_Roles gestionRoles;

	@PostConstruct
	public void valores_Iniciales() {
		rol = new Roles();
		nombre_rol = "";
		visible=true;
	}
	
	// EVENTOS
		public void alta_Rol(ActionEvent evento) {
			try {
				gestionRoles.alta_Rol(rol);
				System.out.println("alta correcta");
			} catch (Exception dae) {
				dae.printStackTrace();
			}
		}

		public void baja_Rol(ActionEvent evento) {
			try {
				gestionRoles.baja_Rol(rol);
				System.out.println("baja correcta");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void modificacion_Rol(ActionEvent evento) {
			try {
				gestionRoles.modificacion_Rol(rol);
				System.out.println("modificación correcta");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void consultar_PorCodigo(ActionEvent evento) {
			try {
				if(gestionRoles.consultar_PorCodigoRol(rol) !=null)
					System.out.println("consulta correcta");
				else
					System.out.println("No existe ese id de rol");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	public Roles getRol() {
		return rol;
	}

	public void setRol(Roles rol) {
		this.rol = rol;
	}

	public String getNombre_rol() {
		return nombre_rol;
	}
	
	public void setNombre_rol(String nombre_rol) {
		this.nombre_rol = nombre_rol;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setGestionRoles(IGestion_Roles gestionRoles) {
		this.gestionRoles = gestionRoles;
	}
}