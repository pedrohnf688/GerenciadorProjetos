package com.pedrohnf688.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
