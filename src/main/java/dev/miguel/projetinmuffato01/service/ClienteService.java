package dev.miguel.projetinmuffato01.service;

import dev.miguel.projetinmuffato01.model.Cliente;
import dev.miguel.projetinmuffato01.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getCliente(UUID id){
        return clienteRepository.findById(id);
    }

}
