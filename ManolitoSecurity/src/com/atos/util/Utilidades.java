package com.atos.util;

import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atos.hibernate.dto.Tareas;

@Component("utilidades")
@Scope("prototype")
public class Utilidades implements IUtilidades {

	private SessionFactory sessionFactory;

	// Spring iniciará el SessionFactory
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	/* (non-Javadoc)
	 * @see com.atos.util.IUtilidades#randomPassword()
	 */
	@Override
	public String randomPassword() {
		// ArrayList que sera la contrasena final
		ArrayList<String> caracter = new ArrayList<String>();
		String pass = "";

		// Array con los caracteres que formaran la contrasena
		String[] caracteres = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
				"r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
				"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6",
				"7", "8", "9" };

		// Hasta 10 porque en la bbdd la contrasena tiene longitud 10
		for (int i = 0; i < 10; i++) {
			pass += caracteres[(int) (Math.floor(Math.random() * caracteres.length))];
		}

		return pass;
	}

	// Consultar del codigo de la tarea de la tabla Roles_Tareas
	/* (non-Javadoc)
	 * @see com.atos.util.IUtilidades#consultarCodigoTarea(com.atos.hibernate.dto.Tareas)
	 */
	@Override
	public Integer consultarCodigoTarea(Tareas transientInstance) {
		Integer codigoTarea = null;
		try {
			Query q = getCurrentSession().createQuery("SELECT CODIGO_TAREA FROM TAREAS WHERE CODIGO_TAREA = ?");
			q.setInteger(0, transientInstance.getId_Tarea());
			codigoTarea = (Integer) q.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codigoTarea;
	}
	
	/*public static void refresh() {
		System.out.println("refrejcando");
		FacesContext contexto = FacesContext.getCurrentInstance();
		String refreshpage = contexto.getViewRoot().getViewId();
		javax.faces.application.ViewHandler handler = contexto.getApplication().getViewHandler();
		javax.faces.component.UIViewRoot root = handler.createView(contexto, refreshpage);
		root.setViewId(refreshpage);
		contexto.setViewRoot(root);
		System.out.println("refrejcado");
	}*/
	
	@RequestMapping(value="/logout", method = RequestMethod.POST)
	public static String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){   
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }
	
}