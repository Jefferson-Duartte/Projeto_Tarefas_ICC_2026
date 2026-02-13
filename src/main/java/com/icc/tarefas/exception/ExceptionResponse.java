package com.icc.tarefas.exception;

import java.time.LocalDateTime;

public record ExceptionResponse (LocalDateTime timestamp, String message, String path, int statusCode) {}
