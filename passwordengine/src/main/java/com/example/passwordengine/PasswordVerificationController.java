package com.example.passwordengine;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@RequestMapping(path = "/password")
public class PasswordVerificationController {

	@RequestMapping(path = "/verify", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> passwordVerifier(@RequestBody @Valid PasswordResource passwordVerifier,
			HttpServletRequest request) {
		return new ResponseEntity<>("Valid Password", HttpStatus.OK);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<String> onValidationException(MethodArgumentNotValidException bindingException,
			HttpServletRequest e) {
		return new ResponseEntity<>(bindingException.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);

	}
}
