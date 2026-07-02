package com.fretebot.api.Service;

import com.fretebot.api.DTO.FreteCreateDTO;
import com.fretebot.api.DTO.FreteDTO;
import com.fretebot.api.DTO.FreteUpdateDTO;
import com.fretebot.api.Entity.Cliente;
import com.fretebot.api.Entity.Enums.StatusFreteEnum;
import com.fretebot.api.Entity.Frete;
import com.fretebot.api.Repository.ClienteRepository;
import com.fretebot.api.Repository.FreteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FreteService {

    @Autowired
    private FreteRepository freteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public FreteDTO cadastrarFrete(FreteCreateDTO dto) {

        // Busca cliente pelo telefone
        Cliente clienteExistente =
                clienteRepository
                        .findByTelefone(
                                dto.getTelefoneCliente()
                        )
                        .orElse(null);

        Cliente cliente;

        // Se já existir reutiliza
        if (clienteExistente != null) {

            cliente = clienteExistente;

        } else {

            // Cria automaticamente
            cliente = Cliente.builder()
                    .nome(dto.getNomeCliente())
                    .telefone(dto.getTelefoneCliente())
                    .documento(null)
                    .build();

            cliente = clienteRepository.save(cliente);

            cliente = clienteExistente;
        }

        // Cria o frete
        Frete frete = new Frete();

        frete.setCepOrigem(dto.getCepOrigem());
        frete.setCidadeOrigem(dto.getCidadeOrigem());

        frete.setCepDestino(dto.getCepDestino());
        frete.setCidadeDestino(dto.getCidadeDestino());

        frete.setDataColeta(dto.getDataColeta());
        frete.setDataEntrega(dto.getDataEntrega());

        frete.setPesoTotal(dto.getPesoTotal());
        frete.setVolumeTotal(dto.getVolumeTotal());

        frete.setTipoCarga(dto.getTipoCarga());

        // Vincula cliente
        frete.setCliente(clienteExistente);

        // Status inicial
        frete.setStatus(StatusFreteEnum.PENDENTE);

        // Datas automáticas
        frete.setDataCriacao(LocalDateTime.now());
        frete.setDataAtualizacao(LocalDateTime.now());

        // Salva
        Frete freteSalvo =
                freteRepository.save(frete);

        // Retorno
        return new FreteDTO(freteSalvo);
    }


    @Transactional(readOnly = true)  // readOnly = true otimiza a query no JPA (sem dirty checking)
    public List<FreteDTO> consultarFretes() {

        List<Frete> fretes = freteRepository.findAll();

        if (fretes.isEmpty()) {
            throw new IllegalArgumentException("Nenhum frete encontrado!");
        }

        // Stream + map converte cada entity em DTO — mesmo padrão do AgendamentoService
        return fretes.stream()
                .map(FreteDTO::new)
                .toList();
    }



    @Transactional(readOnly = true)
    public FreteDTO consultarFretePorId(Long idFrete) {

        Frete frete = freteRepository.findById(idFrete)
                .orElseThrow(() -> new IllegalArgumentException("Frete não encontrado!"));

        return new FreteDTO(frete);
    }


    @Transactional(readOnly = true)
    public List<FreteDTO> consultarFretesPorCliente(Long idCliente) {

        // Valida se o cliente existe antes de buscar os fretes
        clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado!"));

        // Precisa de um metodo no repositório: findByCliente_IdCliente(Long id)
        return freteRepository.findByCliente_IdCliente(idCliente)
                .stream()
                .map(FreteDTO::new)
                .toList();
    }



    @Transactional(readOnly = true)
    public List<FreteDTO> consultarFretesPorStatus(StatusFreteEnum status) {

        // Precisa de um método no repositório: findByStatus(StatusFreteEnum status)
        return freteRepository.findByStatus(status)
                .stream()
                .map(FreteDTO::new)
                .toList();
    }



    public FreteDTO alterarFrete(Long idFrete, FreteUpdateDTO dto) {

        Frete freteExistente = freteRepository.findById(idFrete)
                .orElseThrow(() -> new IllegalArgumentException("Frete não encontrado!"));

        // Só atualiza o campo se vier preenchido no JSON
        if (dto.getCepOrigem() != null)     freteExistente.setCepOrigem(dto.getCepOrigem());
        if (dto.getCidadeOrigem() != null)  freteExistente.setCidadeOrigem(dto.getCidadeOrigem());
        if (dto.getCepDestino() != null)    freteExistente.setCepDestino(dto.getCepDestino());
        if (dto.getCidadeDestino() != null) freteExistente.setCidadeDestino(dto.getCidadeDestino());
        if (dto.getDataColeta() != null)    freteExistente.setDataColeta(dto.getDataColeta());
        if (dto.getDataEntrega() != null)   freteExistente.setDataEntrega(dto.getDataEntrega());
        if (dto.getPesoTotal() != null)     freteExistente.setPesoTotal(dto.getPesoTotal());
        if (dto.getVolumeTotal() != null)   freteExistente.setVolumeTotal(dto.getVolumeTotal());
        if (dto.getTipoCarga() != null)     freteExistente.setTipoCarga(dto.getTipoCarga());

        // Sempre atualiza a data de modificação
        freteExistente.setDataAtualizacao(LocalDateTime.now());

        return new FreteDTO(freteRepository.save(freteExistente));
    }



    public void deletarFrete(Long idFrete) {
        // Valida existência antes de deletar para retornar erro claro
        freteRepository.findById(idFrete)
                .orElseThrow(() -> new IllegalArgumentException("Frete não encontrado!"));

        freteRepository.deleteById(idFrete);
    }



    public FreteDTO aguardarCotacao(Long idFrete) {

        Frete frete = buscarOuLancar(idFrete);

        if (!frete.getStatus().equals(StatusFreteEnum.PENDENTE)) {
            throw new IllegalArgumentException(
                    "Somente fretes PENDENTES podem aguardar cotação. Status atual: "
                            + frete.getStatus().getDescricao());
        }

        frete.setStatus(StatusFreteEnum.AGUARDANDO_COTACAO);
        frete.setDataAtualizacao(LocalDateTime.now());

        return new FreteDTO(freteRepository.save(frete));
    }



    public FreteDTO cotarFrete(Long idFrete) {

        Frete frete = buscarOuLancar(idFrete);

        if (!frete.getStatus().equals(StatusFreteEnum.AGUARDANDO_COTACAO)) {
            throw new IllegalArgumentException(
                    "Somente fretes AGUARDANDO COTAÇÃO podem ser cotados. Status atual: "
                            + frete.getStatus().getDescricao());
        }

        frete.setStatus(StatusFreteEnum.COTADO);
        frete.setDataAtualizacao(LocalDateTime.now());

        return new FreteDTO(freteRepository.save(frete));
    }


    public FreteDTO aprovarFrete(Long idFrete) {

        Frete frete = buscarOuLancar(idFrete);

        if (!frete.getStatus().equals(StatusFreteEnum.COTADO)) {
            throw new IllegalArgumentException(
                    "Somente fretes COTADOS podem ser aprovados. Status atual: "
                            + frete.getStatus().getDescricao());
        }

        frete.setStatus(StatusFreteEnum.APROVADO);
        frete.setDataAtualizacao(LocalDateTime.now());

        return new FreteDTO(freteRepository.save(frete));
    }


    // APROVADO → EM_TRANSPORTE
    // PATCH /fretes/{id}/iniciar-transporte
    public FreteDTO iniciarTransporte(Long idFrete) {

        Frete frete = buscarOuLancar(idFrete);

        if (!frete.getStatus().equals(StatusFreteEnum.APROVADO)) {
            throw new IllegalArgumentException(
                    "Somente fretes APROVADOS podem ser transportados. Status atual: "
                            + frete.getStatus().getDescricao());
        }

        frete.setStatus(StatusFreteEnum.EM_TRANSPORTE);
        frete.setDataAtualizacao(LocalDateTime.now());

        return new FreteDTO(freteRepository.save(frete));
    }


    // EM_TRANSPORTE → CONCLUIDO
    // PATCH /fretes/{id}/concluir
    public FreteDTO concluirFrete(Long idFrete) {

        Frete frete = buscarOuLancar(idFrete);

        if (!frete.getStatus().equals(StatusFreteEnum.EM_TRANSPORTE)) {
            throw new IllegalArgumentException(
                    "Somente fretes EM TRANSPORTE podem ser concluídos. Status atual: "
                            + frete.getStatus().getDescricao());
        }

        frete.setStatus(StatusFreteEnum.CONCLUIDO);
        frete.setDataAtualizacao(LocalDateTime.now());

        return new FreteDTO(freteRepository.save(frete));
    }


    // QUALQUER STATUS → CANCELADO
    // PATCH /fretes/{id}/cancelar
    // Cancelamento pode ocorrer em qualquer etapa, exceto se já estiver cancelado ou concluído.
    public FreteDTO cancelarFrete(Long idFrete) {

        Frete frete = buscarOuLancar(idFrete);

        if (frete.getStatus().equals(StatusFreteEnum.CANCELADO)) {
            throw new IllegalArgumentException("Frete já está cancelado!");
        }

        if (frete.getStatus().equals(StatusFreteEnum.CONCLUIDO)) {
            throw new IllegalArgumentException("Fretes concluídos não podem ser cancelados.");
        }

        frete.setStatus(StatusFreteEnum.CANCELADO);
        frete.setDataAtualizacao(LocalDateTime.now());

        return new FreteDTO(freteRepository.save(frete));
    }


    // ============================================================
    // MÉTODO AUXILIAR PRIVADO
    // ============================================================
    // Centraliza o findById + orElseThrow para não repetir em cada método de status.
    // Padrão semelhante ao consultarAgendamentoPorIdInterno do projeto.
    private Frete buscarOuLancar(Long idFrete) {
        return freteRepository.findById(idFrete)
                .orElseThrow(() -> new RuntimeException("Frete não encontrado!"));
    }
}