package org.project.spring_mini_project.adviser;

import org.project.spring_mini_project.utils.BaseResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalRestControllerAdviser {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse<?> handleNoSuchElementException(NoSuchElementException ex) {
        return BaseResponse
                .notFound()
                .setMetadata(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HashMap<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                fieldError -> {
                    errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
        );
        return BaseResponse.badRequest().setMetadata(errors);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public BaseResponse<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        String message = e.getMostSpecificCause().getMessage();
        if (message.contains("email")) {
            return BaseResponse.badRequest()
                    .setMetadata("Email already exists");
        } else if (message.contains("username")) {
            return BaseResponse.badRequest()
                    .setMetadata("Username already exists");
        } else if (message.contains("alias")) {
            return BaseResponse.badRequest()
                    .setMetadata("Alias already exists");
        } else if (message.contains("national_id_card")) {
            return BaseResponse.badRequest()
                    .setMetadata("National ID Card already exists");
        } else {
            // Handle other types of DataIntegrityViolationException or return a generic message
            return BaseResponse.badRequest()
                    .setMetadata("Data integrity violation");
        }
    }

    //    image exception , image size, image format ...
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException e) {
        return new ResponseEntity<>(e.getReason(), e.getStatusCode());
    }



}
