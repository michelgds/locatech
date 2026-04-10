package br.com.fiap.locatech.controllers.handlers;

import br.com.fiap.locatech.dtos.ResourceNotFoundDTO;
import br.com.fiap.locatech.dtos.ValidationErrorDTO;
import br.com.fiap.locatech.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResourceNotFoundDTO> handlerResourceNotFoundException(ResourceNotFoundException e) {
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value()).body(new ResourceNotFoundDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> handlerResourceNotFoundException(MethodArgumentNotValidException e) {
        var status = HttpStatus.BAD_REQUEST;
        List<String> errors = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        return ResponseEntity.status(status.value()).body(new ValidationErrorDTO(errors, status.value()));
    }
}
