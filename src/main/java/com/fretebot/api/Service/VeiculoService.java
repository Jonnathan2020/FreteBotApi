package com.fretebot.api.Service;

import com.fretebot.api.DTO.VeiculoCreateDTO;
import com.fretebot.api.DTO.VeiculoDTO;
import com.fretebot.api.DTO.VeiculoUpdateDTO;
import com.fretebot.api.Entity.Empresa;
import com.fretebot.api.Entity.Veiculo;
import com.fretebot.api.Repository.EmpresaRepository;
import com.fretebot.api.Repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;


    // ============================================================
    // CADASTRAR VEÍCULO
    // POST /veiculos
    // ============================================================
    public VeiculoDTO cadastrarVeiculo(VeiculoCreateDTO dto) {

        // Placa é identificador único do veículo no Brasil
        if (veiculoRepository.existsByPlaca(dto.getPlaca())) {
            throw new IllegalArgumentException("Já existe um veículo cadastrado com esta placa.");
        }

        Empresa empresa = empresaRepository.findById(dto.getIdEmpresa())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca(dto.getPlaca());
        veiculo.setModelo(dto.getModelo());
        veiculo.setTipo(dto.getTipo());
        veiculo.setCapacidadeKg(dto.getCapacidadeKg());
        veiculo.setEmpresa(empresa);

        return new VeiculoDTO(veiculoRepository.save(veiculo));
    }


    // ============================================================
    // CONSULTAR TODOS OS VEÍCULOS
    // GET /veiculos
    // ============================================================
    @Transactional(readOnly = true)
    public List<VeiculoDTO> consultarVeiculos() {

        List<Veiculo> veiculos = veiculoRepository.findAll();

        if (veiculos.isEmpty()) {
            throw new IllegalArgumentException("Nenhum veículo encontrado!");
        }

        return veiculos.stream()
                .map(VeiculoDTO::new)
                .toList();
    }


    // ============================================================
    // CONSULTAR VEÍCULO POR ID
    // GET /veiculos/{id}
    // ============================================================
    @Transactional(readOnly = true)
    public VeiculoDTO consultarVeiculoPorId(Long idVeiculo) {

        return new VeiculoDTO(buscarOuLancar(idVeiculo));
    }


    // ============================================================
    // CONSULTAR VEÍCULOS POR EMPRESA
    // GET /veiculos/empresa/{idEmpresa}
    // ============================================================
    // Permite que a empresa veja toda a sua frota de veículos.
    @Transactional(readOnly = true)
    public List<VeiculoDTO> consultarVeiculosPorEmpresa(Long idEmpresa) {

        empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada!"));

        // Precisa de findByEmpresa_IdEmpresa no repositório
        return veiculoRepository.findByEmpresa_IdEmpresa(idEmpresa)
                .stream()
                .map(VeiculoDTO::new)
                .toList();
    }


    // ============================================================
    // CONSULTAR VEÍCULOS POR TIPO
    // GET /veiculos/tipo/{tipo}
    // ============================================================
    // Útil para filtrar a frota por tipo de carga suportado.
    @Transactional(readOnly = true)
    public List<VeiculoDTO> consultarVeiculosPorTipo(String tipo) {

        // Precisa de findByTipoIgnoreCase no repositório
        return veiculoRepository.findByTipoIgnoreCase(tipo)
                .stream()
                .map(VeiculoDTO::new)
                .toList();
    }


    // ============================================================
    // ATUALIZAR VEÍCULO
    // PUT /veiculos/{id}
    // ============================================================
    // Placa não pode ser alterada — é o registro legal do veículo.
    // idEmpresa pode ser alterado para transferir o veículo entre empresas.
    public VeiculoDTO alterarVeiculo(Long idVeiculo, VeiculoUpdateDTO dto) {

        Veiculo veiculoExistente = buscarOuLancar(idVeiculo);

        if (dto.getModelo() != null)       veiculoExistente.setModelo(dto.getModelo());
        if (dto.getTipo() != null)         veiculoExistente.setTipo(dto.getTipo());
        if (dto.getCapacidadeKg() != null) veiculoExistente.setCapacidadeKg(dto.getCapacidadeKg());

        // Permite transferir o veículo para outra empresa
        if (dto.getIdEmpresa() != null) {
            Empresa novaEmpresa = empresaRepository.findById(dto.getIdEmpresa())
                    .orElseThrow(() -> new RuntimeException("Empresa destino não encontrada"));
            veiculoExistente.setEmpresa(novaEmpresa);
        }

        return new VeiculoDTO(veiculoRepository.save(veiculoExistente));
    }


    // ============================================================
    // DELETAR VEÍCULO
    // DELETE /veiculos/{id}
    // ============================================================
    public void deletarVeiculo(Long idVeiculo) {
        buscarOuLancar(idVeiculo);
        veiculoRepository.deleteById(idVeiculo);
    }


    // ============================================================
    // MÉTODO AUXILIAR PRIVADO
    // ============================================================
    private Veiculo buscarOuLancar(Long idVeiculo) {
        return veiculoRepository.findById(idVeiculo)
                .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado!"));
    }
}