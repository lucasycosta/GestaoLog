package com.zeussh.gestaoLog.domain.enums;

public enum NivelAcesso {
	GERENTE("Gerente"),
	MEDICO("Medico"),
	ENFERMEIRA("Enfermeira"),
	SECRETARIA("Secretaria"),
	PACIENTE("Paciente"),
	AUXILIAR("Auxiliar"),
	EQUIPE("Equipe");
	
	private String acesso;
	
	NivelAcesso(String acesso){
		this.acesso = acesso;
	}
	
	public String getAcesso() {
		return this.acesso;
	}
}
