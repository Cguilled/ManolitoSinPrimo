package com.atos.hibernate.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIOS")
public class Usuarios implements java.io.Serializable {

	// Fields
	private String das;
	private String nombre;
	private String apellido;
	private String password;
	private Boolean estado;
	private Roles roles;
	private Boolean inicio;

	// Constructors

	/** default constructor */
	public Usuarios() {
	}

	/** minimal constructor */
	public Usuarios(String das) {
		this.das = das;
	}

	/** full constructor */
	public Usuarios(String das, String nombre, String apellido,
			String password, Boolean estado, Roles roles, Boolean inicio) {
		this.das = das;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.estado = estado;
		this.roles = roles;
		this.inicio = inicio;
	}

	// Property accessors
	//@GeneratedValue(strategy = GenerationType.)

	@Column(name = "APELLIDO", nullable = false, length = 20)
	public String getApellido() {
		return apellido;
	}

	@Id
	@Column(name = "DAS", unique = true, nullable = false, length = 15)
	public String getDas() {
		return das;
	}

	public void setDas(String das) {
		this.das = das;
	}

	@Column(name = "NOMBRE", nullable = false, length = 20)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Column(name = "PASSWORD", nullable = false, length = 10)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "ESTADO", nullable = false)
	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	@Column(name = "INICIO", nullable = false)
	public Boolean getInicio() {
		return inicio;
	}

	public void setInicio(Boolean inicio) {
		this.inicio = inicio;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODIGO_ROL")
	public Roles getRoles() {
		return this.roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

}