package com.icc.tarefas.mapper;

import com.icc.tarefas.dto.request.TarefaRequestDTO;
import com.icc.tarefas.dto.response.TarefaResponseDTO;
import com.icc.tarefas.model.Tarefa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TarefaMapper {
    @Mapping(source = "usuario.id", target = "usuarioId")
    TarefaResponseDTO toResponseDTO(Tarefa tarefa);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    Tarefa toEntity(TarefaRequestDTO requestDTO);
}
