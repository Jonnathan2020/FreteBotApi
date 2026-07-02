package com.fretebot.api.Repository;


import com.fretebot.api.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByNomeIgnoreCaseContaining(String nome);

    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);
}


