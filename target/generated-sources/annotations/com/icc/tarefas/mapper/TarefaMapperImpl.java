package com.icc.tarefas.mapper;

import com.icc.tarefas.dto.request.TarefaRequestDTO;
import com.icc.tarefas.dto.response.TarefaResponseDTO;
import com.icc.tarefas.model.Tarefa;
import com.icc.tarefas.model.User;
import com.icc.tarefas.model.enums.StatusTarefa;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-12T19:00:33-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class TarefaMapperImpl implements TarefaMapper {

    @Override
    public TarefaResponseDTO toResponseDTO(Tarefa tarefa) {
        if ( tarefa == null ) {
            return null;
        }

        Long usuarioId = null;
        Long id = null;
        String titulo = null;
        String descricao = null;
        StatusTarefa status = null;
        LocalDateTime dataCriacao = null;
        LocalDateTime dataVencimento = null;

        Integer id1 = tarefaUsuarioId( tarefa );
        if ( id1 != null ) {
            usuarioId = id1.longValue();
        }
        id = tarefa.getId();
        titulo = tarefa.getTitulo();
        descricao = tarefa.getDescricao();
        status = tarefa.getStatus();
        dataCriacao = tarefa.getDataCriacao();
        dataVencimento = tarefa.getDataVencimento();

        TarefaResponseDTO tarefaResponseDTO = new TarefaResponseDTO( id, titulo, descricao, status, dataCriacao, dataVencimento, usuarioId );

        return tarefaResponseDTO;
    }

    @Override
    public Tarefa toEntity(TarefaRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Tarefa tarefa = new Tarefa();

        tarefa.setTitulo( requestDTO.titulo() );
        tarefa.setDescricao( requestDTO.descricao() );
        tarefa.setStatus( requestDTO.status() );
        tarefa.setDataVencimento( requestDTO.dataVencimento() );

        return tarefa;
    }

    private Integer tarefaUsuarioId(Tarefa tarefa) {
        User usuario = tarefa.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        return usuario.getId();
    }
}
