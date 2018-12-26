package com.atos.springSecurity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.atos.hibernate.Roles;
import com.atos.hibernate.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;


@Service("userDetailsService")
public class UserDetails implements UserDetailsService {

	User user;

	@ManagedProperty("#{gestion_usuarios}")
	private IGestion_Usuarios gestion_usuarios;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		Usuarios usu = gestion_usuarios.consultar_conRol(username);

		if(usu!=null) {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Roles rol = usu.getRoles();
		
		GrantedAuthority lista = new GrantedAuthority() {

			@Override
			public String getAuthority() {
				return rol.getDescRol();
			}
		};
		authorities.add(lista);

		user = new User(usu.getDas(),encoder.encode(usu.getPassword()), authorities);
		
		}else {
			System.out.println("Usuario inexistente!");
		}
		
		return user;
	}

	public IGestion_Usuarios getGestion_usuarios() {
		return gestion_usuarios;
	}

	public void setGestion_usuarios(IGestion_Usuarios gestion_usuarios) {
		this.gestion_usuarios = gestion_usuarios;
	}
}