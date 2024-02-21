package com.greenfox.avuspopcode.advice;

import com.greenfox.avuspopcode.dtos.ErrorDTO;
import com.greenfox.avuspopcode.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({EmailAlreadyExistsException.class})
  public ResponseEntity<ErrorDTO> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
    return ResponseEntity.status(HttpStatus.OK).body(new ErrorDTO(e.getMessage()));
  }

  @ExceptionHandler({TokenExpiredException.class})
  public ResponseEntity<ErrorDTO> handleTokenExpiredException(TokenExpiredException exception) {
    return ResponseEntity.status(HttpStatus.OK).body(new ErrorDTO(exception.getMessage()));
  }

  @ExceptionHandler({EmailNotVerifiedException.class})
  public ResponseEntity<ErrorDTO> handleNotVerifiedException(EmailNotVerifiedException exception) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDTO(exception.getMessage()));
  }

  @ExceptionHandler({BadCredentialsException.class})
  public ResponseEntity<ErrorDTO> handleBadCredentialsException(BadCredentialsException exception) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDTO("Incorrect email or password!"));
  }

  @ExceptionHandler({VehicleNotFoundException.class})
  public ResponseEntity<ErrorDTO> handleVehicleNotFoundException(VehicleNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(exception.getMessage()));
  }

  @ExceptionHandler({VehicleAlreadyRentedException.class})
  public ResponseEntity<ErrorDTO> handleVehicleAlreadyRentedException(VehicleAlreadyRentedException exception) {
    return ResponseEntity.status(HttpStatus.OK).body(new ErrorDTO(exception.getMessage()));
  }

  @ExceptionHandler({RentNotFoundException.class})
  public ResponseEntity<ErrorDTO> handleRentNotFoundException(RentNotFoundException exception) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(exception.getMessage()));
  }
}
