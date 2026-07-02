package com.fretebot.api.Repository;

import com.fretebot.api.Entity.Enums.StatusFreteEnum;
import com.fretebot.api.Entity.Frete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// JpaRepository<Frete, Long>:
//   - Frete  = entity gerenciada
//   - Long   = tipo da PK (idFrete)
// O Spring gera automaticamente: findAll, findById, save, deleteById, etc.
@Repository
public interface FreteRepository extends JpaRepository<Frete, Long> {

    // Spring Data interpreta o nome do método e gera o SQL automaticamente.
    // "findBy" + "Cliente" (atributo da entity) + "_" + "IdCliente" (atributo do Cliente)
    // → WHERE c.id_cliente = :idCliente
    List<Frete> findByCliente_IdCliente(Long idCliente);

    // Filtra fretes pelo valor do enum de status
    // → WHERE status = :status
    List<Frete> findByStatus(StatusFreteEnum status);
}