package com.pedrohnf688.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Arquivo;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, String> {

}
