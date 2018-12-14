	package com.atos.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("mensajes")
@Scope("prototype")
public class Mensajes {

	public void crear_mensajes(String tipo, String texto) {
		
		FacesContext facesContext = null;
		FacesMessage facesMessage = null;
		facesContext = FacesContext.getCurrentInstance();
		
		if (tipo=="info") {
			facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, texto ,null);
			facesContext.addMessage("info" , facesMessage);
		}else {
			facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, texto ,null);
			facesContext.addMessage("error" , facesMessage);
		}
		
	}
	

}
