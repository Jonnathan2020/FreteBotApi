package com.fretebot.api.Repository;

import com.fretebot.api.Entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findByEmpresa_IdEmpresa(Long idEmpresa);
    List<Veiculo> findByTipoIgnoreCase(String tipo);

    boolean existsByPlaca(String placa);
}