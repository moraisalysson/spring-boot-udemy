package br.com.erudito.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends NegocioException {
    public UnsupportedMathOperationException(String message) {
        super(message);
    }
}
