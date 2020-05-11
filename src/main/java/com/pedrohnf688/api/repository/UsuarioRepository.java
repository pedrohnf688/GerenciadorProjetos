package com.pedrohnf688.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pedrohnf688.api.modelo.Usuario;
import com.pedrohnf688.api.modelo.enums.EnumTipoUsuario;

@Transactional(readOnly = true)
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByTipoUsuario(EnumTipoUsuario perfil);
	
	@Query(value = "SELECT * FROM Usuario u WHERE u.equipe.id = :equipeId", nativeQuery = true)
	List<Usuario> findAllByEquipeId(@Param("equipeId") Long equipeId);

}
