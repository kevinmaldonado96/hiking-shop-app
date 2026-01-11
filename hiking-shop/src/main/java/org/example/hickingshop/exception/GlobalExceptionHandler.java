package org.example.hickingshop.exception;

import org.example.hickingshop.dto.ErrorDetailsDto;
import org.example.hickingshop.exception.exceptions.NotFoundDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundDataException.class)
    public ResponseEntity<ErrorDetailsDto> duplicateDataException(NotFoundDataException ex){
        ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto(ex.getMessage(), "Ha ocurrido un error en el aplicativo");
        return new ResponseEntity<>(errorDetailsDto, HttpStatus.BAD_REQUEST);
    }
}
