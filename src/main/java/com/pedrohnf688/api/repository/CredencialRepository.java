package com.pedrohnf688.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Credencial;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Long> {

	Credencial findByEmail(String email);
}
