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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pedrohnf688.api.modelo.enums.EnumStatusProjeto;
import com.pedrohnf688.api.modelo.enums.EnumTipoCategoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "projeto")
@Table
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private Date dateCreated;
	private Date dateUpdated;
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private EnumStatusProjeto statusProjeto;
	
	@Enumerated(EnumType.STRING)
	private EnumTipoCategoria tipoCategoria;
	private Integer qtdDiasPrevista;
	private Double custoInicial;
	private String infoContato;

	@ManyToOne
	@JoinColumn(name = "equipe_id")
	private Equipe equipe;
	
	@OneToMany(mappedBy = "projeto", fetch = FetchType.LAZY, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	@JsonIgnore
	private List<Tarefa> listaTarefas;

	@OneToMany(mappedBy = "projeto", fetch = FetchType.LAZY, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	@JsonIgnore
	private List<Orcamento> orcamento;
	
	@OneToOne
	@JoinColumn(name="solicitacao_id")
	private Solicitacao solicitacao;
}
