package com.jp.solution.exception;

/**
 * This class will be used to mark the application exceptions.
 *
 * @author praveen.nair, created on 05/07/2017.
 */
public class ApplicationException extends Exception {

  public static final String INVALID_MESSAGE_TYPE = "Invalid Message Type encountered";

  public ApplicationException(String message) {
    super(message);
  }

  public ApplicationException(String message, Throwable cause) {
    super(message, cause);
  }

  public ApplicationException(Throwable cause) {
    super(cause);
  }
}
