package com.zeussh.gestaoLog.steps;

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

import com.zeussh.gestaoLog.controller.RegistroController;
import com.zeussh.gestaoLog.domain.Registro;
import com.zeussh.gestaoLog.domain.enums.EnumFuncionalidade;
import com.zeussh.gestaoLog.domain.enums.EnumNivelAcesso;

import io.cucumber.java.es.Dado;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class RegistroTeste {

	@Autowired
	private RegistroController registroController;
	private static Long id;

	Registro registro = new Registro();

	private Registro criarRegistro() {
		log.debug("Registro Padrão");
		Registro registro = new Registro();
		registro.setIdUsuario((long) 1);
		registro.setNomeUsuario("Lucas");
		registro.setEmail("lucas@mail.com");
		registro.setNivelAcesso(EnumNivelAcesso.GERENTE);
		registro.setFuncionalidade(EnumFuncionalidade.LOGIN);

		return registro;
	}

	// --------------------------------------------------------------------------------------
	// TESTE - CADASTRO DE REGISTRO
	// --------------------------------------------------------------------------------------

	@Test
	@Order(1)
	@Dado("que um usuario cadastrado no sistema")
	public void que_um_usuario_cadastrado_no_sistema() {
		log.info("DADO - Usuário já cadastrado no sistema");
	}

	@Test
	@Order(1)
	@Quando("executar uma ação no sistema")
	public void executar_uma_ação_no_sistema() {
		log.info("QUANDO - Executada uma funcionalidade dentro do sistema");
	}

	@Test
	@Order(1)
	@Quando("não captura seu id")
	public void não_captura_seu_id() {
		log.info("MAS - O sistema não consegue capturar seu id");
	}

	@Test
	@Order(1)
	@Entao("o sistema não registra a ação sem o id")
	public void o_sistema_não_registra_a_ação_sem_o_id() {
		log.info("ENTAO - o sistema não registra a ação sem o id");

		Registro registro = criarRegistro();
		registro.setIdUsuario(null);

		try {
			log.info("Gravar Log");
			registroController.cadastrar(registro);
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

	@Test
	@Order(1)
	@Quando("não captura seu nome de usuario")
	public void não_captura_seu_nome_de_usuario() {
		log.info("O sistema não consegue capturar seu nome de usuario");
	}

	@Test
	@Order(1)
	@Entao("o sistema não registra a ação sem o nome")
	public void o_sistema_não_registra_a_ação_sem_o_nome() {
		log.info("o sistema não registra a ação sem o nome");

		Registro registro = criarRegistro();
		registro.setNomeUsuario("");

		try {
			log.info("Gravar Log");
			registroController.cadastrar(registro);
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

	@Test
	@Order(1)
	@Quando("seu nome começa com letra minuscula")
	public void seu_nome_começa_com_letra_minuscula() {
		log.info("O nome de usuário é invalido");
	}

	@Test
	@Order(1)
	@Entao("o sistema não registra a ação com nome invalido")
	public void o_sistema_não_registra_a_ação_com_nome_invalido() {
		log.info("o sistema não registra a ação com nome invalido");

		Registro registro = criarRegistro();
		registro.setNomeUsuario("lucas");

		try {
			log.info("Gravar Log");
			registroController.cadastrar(registro);
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

	@Test
	@Order(1)
	@Quando("seu email não segue o padrão")
	public void seu_email_não_segue_o_padrão() {
		log.info("O email do usuario é invalido");
	}

	@Test
	@Order(1)
	@Entao("o sistema não registra a ação com email invalido")
	public void o_sistema_não_registra_a_ação_com_email_invalido() {
		log.info("o sistema não registra a ação com email invalido");

		Registro registro = criarRegistro();
		registro.setEmail("lucasmail.com");

		try {
			log.info("Gravar Log");
			registroController.cadastrar(registro);
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

	@Test
	@Order(1)
	@Quando("não captura seu email")
	public void não_captura_seu_email() {
		log.info("O sistema não consegue capturar seu email do usuario");
	}

	@Test
	@Order(1)
	@Entao("o sistema não registra a ação com email vazio")
	public void o_sistema_não_registra_a_ação_com_email_vazio() {
		log.info("o sistema não registra a ação com email vazio");

		Registro registro = criarRegistro();
		registro.setEmail("");

		try {
			log.info("Gravar Log");
			registroController.cadastrar(registro);
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

	@Test
	@Order(1)
	@Quando("não captura seu nivel de acesso")
	public void não_captura_seu_nivel_de_acesso() {
		log.info("O sistema não captura o nivel de acesso do usuário");
	}

	@Test
	@Order(1)
	@Entao("o sistema não registra a ação sem nivel de acesso")
	public void o_sistema_não_registra_a_ação_sem_nivel_de_acesso() {
		log.info("o sistema não registra a ação sem nivel de acesso");

		Registro registro = criarRegistro();
		registro.setNivelAcesso(null);

		try {
			log.info("Gravar Log");
			registroController.cadastrar(registro);
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

	@Test
	@Order(1)
	@Quando("não captura a funcionalidade utilizada")
	public void não_captura_a_funcionalidade_utilizada() {
		log.info("o sistema não captura a funcioncalidade usada");
	}

	@Test
	@Order(1)
	@Entao("o sistema não registra a ação com funcionalidade vazio")
	public void o_sistema_não_registra_a_ação_com_funcionalidade_vazio() {
		log.info("o sistema não registra a ação com funcionalidade vazio");

		Registro registro = criarRegistro();
		registro.setFuncionalidade(null);

		try {
			log.info("Gravar Log");
			registroController.cadastrar(registro);
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

	@Test
	@Order(1)
	@Dado("que o usuario seja cadastrado no sistema")
	public void que_o_usuario_seja_cadastrado_no_sistema() {
		log.info("Usuario ja cadastrado no sistema");
	}

	@Test
	@Order(1)
	@Quando("realizar uma acao no sistema")
	public void realizar_uma_acao_no_sistema() {
		log.info("Ele realiza a ação de login no sistema");
	}

	@Test
	@Order(1)
	@Entao("um registro dessa acao será realizado")
	public void um_registro_dessa_acao_será_realizado() {
		log.info("um registro dessa acao será realizado");

		Registro registro = registroController.cadastrar(criarRegistro());
		assertNotNull(registro.getId(), "id não nulo");
	}

	// --------------------------------------------------------------------------------------
	// TESTE - BUSCAR TODOS OS REGISTRO
	// --------------------------------------------------------------------------------------
	@Test
	@Order(2)
	@Dado("que o usuario esteja logado com perfil de acesso gerente")
	public void que_o_usuario_esteja_logado_com_perfil_de_acesso_gerente() {
		Registro registro = criarRegistro();
		if (registro.getNivelAcesso() == EnumNivelAcesso.GERENTE) {
			log.info("Usuário locado como 'gerente' - OK");
		} else {
			log.info("Acesso negado");
		}
	}

	@Test
	@Order(2)
	@Quando("eu clicar na opção de visualização dos registros")
	public void eu_clicar_na_opção_de_visualização_dos_registros() {
		log.info("Clica na opção de visualização dos registro");
	}

	@Test
	@Order(2)
	@Então("todos os registros cadastrados serao apresentados")
	public void todos_os_registros_cadastrados_serao_apresentados() {
		log.info("Trazer todos os registros");
		List<Registro> registro = registroController.buscarTodos();
		int tamanho = registro.size();
		assertNotEquals(0, tamanho, "Lista de registros igual a zero");
		id = registro.get(tamanho - 1).getId();
	}

	// --------------------------------------------------------------------------------------
	// TESTE - BUSCAR REGISTRO COM FILTRO
	// --------------------------------------------------------------------------------------

	@Test
	@Order(3)
	@Dado("usuario  logado como gerente na pagina de registro")
	public void usuario_logado_como_gerente_na_pagina_de_registro() {
		Registro registro = criarRegistro();
		if (registro.getNivelAcesso() == EnumNivelAcesso.GERENTE) {
			log.info("Usuário locado como 'gerente' - OK");
		} else {
			log.info("Acesso negado");
		}
	}

	@Test
	@Order(3)
	@Quando("preencher o campo destinado ao nome de usuario")
	public void preencher_o_campo_destinado_ao_nome_de_usuario() {
		log.info("Preencher o campo para filtrar a busca");
	}

	@Test
	@Order(3)
	@Entao("os registro com aquele nome de usuario serão apresentados")
	public void os_registro_com_aquele_nome_de_usuario_serão_apresentados() {
		log.info("O resultado é apresentado");
		ResponseEntity<List<Registro>> registro = registroController.buscarPorNome("Lucas");
	}

	@Test
	@Order(3)
	@Quando("preencher o campo destinado ao id de Usuario")
	public void preencher_o_campo_destinado_ao_id_de_usuario() {
		log.info("Preencher o campo para filtrar a busca");
	}

	@Test
	@Order(3)
	@Entao("os registro com aquele id de usuario serão apresentados")
	public void os_registro_com_aquele_id_de_usuario_serão_apresentados() {
		log.info("O resultado é apresentado");
		ResponseEntity<List<Registro>> registro = registroController.buscarPorIdUsuario((long) 1);
	}

	@Test
	@Order(3)
	@Quando("preencher o campo destinado ao e-mail")
	public void preencher_o_campo_destinado_ao_e_mail() {
		log.info("Preencher o campo para filtrar a busca");
	}

	@Test
	@Order(3)
	@Entao("os registro com aquele email serão apresentados")
	public void os_registro_com_aquele_email_serão_apresentados() {
		log.info("O resultado é apresentado");
		ResponseEntity<List<Registro>> registro = registroController.buscarPorEmail("lucas@mail.com");
	}

	@Test
	@Order(3)
	@Quando("preencher o campo destinado ao Nivel de Acesso")
	public void preencher_o_campo_destinado_ao_nivel_de_acesso() {
		log.info("Preencher o campo para filtrar a busca");
	}

	@Test
	@Order(3)
	@Entao("os registro com aquele nivel de acesso serão apresentados")
	public void os_registro_com_aquele_nivel_de_acesso_serão_apresentados() {
		log.info("O resultado é apresentado");
		ResponseEntity<List<Registro>> registro = registroController.buscarPorNivel(EnumNivelAcesso.GERENTE);
	}

	@Test
	@Order(3)
	@Quando("preencher o campo destinado ao Funcionalidade")
	public void preencher_o_campo_destinado_ao_funcionalidade() {
		log.info("Preencher o campo para filtrar a busca");
	}

	@Test
	@Order(3)
	@Entao("os registro com aquel funcionalidade serão apresentados")
	public void os_registro_com_aquel_funcionalidade_serão_apresentados() {
		log.info("O resultado é apresentado");
		ResponseEntity<List<Registro>> registro = registroController.buscarPorFuncionalidade(EnumFuncionalidade.LOGIN);
	}

	@Test
	@Order(3)
	@Quando("selecionar um intervalo de data")
	public void selecionar_um_intervalo_de_data() {
		log.info("Selecionando um intervalo de data");
	}

	@Test
	@Order(3)
	@Entao("os registro daquele intervalo de data serão apresentados")
	public void os_registro_daquele_intervalo_de_data_serão_apresentados() {
		log.info("O resultado é apresentado");
		ResponseEntity<List<Registro>> registro = registroController.buscarPorData(id, id);
	}

	// --------------------------------------------------------------------------------------
	// TESTE - GERAR GRAFICO DE FUNCIONALIDADE
	// ---------------------------------------------------------------------------------------

	@Test
	@Order(4)
	@Dado("usuario logado como gerente na pagina de registro e na pagina de registro")
	public void usuario_logado_como_gerente_na_pagina_de_registro_e_na_pagina_de_registro() {
		log.info("DADO - Usuario ja logado e dentro da paginca de consulta de registros");
	}

	@Test
	@Order(4)
	@Quando("clicar no botao que direciona para a pagina de graficos")
	public void clicar_no_botao_que_direciona_para_a_pagina_de_graficos() {
		log.info("QUANDO - Clicar no botao");
	}

	@Test
	@Order(4)
	@Quando("seleciona um intervalo de data")
	public void seleciona_um_intervalo_de_data() {
		log.info("E - selecionar um intervalo de datas");
	}

	@Test
	@Order(4)
	@Entao("sera apresentado um grafico com a quantidade de acesso as funcionalidades")
	public void sera_apresentado_um_grafico_com_a_quantidade_de_acesso_as_funcionalidades() {
		log.info("ENTAO - sera apresentado um grafico com a quantidade de acesso as funcionalidades");
		Map<String, Integer> registro = registroController.buscarGraficoFuncionalidade(id, id);
	}


	// --------------------------------------------------------------------------------------
	// TESTE - GERAR GRAFICO DE NIVEL DE ACESSO
	// ---------------------------------------------------------------------------------------
	/*
	@Test
	@Order(5)
	@Quando("selecionar um intervalo de datas")
	public void selecionar_um_intervalo_de_datas() {
		log.info("E - selecionar um intervalo de datas");
	}

	@Test
	@Order(5)
	@Entao("sera apresentado um grafico com a quantidade de acesso dos nivel de acesso")
	public void sera_apresentado_um_grafico_com_a_quantidade_de_acesso_dos_nivel_de_acesso() {
	    log.info("ENTAO - sera apresentado um grafico com a quantidade de acesso dos nivel de acesso");
	    Map<String, Integer> registro = registroController.buscarGraficoNivelAcesso(id, id);
	}
	*/
}
