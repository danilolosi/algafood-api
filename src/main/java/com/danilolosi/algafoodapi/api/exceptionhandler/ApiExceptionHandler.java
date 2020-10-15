package com.danilolosi.algafoodapi.api.exceptionhandler;

import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.danilolosi.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.danilolosi.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.danilolosi.algafoodapi.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if(rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException( (InvalidFormatException) rootCause, headers, status, request);
		}
		
		ErrorType errorType = ErrorType.MENSAGEM_INCOMPREENSIVEL;
		String detail = "O corpo de requisição está inválido. Verifique erro de sintaxe";
		
		Error error = createErrorBuilder(errorType, status, detail).build();

		return handleExceptionInternal(ex, error, new HttpHeaders(), status ,request);
	}
	
	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		String path = ex.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
		
		ErrorType errorType = ErrorType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido."
				+ " Informe um valor compatível com o tipo %s.", path, ex.getValue(), ex.getTargetType().getSimpleName());
		
		Error error = createErrorBuilder(errorType, status, detail).build();
		
		return handleExceptionInternal(ex, error, headers, status, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErrorType errorType = ErrorType.ENTIDADE_NAO_ENCONTRADA;
		String detail = ex.getMessage();
		
		Error error = createErrorBuilder(errorType, status, detail).build();

		return handleExceptionInternal(ex, error, new HttpHeaders(), status ,request);
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.CONFLICT;
		ErrorType errorType = ErrorType.ENTIDADE_EM_USO;
		String detail = ex.getMessage();
		
		Error error = createErrorBuilder(errorType, status, detail).build();
		
		return handleExceptionInternal(ex, error, new HttpHeaders(), status ,request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request){	
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErrorType errorType = ErrorType.ERRO_NEGOCIO;
		String detail = ex.getMessage();
		
		Error error = createErrorBuilder(errorType, status, detail).build();
		
		return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST ,request);	
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if(body == null) 
			body = createErrorBuilder(status).build();	
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Error.ErrorBuilder createErrorBuilder(ErrorType errorType, HttpStatus status, String detail) {
		return Error.builder()
				.status(status.value())
				.type(errorType.getUri())
				.title(errorType.getTitle())
				.detail(detail);
	}
	
	private Error.ErrorBuilder createErrorBuilder(HttpStatus status) {
		return Error.builder()
		.title(status.getReasonPhrase())
		.status(status.value());
	}
}
