package com.pedrohnf688.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pedrohnf688.api.modelo.Usuario;
import com.pedrohnf688.api.modelo.enums.EnumTipoUsuario;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

	Page<Usuario> findByTipoUsuario(EnumTipoUsuario perfil, Pageable pageable);

	Page<Usuario> findAllByEquipeId(Long equipeId, Pageable pageable);

}
