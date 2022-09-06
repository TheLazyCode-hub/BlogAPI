package com.example.blogAPI.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.blogAPI.payloads.APIresponse;

@RestControllerAdvice
public class GlobalExceptionHandler{
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIresponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String msg = ex.getMessage();
		APIresponse response = new APIresponse(msg, false);
		return new ResponseEntity<APIresponse>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		Map<String,String> response = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError)error).getField();
			String defaultMsg = error.getDefaultMessage();
			response.put(fieldName, defaultMsg);
		});
		return new ResponseEntity<Map<String,String>>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<APIresponse> resourceWhileDeleteNotFound(EmptyResultDataAccessException ex){
		String msg = ex.getMessage();
		APIresponse res = new APIresponse(msg, false);
		return new ResponseEntity<APIresponse>(res,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(APIException.class)
	public ResponseEntity<APIresponse> apiExceptionBadCredentials(APIException ex){
		String msg = ex.getMessage();
		APIresponse response = new APIresponse(msg, false);
		return new ResponseEntity<APIresponse>(response,HttpStatus.BAD_REQUEST);
	}
	
}
