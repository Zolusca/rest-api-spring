package com.id.vonsan.jpastarter.Exception;

public class NotFoundException extends RuntimeException{
    
    /**
     * this class is for controller advice not found data
     * @param message message exception
     */
    public NotFoundException(String message) {
        super(message);
    }
}
