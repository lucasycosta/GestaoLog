package com.zeussh.gestaoLog.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.zeussh.gestaoLog.domain.enums.EnumFuncionalidade;
import com.zeussh.gestaoLog.domain.enums.EnumNivelAcesso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@SuppressWarnings("serial")
@Data
@AllArgsConstructor                                                                                             
@NoArgsConstructor
public class Registro implements Serializable{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_registro")
	@SequenceGenerator(allocationSize = 1, name="seq_registro", sequenceName = "seq_registro")
	private Long id;
	
	@Column(name="id_usuario")
	@NotNull(message = "Este campo é obrigatorio")
	private Long idUsuario;
	
	@Column(name = "nome_usuario")
	@NotBlank(message = "Este campo é obrigatorio")
	@Pattern(regexp = "^[A-Z]+(.)*", message = "Nome de usuário invalido")
	private String nomeUsuario;
	
	@Column(name="email")                                                                
	@Email(message = "E-mail invalido")                                                         
	@NotBlank(message = "Este campo é obrigatorio") 
	private String email;
	
	@Column(name="data")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data;
	
	@Enumerated(EnumType.STRING)
	@Column(name="nivel_acesso")
	@NotNull(message = "Este campo é obrigatorio")
	private EnumNivelAcesso nivelAcesso;
	
	@Enumerated(EnumType.STRING)
	@Column(name="funcionalidade")
	@NotNull(message = "Este campo é obrigatorio")
	private EnumFuncionalidade funcionalidade;

}
