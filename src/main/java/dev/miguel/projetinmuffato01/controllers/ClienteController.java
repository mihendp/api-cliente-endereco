package dev.miguel.projetinmuffato01.controllers;

import dev.miguel.projetinmuffato01.dto.ClienteDTO;
import dev.miguel.projetinmuffato01.model.Cliente;
import dev.miguel.projetinmuffato01.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/all-clientes")
    public ResponseEntity<List<Cliente>> getAllClientes(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable UUID id){
        return ResponseEntity.ok(clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado.")));
    }

    @PostMapping("/create")
    public ResponseEntity<Cliente> createCliente(@RequestBody ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteDTO, cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable UUID id, @RequestBody ClienteDTO clienteDTO){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        BeanUtils.copyProperties(clienteDTO, cliente.get());
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(cliente.get()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable UUID id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        try {
            clienteRepository.delete(cliente.get());
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso.");
    }
}
