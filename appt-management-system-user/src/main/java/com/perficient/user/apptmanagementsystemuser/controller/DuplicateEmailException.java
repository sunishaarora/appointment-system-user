package com.perficient.user.apptmanagementsystemuser.controller;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String message){
        super(message);
    }
}
