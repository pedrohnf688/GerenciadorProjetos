package com.pedrohnf688.api.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.pedrohnf688.api.modelo.enums.EnumTipoPrioridade;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "orcamento")
@Table
public class Orcamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty(access = Access.READ_ONLY)
	private Date dateCreated;

	@JsonProperty(access = Access.READ_ONLY)
	private Date dateUpdated;

	@Column(name = "horas_por_dia_trabalhada", nullable = false)
	private Integer horasPorDiaTrabalhada;

	@Column(name = "custo_inicial", nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private Double custoInicial;

	@Column(name = "custo_final", columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private Double custoFinal;

	@Column(name = "observacoes")
	private String observacoes;

	@Column(name = "prioridade", nullable = false)
	@Enumerated(EnumType.STRING)
	private EnumTipoPrioridade prioridade;

	@Column(name = "prazo_entrega", nullable = false)
	private Integer prazoEntrega;

	@Column(name = "date_entrega", nullable = false, columnDefinition = "DATE")
	private Date dateEntrega;

	@ManyToOne
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