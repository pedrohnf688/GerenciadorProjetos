package com.pedrohnf688.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pedrohnf688.api.modelo.Usuario;
import com.pedrohnf688.api.modelo.enums.EnumTipoUsuario;

@Transactional(readOnly = true)
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	List<Usuario> findByTipoUsuario(EnumTipoUsuario perfil);
	
	List<Usuario> findAllByEquipeId(Long equipeId);

}
