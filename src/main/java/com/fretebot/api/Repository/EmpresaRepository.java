package com.fretebot.api.Repository;

import com.fretebot.api.Entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    // Busca empresa pelo CNPJ (único) — retorna Optional pois pode não existir
    Optional<Empresa> findByDocumento(String documento);

    // Verifica existência pelo CNPJ — usado na validação de duplicata no service
    boolean existsByDocumento(String documento);
}
