package com.atos.springSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@EnableWebSecurity
//@Configuration
//@ImportResource("classpath:/com/atos/spring/modelo.xml")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//Encriptado de contrasena
		auth.authenticationProvider(authProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		//Permisos segun rol, pagina de login y logout y sesion
		http
			.csrf().disable()
			//"/javax.faces.resource/**" para que spring security tenga accesso a la carpeta de estilos de icefaces y los muestre bien
			.authorizeRequests().antMatchers("/javax.faces.resource/**","/login.xhtml", "/expired/**").permitAll()
				.antMatchers("/admin/**").hasAuthority("ADMIN")
				.antMatchers("/user/**").hasAnyAuthority("ADMIN","USER")
				.antMatchers("/error/**").hasAnyAuthority("ADMIN","USER")
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login.xhtml") //Pagina de login
				.loginProcessingUrl("/doLogin") //importante, es la url request del servlet de login de spring security (post)
				.failureUrl("/error/error.html")
				.successHandler(customAuthenticationSuccessHandler()) //Clase para redireccionar
				.usernameParameter("username").passwordParameter("password") //Parametros del login
			.and()
				//Timeout invalida sesion, logout no
				.logout().logoutUrl("/logout").logoutSuccessUrl("/login.xhtml").invalidateHttpSession(false).deleteCookies("JSESSIONID")
				
			.and()
				.exceptionHandling().accessDeniedPage("/error/forbidden.xhtml");

			//proteccion contra ataques de secuestro de sesion cuando el usuario vuelve a hacer log in. (no va)
		http
			.sessionManagement().invalidSessionUrl("/expired/expired.xhtml");
			//.sessionFixation().migrateSession().maximumSessions(1).expiredUrl("/expired/expired.xhtml"); //Pagina para cuando expire la sesion
	}
	
	//Bean de la clase para encriptar la contrasena
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider daoAuth = new DaoAuthenticationProvider();
		daoAuth.setPasswordEncoder(encoder());
		daoAuth.setUserDetailsService(userService); // mi servicio que debe implementar UserDetailsService, solo se usa
													// para logueo
	    return daoAuth;
	}
	
	//Bean de la clase para redirecciones
	@Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler(){
        return new CustomUrlAuthenticationSuccessHandler();
    }

	//Bean para activar el control simultaneo de la sesion
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
}