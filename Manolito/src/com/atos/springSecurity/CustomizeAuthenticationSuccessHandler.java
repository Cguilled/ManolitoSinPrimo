package com.atos.springSecurity;

import java.io.IOException;
import java.util.Collection;

import javax.faces.bean.ManagedProperty;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.atos.hibernate.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;

public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	@ManagedProperty("#{gestion_usuarios}")
	private IGestion_Usuarios gestion_usuarios;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		handle(request, response, authentication);

		// Session timeout
		HttpSession session = request.getSession();
		if (session != null)
			session.setMaxInactiveInterval(10);

		clearAuthenticationAttributes(request);
	}

	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {

		String targetUrl = determineTargetUrl(authentication);
		
		if (response.isCommitted()) {
			System.out.println("La respuesta ya ha sido mandada. Imposible redireccionar a: " + targetUrl);
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(Authentication authentication) {
		boolean isUser = false;
		boolean isAdmin = false;

		Usuarios usuario = gestion_usuarios.consultar_Das(authentication.getName());

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("usuario")) {
				isUser = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("administrador")) {
				isAdmin = true;
				break;
			}
		}

		if (usuario.getAccesoAplicacion() == 0) 
			return "/Login.xhtml?error";
		else {
			if (usuario.getPrimerLogin() == 0) {
				return "/xhtml/CambioPass.xhtml";
			} else {
				if (isUser) {
					return "/xhtml/usuario/inicio.xhtml";
				} else if (isAdmin) {
					return "/xhtml/admin/Administrador2.xhtml";
				} else {
					throw new IllegalStateException();
				}
			}
		}
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public IGestion_Usuarios getGestion_usuarios() {
		return gestion_usuarios;
	}

	public void setGestion_usuarios(IGestion_Usuarios gestion_usuarios) {
		this.gestion_usuarios = gestion_usuarios;
	}

	
	/*
	 protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {

		String targetUrl = determineTargetUrl(authentication);

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(Authentication authentication) {
		boolean isUser = false;
		boolean isAdmin = false;

		Usuarios usuario = gestion_usuarios.consultar_Das(authentication.getName());

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("usuario")) {
				isUser = true;
				break;
			} else if (grantedAuthority.getAuthority().equals("administrador")) {
				isAdmin = true;
				break;
			}
		}

		if (usuario.getAccesoAplicacion() == 0) {
			return "/Login.xhtml?error";
		} else {
			if (usuario.getPrimerLogin() == 0) {
				return "/xhtml/CambioPass.xhtml";
			} else {
				if (isUser) {
					return "/xhtml/usuario/inicio.xhtml";
				} else if (isAdmin) {
					return "/xhtml/admin/Administrador2.xhtml";
				} else {
					throw new IllegalStateException();
				}
			}
		}
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public IGestion_Usuarios getGestion_usuarios() {
		return gestion_usuarios;
	}

	public void setGestion_usuarios(IGestion_Usuarios gestion_usuarios) {
		this.gestion_usuarios = gestion_usuarios;
	}
	 */
	
}
