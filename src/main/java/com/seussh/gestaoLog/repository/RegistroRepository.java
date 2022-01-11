package com.seussh.gestaoLog.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.seussh.gestaoLog.domain.Registro;
import com.seussh.gestaoLog.domain.enums.Funcionalidade;
import com.seussh.gestaoLog.domain.enums.NivelAcesso;

@Repository
public interface RegistroRepository extends PagingAndSortingRepository<Registro, Long>{

	Registro findByNome(String nome_usuario);
	Registro findByEmail(String email);
	Registro findByNivel(NivelAcesso nivel);
	Registro findByFuncionalidade(Funcionalidade funcionalidade);
}
