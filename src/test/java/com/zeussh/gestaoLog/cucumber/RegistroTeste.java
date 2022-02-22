package com.zeussh.gestaoLog.cucumber;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

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
	// TESTE - BUSCAR TODOS OS REGISTRO
	// --------------------------------------------------------------------------------------
	@Test
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
	@Quando("eu clicar na opção de visualização dos registros")
	public void eu_clicar_na_opção_de_visualização_dos_registros() {
		log.info("Clica na opção de visualização dos registro");
	}

	@Test
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
	@Quando("preencher o campo {string}")
	public void preencher_o_campo(String string) {
		log.info("Preencher o campo para filtrar a busca");
	}

	@Test
	@Entao("os registro com aquele nome de usuario serão apresentados")
	public void os_registro_com_aquele_nome_de_usuario_serão_apresentados() {
		ResponseEntity<List<Registro>> registro = registroController.buscarPorNome("Lucas");
	}

	@Test
	@Entao("os registro com aquele id de usuario serão apresentados")
	public void os_registro_com_aquele_id_de_usuario_serão_apresentados() {
		ResponseEntity<List<Registro>> registro = registroController.buscarPorIdUsuario((long) 1);
	}
	
	@Test
	@Entao("os registro com aquele email serão apresentados")
	public void os_registro_com_aquele_email_serão_apresentados() {
		ResponseEntity<List<Registro>> registro = registroController.buscarPorEmail("lucas@mail.com");
	}

	@Test
	@Quando("selecionar um nivel no campo {nivelAcesso}")
	public void selecionar_um_nivel_no_campo(EnumNivelAcesso nivelAcesso) {
		log.info("Selecionando um nivel de acesso");
	}

	@Test
	@Entao("os registro com aquele nivel de acesso serão apresentados")
	public void os_registro_com_aquele_nivel_de_acesso_serão_apresentados() {
		ResponseEntity<List<Registro>> registro = registroController.buscarPorNivel(EnumNivelAcesso.GERENTE);
	}
	
	@Test
	@Quando("selecionar uma funcionalidade no campo {funcionalidade}")
	public void selecionar_uma_funcionalidade_no_campo(EnumFuncionalidade funcionalidade) {
	    log.info("Selecionando uma funcionalidade");
	}
	
	@Test
	@Entao("os registro com aquel funcionalidade serão apresentados")
	public void os_registro_com_aquel_funcionalidade_serão_apresentados() {
		ResponseEntity<List<Registro>> registro = registroController.buscarPorFuncionalidade(EnumFuncionalidade.LOGIN);
	}

	@Test
	@Quando("selecionar um intervalo de data")
	public void selecionar_um_intervalo_de_data() {
		log.info("Selecionando um intervalo de data");
	}

	@Test
	@Entao("os registro daquele intervalo de data serão apresentados")
	public void os_registro_daquele_intervalo_de_data_serão_apresentados() {
		ResponseEntity<List<Registro>> registro = registroController.buscarPorData(id, id);
	}
}
