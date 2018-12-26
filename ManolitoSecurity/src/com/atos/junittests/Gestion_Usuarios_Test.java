package com.atos.junittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atos.hibernate.dto.Roles;
import com.atos.hibernate.dto.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;

public class Gestion_Usuarios_Test {
	private Usuarios defaultUser = new Usuarios("Javier", "A704755", "Gonzalez", "clem", false, new Roles(99), true);
	private IGestion_Usuarios gestion;
	private ApplicationContext ctx;
	
	public Gestion_Usuarios_Test() {
		ctx = new ClassPathXmlApplicationContext("com/atos/spring/modelo.xml");
	}
	
	@Before
	public void restartGestion() {
		gestion = ctx.getBean(IGestion_Usuarios.class);
	}

	@Test
	public void testConsultar_PorDas() {
		Usuarios got;
		
		gestion.alta_Usuario(defaultUser);
		got = gestion.consultar_PorDas(defaultUser.getDas());
		gestion.baja_Usuario(got);
		
		assertEquals(defaultUser.getDas(), got.getDas());
	}

	@Test
	public void testConsultar_Todos() {
		List<Usuarios> got;
		got = gestion.consultar_Todos();		
		
		assertEquals(got.size(), 1);
		//2, 1 para un elemento estatico que SIEMPRE ha de estar en la tabla
		//como referencia y el otro para el ejemplo metido ya de antemano.
	}

	@Test
	public void testConsultar_PorClaveYDAS() {
		Usuarios newInicial = new Usuarios("Javier", "A704753", "Gonzalez", "clem", false, new Roles(99), true);
		Usuarios got;
		
		gestion.alta_Usuario(newInicial);
		got = gestion.consultar_PorClaveYDAS(newInicial.getDas(), newInicial.getPassword());
		gestion.baja_Usuario(newInicial);
		
		assertEquals(got.getDas(), newInicial.getDas());
	}
	
	@Test
	public void testModificacion_Usuario() {
		Usuarios modifiedUser = defaultUser;
		modifiedUser.setPassword("22");
		Usuarios result;
		
		gestion.alta_Usuario(defaultUser);
		gestion.modificacion_Usuario(modifiedUser);
		result = gestion.consultar_PorDas(defaultUser.getDas());
		gestion.baja_Usuario(defaultUser);
		
		assertEquals(result.getPassword(), modifiedUser.getPassword());
	}

}