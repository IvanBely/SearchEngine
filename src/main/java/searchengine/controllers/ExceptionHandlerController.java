package searchengine.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import searchengine.dto.response.ResultDto;
import searchengine.exception.CurrentRuntimeException;

@RestControllerAdvice
public record ExceptionHandlerController() {
    @ExceptionHandler(NullPointerException.class)
    public ResultDto nullPointerException(NullPointerException exception) {
        return new  ResultDto(true, exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CurrentRuntimeException.class)
    public ResultDto handlerInterruptedException(CurrentRuntimeException exception) {
        return new  ResultDto(true, exception.getMessage(), HttpStatus.OK);
    }
}
