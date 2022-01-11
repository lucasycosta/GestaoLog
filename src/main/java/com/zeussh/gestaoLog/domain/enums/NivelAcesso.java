package com.zeussh.gestaoLog.domain.enums;

public enum NivelAcesso {
	GERENTE("Gerente"),
	MEDICO("Médico"),
	ENFERMEIRA("Enfermeira"),
	SECRETARIA("Scertária"),
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
