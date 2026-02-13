package com.icc.tarefas.dto.response;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String nome,
        String email,
        LocalDateTime dataDeCriacao
) {
}
