package com.group.kamiloses.orderstreamapp.controller;

public class InvalidFieldException extends RuntimeException{
    public InvalidFieldException(String message) {
        super(message);
    }
}
