package com.atos.springSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/javax.faces.resource/**","/Login.xhtml").permitAll()
				.antMatchers("/xhtml/admin/**").hasAuthority("administrador")
				.antMatchers("/xhtml/usuario/**").hasAnyAuthority("administrador", "usuario").anyRequest()
				.authenticated()
				.and()
			.formLogin()
				.loginPage("/Login.xhtml")
				.loginProcessingUrl("/login")
				.successHandler(customizeAuthenticationSuccessHandler())
				.failureUrl("/Login.xhtml?error")
				.usernameParameter("das").passwordParameter("password")
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/Login.xhtml?logout")
				.deleteCookies("remove")
				.invalidateHttpSession(true)
				.and()
				.exceptionHandling().accessDeniedPage("/xhtml/AccessDenied.xhtml")
				.and()
			.sessionManagement()
				.maximumSessions(1).expiredUrl("/Login.xhtml");
			
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public AuthenticationSuccessHandler customizeAuthenticationSuccessHandler() {
		return new CustomizeAuthenticationSuccessHandler();
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
