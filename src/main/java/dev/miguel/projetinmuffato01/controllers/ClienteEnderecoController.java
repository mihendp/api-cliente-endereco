package dev.miguel.projetinmuffato01.controllers;

import dev.miguel.projetinmuffato01.dto.EnderecoDTO;
import dev.miguel.projetinmuffato01.model.Cliente;
import dev.miguel.projetinmuffato01.model.Endereco;
import dev.miguel.projetinmuffato01.repository.ClienteRepository;
import dev.miguel.projetinmuffato01.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/endereco/cliente")
@AllArgsConstructor
public class ClienteEnderecoController {
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;

    @GetMapping("/{clienteId}")
    public ResponseEntity<List<Endereco>> getAllEnderecosByCliente(@PathVariable(name = "clienteId") UUID id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        return ResponseEntity.status(HttpStatus.OK).body(cliente.getEnderecos());
    }

    @PatchMapping("/{clienteId}/{enderecoId}")
    public ResponseEntity<Cliente> vincularClienteEndereco(@PathVariable UUID clienteId, @PathVariable Long enderecoId){
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        Endereco endereco = enderecoRepository.findById(enderecoId).orElseThrow(() -> new RuntimeException("Endereço não encontrado."));

        cliente.getEnderecos().add(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
    }

    @PutMapping("/editar/{enderecoId}")
    public ResponseEntity<Endereco> updateEndereco(@PathVariable Long enderecoId, @RequestBody EnderecoDTO enderecoDTO){
        Endereco endereco = enderecoRepository.findById(enderecoId).orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        BeanUtils.copyProperties(enderecoDTO, endereco);

        return ResponseEntity.status(HttpStatus.OK).body(enderecoRepository.save(endereco));
    }


}
