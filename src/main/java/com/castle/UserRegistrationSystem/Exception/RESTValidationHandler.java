package com.castle.UserRegistrationSystem.Exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.awt.TrayIcon;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RESTValidationHandler {

    private MessageSource mMessageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        mMessageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<FiledValidationErrorDetails> handleValidationError(
            MethodArgumentNotValidException mNotValidException,
            HttpServletRequest request
    ){
        FiledValidationErrorDetails details = new FiledValidationErrorDetails();

        details.setError_timeStamp(new Date().getTime());
        details.setError_status(HttpStatus.BAD_REQUEST.value());
        details.setError_title("Field Validation Error");
        details.setError_detail("Input Field Validation Error");
        details.setError_developer_message(mNotValidException.getClass().getName());
        details.setError_path(request.getRequestURI());

        BindingResult bindingResult = mNotValidException.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.stream().map(this::mapToFieldValidationError)
                .forEach(fieldValidationError -> addErrors(fieldValidationError, details));
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    private void addErrors(FieldValidationError fieldValidationError, FiledValidationErrorDetails details) {
        List<FieldValidationError> errors = details.getErrors().get(fieldValidationError.getField());
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(fieldValidationError);
        details.getErrors().put(fieldValidationError.getField(), errors);
    }

    private FieldValidationError mapToFieldValidationError(FieldError fieldError) {
        FieldValidationError error = new FieldValidationError();
        if (fieldError != null) {
            Locale locale = LocaleContextHolder.getLocale();
            String message = mMessageSource.getMessage(fieldError.getDefaultMessage(), null, locale);
            error.setField(fieldError.getField());
            error.setMessage(message);
            error.setType(TrayIcon.MessageType.ERROR);
        }
        return error;
    }
}
