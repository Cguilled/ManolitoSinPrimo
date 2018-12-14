package com.atos.util;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Utilidad para el acceso a los distintos contextos necesarios dentro de la
 * aplicacion. Aprovecha el echo de que los accesos a los mismo son todos
 * objetos estaticos y los expone de la misma forma estatica.
 * 
 * @author Juan Antonio Solves Garcia
 * @version 2.0
 * @since 2-6-2017.
 */
@Component("accesos_contextos")
@Scope("prototype")
public class Acceso_Contextos implements IAcceso_Contextos, Serializable {

	/**
	 * Metodo de conveniencia para la creacion de mensajes solo con sumario y con
	 * nivel INFO por defecto. Mirar {@link Acceso_Contextos.addMensaje}
	 * 
	 * @param texto_sumario
	 *            Texto del mensaje para ser mostrado en la vista.
	 * @param identificador
	 *            Identificador del mensaje.
	 */
	@Override
	public void addMensaje(String texto_sumario, String identificador) {
		FacesContext.getCurrentInstance().addMessage(identificador, new FacesMessage(texto_sumario));
	}

	/**
	 * Metodo de conveniencia para la inclusion de mensajes en los managedbean sin
	 * tener que llamar todo el rato al facescontext.
	 * 
	 * @param mensaje_sumario
	 *            Texto para el sumario del mensaje, no es obligatorio.
	 * @param mensaje_detalle
	 *            Texto para el detalle del mensaje, no es obligatorio.
	 * @param identificador
	 *            Identificador del mensaje, para luego ser llamado en la vista.
	 * @param nivel
	 *            Nivel de resalte del mensaje, no es obligatorio.
	 */
	@Override
	public void addMensaje(String mensaje_sumario, String mensaje_detalle, Severity nivel, String identificador) {
		// ESTABLECEMOS EL NIVEL POR DEFECTO, SI ES NECESARIO
		if (nivel == null) {
			nivel = INFO;
		}
		// GENERAMOS EL MENSAJE
		FacesContext.getCurrentInstance().addMessage(identificador,
				new FacesMessage(nivel, mensaje_sumario, mensaje_detalle));
	}

	/**
	 * Metodo de conveniencia para la inclusion de mensajes en los managedbean sin
	 * tener que llamar todo el rato al facescontext.<br/>
	 * En esta metodo no se envian los textos, solo las claves para los properties.
	 * Por lo tanto es necesario crear el ResourceBundle que lea del properties
	 * correspondiente.
	 * 
	 * @param mensaje_sumario
	 *            Clave para el properties que sera el sumario del mensaje, no es
	 *            obligatorio.
	 * @param mensaje_detalle
	 *            Clave para el properties que sera el para el detalle del mensaje,
	 *            no es obligatorio.
	 * @param identificador
	 *            Identificador del mensaje, para luego ser llamado en la vista.
	 * @param nivel
	 *            Nivel de resalte del mensaje, no es obligatorio.
	 */
	@Override
	public void addMensaje_Idioma(String clave_sumario, String clave_detalle, Severity nivel, String identificador) {
		try {
			// CREO EL RB PARA LEER DEL PROPERTIES ADECUADO
			ResourceBundle rb = ResourceBundle.getBundle((String) getSesion().getAttribute("idioma_seleccionado"));
			// COMPRUEBO QUE NO VENGAN VACIOS O NULOS
			String sumario = "";
			String detalle = "";
			// SE RECUPERA EL TEXTO EN EL IDIOMA PREFERIDO POR EL USUARIO
			if (clave_detalle != null && !clave_detalle.equals("")) {
				detalle = rb.getString(clave_detalle);
			}
			if (clave_sumario != null && !clave_sumario.equals("")) {
				sumario = rb.getString(clave_sumario);
			}
			// ESTABLECEMOS EL NIVEL POR DEFECTO, SI ES NECESARIO
			if (nivel == null) {
				nivel = INFO;
			}
			// GENERAMOS EL MENSAJE
			FacesContext.getCurrentInstance().addMessage(identificador, new FacesMessage(nivel, sumario, detalle));
		} catch (Exception e) {
			System.out.println("NO EXISTE PROPERTIES DEFINIDO. REVISAR ATRIBUTO DE SESION IDIOMA_ELEGIDO");
		}
	}

	/**
	 * Metodo de conveniencia para la inclusion de mensajes en los managedbean sin
	 * tener que llamar todo el rato al facescontext.<br/>
	 * En esta metodo no se envian los textos, solo las claves para los properties.
	 * Por lo tanto es necesario crear el ResourceBundle que lea del properties
	 * correspondiente.
	 * 
	 * @param clave_sumario
	 *            Clave para el properties que sera el sumario del mensaje, no es
	 *            obligatorio.
	 * 
	 * @param valores_sumario
	 *            Tabla de String para la personalizacion del mensaje. La logica es
	 *            posicional, cada variable esta numerada y coincidira con la
	 *            posicion en la tabla.
	 * @param clave_detalle
	 *            Clave para el properties que sera el para el detalle del mensaje,
	 *            no es obligatorio.
	 * @param valores_detalle
	 *            Tabla de String para la personalizacion del mensaje. La logica es
	 *            posicional, cada variable esta numerada y coincidira con la
	 *            posicion en la tabla.
	 * @param identificador
	 *            Identificador del mensaje, para luego ser llamado en la vista.
	 * @param nivel
	 *            Nivel de resalte del mensaje, no es obligatorio.
	 */
	@Override
	public void addMensaje_Idioma(String clave_sumario, String valores_sumario[], String clave_detalle,
			String valores_detalle[], Severity nivel, String identificador) {
		try {
			// CREO EL RB PARA LEER DEL PROPERTIES ADECUADO
			ResourceBundle rb = ResourceBundle.getBundle((String) getSesion().getAttribute("idioma_seleccionado"));
			// COMPRUEBO QUE NO VENGAN VACIOS O NULOS
			String sumario = "";
			String detalle = "";
			// SE RECUPERA EL TEXTO EN EL IDIOMA PREFERIDO POR EL USUARIO
			if (clave_detalle != null && !clave_detalle.equals("")) {
				detalle = rb.getString(clave_detalle);
			}
			if (clave_sumario != null && !clave_sumario.equals("")) {
				sumario = rb.getString(clave_sumario);
			}
			// SUSTITUCION DE VARIABLES, SI LOS HAY, EN LOS TEXTOS PARA SU
			// PERSONALIZACION
			if (valores_sumario != null && valores_sumario.length > 0) {
				sumario = MessageFormat.format(sumario, valores_sumario);
			}
			if (valores_detalle != null && valores_detalle.length > 0) {
				detalle = MessageFormat.format(detalle, valores_detalle);
			}
			// ESTABLECEMOS EL NIVEL POR DEFECTO, SI ES NECESARIO
			if (nivel == null) {
				nivel = INFO;
			}
			// GENERAMOS EL MENSAJE
			FacesContext.getCurrentInstance().addMessage(identificador, new FacesMessage(nivel, sumario, detalle));

		} catch (Exception e) {
			System.out.println("NO EXISTE PROPERTIES DEFINIDO. REVISAR ATRIBUTO DE SESION IDIOMA_ELEGIDO");
		}
	}

	/**
	 * Proceso de conveniencia para recoger el valor de un atributo de sesion.
	 * 
	 * @param nombre_atributo
	 * @return
	 */
	@Override
	public Object getAtributo(String nombre_atributo) {
		return getSesion().getAttribute(nombre_atributo);
	}

	/**
	 * Proceso de conveniencia para dar valor a un atributo de sesion.
	 * 
	 * @param nombre_atributo
	 * @return
	 */
	@Override
	public void setAtributo(String nombre_atributo, Object objeto_valor) {
		getSesion().setAttribute(nombre_atributo, objeto_valor);
	}

	/**
	 * Devuelve la sesion si existe. De no existir no devuelve nada, no crea una
	 * nueva.
	 * 
	 * @return Sesion en curso. {@link HttpSession}
	 */
	@Override
	public HttpSession getSesion() {
		HttpSession salida = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return salida;
	}

	/**
	 * Devuelve la peticion de en curso.
	 * 
	 * @return Peticion en curso. {@link HttpServletRequest}
	 */
	@Override
	public HttpServletRequest getPeticion() {
		HttpServletRequest peticion = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		return peticion;
	}

	/**
	 * Devuelve el contexto de aplicacion correspondiente.
	 * 
	 * @return Contexto de aplicion JEE. {@link ServletContext}
	 */
	@Override
	public ServletContext getAplicacion() {
		ServletContext aplicacion = ((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
				.getSession(false)).getServletContext();
		return aplicacion;
	}

	/**
	 * Devuelve la respuesta de en curso.
	 * 
	 * @return Respuesta en curso. {@link HttpServletResponse}
	 */
	@Override
	public HttpServletResponse getRespuesta() {
		HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		return respuesta;
	}

	/**
	 * Devuelve el applicationContext de spring para su uso.
	 * 
	 * @return ApplicationContext de spring. {@link ApplicationContext}
	 */
	@Override
	public ApplicationContext getApplication_Context() {
		ApplicationContext application = WebApplicationContextUtils.getWebApplicationContext(getAplicacion());
		return application;
	}

}
