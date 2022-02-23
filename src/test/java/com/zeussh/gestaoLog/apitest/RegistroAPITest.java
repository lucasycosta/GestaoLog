
package com.zeussh.gestaoLog.apitest;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.zeussh.gestaoLog.domain.Registro;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestMethodOrder(OrderAnnotation.class)
public class RegistroAPITest {

	private static Registro registro;
	
	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8080/";
	}
	
	@Test
	@Order(1)
	public void naoDevoCadastrarRegistroComIdUsuarioVazio() {
		log.info("**TEST - naoDevoCadastrarRegistroComIdUsuarioVazio");
		log.info("Gravar Registro");
		RestAssured.given()
			.body("{\"nomeUsuario\": \"Lucas\", \"email\": \"lucas@mail.com\", \"nivelAcesso\": \"MEDICO\", \"funcionalidade\": \"LOGIN\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("cadastrar")
		.then()
			.log().all()
			.statusCode(400)
			.body("idUsuario", CoreMatchers.is("Este campo é obrigatorio"));
	}
	
	@Test
	@Order(1)
	public void naoDevoCadastrarRegistroComNomeUsuarioEmBranco() {
		log.info("**TEST - naoDevoCadastrarRegistroComNomeUsuarioEmBranco");
		log.info("Gravar Registro");
		RestAssured.given()
			.body("{\"idUsuario\": \"1\", \"nomeUsuario\": \"\", \"email\": \"lucas@mail.com\", \"nivelAcesso\": \"MEDICO\", \"funcionalidade\": \"LOGIN\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("cadastrar")
		.then()
			.log().all()
			.statusCode(400)
			.body("nomeUsuario", CoreMatchers.is("Nome de usuário em branco"));
	}
	
	@Test
	@Order(1)
	public void naoDevoCadastrarRegistroComNomeUsuarioComLetraMinuscula() {
		log.info("**TEST - naoDevoCadastrarRegistroComNomeUsuarioComLetraMinuscula");
		log.info("Gravar Registro");
		RestAssured.given()
		.body("{\"idUsuario\": \"1\", \"nomeUsuario\": \"lucas\", \"email\": \"lucas@mail.com\", \"nivelAcesso\": \"MEDICO\", \"funcionalidade\": \"LOGIN\"}")
		.contentType(ContentType.JSON)
	.when()
		.post("cadastrar")
	.then()
		.log().all()
		.statusCode(400)
		.body("nomeUsuario", CoreMatchers.is("Nome de usuário invalido"));
	}
	
	@Test
	@Order(1)
	public void naoDevoCadastrarRegistroComEmailEmBranco() {
		log.info("**TEST - naoDevoCadastrarRegistroComEmailEmBranco");
		log.info("Gravar Registro");
		RestAssured.given()
			.body("{\"idUsuario\": \"1\", \"nomeUsuario\": \"Lucas\", \"email\": \"\", \"nivelAcesso\": \"MEDICO\", \"funcionalidade\": \"LOGIN\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("cadastrar")
		.then()
			.log().all()
			.statusCode(400)
			.body("email", CoreMatchers.is("Este campo é obrigatorio"));
	}
	
	@Test
	@Order(1)
	public void naoDevoCadastrarRegistroComEmailInvalido() {
		log.info("**TEST - naoDevoCadastrarRegistroComEmailInvalido");
		log.info("Gravar Registro");
		RestAssured.given()
			.body("{\"idUsuario\": \"1\", \"nomeUsuario\": \"Lucas\", \"email\": \"lucasmail.com\", \"nivelAcesso\": \"MEDICO\", \"funcionalidade\": \"LOGIN\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("cadastrar")
		.then()
			.log().all()
			.statusCode(400)
			.body("email", CoreMatchers.is("E-mail invalido"));
	}
	
	@Test
	@Order(1)
	public void naoDevoCadastrarRegistroComNivelAcessoVazio() {
		log.info("**TEST - naoDevoCadastrarRegistroComNivelAcessoVazio");
		log.info("Gravar Registro");
		RestAssured.given()
			.body("{\"idUsuario\": \"1\", \"nomeUsuario\": \"Lucas\", \"email\": \"lucas@mail.com\", \"funcionalidade\": \"LOGIN\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("cadastrar")
		.then()
			.log().all()
			.statusCode(400)
			.body("nivelAcesso", CoreMatchers.is("Este campo é obrigatorio"));
	}

	@Test
	@Order(1)
	public void naoDevoCadastrarRegistroComFuncionalidadeVazio() {
		log.info("**TEST - naoDevoCadastrarRegistroComFuncionalidadeVazio");
		log.info("Gravar Registro");
		RestAssured.given()
			.body("{\"idUsuario\": \"1\", \"nomeUsuario\": \"Lucas\", \"email\": \"lucas@mail.com\", \"nivelAcesso\": \"MEDICO\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("cadastrar")
		.then()
			.log().all()
			.statusCode(400)
			.body("funcionalidade", CoreMatchers.is("Este campo é obrigatorio"));
	}
	
	@Test
	@Order(2)
	public void devoCadastrarRegistro() {
		log.info("**TEST - devoCadastrarRegistro");
		log.info("Gravar Registro");
		RestAssured.given()
		.body("{\"idUsuario\": \"157\", \"nomeUsuario\": \"Ramires\", \"email\": \"rami@mail.com\", \"nivelAcesso\": \"MEDICO\", \"funcionalidade\": \"CADASTRAR_EXAME\"}")
		.contentType(ContentType.JSON)
	.when()
		.post("cadastrar")
	.then()
		.log().all()
		.statusCode(200);
	}
	
	@Test
	@Order(3)
	public void devoBuscarTodosRegistro() {
		log.info("** Test - devoBuscarTodosRegistro");

		log.info("Buscar todos registros");
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("buscarTodos");
		response.then().log().all().statusCode(200);
		JsonPath jsonPathEvaluator = response.jsonPath();
		List<Registro> lista =  jsonPathEvaluator.getList("", Registro.class);
		registro = lista.get(lista.size()-1);
		log.debug("Valor ID: " + registro.getId());
	}

	
	@Test
	@Order(4)
	public void devoBuscarRegistroPorIdUsuario() {
		log.info("** Test - devoBuscarRegistroPorIdUsuario");

		log.info("Buscar usuario por id do usuário");
		RestAssured.given()
		.when()
			.get("buscarPorIdUsuario?idUsuario=" + registro.getIdUsuario())
		.then()
			.log().all()
			.statusCode(200);
	}
	
	
	@Test
	@Order(5)
	public void devoBuscarRegistroPorNomeUsuario() {
		log.info("** Test - devoBuscarRegistroPorNomeUsuario");

		log.info("Buscar usuario por nome do usuário");
		RestAssured.given()
		.when()
			.get("buscarPorNome?nomeUsuario=" + registro.getNomeUsuario())
		.then()
			.log().all()
			.statusCode(200);
	}
	
	@Test
	@Order(6)
	public void devoBuscarRegistroPorEmail() {
		log.info("** Test - devoBuscarRegistroPorEmail");

		log.info("Buscar usuario por email do usuário");
		RestAssured.given()
		.when()
			.get("buscarPorEmail?email=" + registro.getEmail())
		.then()
			.log().all()
			.statusCode(200);
	}
	
	@Test
	@Order(7)
	public void devoBuscarRegistroPorFuncionalidade() {
		log.info("** Test - devoBuscarRegistroPorFuncionalidade");

		log.info("Buscar usuario por funcionalidade usada");
		RestAssured.given()
		.when()
			.get("buscarPorFuncionalidade?funcionalidade=" + registro.getFuncionalidade())
		.then()
			.log().all()
			.statusCode(200);
	}
	
	@Test
	@Order(8)
	public void devoBuscarRegistroPorNivelAcesso() {
		log.info("** Test - devoBuscarRegistroPorNivelAcesso");

		log.info("Buscar usuario por nivel de acesso");
		RestAssured.given()
		.when()
			.get("buscarPorNivel?nivelAcesso=" + registro.getNivelAcesso())
		.then()
			.log().all()
			.statusCode(200);
	}
	
	@Test
	@Order(9)
	public void devoBuscarRegistroPorData() {
		log.info("** Test - devoBuscarRegistroPorData");

		log.info("Buscar usuario pela data");
		RestAssured.given()
		.when()
			.get("buscarPorData?dataInicio=1642636800000&dataFim=1643241600000")
		.then()
			.log().all()
			.statusCode(200);
	}
	/*
	 * Date date = new Date();  
       Timestamp ts=new Timestamp(date.getTime());  
       
       Calendar cal = Calendar.getInstance(); 
	   cal.setTime(dataTeste ); 
	   cal.add(Calendar.DATE, 1);
	   dataTeste = cal.getTime();
	*/
	
	
	@Test
	@Order(10)
	public void devoBuscarGraficoFuncionalidade() {
		log.info("** Test - devoBuscarGraficoFuncionalidade");

		log.info("Gerar grafico de funcionalidade");
		RestAssured.given()
		.when()
			.get("buscarGraficoFuncionalidade?dataInicio=1642464000000&dataFim=1643500800000")
		.then()
			.log().all()
			.statusCode(200);
	}
	
	@Test
	@Order(11)
	public void devoBuscarGraficoNivelAcesso() {
		log.info("** Test - devoBuscarGraficoNivelAcesso");

		log.info("Gerar grafico de nivel de acesso");
		RestAssured.given()
		.when()
			.get("buscarGraficoNivelAcesso?dataInicio=1642464000000&dataFim=1643500800000")
		.then()
			.log().all()
			.statusCode(200);
	}	
}

