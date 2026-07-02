package com.fretebot.api.Service;

import com.fretebot.api.DTO.EmpresaCreateDTO;
import com.fretebot.api.DTO.EmpresaDTO;
import com.fretebot.api.DTO.EmpresaUpdateDTO;
import com.fretebot.api.Entity.Empresa;
import com.fretebot.api.Entity.Usuario;
import com.fretebot.api.Repository.EmpresaRepository;
import com.fretebot.api.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    // ============================================================
    // CADASTRAR EMPRESA
    // POST /empresas
    // ============================================================
    public EmpresaDTO cadastrarEmpresa(EmpresaCreateDTO dto) {

        // Garante que não existem duas empresas com o mesmo CNPJ
        if (empresaRepository.existsByDocumento(dto.getDocumento())) {
            throw new IllegalArgumentException("Já existe uma empresa cadastrada com este CNPJ.");
        }

        // Busca o Usuario que será vinculado à empresa (relação @OneToOne na entity)
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Empresa empresa = new Empresa();
        empresa.setRazaoSocial(dto.getRazaoSocial());
        empresa.setNomeFantasia(dto.getNomeFantasia());
        empresa.setDocumento(dto.getDocumento());
        empresa.setUsuario(usuario);
        // notaMedia começa nula — será calculada conforme avaliações chegarem
        empresa.setNotaMedia(null);

        return new EmpresaDTO(empresaRepository.save(empresa));
    }


    // ============================================================
    // CONSULTAR TODAS AS EMPRESAS
    // GET /empresas
    // ============================================================
    @Transactional(readOnly = true)
    public List<EmpresaDTO> consultarEmpresas() {

        List<Empresa> empresas = empresaRepository.findAll();

        if (empresas.isEmpty()) {
            throw new IllegalArgumentException("Nenhuma empresa encontrada!");
        }

        return empresas.stream()
                .map(EmpresaDTO::new)
                .toList();
    }


    // ============================================================
    // CONSULTAR EMPRESA POR ID
    // GET /empresas/{id}
    // ============================================================
    @Transactional(readOnly = true)
    public EmpresaDTO consultarEmpresaPorId(Long idEmpresa) {

        Empresa empresa = buscarOuLancar(idEmpresa);
        return new EmpresaDTO(empresa);
    }


    // ============================================================
    // CONSULTAR EMPRESA POR CNPJ
    // GET /empresas/cnpj/{cnpj}
    // ============================================================
    @Transactional(readOnly = true)
    public EmpresaDTO consultarEmpresaPorDocumento(String documento) {

        Empresa empresa = empresaRepository.findByDocumento(documento)
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada para o CNPJ informado."));

        return new EmpresaDTO(empresa);
    }


    // ============================================================
    // ATUALIZAR EMPRESA
    // PUT /empresas/{id}
    // ============================================================
    // CNPJ não pode ser alterado — é o identificador fiscal da empresa.
    // Para trocar o CNPJ seria necessário criar uma nova empresa.
    public EmpresaDTO alterarEmpresa(Long idEmpresa, EmpresaUpdateDTO dto) {

        Empresa empresaExistente = buscarOuLancar(idEmpresa);

        if (dto.getRazaoSocial() != null)  empresaExistente.setRazaoSocial(dto.getRazaoSocial());
        if (dto.getNomeFantasia() != null) empresaExistente.setNomeFantasia(dto.getNomeFantasia());
        if (dto.getNotaMedia() != null)    empresaExistente.setNotaMedia(dto.getNotaMedia());

        return new EmpresaDTO(empresaRepository.save(empresaExistente));
    }


    // ============================================================
    // DELETAR EMPRESA
    // DELETE /empresas/{id}
    // ============================================================
    public void deletarEmpresa(Long idEmpresa) {
        buscarOuLancar(idEmpresa); // valida existência antes de deletar
        empresaRepository.deleteById(idEmpresa);
    }


    // ============================================================
    // MÉTODO AUXILIAR PRIVADO
    // ============================================================
    private Empresa buscarOuLancar(Long idEmpresa) {
        return empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada!"));
    }
}