package com.fretebot.api.Controller;

import com.fretebot.api.DTO.ClienteCreateDTO;
import com.fretebot.api.DTO.ClienteUpdateDTO;
import com.fretebot.api.Entity.Cliente;
import com.fretebot.api.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    //cadastrar cliente
    @PostMapping("/registro")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody ClienteCreateDTO clienteCreateDTO){
        Cliente clienteCriado = clienteService.cadastrarCliente(clienteCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriado);
    }

    //listar todos clientes
    @GetMapping
    public List<Cliente> listarClientes(){
        return clienteService.consultarClientes();
    }

    //listar cliente pelo id
    @GetMapping("/{id}")
    public Cliente listarClientePorId(@PathVariable Long id){
        return clienteService.consultarClientePorId(id);
    }


    //consultar clientes pelo nome
    @GetMapping("/nome/{nome}")
    public List<Cliente> listarClientePorNome(@PathVariable String nome){
        return clienteService.consultarClientePorNome(nome);
    }

    @PutMapping("/{id}")
    public Cliente alterarCliente(@RequestBody ClienteUpdateDTO clienteUpdateDTO, @PathVariable("id") Long id){
        if(id == clienteUpdateDTO.getIdCliente()){
            return clienteService.alterarCliente(id, clienteUpdateDTO);
        }
        else
            return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        clienteService.delete(id);
    }


}
