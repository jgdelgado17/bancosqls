package org.cuentas.exceptions;

import java.time.LocalDateTime;

public class Message {
    private String error;
    private LocalDateTime timestamp;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
        LocalDateTime now = LocalDateTime.now();
        setTimestamp(now);
    }
}
