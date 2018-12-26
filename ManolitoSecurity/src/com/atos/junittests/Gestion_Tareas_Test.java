package com.atos.junittests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atos.hibernate.dto.Roles;
import com.atos.hibernate.dto.Tareas;
import com.atos.hibernate.modelo.IGestion_Tareas;
import com.atos.hibernate.modelo.IGestion_Usuarios;

public class Gestion_Tareas_Test {

	private Tareas test = new Tareas(new Integer(7), "tarea ejemplo", "descripcion de tarea", false, null);
	private IGestion_Tareas gestion;
	private ApplicationContext ctx;
	
	public Gestion_Tareas_Test() {
		ctx = new ClassPathXmlApplicationContext("com/atos/spring/modelo.xml");
	}
	
	@Before
	public void restartGestion() {
		gestion = ctx.getBean(IGestion_Tareas.class);
	}
	
	@Test
	public void testConsultar_PorCodigoTarea() {
		Tareas result;
		
		gestion.alta_Tarea(test);
		result = gestion.consultar_PorCodigoTarea(test);
		gestion.baja_Tarea(test);
		
		assertEquals(result.getId_Tarea(), test.getId_Tarea());
	}

	@Test
	public void testConsultar_Todos() {
		int size = gestion.consultar_Todos().size();
		
		assertEquals(size, 1);
	}

	@Test
	public void testModificacion_Tarea() {
		Tareas modified = test;
		Tareas result;
		modified.setDescripcion_Tarea("Esto es una descripcion");
		
		gestion.alta_Tarea(test);
		gestion.modificacion_Tarea(modified);
		result = gestion.consultar_PorCodigoTarea(modified);
		gestion.baja_Tarea(modified);
		
		assertEquals(modified.getDescripcion_Tarea(), result.getDescripcion_Tarea());
	}

}
