package dev.miguel.projetinmuffato01.controllers;

import dev.miguel.projetinmuffato01.dto.EnderecoDTO;
import dev.miguel.projetinmuffato01.model.Endereco;
import dev.miguel.projetinmuffato01.repository.EnderecoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    public ResponseEntity<List<Endereco>> getAllEnderecos(){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoRepository.findAll());
    }
    //TODO fazer get by id

    @PostMapping("/create")
    public ResponseEntity<Endereco> createEndereco(@RequestBody EnderecoDTO enderecoDTO){
        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(enderecoDTO, endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoRepository.save(endereco));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteEndereco(@PathVariable Long id){
        Endereco endereco = enderecoRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        try {
            enderecoRepository.delete(endereco);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Endereço deletado com sucesso.");
    }

}
