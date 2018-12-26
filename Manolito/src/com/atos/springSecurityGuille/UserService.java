package com.atos.springSecurityGuille;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.atos.hibernate.dto.Roles;
import com.atos.hibernate.dto.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;

@Service
public class UserService implements UserDetailsService {

	// Inyecto un objecto de gestionUsuarios
	@ManagedProperty("#{gestionUsuarios}")
	private IGestion_Usuarios gestionUsuarios;

	@Autowired
	private BCryptPasswordEncoder encoder;

	private Roles roles;
	UserDetails userDetails;

	@Override
	public UserDetails loadUserByUsername(String das) throws UsernameNotFoundException {
		//Consulto el usuario a traves del das introducido en el login
		Usuarios usuario = gestionUsuarios.consultar_PorDas(das);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		if (usuario != null) {
			// authorities a partir de roles
			roles = usuario.getRoles();

			GrantedAuthority ga = new GrantedAuthority() {

				@Override
				public String getAuthority() {
					return roles.getNombreRol();
				}
			};
			//Anado los roles del usuario a la lista de tipo Authority
			authorities.add(ga);

			//Paso los datos del usuario a userDetails para que sean validados contra la bbdd
			//Condificando la pass
			userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(
					usuario.getDas(), encoder.encode(usuario.getPassword()), authorities);
			
		} else
			System.out.println("no existe el usuario");

		return userDetails;

	}

	public void setGestionUsuarios(IGestion_Usuarios gestionUsuarios) {
		this.gestionUsuarios = gestionUsuarios;
	}

}