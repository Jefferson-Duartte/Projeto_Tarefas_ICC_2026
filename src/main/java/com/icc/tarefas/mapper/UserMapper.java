package com.icc.tarefas.mapper;

import com.icc.tarefas.dto.request.UserRequestDTO;
import com.icc.tarefas.dto.response.UserResponseDTO;
import com.icc.tarefas.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toResponseDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataDeCriacao", ignore = true)
    @Mapping(target = "tarefas", ignore = true)
    User toEntity(UserRequestDTO requestDTO);
}
