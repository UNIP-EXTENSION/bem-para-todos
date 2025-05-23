package br.ong.bemparatodos.bemparatodos.controller.exception;

import br.ong.bemparatodos.bemparatodos.service.exception.database.DatabaseIntegrityViolationException;
import br.ong.bemparatodos.bemparatodos.service.exception.resource.ResourceInvalidException;
import br.ong.bemparatodos.bemparatodos.service.exception.resource.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<StandardErrorRecord> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
    return buildResponse(HttpStatus.NOT_FOUND, List.of(e.getMessage()), request);
  }

  @ExceptionHandler(ResourceInvalidException.class)
  public ResponseEntity<StandardErrorRecord> resourceInvalid(ResourceInvalidException e, HttpServletRequest request) {
    return buildResponse(HttpStatus.UNPROCESSABLE_ENTITY, Collections.singletonList(e.getMessage()), request);
  }

  @ExceptionHandler(DatabaseIntegrityViolationException.class)
  public ResponseEntity<StandardErrorRecord> databaseIntegrityViolation(DatabaseIntegrityViolationException e, HttpServletRequest request) {
    return buildResponse(HttpStatus.CONFLICT, Collections.singletonList(e.getMessage()), request);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<StandardErrorRecord> methodArgumentTypeMismatch(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
    return buildResponse(HttpStatus.BAD_REQUEST, Collections.singletonList(e.getMessage()), request);
  }


  private ResponseEntity<StandardErrorRecord> buildResponse(HttpStatus status, Collection<String> messages, HttpServletRequest request) {
    StandardErrorRecord error = new StandardErrorRecord(
       Instant.now(),
       status.value(),
       messages,
       request.getRequestURI());
    return ResponseEntity.status(status).body(error);
  }

}