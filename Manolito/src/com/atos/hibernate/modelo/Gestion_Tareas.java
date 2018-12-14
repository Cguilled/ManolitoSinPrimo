package com.atos.hibernate.modelo;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.atos.dao_ext.TareasDAO_EXT;
import com.atos.hibernate.Tareas;

@Component("gestion_tareas")
@Scope("prototype")
public class Gestion_Tareas implements IGestion_Tareas {

	private TareasDAO_EXT tareasdao;

	private List<Tareas> resultado;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Tareas> consultar_Todos() {
		// TODO Auto-generated method stub
		return tareasdao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Tareas consultar_Codigo(Integer codigo) {
		// TODO Auto-generated method stub
		return tareasdao.findById(codigo);

	}
	
	@Override
	@Transactional(readOnly = true)
	public Tareas consultar_Nombre(String nombre) {
		return tareasdao.findByProperty(nombre);
	}
	

	@Override
	@Transactional
	public void alta_Tarea(Tareas tarea) {
		tareasdao.save(tarea);
	}

	@Override
	@Transactional
	public void baja_Tarea(Tareas tarea) {
		tareasdao.attachDirty(tarea);
	}

	@Override
	@Transactional
	public void modificacion_Tarea(Tareas tarea) {
		tareasdao.attachDirty(tarea);
	}

	public TareasDAO_EXT getTareasdao() {
		return tareasdao;
	}

	public void setTareasdao(TareasDAO_EXT tareasdao) {
		this.tareasdao = tareasdao;
	}

}
