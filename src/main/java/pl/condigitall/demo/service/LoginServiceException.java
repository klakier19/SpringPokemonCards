package pl.condigitall.demo.service;

public class LoginServiceException extends RuntimeException{
    public LoginServiceException(String message) {
        super(message);
    }
}
