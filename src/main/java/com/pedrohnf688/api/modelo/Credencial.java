package com.pedrohnf688.api.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "credencial")
@Table
public class Credencial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty(access = Access.READ_ONLY)
	private Date dateCreated;

	@JsonProperty(access = Access.READ_ONLY)
	private Date dateUpdated;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "senha", nullable = false)
	private String senha;

	@OneToOne
	@Cascade({ org.hibernate.annotations.CascadeType.ALL })
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
