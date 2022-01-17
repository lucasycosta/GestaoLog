package com.zeussh.gestaoLog.repository;

import java.time.Instant;
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
	@Query(value = "select u from Registro u where nome_usuario in (:nomeUsuario)")
	List<Registro> buscarPorNome(String nomeUsuario);
	
	@Query(value = "select u from Registro u where email in (:email)")
	List<Registro> buscarPorEmail(String email);
	
	@Query(value = "select u from Registro u where nivel_acesso in (:nivel_acesso)")
	List<Registro> buscarPorNivel(NivelAcesso nivel_acesso);
	
	@Query(value = "select u from Registro u where funcionalidade in (:funcionalidade)")
	List<Registro> buscarPorFuncionalidade(Funcionalidade funcionalidade);
	
	@Query(value = "select u from Registro u where u.id_usuario like %?1%")
	List<Registro> buscarPorIdUsuario(String id_usuario);
	
	@Query(value = "select u from Registro u where data_hora <= :dataHora")
	List<Registro> buscarPorData(Instant dataHora);
	
}
