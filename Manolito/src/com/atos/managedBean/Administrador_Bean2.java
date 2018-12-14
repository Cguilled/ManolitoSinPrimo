package com.atos.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.atos.hibernate.Tareas;
import com.atos.hibernate.modelo.IGestion_Tareas;
import com.atos.util.IAcceso_Contextos;
import com.atos.util.Mensajes;

@ManagedBean(name = "administrador_bean2")
@ViewScoped
public class Administrador_Bean2 implements Serializable {

	private static final long serialVersionUID = 1L;

	private Tareas tarea;
	private Tareas tarea_nueva;
	private List<Tareas> lista_tareas;

	@ManagedProperty("#{gestion_tareas}")
	private IGestion_Tareas gestion_tareas;

	@ManagedProperty("#{mensajes}")
	private Mensajes mensaje;

	// propiedades auxiliares test
	private boolean bot_bm;
	private boolean bot_alt;
	private boolean campo_cod;
	private int numero_filas;

	// UTILIDAD GENERAL PARA LA GESTION DE MENSAJES EN MANAGEDBEAN
	@ManagedProperty("#{accesos_contextos}")
	private IAcceso_Contextos accesos_contextos;

	@PostConstruct
	public void valores_Iniciales() {
		tarea = new Tareas();
		tarea_nueva = new Tareas();
		lista_tareas = gestion_tareas.consultar_Todos();
		// ESTADO INICIAL DE LOS BOTONES DEL FORMULARIO
		bot_bm = true;
		bot_alt = false;
		setCampo_cod(false);

	}

	// opcion de alta
	public void alta(ActionEvent evento) {
		
		boolean estado=false;
		if(tarea.getEstado() != null) {
			estado = true;
		}
		
		if (tarea.getDesc() != "" || estado || tarea.getNombre_tarea() != "") {

			Tareas t = gestion_tareas.consultar_Nombre(tarea.getNombre_tarea());

			if (t == null) {
				if (tarea.getCodigo_tarea() != null) {

					tarea_nueva.setNombre_tarea(tarea.getNombre_tarea());
					tarea_nueva.setDesc(tarea.getDesc());
					tarea_nueva.setEstado(tarea.getEstado());

					gestion_tareas.alta_Tarea(tarea_nueva);
					tarea = tarea_nueva;
					
					mensaje.crear_mensajes("info", "El código de la tarea no se puede elegir, es automático");

				} else {

					gestion_tareas.alta_Tarea(tarea);
				}
				
				mensaje.crear_mensajes("info", "Alta realizada");

				bot_alt = true;
				bot_bm = false;
			} else {

				mensaje.crear_mensajes("error", "Error en el alta!");

			}
		} else {
			mensaje.crear_mensajes("error", "Todos los campos menos el código son obligatorios!");
		}

	}

	public void baja(ActionEvent evento) {

		tarea.setEstado(0);
		gestion_tareas.baja_Tarea(tarea);

		mensaje.crear_mensajes("info", "Baja realizada!");

	}

	public void modificacion(ActionEvent evento) {

		gestion_tareas.modificacion_Tarea(tarea);
		mensaje.crear_mensajes("info", "Modificación realizada");

	}

	public void consulta(ActionEvent evento) {

		tarea = gestion_tareas.consultar_Codigo(tarea.getCodigo_tarea());
		if (tarea == null) {

			mensaje.crear_mensajes("error", "Tarea no registrada!");

		} else {

			mensaje.crear_mensajes("info", "Consulta realizada!");

			bot_bm = false;
			bot_alt = true;
			setCampo_cod(true);
		}
	}

	// limapia el formulario
	public void clear(ActionEvent evento) {

		tarea = new Tareas();
		mensaje.crear_mensajes("info", "Formulario limpiado!");
		bot_bm = true;
		bot_alt = false;
		setCampo_cod(false);
	}

	// boton refrescar tabla
	public void refresh_tabla(ActionEvent event) {
		lista_tareas = gestion_tareas.consultar_Todos();
		mensaje.crear_mensajes("info", "Tabla refrescada!");
	}

	public Tareas getTarea_nueva() {
		return tarea_nueva;
	}

	public void setTarea_nueva(Tareas tarea_nueva) {
		this.tarea_nueva = tarea_nueva;
	}

	public Mensajes getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensajes mensaje) {
		this.mensaje = mensaje;
	}

	public Tareas getTarea() {
		return tarea;
	}

	public void setTarea(Tareas tarea) {
		this.tarea = tarea;
	}

	public IGestion_Tareas getGestion_tareas() {
		return gestion_tareas;
	}

	public void setGestion_tareas(IGestion_Tareas gestion_tareas) {
		this.gestion_tareas = gestion_tareas;
	}

	public boolean isBot_bm() {
		return bot_bm;
	}

	public void setBot_bm(boolean bot_bm) {
		this.bot_bm = bot_bm;
	}

	public boolean isBot_alt() {
		return bot_alt;
	}

	public void setBot_alt(boolean bot_alt) {
		this.bot_alt = bot_alt;
	}

	public boolean isCampo_cod() {
		return campo_cod;
	}

	public void setCampo_cod(boolean campo_cod) {
		this.campo_cod = campo_cod;
	}

	public IAcceso_Contextos getAccesos_contextos() {
		return accesos_contextos;
	}

	public void setAccesos_contextos(IAcceso_Contextos accesos_contextos) {
		this.accesos_contextos = accesos_contextos;
	}

	public List<Tareas> getLista_tareas() {
		return lista_tareas;
	}

	public void setLista_tareas(List<Tareas> lista_tareas) {
		this.lista_tareas = lista_tareas;
	}

	public int getNumero_filas() {
		return numero_filas;
	}

	public void setNumero_filas(int numero_filas) {
		this.numero_filas = numero_filas;
	}

}
