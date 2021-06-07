package com.cognixia.jump.assignment;

public class InvalidEmployeeInfoException extends Exception {
    public InvalidEmployeeInfoException() {
        super("\nPlease enter the correct information for the employee!\n");
    }
}
