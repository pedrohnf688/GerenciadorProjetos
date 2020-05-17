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
	private Date dateCreated;
	private String titulo;
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	private EnumTipoCategoria tipoCategoria;
	
	@Enumerated(EnumType.STRING)
	private EnumStatusSolicitacao statusSolicitacao;
	private String problema;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@OneToMany(mappedBy = "solicitacao", fetch = FetchType.LAZY, orphanRemoval = true)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
	@JsonIgnore	
	private List<Arquivo> listaImgs;
	
	
}
