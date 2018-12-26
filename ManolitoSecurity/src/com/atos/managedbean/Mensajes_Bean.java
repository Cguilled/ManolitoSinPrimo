package com.atos.managedbean;

import java.io.Serializable;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.web.context.annotation.SessionScope;

@ManagedBean(name = "mensajes_bean")
@ViewScoped
public class Mensajes_Bean implements Serializable {

	// Propiedades del bean
	private String autoHide = "true";
	private boolean closeAll = true;
	private int maxVisibleMessages = 100;
	private String position = "top-right";
	private String header = "Error de validación";
	private int displayDuration = 5000;
	private int mode;

	public final int PASSWORD_MISMATCH = 1;
	public final int PASSWORD_CHANGED = 2;
	public final int SUCCESSFUL_LOGIN = 3;
	public final int FAILED_LOGIN = 4;

	// Getters y setters
	public String getAutoHide() {
		return autoHide;
	}

	public void setAutoHide(String autoHide) {
		this.autoHide = autoHide;
	}

	public boolean isCloseAll() {
		return closeAll;
	}

	public void setCloseAll(boolean closeAll) {
		this.closeAll = closeAll;
	}

	public int getMaxVisibleMessages() {
		return maxVisibleMessages;
	}

	public void setMaxVisibleMessages(int maxVisibleMessages) {
		this.maxVisibleMessages = maxVisibleMessages;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public int getDisplayDuration() {
		return displayDuration;
	}

	public void setDisplayDuration(int displayDuration) {
		this.displayDuration = displayDuration;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public void showLoginSuccess() {
		FacesContext.getCurrentInstance().addMessage("Iniciando sesión...", new FacesMessage("Iniciando sesión..."));
	}

	public void showPasswordMismatch() {
		FacesContext.getCurrentInstance().addMessage("Las contraseñas no coinciden",
				new FacesMessage("Las contraseñas no coinciden"));
	}

	public void showPasswordChangeSuccess() {
		FacesContext.getCurrentInstance().addMessage("Cambiando contraseña...", new FacesMessage("Cambiando contraseña..."));
	}

	public void messageListener(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		// Eliminar mensajes existentes
		Iterator<FacesMessage> i = facesContext.getMessages();
		while (i.hasNext()) {
			i.next();
			i.remove();
		}

		UIComponent component = event.getComponent();

		if (mode == SUCCESSFUL_LOGIN) {
			String message = "Iniciando sesión...";
			setHeader("Info");
			FacesMessage facesMessage = new FacesMessage(message, message);
			facesContext.addMessage(component.getClientId(), facesMessage);
		}

		else if (mode == PASSWORD_CHANGED) {
			String message = "Contraseña cambiada";
			setHeader("Info");
			FacesMessage facesMessage = new FacesMessage(message, message);
			facesContext.addMessage(component.getClientId(), facesMessage);
		}

		else if (mode == PASSWORD_MISMATCH) {
			String message = "Las contraseñas no coinciden";
			FacesMessage facesMessage = new FacesMessage((FacesMessage.Severity) FacesMessage.VALUES.get(2), message,
					message);
			facesContext.addMessage(component.getClientId(), facesMessage);
		}

		else if (mode == FAILED_LOGIN) {
			String message = "Fallo al iniciar sesión";
			FacesMessage facesMessage = new FacesMessage((FacesMessage.Severity) FacesMessage.VALUES.get(2), message,
					message);
			facesContext.addMessage(component.getClientId(), facesMessage);
		}
		// Anadir mensajes
		String message = "Iniciando sesión...";
		FacesContext.getCurrentInstance().addMessage(message, new FacesMessage(message));
	}
}