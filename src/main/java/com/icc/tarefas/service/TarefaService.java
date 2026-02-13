package com.icc.tarefas.service;

import com.icc.tarefas.dto.request.TarefaRequestDTO;
import com.icc.tarefas.dto.response.TarefaResponseDTO;
import com.icc.tarefas.mapper.TarefaMapper;
import com.icc.tarefas.model.Tarefa;
import com.icc.tarefas.model.User;
import com.icc.tarefas.model.enums.StatusTarefa;
import com.icc.tarefas.repository.TarefaRepository;
import com.icc.tarefas.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository repository;
    private final UserRepository userRepository;
    private final TarefaMapper mapper;

    public TarefaService(TarefaRepository repository,
                         UserRepository userRepository,
                         TarefaMapper mapper) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public TarefaResponseDTO criar(TarefaRequestDTO request) {

        User usuario = userRepository.findById(request.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        Tarefa tarefa = mapper.toEntity(request);
        tarefa.setUsuario(usuario);

        atualizarStatusAutomaticamente(tarefa);

        return mapper.toResponseDTO(repository.save(tarefa));
    }

    public List<TarefaResponseDTO> listarTodas() {
        return repository.findAll()
                .stream()
                .peek(this::atualizarStatusAutomaticamente)
                .map(mapper::toResponseDTO)
                .toList();
    }

    public TarefaResponseDTO buscarPorId(Integer id) {
        Tarefa tarefa = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada."));

        atualizarStatusAutomaticamente(tarefa);

        return mapper.toResponseDTO(tarefa);
    }

    public TarefaResponseDTO atualizar(Integer id, TarefaRequestDTO request) {

        Tarefa tarefa = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada."));

        User usuario = userRepository.findById(request.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        tarefa.setTitulo(request.titulo());
        tarefa.setDescricao(request.descricao());
        tarefa.setDataVencimento(request.dataVencimento());
        tarefa.setStatus(request.status() != null ? request.status() : tarefa.getStatus());
        tarefa.setUsuario(usuario);

        atualizarStatusAutomaticamente(tarefa);

        return mapper.toResponseDTO(repository.save(tarefa));
    }

    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Tarefa não encontrada.");
        }
        repository.deleteById(id);
    }

    private void atualizarStatusAutomaticamente(Tarefa tarefa) {
        if (tarefa.getDataVencimento().isBefore(LocalDateTime.now())
                && tarefa.getStatus() != StatusTarefa.CONCLUIDA) {
            tarefa.setStatus(StatusTarefa.ATRASADA);
        }
    }

}
