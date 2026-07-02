package com.fretebot.api.Repository;

import com.fretebot.api.Entity.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

    List<Motorista> findByEmpresa_IdEmpresa(Long idEmpresa);

    boolean existsByCpf(String cpf);
}