package com.atos.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atos.hibernate.modelo.IGestion_Roles;
import com.atos.hibernate.modelo.IGestion_Tareas;
import com.atos.hibernate.modelo.IGestion_Usuarios;
import com.atos.util.Generar_Pass;

public class Test {

	public static void main(String[] args) {

		ApplicationContext contexto = new ClassPathXmlApplicationContext("com/spring/modelo.xml");
		
		IGestion_Usuarios gestion_cliente = contexto.getBean(IGestion_Usuarios.class);
		
		Generar_Pass generar_pass = new Generar_Pass();
	
		IGestion_Roles gestion_roles = contexto.getBean(IGestion_Roles.class);
		
		IGestion_Tareas gestion_tareas = contexto.getBean(IGestion_Tareas.class);
		
		
		Usuarios cliente = gestion_cliente.consultar_Das("M000001");
		 		
		//COMPROBAR METODO CONSULTAR LOGIN
		boolean usuario = gestion_cliente.consultar_Existencia("M000010","usu@usu.cm");
		System.out.println(usuario);

		//COMPROBAR GENERACION DE PASS AUTO
		String pass = generar_pass.generar_Pass(); 
		System.out.println(pass);

		Tareas t = gestion_tareas.consultar_Nombre("tarea1");
		System.out.println(t);
		
		
	}

}
