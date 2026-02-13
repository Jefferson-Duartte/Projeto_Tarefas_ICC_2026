package com.icc.tarefas.controller;

import com.icc.tarefas.dto.request.TarefaRequestDTO;
import com.icc.tarefas.dto.response.TarefaResponseDTO;
import com.icc.tarefas.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @Operation(summary = "Criar nova tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TarefaResponseDTO criar(@RequestBody TarefaRequestDTO request) {
        return tarefaService.criar(request);
    }

    @Operation(summary = "Listar todas as tarefas")
    @GetMapping
    public List<TarefaResponseDTO> listar() {
        return tarefaService.listarTodas();
    }

    @Operation(summary = "Buscar tarefa por ID")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @GetMapping("/{id}")
    public TarefaResponseDTO buscarPorId(@PathVariable Integer id) {
        return tarefaService.buscarPorId(id);
    }

    @Operation(summary = "Atualizar uma tarefa existente")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "400", description = "Erro de validação")
    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> atualizar(
            @PathVariable Integer id,
            @RequestBody TarefaRequestDTO dto) {

        TarefaResponseDTO tarefaAtualizada = tarefaService.atualizar(id, dto);

        return ResponseEntity.ok(tarefaAtualizada);
    }

    @Operation(summary = "Deletar tarefa")
    @ApiResponse(responseCode = "204", description = "Tarefa deletada")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        tarefaService.deletar(id);
    }


}
