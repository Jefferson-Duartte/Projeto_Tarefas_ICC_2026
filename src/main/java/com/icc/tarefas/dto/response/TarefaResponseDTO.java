package com.icc.tarefas.dto.response;

import com.icc.tarefas.model.enums.StatusTarefa;

import java.time.LocalDateTime;

public record TarefaResponseDTO(
        Long id,
        String titulo,
        String descricao,
        StatusTarefa status,
        LocalDateTime dataCriacao,
        LocalDateTime dataVencimento,
        Long usuarioId
) {
}
