package dev.miguel.projetinmuffato01.dto;

import java.time.LocalDate;

public record ClienteDTO(
         String nome,
         String email,
         LocalDate dob
){}
