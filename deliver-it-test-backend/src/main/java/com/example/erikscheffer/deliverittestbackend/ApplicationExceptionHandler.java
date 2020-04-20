package com.example.erikscheffer.deliverittestbackend;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public ApplicationExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex,
                createErrorMessageWrappers(ex.getBindingResult()),
                headers,
                HttpStatus.BAD_REQUEST,
                request);
    }

    private List<ErrorMessageWrapper> createErrorMessageWrappers(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(fieldError -> new ErrorMessageWrapper(
                        messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()),
                        fieldError.toString()))
                .collect(Collectors.toList());
    }

    @Data
    @AllArgsConstructor
    public static class ErrorMessageWrapper {
        private String simpleMessage;
        private String detailedMessage;
    }
}
