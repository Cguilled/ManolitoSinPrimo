package com.atos.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public interface INiveles_FacesContext {

	public static Severity INFO = FacesMessage.SEVERITY_INFO;
	public static Severity WARN = FacesMessage.SEVERITY_WARN;
	public static Severity ERROR = FacesMessage.SEVERITY_ERROR;
	public static Severity FATAL = FacesMessage.SEVERITY_FATAL;

}
