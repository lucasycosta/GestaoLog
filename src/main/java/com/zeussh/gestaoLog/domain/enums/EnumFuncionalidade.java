package com.zeussh.gestaoLog.domain.enums;

import lombok.Getter;
import lombok.Setter;

public enum EnumFuncionalidade {
	LOGIN("Login"),
	FECHAR_APP("Fechar app"),
	ABRIR_APP("Abrir app"),
	REGISTRAR_DP("Registrar DP"),
	REGISTRAR_HDF("Registrar HDF"),
	CADASTRAR_PACIENTE("Cadastrar paciente"),
	CADASTRAR_EXAME("Cadastrar exame"),
	CADASTRAR_MEDICAMENTO("Cadastrar medicamento"),
	SAIR("Sair");
	
	@Getter
	@Setter
	private String descricao;

	private EnumFuncionalidade(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
