/*
package com.seussh.gestaoLog.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;
import com.seussh.gestaoLog.domain.Registro;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class RegistroTest {
	
	@Autowired
	private RegistroController registroC;
	
	private Registro criarRegistro() {
		log.debug("Registro Padrão");
		Registro registro = new Registro();
		registro.setId_usuario(1);
		registro.setNome_usuario("Lucas");
		registro.setEmail("lucas@mail.com");
		registro.setData_hora("01/01/2022 12:30:00");
		registro.setNivel("Medico");
		registro.setFuncionalidade("Cadastrar Exame");
	}
	
	private static Long id;
	
	@Order(1)
	@Test
	public void naoPossoCadastrarRegistroComIdUsuarioVazio() {
		log.info("** TEST - naoPossoCadastrarRegistroComIdUsuarioVazio");
		log.debug("Cadastrando registro com ID do usuario vazio");
		Registro registro = criarRegistro();
		registro.setId_usuario();
		
		try {
			log.info("Gravar Log");
			registroC.cadastrar(registro);
		} catch (Exception ex) {
			log.info("Não foi possivel cadastrar");
			Throwable cause = ((TransactionSystemException) ex).getRootCause();
			if (cause instanceof ConstraintViolationException) {
				Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) cause)
						.getConstraintViolations();
				String campo = violations.iterator().next().getPropertyPath().toString();
				String mensagem = violations.iterator().next().getMessage();
				log.debug(campo + ": " + mensagem);
				assertEquals("Este campo é obrigatorio", mensagem);
			}
		}
	}
	
	@Order(1)
	@Test
	public void naoPossoCadastrarRegistroComNomeUsuarioVazio() {
		log.info("** TEST - naoPossoCadastrarRegistroComNomeUsuarioVazio");
		log.debug("Cadastrando registro com nome do usuario vazio");
		Registro registro = criarRegistro();
		registro.setNome_usuario("");
		
		try {
			log.info("Gravar Log");
			registroC.cadastrar(registro);
		} catch (Exception ex) {
			log.info("Não foi possivel cadastrar");
			Throwable cause = ((TransactionSystemException) ex).getRootCause();
			if (cause instanceof ConstraintViolationException) {
				Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) cause)
						.getConstraintViolations();
				String campo = violations.iterator().next().getPropertyPath().toString();
				String mensagem = violations.iterator().next().getMessage();
				log.debug(campo + ": " + mensagem);
				assertEquals("Este campo é obrigatorio", mensagem);
			}
		}
	}
	
	@Order(1)
	@Test
	public void naoPossoCadastrarRegistroComNomeUsuarioInvalido() {
		log.info("** TEST - naoPossoCadastrarRegistroComNomeUsuarioInvalido");
		log.debug("Cadastrando registro com nome do usuario invalido");
		Registro registro = criarRegistro();
		registro.setNome_usuario("lucas");
		
		try {
			log.info("Gravar Log");
			registroC.cadastrar(registro);
		} catch (Exception ex) {
			log.info("Não foi possivel cadastrar");
			Throwable cause = ((TransactionSystemException) ex).getRootCause();
			if (cause instanceof ConstraintViolationException) {
				Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) cause)
						.getConstraintViolations();
				String campo = violations.iterator().next().getPropertyPath().toString();
				String mensagem = violations.iterator().next().getMessage();
				log.debug(campo + ": " + mensagem);
				assertEquals("Nome de usuário invalido", mensagem);
			}
		}
	}
	
	@Order(1)
	@Test
	public void naoPossoCadastrarRegistroComEmailVazio() {
		log.info("** TEST - naoPossoCadastrarRegistroComEmailVazio");
		log.debug("Cadastrando registro com email vazio");
		Registro registro = criarRegistro();
		registro.setEmail("");
		
		try {
			log.info("Gravar Log");
			registroC.cadastrar(registro);
		} catch (Exception ex) {
			log.info("Não foi possivel cadastrar");
			Throwable cause = ((TransactionSystemException) ex).getRootCause();
			if (cause instanceof ConstraintViolationException) {
				Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) cause)
						.getConstraintViolations();
				String campo = violations.iterator().next().getPropertyPath().toString();
				String mensagem = violations.iterator().next().getMessage();
				log.debug(campo + ": " + mensagem);
				assertEquals("Este campo é obrigatorio", mensagem);
			}
		}
	}
	
	@Order(1)
	@Test
	public void naoPossoCadastrarRegistroComEmailInvalido() {
		log.info("** TEST - naoPossoCadastrarRegistroComEmailInvalido");
		log.debug("Cadastrando registro com email invalido");
		Registro registro = criarRegistro();
		registro.setEmail("lucasmail.com");
		
		try {
			log.info("Gravar Log");
			registroC.cadastrar(registro);
		} catch (Exception ex) {
			log.info("Não foi possivel cadastrar");
			Throwable cause = ((TransactionSystemException) ex).getRootCause();
			if (cause instanceof ConstraintViolationException) {
				Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) cause)
						.getConstraintViolations();
				String campo = violations.iterator().next().getPropertyPath().toString();
				String mensagem = violations.iterator().next().getMessage();
				log.debug(campo + ": " + mensagem);
				assertEquals("E-mail invalido", mensagem);
			}
		}
	}
	
	@Order(1)
	@Test
	public void naoPossoCadastrarRegistroComDataHoraVazia() {
		log.info("** TEST - naoPossoCadastrarRegistroComDataHoraVazia");
		log.debug("Cadastrando registro com data/hora vazio");
		Registro registro = criarRegistro();
		registro.setData_hora("");
		
		try {
			log.info("Gravar Log");
			registroC.cadastrar(registro);
		} catch (Exception ex) {
			log.info("Não foi possivel cadastrar");
			Throwable cause = ((TransactionSystemException) ex).getRootCause();
			if (cause instanceof ConstraintViolationException) {
				Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) cause)
						.getConstraintViolations();
				String campo = violations.iterator().next().getPropertyPath().toString();
				String mensagem = violations.iterator().next().getMessage();
				log.debug(campo + ": " + mensagem);
				assertEquals("Este campo é obrigatorio", mensagem);
			}
		}
	}
	
	@Order(1)
	@Test
	public void naoPossoCadastrarRegistroComNivelAcessoVazio() {
		log.info("** TEST - naoPossoCadastrarRegistroComNivelAcessoVazio");
		log.debug("Cadastrando registro com nível de acesso vazio");
		Registro registro = criarRegistro();
		registro.setNivel("");
		
		try {
			log.info("Gravar Log");
			registroC.cadastrar(registro);
		} catch (Exception ex) {
			log.info("Não foi possivel cadastrar");
			Throwable cause = ((TransactionSystemException) ex).getRootCause();
			if (cause instanceof ConstraintViolationException) {
				Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) cause)
						.getConstraintViolations();
				String campo = violations.iterator().next().getPropertyPath().toString();
				String mensagem = violations.iterator().next().getMessage();
				log.debug(campo + ": " + mensagem);
				assertEquals("Este campo é obrigatorio", mensagem);
			}
		}
	}
	
	@Order(1)
	@Test
	public void naoPossoCadastrarRegistroComFuncionalidadeVazio() {
		log.info("** TEST - naoPossoCadastrarRegistroComFuncionalidadeVazio");
		log.debug("Cadastrando registro com funcionalidade vazio");
		Registro registro = criarRegistro();
		registro.setFuncionalidade("");
		
		try {
			log.info("Gravar Log");
			registroC.cadastrar(registro);
		} catch (Exception ex) {
			log.info("Não foi possivel cadastrar");
			Throwable cause = ((TransactionSystemException) ex).getRootCause();
			if (cause instanceof ConstraintViolationException) {
				Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) cause)
						.getConstraintViolations();
				String campo = violations.iterator().next().getPropertyPath().toString();
				String mensagem = violations.iterator().next().getMessage();
				log.debug(campo + ": " + mensagem);
				assertEquals("Este campo é obrigatorio", mensagem);
			}
		}
	}
}
*/
