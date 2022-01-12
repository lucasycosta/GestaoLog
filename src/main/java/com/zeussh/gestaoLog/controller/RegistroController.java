package com.zeussh.gestaoLog.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zeussh.gestaoLog.domain.Registro;
import com.zeussh.gestaoLog.domain.enums.Funcionalidade;
import com.zeussh.gestaoLog.domain.enums.NivelAcesso;
import com.zeussh.gestaoLog.repository.RegistroRepository;
import com.zeussh.gestaoLog.service.RegistroService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RegistroController {
	
	@Autowired
	private RegistroService registroService;
	
	@Autowired
	private RegistroRepository registroRepository;
	
	@PostMapping(value = "cadastrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public Registro cadastrar(@RequestBody @Valid Registro registro) {
		
		log.info("**CONTROLLER - Cadastrar Registro de log");
		
		return registroService.cadastrar(registro);
	}
	
	@GetMapping(value = "buscarTodos", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Registro> buscarTodos(){
		
		log.info("**CONTROLLER - Buscar registros");
		
		return registroService.buscarTodos();
	}
	
	@GetMapping(value = "buscarPorIdUsuario")
	@ResponseBody
	public ResponseEntity<List<Registro>> buscarPorIdUsuario(@RequestParam(name = "id_usuario") String id_usuario){
		
		log.info("**CONTROLLER - Buscar por id do usuario");
		
		List<Registro> registro = registroRepository.buscarPorIdUsuario(id_usuario);
		
		return new ResponseEntity<List<Registro>>(registro, HttpStatus.OK);
	}
	
	@GetMapping(value = "buscarPorNome")
	@ResponseBody
	public ResponseEntity<List<Registro>> buscarPorNome(@RequestParam(name = "nome_usuario") String nome_usuario){
		
		log.info("**CONTROLLER - Buscar por nome");
		
		List<Registro> registro = registroRepository.buscarPorNome(nome_usuario);
		
		return new ResponseEntity<List<Registro>>(registro, HttpStatus.OK);
	}
	
	@GetMapping(value = "buscarPorEmail")
	@ResponseBody
	public ResponseEntity<List<Registro>> buscarPorEmail(@RequestParam(name = "email") String email){
		
		log.info("**CONTROLLER - Buscar por email");
		
		List<Registro> registro = registroRepository.buscarPorEmail(email);
		
		return new ResponseEntity<List<Registro>>(registro, HttpStatus.OK);
	}
	
	@GetMapping(value = "buscarPorNivel")
	@ResponseBody
	public ResponseEntity<List<Registro>> buscarPorNivel(@RequestParam(name = "nivel") NivelAcesso nivel){
		
		log.info("**CONTROLLER - Buscar por nivel de acesso");
		
		List<Registro> registro = registroRepository.buscarPorNivel(nivel);
		
		return new ResponseEntity<List<Registro>>(registro, HttpStatus.OK);
	}
	
	@GetMapping(value = "buscarPorFuncionalidade")
	@ResponseBody
	public ResponseEntity<List<Registro>> buscarPorFuncionalidade(@RequestParam(name = "funcionalidade") Funcionalidade funcionalidade){
		
		log.info("**CONTROLLER - Buscar por funcionalidade");
		
		List<Registro> registro = registroRepository.buscarPorFuncionalidade(funcionalidade);
		
		return new ResponseEntity<List<Registro>>(registro, HttpStatus.OK);
	}
	
	@GetMapping(value = "buscarPorData")
	@ResponseBody
	public ResponseEntity<List<Registro>> buscarPorData(@RequestParam(name = "data") LocalDate data){
		
		log.info("**CONTROLLER - Buscar por funcionalidade");
		
		List<Registro> registro = registroRepository.buscarPorData(data);
		
		return new ResponseEntity<List<Registro>>(registro, HttpStatus.OK);
	}
	
}
