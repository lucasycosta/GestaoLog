package com.zeussh.gestaoLog.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeussh.gestaoLog.domain.Registro;
import com.zeussh.gestaoLog.repository.RegistroRepository;

@Service
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

}
