package com.pedrohnf688.api.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pedrohnf688.api.modelo.enums.EnumTipoSexo;
import com.pedrohnf688.api.modelo.enums.EnumTipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "usuario")
@Table
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Date dataNascimento;

	@Enumerated(EnumType.STRING)
	private EnumTipoUsuario tipoUsuario;

	@Enumerated(EnumType.STRING)
	private EnumTipoSexo tipoSexo;
	private String cargo;
	private String descricao;
	private Boolean lider = false;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, orphanRemoval = false)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.PERSIST, 
        org.hibernate.annotations.CascadeType.MERGE})
	@JsonIgnore
	private List<Tarefa> listaTarefas;

	@ManyToOne
	@JoinColumn(name = "equipe_id")
	private Equipe equipe;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, orphanRemoval = false)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.PERSIST, 
        org.hibernate.annotations.CascadeType.MERGE})
	@JsonIgnore	
	private List<Solicitacao> listaSolitacoes;

}
