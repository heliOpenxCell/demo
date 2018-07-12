package com.example.demo.exception;

@SuppressWarnings("serial")
public class WrongParameters extends RuntimeException {

    public WrongParameters() {
    }

    public WrongParameters(String message) {
        super(message);
    }

}