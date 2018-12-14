package com.atos.dao_ext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.atos.dao.TareasDAO;

@Repository("tareasdao")
@Scope("prototype")
public class TareasDAO_EXT extends TareasDAO{
	

}
