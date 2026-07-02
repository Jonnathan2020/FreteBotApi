package com.fretebot.api.Repository;

import com.fretebot.api.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNomeIgnoreCaseContaining(String nome);
    Optional<Cliente> findByTelefone(Long telefone);
}
