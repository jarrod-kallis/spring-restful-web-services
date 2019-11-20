package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minutes.rest.webservices.restfulwebservices.user.UserAlreadyExistsException;

@RestController
@RestControllerAdvice
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {
//	@ExceptionHandler(Exception.class)
//	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
//		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
//				request.getDescription(false));
//
//		return new ResponseEntity<Object>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) throws Exception {
		return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public final ResponseEntity<Object> handleUserAlreadyExistsException(Exception ex, WebRequest request)
			throws Exception {
		return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		super.handleExceptionInternal(ex, body, headers, status, request);

		ExceptionResponse exceptionResponse;

		if (ex instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException validationEx = (MethodArgumentNotValidException) ex;

			String validationError = "";
			for (ObjectError e : validationEx.getBindingResult().getAllErrors()) {
				if (validationError.length() > 0) {
					validationError += ", ";
				}

				validationError += e.getDefaultMessage();
			}

			exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed", validationError);
		} else {
			exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		}

		return new ResponseEntity<>(exceptionResponse, status);
	}
}
