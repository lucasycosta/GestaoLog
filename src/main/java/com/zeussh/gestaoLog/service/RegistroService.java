package com.zeussh.gestaoLog.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeussh.gestaoLog.domain.Registro;
import com.zeussh.gestaoLog.domain.enums.EnumFuncionalidade;
import com.zeussh.gestaoLog.domain.enums.EnumNivelAcesso;
import com.zeussh.gestaoLog.repository.RegistroRepository;

@Service
public class RegistroService {

	@Autowired
	public RegistroRepository registroRepository;

	public Registro cadastrar(@Valid Registro registro) {

		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		Date data = new Date(stamp.getTime());
		System.out.println(data);

		return registroRepository.save(registro);
	}

	public List<Registro> buscarTodos() {

		return (List<Registro>) registroRepository.findAll();
	}

	public List<Registro> buscarPorNome(String nomeUsuario) {

		return registroRepository.buscarPorNome(nomeUsuario);
	}

	public List<Registro> buscarPorIdUsuario(Long idUsuario) {

		return registroRepository.buscarPorIdUsuario(idUsuario);
	}

	public List<Registro> buscarPorEmail(String email) {

		return registroRepository.buscarPorEmail(email);
	}

	public List<Registro> buscarPorNivel(EnumNivelAcesso nivelAcesso) {

		return registroRepository.buscarPorNivel(nivelAcesso);
	}

	public List<Registro> buscarPorFuncionalidade(EnumFuncionalidade funcionalidade) {

		return registroRepository.buscarPorFuncionalidade(funcionalidade);
	}

	public List<Registro> buscarPorData(Long dataInicio, Long dataFim) {
		//Timestamp stamp = new Timestamp(dataInicio);
		Date date = new Date(new Timestamp(dataInicio).getTime());
		
		Timestamp stamp2 = new Timestamp(dataFim);
		Date date2 = new Date(stamp2.getTime());
		return registroRepository.buscarPorData(date, date2);
		
	}

	public Map<String, Integer> buscarGraficoFuncionalidade() {

		Map<String, Integer> mappedResult = new HashMap<>();
		List<Object[]> queryResult = registroRepository.buscarGraficoFuncionalidade();
		for (Object[] obj : queryResult) {
			EnumFuncionalidade funcionalidade = (EnumFuncionalidade) obj[0];
			Integer contador = Integer.parseInt(obj[1].toString());
			mappedResult.put(funcionalidade.getDescricao(), contador);
		}

		return mappedResult;
	}

	public Map<String, Integer> buscarGraficoNivelAcesso() {
		Map<String, Integer> mappedResult = new HashMap<>();
		List<Object[]> queryResult = registroRepository.buscarGraficoNivelAcesso();
		for (Object[] obj : queryResult) {
			EnumNivelAcesso nivelAcesso = (EnumNivelAcesso) obj[0];
			Integer contador = Integer.parseInt(obj[1].toString());
			mappedResult.put(nivelAcesso.getDescricao(), contador);
		}

		return mappedResult;
	}
}
