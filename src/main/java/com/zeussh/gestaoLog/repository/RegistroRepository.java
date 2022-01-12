package com.zeussh.gestaoLog.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.zeussh.gestaoLog.domain.Registro;
import com.zeussh.gestaoLog.domain.enums.Funcionalidade;
import com.zeussh.gestaoLog.domain.enums.NivelAcesso;

@Repository
public interface RegistroRepository extends PagingAndSortingRepository<Registro, Long>{
	//HQL
	//AO RETORNAR UM NOME, PODEM VIM OU MAIS DE UM, POR ISSO UMA LISTA
	@Query(value = "select u from Registro u where u.nome_usuario like %?1%")
	List<Registro> buscarPorNome(String nome_usuario);
	
	@Query(value = "select u from Registro u where u.email like %?1%")
	List<Registro> buscarPorEmail(String email);
	
	@Query(value = "select u from Registro u where u.nivel like %?1%")
	List<Registro> buscarPorNivel(NivelAcesso nivel);
	
	@Query(value = "select u from Registro u where u.funcionalidade like %?1%")
	List<Registro> buscarPorFuncionalidade(Funcionalidade funcionalidade);
	
	@Query(value = "select u from Registro u where u.id_usuario like %?1%")
	List<Registro> buscarPorIdUsuario(String id_usuario);
	
	@Query(value = "select u from Registro u where u.data like %?1%")
	List<Registro> buscarPorData(LocalDate data);
	
}
