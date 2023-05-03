package org.cuentas.exceptions;

import java.time.LocalDateTime;

public class Message {
    private String error;
    private String detail;
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
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void buildMessage(String message){
        String[] items = message.split("Detail: ");
        setError(items[0].split("ERROR: ")[1]);
        setDetail(items[1]);
        LocalDateTime now = LocalDateTime.now();
        setTimestamp(now);
    }
}
