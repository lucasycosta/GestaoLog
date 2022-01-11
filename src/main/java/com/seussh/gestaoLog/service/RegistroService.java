package com.seussh.gestaoLog.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import com.seussh.gestaoLog.domain.Registro;
import com.seussh.gestaoLog.domain.enums.Funcionalidade;
import com.seussh.gestaoLog.domain.enums.NivelAcesso;
import com.seussh.gestaoLog.repository.RegistroRepository;

public class RegistroService {

	@Autowired
	public RegistroRepository registroRepository;
	
	public Registro cadastrar(@Valid Registro registro) {
		// TODO Auto-generated method stub
		return registroRepository.save(registro);
	}

	public List<Registro> buscarTodos() {
		// TODO Auto-generated method stub
		return (List<Registro>) registroRepository.findAll();
	}

	public Registro buscarPorId(Long id_usuario) {
		// TODO Auto-generated method stub
		return registroRepository.findById(id_usuario).orElse(null);
	}

	public Registro buscarPorNome(String nome_usuario) {
		// TODO Auto-generated method stub
		return registroRepository.findByNome(nome_usuario);
	}

	public Registro buscarPorEmail(String email) {
		// TODO Auto-generated method stub
		return registroRepository.findByEmail(email);
	}

	public Registro buscarPorNivel(NivelAcesso nivel) {
		// TODO Auto-generated method stub
		return registroRepository.findByNivel(nivel);
	}

	public Registro buscarPelaFuncionalidade(Funcionalidade funcionalidade) {
		// TODO Auto-generated method stub
		return registroRepository.findByFuncionalidade(funcionalidade);
	}

}
