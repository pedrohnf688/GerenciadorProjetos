package com.pedrohnf688.api.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "equipe")
@Table
public class Equipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_equipe")
	private Long id;
	private String titulo;
	private Date dateCreated;
	private Integer qtdMembros;

	@OneToMany(mappedBy = "equipe", fetch = FetchType.LAZY, orphanRemoval = false)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.PERSIST,
			org.hibernate.annotations.CascadeType.MERGE })
	@JsonIgnore
	private List<Projeto> listaProjetos;

	@OneToMany(mappedBy = "equipe", fetch = FetchType.LAZY, orphanRemoval = false)
	@Fetch(FetchMode.SUBSELECT)
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.PERSIST,
			org.hibernate.annotations.CascadeType.MERGE })
	@JsonIgnore
	private List<Usuario> listaUsuarios;

}