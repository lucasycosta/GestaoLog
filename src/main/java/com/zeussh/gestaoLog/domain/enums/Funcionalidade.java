package com.zeussh.gestaoLog.domain.enums;

public enum Funcionalidade {
	LOGIN("Login"),
	FECHAR_APP("Fechar app"),
	ABRIR_APP("Abrir app"),
	REGISTRAR_DP("Registrar DP"),
	REGISTRAR_HDF("Registrar HDF"),
	CADASTRAR_PACIENTE("Cadastrar paciente"),
	CADASTRAR_EXAME("Cadastrar exame"),
	CADASTRAR_MEDICAMENTO("Cadastrar medicamento"),
	SAIR("Sair");
	
	private String funcionalidade;
	Funcionalidade(String funcionalidade){
		this.funcionalidade = funcionalidade;
	}
	
	public String getFuncionalidade() {
		return this.funcionalidade;
	}
}
