package com.seussh.gestaoLog.domain.enums;

public enum NivelAcesso {
	GERENTE("Gerente"),
	MEDICO("Médico"),
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
