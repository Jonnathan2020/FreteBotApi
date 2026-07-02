package com.fretebot.api.Service;

import com.fretebot.api.DTO.MotoristaCreateDTO;
import com.fretebot.api.DTO.MotoristaDTO;
import com.fretebot.api.DTO.MotoristaUpdateDTO;
import com.fretebot.api.Entity.Empresa;
import com.fretebot.api.Entity.Motorista;
import com.fretebot.api.Repository.EmpresaRepository;
import com.fretebot.api.Repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;


    // ============================================================
    // CADASTRAR MOTORISTA
    // POST /motoristas
    // ============================================================
    public MotoristaDTO cadastrarMotorista(MotoristaCreateDTO dto) {

        // CPF é identificador único — não permite duplicata
        if (motoristaRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("Já existe um motorista cadastrado com este CPF.");
        }

        Empresa empresa = empresaRepository.findById(dto.getIdEmpresa())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        Motorista motorista = new Motorista();
        motorista.setNome(dto.getNome());
        motorista.setCpf(dto.getCpf());
        motorista.setCnh(dto.getCnh());
        motorista.setEmpresa(empresa);

        return new MotoristaDTO(motoristaRepository.save(motorista));
    }


    // ============================================================
    // CONSULTAR TODOS OS MOTORISTAS
    // GET /motoristas
    // ============================================================
    @Transactional(readOnly = true)
    public List<MotoristaDTO> consultarMotoristas() {

        List<Motorista> motoristas = motoristaRepository.findAll();

        if (motoristas.isEmpty()) {
            throw new IllegalArgumentException("Nenhum motorista encontrado!");
        }

        return motoristas.stream()
                .map(MotoristaDTO::new)
                .toList();
    }


    // ============================================================
    // CONSULTAR MOTORISTA POR ID
    // GET /motoristas/{id}
    // ============================================================
    @Transactional(readOnly = true)
    public MotoristaDTO consultarMotoristaPorId(Long idMotorista) {

        return new MotoristaDTO(buscarOuLancar(idMotorista));
    }


    // ============================================================
    // CONSULTAR MOTORISTAS POR EMPRESA
    // GET /motoristas/empresa/{idEmpresa}
    // ============================================================
    // Útil para a empresa listar sua própria frota de motoristas.
    @Transactional(readOnly = true)
    public List<MotoristaDTO> consultarMotoristasPorEmpresa(Long idEmpresa) {

        // Valida se a empresa existe antes de buscar
        empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada!"));

        // Precisa de findByEmpresa_IdEmpresa no repositório
        return motoristaRepository.findByEmpresa_IdEmpresa(idEmpresa)
                .stream()
                .map(MotoristaDTO::new)
                .toList();
    }


    // ============================================================
    // ATUALIZAR MOTORISTA
    // PUT /motoristas/{id}
    // ============================================================
    // CPF não pode ser alterado — é o documento de identificação.
    // idEmpresa pode ser alterado para transferir o motorista.
    public MotoristaDTO alterarMotorista(Long idMotorista, MotoristaUpdateDTO dto) {

        Motorista motoristaExistente = buscarOuLancar(idMotorista);

        if (dto.getNome() != null) motoristaExistente.setNome(dto.getNome());
        if (dto.getCnh() != null)  motoristaExistente.setCnh(dto.getCnh());

        // Permite transferir motorista para outra empresa
        if (dto.getIdEmpresa() != null) {
            Empresa novaEmpresa = empresaRepository.findById(dto.getIdEmpresa())
                    .orElseThrow(() -> new RuntimeException("Empresa destino não encontrada"));
            motoristaExistente.setEmpresa(novaEmpresa);
        }

        return new MotoristaDTO(motoristaRepository.save(motoristaExistente));
    }


    // ============================================================
    // DELETAR MOTORISTA
    // DELETE /motoristas/{id}
    // ============================================================
    public void deletarMotorista(Long idMotorista) {
        buscarOuLancar(idMotorista);
        motoristaRepository.deleteById(idMotorista);
    }


    // ============================================================
    // MÉTODO AUXILIAR PRIVADO
    // ============================================================
    private Motorista buscarOuLancar(Long idMotorista) {
        return motoristaRepository.findById(idMotorista)
                .orElseThrow(() -> new IllegalArgumentException("Motorista não encontrado!"));
    }
}