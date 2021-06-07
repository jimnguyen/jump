package com.cognixia.jump.assignment;

public class InvalidEmployeeNameException extends Exception {
    public InvalidEmployeeNameException() {
        super("\nPlease enter the correct information for the employee!\n");
    }
}
