package dev.vabalas.studentperformancesorter.rest.common;

import dev.vabalas.studentperformancesorter.rest.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorResponse> handleValidationException(ValidationException e) {
    return generateResponseEntity(HttpStatus.BAD_REQUEST, e.getErrors());
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    return generateResponseEntity(HttpStatus.METHOD_NOT_ALLOWED, List.of(new Error(e.getMessage())));
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<ErrorResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
    return generateResponseEntity(HttpStatus.UNSUPPORTED_MEDIA_TYPE, List.of(new Error(e.getMessage())));
  }

  @ExceptionHandler(Throwable.class)
  public ResponseEntity<ErrorResponse> handleInternalServerErrorException(Throwable t) {
    return generateResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, List.of(new Error(t.getMessage())));
  }

  private ResponseEntity<ErrorResponse> generateResponseEntity(HttpStatus status, List<Error> errors) {
    return new ResponseEntity<>(new ErrorResponse(status.value(), status.getReasonPhrase(), LocalDateTime.now().toString(), errors.stream().map(Error::getMessage).collect(Collectors.toList())), status);
  }

  private static class ErrorResponse {
    private final int code;
    private final String error;
    private final String timestamp;
    private final List<String> errors;

    public ErrorResponse(int code, String error, String timestamp, List<String> errors) {
      this.code = code;
      this.error = error;
      this.timestamp = timestamp;
      this.errors = errors;
    }

    public int getCode() {
      return code;
    }

    public String getError() {
      return error;
    }

    public String getTimestamp() {
      return timestamp;
    }

    public List<String> getErrors() {
      return errors;
    }

    @Override
    public String toString() {
      return "ErrorResponse{" +
             "code='" + code + '\'' +
             ", error='" + error + '\'' +
             ", timestamp='" + timestamp + '\'' +
             ", errors=" + errors +
             '}';
    }
  }
}
