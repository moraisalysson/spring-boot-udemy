package br.com.erudito.exception.handler;

import br.com.erudito.exception.DivisionByZeroException;
import br.com.erudito.exception.ExceptionResponse;
import br.com.erudito.exception.NegocioException;
import br.com.erudito.exception.UnsupportedMathOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice //
@RestController
public class CustomEntityResponseHandler extends ResponseEntityExceptionHandler {

    //para exceções genéricas
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //tratamento da exceções de negócio
    @ExceptionHandler(NegocioException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestException(NegocioException ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}