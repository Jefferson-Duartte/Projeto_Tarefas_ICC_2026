package com.icc.tarefas.controller;

import com.icc.tarefas.dto.request.UserRequestDTO;
import com.icc.tarefas.dto.response.UserResponseDTO;
import com.icc.tarefas.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Criar novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Email já cadastrado")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO criar(@RequestBody @Valid UserRequestDTO request) {
        return userService.criar(request);
    }

    @Operation(summary = "Listar todos os usuários")
    @GetMapping
    public List<UserResponseDTO> listar() {
        return userService.listarTodos();
    }

    @Operation(summary = "Buscar usuário por ID")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @GetMapping("/{id}")
    public UserResponseDTO buscarPorId(@PathVariable Integer id) {
        return userService.buscarPorId(id);
    }

    @Operation(summary = "Atualizar usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/{id}")
    public UserResponseDTO atualizar(
            @PathVariable Integer id,
            @RequestBody @Valid UserRequestDTO request) {
        return userService.atualizar(id, request);
    }

    @Operation(summary = "Deletar usuário")
    @ApiResponse(responseCode = "204", description = "Usuário deletado")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        userService.deletar(id);
    }
}
