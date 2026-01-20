package com.ey.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class CustomExceptionHandler {
 
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex){

		Map<String,List<String>>body=new HashMap<>();
		List<String> errors=ex.getBindingResult()
							.getFieldErrors()
							.stream()
							.map(DefaultMessageSourceResolvable::getDefaultMessage)
							.collect(Collectors.toList());

		body.put("errors",errors);

		return new ResponseEntity<>(body,HttpStatus.BAD_REQUEST);

	}
	

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex){

		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(DisposeNotFound.class)
	public ResponseEntity<Object> handleDisposeNotFound(DisposeNotFound ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(InspectionNotFoundException.class)
	public ResponseEntity<Object> hanldeInspectionNotFoundException(InspectionNotFoundException ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(OrderNotFound.class)
	public ResponseEntity<Object> hanldeOrderNotFound(OrderNotFound ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(UserAlreadyExsistsException.class)
	public ResponseEntity<Object> hanldeUserAlreadyExsistsException(UserAlreadyExsistsException ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> hanldeUserNotFoundException(UserNotFoundException ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<Object> hanldeInvalidPasswordException(InvalidPasswordException ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDuplicateEntry(DataIntegrityViolationException ex) {

        String message = "Duplicate value detected";

        if (ex.getRootCause() != null &&
            ex.getRootCause().getMessage().contains("licenceNumber")) {
            message = "Licence number already exists";
        } 
        else if (ex.getRootCause().getMessage().contains("email")) {
            message = "Email already exists";
        }

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(message);
    }

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> hanldeException(Exception ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<Object> handleExpired(ExpiredJwtException ex){
		
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
}

 