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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.pedrohnf688.api.modelo.enums.EnumStatusTarefa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "tarefa")
@Table
public class Tarefa {

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

	@Column(name = "prazo", nullable = false)
	private double prazo;

	@Column(name = "status_tarefa", nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumStatusTarefa statusTarefa;

	@Column(name = "criador", nullable = false)
	private String criador;

	@ManyToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;

	@OneToMany(mappedBy = "tarefa", fetch = FetchType.LAZY, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.PERSIST,
			org.hibernate.annotations.CascadeType.MERGE })
	@JsonIgnore
	private List<Comentario> listaComentarios;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

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
