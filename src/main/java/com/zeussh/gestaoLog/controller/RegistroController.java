package com.zeussh.gestaoLog.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.zeussh.gestaoLog.domain.Registro;
import com.zeussh.gestaoLog.domain.enums.EnumFuncionalidade;
import com.zeussh.gestaoLog.domain.enums.EnumNivelAcesso;
import com.zeussh.gestaoLog.service.RegistroService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RegistroController {

	@Autowired
	private RegistroService registroService;

	@PostMapping(value = "cadastrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public Registro cadastrar(@RequestBody @Valid Registro registro) {

		log.info("**CONTROLLER - Cadastrar Registro de log");
		registro.setData(new Date());
		return registroService.cadastrar(registro);
	}

	@GetMapping(value = "buscarTodos", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Registro> buscarTodos() {

		log.info("**CONTROLLER - Buscar registros");

		return registroService.buscarTodos();
	}

	@GetMapping(value = "buscarPorIdUsuario")
	@ResponseBody
	public ResponseEntity<List<Registro>> buscarPorIdUsuario(@RequestParam(name = "idUsuario") Long idUsuario) {

		log.info("**CONTROLLER - Buscar por id do usuario");

		List<Registro> registro = registroService.buscarPorIdUsuario(idUsuario);

		return new ResponseEntity<List<Registro>>(registro, HttpStatus.OK);
	}

	@GetMapping(value = "buscarPorNome")
	@ResponseBody
	public ResponseEntity<List<Registro>> buscarPorNome(@RequestParam(name = "nomeUsuario") String nomeUsuario) {

		log.info("**CONTROLLER - Buscar por nome");

		List<Registro> registro = registroService.buscarPorNome(nomeUsuario);

		return new ResponseEntity<List<Registro>>(registro, HttpStatus.OK);
	}

	@GetMapping(value = "buscarPorEmail")
	@ResponseBody
	public ResponseEntity<List<Registro>> buscarPorEmail(@RequestParam(name = "email") String email) {

		log.info("**CONTROLLER - Buscar por email");

		List<Registro> registro = registroService.buscarPorEmail(email);

		return new ResponseEntity<List<Registro>>(registro, HttpStatus.OK);
	}

	@GetMapping(value = "buscarPorNivel")
	@ResponseBody
	public ResponseEntity<List<Registro>> buscarPorNivel(@RequestParam(name = "nivelAcesso") EnumNivelAcesso nivelAcesso) {

		log.info("**CONTROLLER - Buscar por nivel de acesso");

		List<Registro> registro = registroService.buscarPorNivel(nivelAcesso);

		return new ResponseEntity<List<Registro>>(registro, HttpStatus.OK);
	}

	@GetMapping(value = "buscarPorFuncionalidade")
	@ResponseBody
	public ResponseEntity<List<Registro>> buscarPorFuncionalidade(@RequestParam(name = "funcionalidade") EnumFuncionalidade funcionalidade) {

		log.info("**CONTROLLER - Buscar por funcionalidade");

		List<Registro> registro = registroService.buscarPorFuncionalidade(funcionalidade);

		return new ResponseEntity<List<Registro>>(registro, HttpStatus.OK);
	}

	@GetMapping(value = "buscarPorData")
	@ResponseBody
	public ResponseEntity<List<Registro>> buscarPorData(@RequestParam(value = "dataInicio") Long dataInicio,
														@RequestParam(value = "dataFim") Long dataFim) {

		log.info("**CONTROLLER - Buscar por data");

		List<Registro> registro = registroService.buscarPorData(dataInicio, dataFim);

		return new ResponseEntity<List<Registro>>(registro, HttpStatus.OK);
	}

	@GetMapping(value = "buscarGraficoFuncionalidade", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Integer> buscarGraficoFuncionalidade(@RequestParam(value = "dataInicio") Long dataInicio,
																		  @RequestParam(value = "dataFim") Long dataFim) {

		log.info("**CONTROLLER - Gráfico de funcionalidades");

		return registroService.buscarGraficoFuncionalidade(dataInicio, dataFim);
	}

	@GetMapping(value = "buscarGraficoNivelAcesso", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Integer> buscarGraficoNivelAcesso(@RequestParam(value = "dataInicio") Long dataInicio,
			  														   @RequestParam(value = "dataFim") Long dataFim) {

		log.info("**CONTROLLER - Gráfico de niveis de acesso");

		return registroService.buscarGraficoNivelAcesso(dataInicio, dataFim);
	}

	
	@GetMapping(value = "buscaCombinada")
	@ResponseBody
	public ResponseEntity<List<Registro>>buscaCombinada(@RequestParam(value = "idUsuario") Long idUsuario,
								        				 @RequestParam(value = "nomeUsuario") String nomeUsuario,
														 @RequestParam(value = "email") String email,
														 @RequestParam(value = "funcionalidade") EnumFuncionalidade funcionalidade,
														 @RequestParam(value = "nivelAcesso") EnumNivelAcesso nivelAcesso) {

		log.info("**CONTROLLER - Busca com filtros combinados");

		List<Registro> registro = registroService.buscaCombinada(idUsuario, nomeUsuario, email, funcionalidade, nivelAcesso);

		return new ResponseEntity<List<Registro>>(registro, HttpStatus.OK);
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handlerValidationException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;

	}
}
