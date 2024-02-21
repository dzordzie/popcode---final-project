package com.greenfox.avuspopcode.exceptions;

public class VehicleAlreadyRentedException extends RuntimeException {
  public VehicleAlreadyRentedException(String message) {
    super(message);
  }
}
