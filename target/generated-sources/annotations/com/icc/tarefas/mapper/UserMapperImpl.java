package com.icc.tarefas.mapper;

import com.icc.tarefas.dto.request.UserRequestDTO;
import com.icc.tarefas.dto.response.UserResponseDTO;
import com.icc.tarefas.model.User;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-12T19:00:33-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDTO toResponseDTO(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String nome = null;
        String email = null;
        LocalDateTime dataDeCriacao = null;

        if ( user.getId() != null ) {
            id = user.getId().longValue();
        }
        nome = user.getNome();
        email = user.getEmail();
        dataDeCriacao = user.getDataDeCriacao();

        UserResponseDTO userResponseDTO = new UserResponseDTO( id, nome, email, dataDeCriacao );

        return userResponseDTO;
    }

    @Override
    public User toEntity(UserRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        User user = new User();

        user.setNome( requestDTO.nome() );
        user.setEmail( requestDTO.email() );
        user.setSenha( requestDTO.senha() );

        return user;
    }
}
