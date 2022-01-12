package com.zeussh.gestaoLog.domain.enums;

public enum NivelAcesso {
	GERENTE("Gerente"),
	MEDICO("Medico"),
	ENFERMEIRA("Enfermeira"),
	SECRETARIA("Secretaria"),
	PACIENTE("Paciente"),
	AUXILIAR("Auxiliar"),
	EQUIPE("Equipe");
	
	private String nivel_acesso;
	
	NivelAcesso(String nivel_acesso){
		this.nivel_acesso = nivel_acesso;
	}
	
	public String getNivel_acesso() {
		return this.nivel_acesso;
	}
}
