package flagpicker.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import flagpicker.entities.ErrorResponse;
import flagpicker.exceptions.BadRequest;
import flagpicker.exceptions.ServerException;

/**
 * Handles all errors for the Flag Picker REST API.
 * 
 * @author apratimshaw
 *
 */
@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(BadRequest.class)
	public ResponseEntity<ErrorResponse> handleApplicationException(BadRequest ex) {
		ErrorResponse body = new ErrorResponse(ex.getCode(), ex.getMessage());
		return new ResponseEntity<ErrorResponse>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ServerException.class)
	public ResponseEntity<ErrorResponse> handleApplicationException(ServerException ex) {
		ErrorResponse body = new ErrorResponse(ex.getCode(), ex.getMessage());
		return new ResponseEntity<ErrorResponse>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleApplicationException(Exception ex) {
		return handleApplicationException(new ServerException(ex));
	}
}
