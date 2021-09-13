package dev.vabalas.studentperformancesorter.rest.exception;

import dev.vabalas.studentperformancesorter.rest.common.Error;

import java.util.List;

public class ValidationException extends RuntimeException {
  private final List<Error> errors;

  public ValidationException(List<Error> errors) {
    super();
    this.errors = errors;
  }

  public List<Error> getErrors() {
    return errors;
  }

  @Override
  public String toString() {
    return "ValidationException{" +
           "errors=" + errors +
           '}';
  }
}
