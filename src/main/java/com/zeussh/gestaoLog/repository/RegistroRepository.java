package com.zeussh.gestaoLog.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.zeussh.gestaoLog.domain.Registro;

@Repository
public interface RegistroRepository extends PagingAndSortingRepository<Registro, Long>{
/*
	Registro findByNome(String nome_usuario);
	Registro findByEmail(String email);
	Registro findByNivel(NivelAcesso nivel);
	Registro findByFuncionalidade(Funcionalidade funcionalidade);
	*/
}
