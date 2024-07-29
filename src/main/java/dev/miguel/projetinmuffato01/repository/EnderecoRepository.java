package dev.miguel.projetinmuffato01.repository;

import dev.miguel.projetinmuffato01.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
