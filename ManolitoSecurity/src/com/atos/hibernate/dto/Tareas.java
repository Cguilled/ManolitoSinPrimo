package com.atos.hibernate.dto;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TAREAS")
public class Tareas implements java.io.Serializable {
	// Fields

	private Integer id_Tarea;
	private String nombre_Tarea;
	private String descripcion_Tarea;
	private boolean estado;
	private Set<Roles> roleses = new HashSet<Roles>(0);

	// Constructors

	/** default constructor */
	public Tareas() {
	}

	/** minimal constructor */
	public Tareas(Integer id_Tarea) {
		this.id_Tarea = id_Tarea;
	}

	public Tareas(String nombre_Tarea, String descripcion_Tarea, boolean estado) {
		super();
		this.nombre_Tarea = nombre_Tarea;
		this.descripcion_Tarea = descripcion_Tarea;
		this.estado = estado;
	}

	public Tareas(Integer id_Tarea, String nombre_Tarea, String descripcion_Tarea, boolean estado) {
		super();
		this.id_Tarea = id_Tarea;
		this.nombre_Tarea = nombre_Tarea;
		this.descripcion_Tarea = descripcion_Tarea;
		this.estado = estado;
	}

	/** full constructor */
	public Tareas(Integer id_Tarea, String nombre_Tarea, String descripcion_Tarea, boolean estado,
			Set<Roles> roleses) {
		this.id_Tarea = id_Tarea;
		this.nombre_Tarea = nombre_Tarea;
		this.descripcion_Tarea = descripcion_Tarea;
		this.estado = estado;
		this.roleses = roleses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_TAREA", unique = true, nullable = false, precision = 2, scale = 0)
	public Integer getId_Tarea() {
		return id_Tarea;
	}

	public void setId_Tarea(Integer id_Tarea) {
		this.id_Tarea = id_Tarea;
	}
	
	@Column(name = "NOMBRE_TAREA", nullable = true, length = 100)
	public String getNombre_Tarea() {
		return nombre_Tarea;
	}

	public void setNombre_Tarea(String nombre_Tarea) {
		this.nombre_Tarea = nombre_Tarea;
	}
	
	@Column(name = "DESCRIPCION_TAREA", nullable = true, length = 2000)
	public String getDescripcion_Tarea() {
		return descripcion_Tarea;
	}

	public void setDescripcion_Tarea(String descripcion_Tarea) {
		this.descripcion_Tarea = descripcion_Tarea;
	}
	
	@Column(name = "ESTADO", nullable = false)
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ROLES_TAREAS", joinColumns = { @JoinColumn(name = "CODIGO_TAREAS", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "CODIGO_ROL", nullable = false, updatable = false) })
	public Set<Roles> getRoleses() {
		return this.roleses;
	}

	public void setRoleses(Set<Roles> roleses) {
		this.roleses = roleses;
	}

}