package com.atos.hibernate;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "tareas")
public class Tareas {
	private Set<Roles> roles = new HashSet<Roles>(0);
	private Integer codigo_tarea;
	private String nombre_tarea;
	private String desc;
	private Integer estado;

	public Tareas() {
		super();
	}

	public Tareas(String nombre_tarea, String desc, Integer estado) {
		super();
		this.nombre_tarea = nombre_tarea;
		this.desc = desc;
		this.estado = estado;
	}

	public Tareas(Integer codigo_tarea, String nombre_tarea, String desc, Integer estado) {
		super();
		this.codigo_tarea = codigo_tarea;
		this.nombre_tarea = nombre_tarea;
		this.desc = desc;
		this.estado = estado;
	}

	public Tareas(Integer codigo_tarea, String nombre_tarea, String desc, Integer estado, Set<Roles> roles) {
		super();
		this.codigo_tarea = codigo_tarea;
		this.nombre_tarea = nombre_tarea;
		this.desc = desc;
		this.estado = estado;
		this.roles = roles;
	}

	@Id
	@Column(name = "codigo_tarea", unique = true, nullable = false, precision = 5, scale = 0)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getCodigo_tarea() {
		return codigo_tarea;
	}

	public void setCodigo_tarea(Integer codigo_tarea) {
		this.codigo_tarea = codigo_tarea;
	}

	@Column(name = "tarea", length = 100)
	public String getNombre_tarea() {
		return nombre_tarea;
	}

	public void setNombre_tarea(String nombre_tarea) {
		this.nombre_tarea = nombre_tarea;
	}

	@Column(name = "descTarea", length = 200)
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Column(name = "estado", nullable = false, precision = 1, scale = 0)
	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	
	
	// ojito aqui hay que revisar esta mierda campo schema dentro a la anotacion
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "roles_tareas", joinColumns = {
			@JoinColumn(name = "codigo_tarea", nullable = false, updatable = true) }, inverseJoinColumns = {
					@JoinColumn(name = "codigo_rol", nullable = false, updatable = true) })
	public Set<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}

}
