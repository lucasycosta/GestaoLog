
package com.zeussh.gestaoLog.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;

import com.zeussh.gestaoLog.domain.Registro;
import com.zeussh.gestaoLog.domain.enums.EnumFuncionalidade;
import com.zeussh.gestaoLog.domain.enums.EnumNivelAcesso;

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
		registro.setIdUsuario((long) 1);
		registro.setNomeUsuario("Lucas");
		registro.setEmail("lucas@mail.com");
		registro.setNivelAcesso(EnumNivelAcesso.MEDICO);
		registro.setFuncionalidade(EnumFuncionalidade.LOGIN);
		
		return registro;
	}
	
	private static Long id;
	
	@Order(1)
	@Test
	public void naoPossoCadastrarRegistroComIdUsuarioVazio() {
		log.info("** TEST - naoPossoCadastrarRegistroComIdUsuarioVazio");
		log.debug("Cadastrando registro com ID do usuario vazio");
		Registro registro = criarRegistro();
		registro.setIdUsuario(null);
		
		try {
			log.info("Gravar Log");
			registroC.cadastrar(registro);
		} catch (Exception ex) {
			log.info("Não foi possivel cadastrar");
			Throwable cause = ((TransactionSystemException) ex).getRootCause();
			if (cause instanceof ConstraintViolationException) {
				Set<ConstraintViolation<?>> violations = ((ConstraintViolationException) cause).getConstraintViolations();
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
		registro.setNomeUsuario("");
		
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
	public void naoPossoCadastrarRegistroComNomeUsuarioInvalido() {
		log.info("** TEST - naoPossoCadastrarRegistroComNomeUsuarioInvalido");
		log.debug("Cadastrando registro com nome do usuario invalido");
		Registro registro = criarRegistro();
		registro.setNomeUsuario("lucas");
		
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
	public void naoPossoCadastrarRegistroComNivelAcessoVazio() {
		log.info("** TEST - naoPossoCadastrarRegistroComNivelAcessoVazio");
		log.debug("Cadastrando registro com nível de acesso vazio");
		Registro registro = criarRegistro();
		registro.setNivelAcesso(null);
		
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
		registro.setFuncionalidade(null);
		
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
	
	@Order(2)
	@Test
	public void devoCadastrarRegistro() {
		log.info("** TEST - devoCadastrarRegistro");

		log.debug("Incluindo um registro");
		Registro registro = registroC.cadastrar(criarRegistro());
		assertNotNull(registro.getId(), "id não nulo");
	}
	
	@Order(3)
	@Test
	public void devoBuscarTodosRegistros() {
		log.info("** TEST - devoBuscarTodosRegistros");

		log.debug("Buscar todos os registros");
		List<Registro> registro = registroC.buscarTodos();

		int tamanho = registro.size();
		assertNotEquals(0, tamanho, "Lista de registros igual a zero");
		id = registro.get(tamanho - 1).getId();
	}
	
	@Order(4)
	@Test
	public void devoBuscarPorIdUsuario() {
		log.info("** TEST - devoBuscarPorIdUsuario");

		log.debug("Buscar registro atraves do id de usuário");
		ResponseEntity<List<Registro>> registro = registroC.buscarPorIdUsuario((long) 1);
	}
	
	@Order(5)
	@Test
	public void devoBuscarPorNomeUsuario() {
		log.info("** TEST - devoBuscarPorNomeUsuario");

		log.debug("Buscar registro atraves do nome de usuário");
		ResponseEntity<List<Registro>> registro = registroC.buscarPorNome("Lucas");
	}
	
	@Order(5)
	@Test
	public void devoBuscarPorEmailUsuario() {
		log.info("** TEST - devoBuscarPorEmailUsuario");

		log.debug("Buscar registro atraves do email de usuário");
		ResponseEntity<List<Registro>> registro = registroC.buscarPorEmail("lucas@mail.com");
	}
	
	@Order(6)
	@Test
	public void devoBuscarPorNivelAcesso() {
		log.info("** TEST - devoBuscarPorNivelAcesso");

		log.debug("Buscar registro atraves do nivel de acesso");
		ResponseEntity<List<Registro>> registro = registroC.buscarPorNivel(EnumNivelAcesso.MEDICO);
	}
	
	@Order(7)
	@Test
	public void devoBuscarPorFuncionalidade() {
		log.info("** TEST - devoBuscarPorFuncionalidade");

		log.debug("Buscar registro atraves da funcionalidade");
		ResponseEntity<List<Registro>> registro = registroC.buscarPorFuncionalidade(EnumFuncionalidade.LOGIN);
	}
	
	@Order(8)
	@Test
	public void devoBuscarPorData() {
		log.info("** TEST - devoBuscarPorData");

		log.debug("Buscar registro atraves da data");
		ResponseEntity<List<Registro>> registro = registroC.buscarPorData(id, id);
	}
	
	@Order(9)
	@Test
	public void devoGerarGraficoFuncionalidade() {
		log.info("** TEST - devoGerarGraficoFuncionalidade");

		log.debug("Gerar gráfico das funcionalidades mais usadas");
		Map<String, Integer> registro = registroC.buscarGraficoFuncionalidade(id, id);
	}
	
	@Order(10)
	@Test
	public void devoGerarGraficoNivelAcesso() {
		log.info("** TEST - devoGerarGraficoNivelAcesso");

		log.debug("Gerar gráfico dos niveis de acesso");
		Map<String, Integer> registro = registroC.buscarGraficoNivelAcesso(id, id);
	}
}
