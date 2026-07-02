package com.fretebot.api.Service;

import com.fretebot.api.Controller.ClienteController;
import com.fretebot.api.DTO.ClienteCreateDTO;
import com.fretebot.api.DTO.ClienteUpdateDTO;
import com.fretebot.api.Entity.Cliente;
import com.fretebot.api.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarCliente(ClienteCreateDTO clienteCreateDTO){
        Cliente cliente = new Cliente();
        cliente.setDocumento(clienteCreateDTO.getDocumento());
        cliente.setNome(clienteCreateDTO.getNome());
        cliente.setTelefone(clienteCreateDTO.getTelefone());

        return clienteRepository.save(cliente);

    }

    public Cliente buscarOuCriarCliente(
            ClienteCreateDTO clienteDTO
    ) {

        Cliente clienteExistente =
                clienteRepository
                        .findByTelefone(
                                clienteDTO.getTelefone()
                        )
                        .orElse(null);

        if (clienteExistente != null) {

            return clienteExistente;
        }

        Cliente novoCliente = new Cliente();

        novoCliente.setNome(clienteDTO.getNome());

        novoCliente.setTelefone(
                clienteDTO.getTelefone()
        );

        novoCliente.setDocumento(
                clienteDTO.getDocumento()
        );

        return clienteRepository.save(novoCliente);
    }

    //alterar informaçoes do usuario
    public Cliente alterarCliente(Long idCliente, ClienteUpdateDTO clienteUpdateDTO) {
        Cliente clienteExistente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado!!"));

        if(clienteUpdateDTO.getNome() != null){
            clienteExistente.setNome(clienteUpdateDTO.getNome());
        }

        if(clienteUpdateDTO.getTelefone() != 0){
            clienteExistente.setTelefone(clienteUpdateDTO.getTelefone());
        }

        return clienteRepository.save(clienteExistente);
    }

    //consultar clientes
    public List<Cliente> consultarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream().map(cliente -> {
            Cliente dto = new Cliente();
            dto.setIdCliente(cliente.getIdCliente());
            dto.setDocumento(cliente.getDocumento());
            dto.setNome(cliente.getNome());
            dto.setTelefone(cliente.getTelefone());

            // transforma agendamentos em resumos

            return dto;
        }).collect(Collectors.toList());
    }


    //consultar cliente pelo nome
    public List<Cliente> consultarClientePorNome(String nome) {
        return clienteRepository.findByNomeIgnoreCaseContaining(nome);
    }

    //consultar cliente especifico
    public Cliente consultarClientePorId(Long idCliente) {
        Cliente clienteEspecifico = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado!!"));

        return clienteEspecifico;
    }

    //deletar cliente pelo id
    public void delete(Long idCliente){
        clienteRepository.deleteById(idCliente);           //metodo que faz o delete do usuario

    }

}
