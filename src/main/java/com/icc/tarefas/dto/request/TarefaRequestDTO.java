package com.icc.tarefas.dto.request;

import com.icc.tarefas.model.enums.StatusTarefa;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TarefaRequestDTO(
        @NotBlank(message = "Título é obrigatório")
        String titulo,

        String descricao,

        @NotNull(message = "Data de vencimento é obrigatória")
        @Future(message = "Data de vencimento deve estar no futuro")
        LocalDateTime dataVencimento,

        StatusTarefa status,

        @NotNull(message = "Usuário é obrigatório")
        Integer usuarioId
) {}
