package com.icc.tarefas.service;


import com.icc.tarefas.dto.request.UserRequestDTO;
import com.icc.tarefas.dto.response.UserResponseDTO;
import com.icc.tarefas.mapper.UserMapper;
import com.icc.tarefas.model.User;
import com.icc.tarefas.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserResponseDTO criar(UserRequestDTO request) {
        if (repository.existsByEmail((request.email()))) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        User user = mapper.toEntity(request);
        return mapper.toResponseDTO(repository.save(user));
    }

    public List<UserResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public UserResponseDTO buscarPorId(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));
        return mapper.toResponseDTO(user);
    }

    public UserResponseDTO atualizar(Integer id, UserRequestDTO request) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        user.setNome(request.nome());
        user.setEmail(request.email());
        user.setSenha(request.senha());

        return mapper.toResponseDTO(repository.save(user));
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado.");
        }
        repository.deleteById(id);
    }
}
