package com.atos.managedbean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.springframework.dao.DataAccessException;

import com.atos.hibernate.dto.Tareas;
import com.atos.hibernate.dto.Usuarios;
import com.atos.hibernate.modelo.IGestion_Tareas;
import com.atos.hibernate.modelo.IGestion_Usuarios;

/**
 * 
 * @author Guillermo Cermeño
 *
 * 09 ago. 2018
 */

@ManagedBean(name = "tareas_bean")
@ViewScoped
public class Tareas_Bean {
	private Tareas tarea;
	private String nombre_tarea;
	private String descripcion_Tarea;
	private Integer estado;
	private boolean visible;
	private boolean selected = false;

	@ManagedProperty("#{gestion_tareas}")
	private IGestion_Tareas gestionTareas;

	@PostConstruct
	public void valores_Iniciales() {
		tarea = new Tareas();
		nombre_tarea = "";
		descripcion_Tarea = "";
		estado = 0;
		visible=true;
	}

	public void alta_Tarea(ActionEvent evento) {
		try {
			gestionTareas.alta_Tarea(tarea);
			System.out.println("alta correcta");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public void baja_Tarea(ActionEvent evento) {
		try {
			// NO SE TIENE QUE ELIMINAR LA TAREA SINO CAMBIAR EL CAMPO ESTADO A 0
			gestionTareas.baja_Tarea(tarea);
			System.out.println("baja correcta");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public void modificacion_Tarea(ActionEvent evento) {
		try {
			gestionTareas.modificacion_Tarea(tarea);
			System.out.println("modificación correcta");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}

	public void consultar_PorCodigo(ActionEvent evento) {
		try {
			if(gestionTareas.consultar_PorCodigoTarea(tarea) !=null) {
				System.out.println("consulta correcta");
			}else
				System.out.println("No existe ese id de tarea");
		} catch (DataAccessException dae) {
			dae.printStackTrace();
		}
	}
	
	public String getCheckBoxValue() {
        if(selected)
            return "activa";
        else
            return "inactiva";
    }

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Tareas getTarea() {
		return tarea;
	}

	public void setTarea(Tareas tarea) {
		this.tarea = tarea;
	}

	public String getNombre_tarea() {
		return nombre_tarea;
	}

	public void setNombre_tarea(String nombre_tarea) {
		this.nombre_tarea = nombre_tarea;
	}

	public String getDescripcion_Tarea() {
		return descripcion_Tarea;
	}

	public void setDescripcion_Tarea(String descripcion_Tarea) {
		this.descripcion_Tarea = descripcion_Tarea;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setGestionTareas(IGestion_Tareas gestionTareas) {
		this.gestionTareas = gestionTareas;
	}
}