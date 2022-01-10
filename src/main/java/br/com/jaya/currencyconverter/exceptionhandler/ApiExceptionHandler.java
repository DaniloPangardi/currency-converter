package br.com.jaya.currencyconverter.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

import br.com.jaya.currencyconverter.exception.ResourceNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler {

	private static final String BAD_REQUEST_GENERIC = "One or more fields are invalid. Please fill in correctly and try again.";
	
	private static final String INTERNAL_ERROR_MESSAGE = "An unexpected internal system error has occurred. Try again and if the problem persists, contact your system administrator.";
	
    private static final Logger LOG = LoggerFactory.getLogger(ApiExceptionHandler.class);
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException e) {
    	return new ResponseEntity<>(BAD_REQUEST_GENERIC, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundException(Exception e) {
       return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<Object> restClientException(RestClientException e) {
       LOG.error(e.getMessage(), e);
       return new ResponseEntity<>(INTERNAL_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> genericException(Exception e) {
       LOG.error(e.getMessage(), e.getClass(), e);
       return new ResponseEntity<>(INTERNAL_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}