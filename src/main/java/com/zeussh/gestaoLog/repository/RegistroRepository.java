package com.zeussh.gestaoLog.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.zeussh.gestaoLog.domain.Registro;
import com.zeussh.gestaoLog.domain.enums.EnumFuncionalidade;
import com.zeussh.gestaoLog.domain.enums.EnumNivelAcesso;

@Repository
public interface RegistroRepository extends PagingAndSortingRepository<Registro, Long>{
	
	@Query(value = "FROM Registro r WHERE r.nomeUsuario LIKE %?1%")
	List<Registro> buscarPorNome(String nomeUsuario);
	
	@Query(value = "FROM Registro r WHERE r.email LIKE %?1%")
	List<Registro> buscarPorEmail(String email);
	
	@Query(value = "FROM Registro r WHERE nivelAcesso IN (:nivelAcesso)")
	List<Registro> buscarPorNivel(EnumNivelAcesso nivelAcesso);
	
	@Query(value = "FROM Registro r WHERE funcionalidade IN (:funcionalidade)")
	List<Registro> buscarPorFuncionalidade(EnumFuncionalidade funcionalidade);
	
	@Query(value = "FROM Registro r WHERE r.idUsuario IN (:idUsuario)")
	List<Registro> buscarPorIdUsuario(Long idUsuario);
	
	@Query(value = "FROM Registro r WHERE r.data BETWEEN ?1 and ?2")
	List<Registro> buscarPorData(Date dataInicio, Date dataFim);
	
	@Query(value = "SELECT funcionalidade, count(*) from Registro group by funcionalidade")
	List<Object[]> buscarGraficoFuncionalidade();
	
	@Query(value = "SELECT nivelAcesso, count(*) from Registro group by nivelAcesso")
	List<Object[]> buscarGraficoNivelAcesso();
}
