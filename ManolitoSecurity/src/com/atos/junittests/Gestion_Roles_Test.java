package com.atos.junittests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atos.hibernate.dto.Roles;
import com.atos.hibernate.dto.Tareas;
import com.atos.hibernate.modelo.IGestion_Roles;

public class Gestion_Roles_Test {
	private Roles test = new Roles(new Integer(7), "rol ejemplo", null, null);
	private IGestion_Roles gestion;
	private ApplicationContext ctx;
	
	public Gestion_Roles_Test() {
		ctx = new ClassPathXmlApplicationContext("com/atos/spring/modelo.xml");
	}
	
	@Before
	public void restartGestion() {
		gestion = ctx.getBean(IGestion_Roles.class);
	}
	
	@Test
	public void testConsultar_Todos() {
		int size = gestion.consultar_Todos().size();
		
		assertEquals(size, 1);
	}
	
	@Test
	public void testGeneral_Rol() {
		Roles modified = test;
		modified.setNombreRol("Esto es el nombre #1");
		Roles result;
		
		gestion.alta_Rol(test);
		gestion.modificacion_Rol(modified);
		result = gestion.consultar_PorCodigoRol(modified);
		gestion.baja_Rol(modified);
		
		assertEquals(result.getNombreRol(), modified.getNombreRol());
	}

}
