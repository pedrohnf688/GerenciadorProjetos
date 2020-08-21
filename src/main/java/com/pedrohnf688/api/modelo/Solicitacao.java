package com.pedrohnf688.api.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.pedrohnf688.api.modelo.enums.EnumStatusSolicitacao;
import com.pedrohnf688.api.modelo.enums.EnumTipoCategoria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "solicitacao")
@Table
public class Solicitacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty(access = Access.READ_ONLY)
	private Date dateCreated;

	@JsonProperty(access = Access.READ_ONLY)
	private Date dateUpdated;

	@Column(name = "titulo", nullable = false, unique = true)
	private String titulo;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	@Column(name = "tipo_categoria", nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumTipoCategoria tipoCategoria;

	@Column(name = "status_solicitacao", nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumStatusSolicitacao statusSolicitacao;

	@Column(name = "problema")
	private String problema;

	@Column(name = "resposta")
	private String resposta;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@OneToMany(mappedBy = "solicitacao", fetch = FetchType.LAZY, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	@JsonIgnore
	private List<Arquivo> listaImgs;

	@OneToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;

	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dateCreated = atual;
		dateUpdated = atual;
	}

	@PreUpdate
	public void preUpdate() {
		dateUpdated = new Date();
	}

}
