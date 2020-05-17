package com.pedrohnf688.api.modelo;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	private Date dateCreated;
	private Integer qtdDiasTrabalhada;
	private Double custoFinal;
	private String observacoes;
	
	@ManyToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;
}
