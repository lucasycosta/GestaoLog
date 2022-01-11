package com.zeussh.gestaoLog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zeussh.gestaoLog.domain.Registro;
import com.zeussh.gestaoLog.service.RegistroService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("registro")
public class RegistroController {
	
	@Autowired
	private RegistroService registroService;
	
	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public Registro cadastrar(@RequestBody @Valid Registro registro) {
		
		log.info("**CONTROLLER - Cadastrar Registro de log");
		
		return registroService.cadastrar(registro);
	}
	
	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Registro> buscarTodos(){
		
		log.info("**CONTROLLER - Buscar registros");
		
		return registroService.buscarTodos();
	}
	
	@GetMapping(value = "/{id_usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Registro buscarPorIdUsuario(@PathVariable Long id_usuario) {
		
		log.info("**CONTROLLER - Buscar registros pelo id do usuario");
		
		return registroService.buscarPorId(id_usuario);
	}
	/*
	@GetMapping(value = "/{nome_usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Registro buscarPorNome(@PathVariable String nome_usuario) {
		
		log.info("**CONTROLLER - Buscar registros pelo nome do usuario");
		
		return registroService.buscarPorNome(nome_usuario);
	}
	
	@GetMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Registro buscarPorIdUsuario(@PathVariable String email) {
		
		log.info("**CONTROLLER - Buscar registros pelo email do usuario");
		
		return registroService.buscarPorEmail(email);
	}
	
	@GetMapping(value = "/{nivel}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Registro buscarPorNivel(@PathVariable NivelAcesso nivel) {
		
		log.info("**CONTROLLER - Buscar registros pelo nivel de acesso");
		
		return registroService.buscarPorNivel(nivel);
	}
	
	@GetMapping(value = "/{funcionalidade}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Registro buscarPorFuncionalidade(@PathVariable Funcionalidade funcionalidade) {
		
		log.info("**CONTROLLER - Buscar registros pela funcionalidade");
		
		return registroService.buscarPelaFuncionalidade(funcionalidade);
	}
	*/
}
