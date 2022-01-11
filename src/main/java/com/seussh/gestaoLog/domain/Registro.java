package com.seussh.gestaoLog.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.seussh.gestaoLog.domain.enums.Funcionalidade;
import com.seussh.gestaoLog.domain.enums.NivelAcesso;
import lombok.Data;

@Entity
@Table
@SuppressWarnings("serial")
@Data
public class Registro implements Serializable{
	private Integer id;
	private Integer id_usuario;
	private String nome_usuario;
	private String email;
	private Date data_hora;
	private NivelAcesso nivel;
	private Funcionalidade funcionalidade;
}
