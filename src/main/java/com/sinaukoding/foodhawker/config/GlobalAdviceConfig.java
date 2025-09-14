package com.sinaukoding.foodhawker.config;

import com.sinaukoding.foodhawker.model.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalAdviceConfig {
    @ExceptionHandler({RuntimeException.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<?> handleException(Exception e) {
        return BaseResponse.error("Something bad happen on app server please try again later, contact support for this error", e.getMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<?> handleExceptionBadRequest(Exception e) {
        return BaseResponse.badRequest(e.getMessage());
    }
}