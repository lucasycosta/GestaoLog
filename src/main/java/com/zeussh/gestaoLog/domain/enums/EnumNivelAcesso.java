package com.zeussh.gestaoLog.domain.enums;

import lombok.Getter;
import lombok.Setter;

public enum EnumNivelAcesso {
	GERENTE("Gerente"),
	MEDICO("Medico"),
	ENFERMEIRA("Enfermeira"),
	SECRETARIA("Secretaria"),
	PACIENTE("Paciente"),
	AUXILIAR("Auxiliar"),
	EQUIPE("Equipe");
	
	@Getter
	@Setter
	private String descricao;

	private EnumNivelAcesso(String descricao) {
		this.descricao = descricao;
	}
	
	
}
