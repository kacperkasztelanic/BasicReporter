package com.kkasztel.basicreporter.model;

public class ReportingException extends RuntimeException {

    public ReportingException(String message, Throwable cause) {
        super(message, cause);
    }
}
