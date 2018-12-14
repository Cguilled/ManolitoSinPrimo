package com.atos.util;

import javax.faces.application.FacesMessage.Severity;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;

public interface IAcceso_Contextos extends INiveles_FacesContext {

	/**
	 * Metodo de conveniencia para la inclusion de mensajes en los managedbean
	 * sin tener que llamar todo el rato al facescontext.
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
	public void addMensaje(String mensaje_sumario, String mensaje_detalle,
			Severity nivel, String identificador);

	/**
	 * Metodo de conveniencia para la inclusion de mensajes en los managedbean
	 * sin tener que llamar todo el rato al facescontext.<br/>
	 * En esta metodo no se envian los textos, solo las claves para los
	 * properties. Por lo tanto es necesario crear el ResourceBundle que lea del
	 * properties correspondiente.
	 * 
	 * @param mensaje_sumario
	 *            Clave para el properties que sera el sumario del mensaje, no
	 *            es obligatorio.
	 * @param mensaje_detalle
	 *            Clave para el properties que sera el para el detalle del
	 *            mensaje, no es obligatorio.
	 * @param identificador
	 *            Identificador del mensaje, para luego ser llamado en la vista.
	 * @param nivel
	 *            Nivel de resalte del mensaje, no es obligatorio.
	 */
	public void addMensaje_Idioma(String clave_sumario, String clave_detalle,
			Severity nivel, String identificador);

	/**
	 * Metodo de conveniencia para la inclusion de mensajes en los managedbean
	 * sin tener que llamar todo el rato al facescontext.<br/>
	 * En esta metodo no se envian los textos, solo las claves para los
	 * properties. Por lo tanto es necesario crear el ResourceBundle que lea del
	 * properties correspondiente.
	 * 
	 * @param clave_sumario
	 *            Clave para el properties que sera el sumario del mensaje, no
	 *            es obligatorio.
	 * 
	 * @param valores_sumario
	 *            Tabla de String para la personalizacion del mensaje. La logica
	 *            es posicional, cada variable esta numerada y coincidira con la
	 *            posicion en la tabla.
	 * @param clave_detalle
	 *            Clave para el properties que sera el para el detalle del
	 *            mensaje, no es obligatorio.
	 * @param valores_detalle
	 *            Tabla de String para la personalizacion del mensaje. La logica
	 *            es posicional, cada variable esta numerada y coincidira con la
	 *            posicion en la tabla.
	 * @param identificador
	 *            Identificador del mensaje, para luego ser llamado en la vista.
	 * @param nivel
	 *            Nivel de resalte del mensaje, no es obligatorio.
	 */
	public void addMensaje_Idioma(String clave_sumario,
			String valores_sumario[], String clave_detalle,
			String valores_detalle[], Severity nivel, String identificador);

	/**
	 * Proceso de conveniencia para recoger el valor de un atributo de sesion.
	 * 
	 * @param nombre_atributo
	 * @return
	 */
	public Object getAtributo(String nombre_atributo);

	/**
	 * Proceso de conveniencia para dar valor a un atributo de sesion.
	 * 
	 * @param nombre_atributo
	 * @return
	 */
	public void setAtributo(String nombre_atributo, Object objeto_valor);

	/**
	 * Devuelve la sesion si existe. De no existir no devuelve nada, no crea una
	 * nueva.
	 * 
	 * @return Sesion en curso. {@link HttpSession}
	 */
	public HttpSession getSesion();

	/**
	 * Devuelve la peticion de en curso.
	 * 
	 * @return Peticion en curso. {@link HttpServletRequest}
	 */
	public HttpServletRequest getPeticion();

	/**
	 * Devuelve el contexto de aplicacion correspondiente.
	 * 
	 * @return Contexto de aplicion JEE. {@link ServletContext}
	 */
	public ServletContext getAplicacion();

	/**
	 * Devuelve la respuesta de en curso.
	 * 
	 * @return Respuesta en curso. {@link HttpServletResponse}
	 */
	public HttpServletResponse getRespuesta();

	/**
	 * Devuelve el applicationContext de spring para su uso.
	 * 
	 * @return ApplicationContext de spring. {@link ApplicationContext}
	 */
	public ApplicationContext getApplication_Context();

	void addMensaje(String texto_sumario, String identificador);

}