package br.com.fiap.pos.challenge.trips.exception;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ExceptionResponse() { }

    public ExceptionResponse(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

}
