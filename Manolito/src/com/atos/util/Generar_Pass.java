	package com.atos.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("generar_pass")
@Scope("prototype")
public class Generar_Pass {

	private String password="";
	
	//Metodo para generar una contraseña automatica al dar de alta un usuario nuevo
	public String generar_Pass() {

		String[] abecedario = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
				"R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "a", "b",
				"c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
				"x", "y", "z" };

		for (int i = 0; i <= 10; i++) {
			int numero = (int) Math.round(Math.random() * abecedario.length);
			password += abecedario[numero];	
		}

		return password;

	}

}
