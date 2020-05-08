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
@Entity(name = "comentario")
@Table
public class Comentario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String autor;
	private Date dateCreated;
	private Date dateUpdated;
	private String comentario;
	
	@ManyToOne
	@JoinColumn(name = "tarefa_id")
	private Tarefa tarefa;
}
