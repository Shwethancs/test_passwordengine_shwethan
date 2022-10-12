package com.example.passwordengine.exception;

import java.net.BindException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(InvalidPasswordException.class)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody String onValidationException(BindException e, HttpServletResponse response) {
		return e.getMessage();
	}
}
