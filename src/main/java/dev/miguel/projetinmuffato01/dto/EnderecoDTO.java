package dev.miguel.projetinmuffato01.dto;

public record EnderecoDTO(
         String logradouro,
         String numero,
         String complemento,
         String cep,
         String cidade,
         String UF
) {
}
