package com.example.audioplayerfinal.Exceptions;

public class CancionNoExistenteException extends RuntimeException {
  public CancionNoExistenteException(String message) {
    super(message);
  }
}
