package dev.vabalas.studentperformancesorter.rest.common;

public class Error {
  private final String message;

  public Error(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return "Error{" +
           "message='" + message + '\'' +
           '}';
  }
}
