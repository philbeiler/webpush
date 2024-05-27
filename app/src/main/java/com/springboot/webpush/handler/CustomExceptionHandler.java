package com.springboot.webpush.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springboot.webpush.exception.ClientKeyPairException;
import com.springboot.webpush.exception.InvalidFieldException;
import com.springboot.webpush.exception.InvalidHeaderFieldException;
import com.springboot.webpush.exception.PushkeyNotFoundException;

/**
 * {@link CustomExceptionHandler}.
 */
@RestControllerAdvice
public class CustomExceptionHandler {
    /**
     * Handle.
     *
     * @param exception The {@link InvalidFieldException} instance.
     * @return The message.
     */
    @ExceptionHandler
    public String handleInvalidFieldException(final InvalidFieldException exception) {
        return exception.getMessage();
    }

    /**
     * Handle.
     *
     * @param exception The {@link InvalidHeaderFieldException} instance.
     * @return The {@link ResponseEntity}.
     */

    @ExceptionHandler
    public ResponseEntity<String> handleInvalidHeaderFieldException(final InvalidHeaderFieldException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.PRECONDITION_FAILED);
    }

    /**
     * Testing.
     *
     * @param ex The {@link PushkeyNotFoundException} instance.
     * @return The message.
     */
    @ResponseBody
    @ExceptionHandler(PushkeyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String pushKeyNotFoundHandler(final PushkeyNotFoundException ex) {
        return ex.getMessage();
    }

    /**
     * Testing.
     *
     * @param ex The {@link ClientKeyPairException} instance
     * @return The message.
     */
    @ResponseBody
    @ExceptionHandler(ClientKeyPairException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String pushKeyNotFoundHandler(final ClientKeyPairException ex) {
        return ex.getMessage();
    }

}
