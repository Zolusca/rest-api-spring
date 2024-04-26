package com.id.vonsan.jpastarter.DTO;

import java.util.List;

public class ApiErrorException {
    private String status;
    private List<String> errors;

    public ApiErrorException() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
