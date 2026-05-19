package com.example.caludetestapp.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Standard error response returned when an exception occurs")
public class ErrorResponse {

    @Schema(description = "HTTP status code", example = "400")
    private int status;

    @Schema(description = "Short description of the HTTP status", example = "Bad Request")
    private String error;

    @Schema(description = "Detailed error message", example = "Validation failed for field 'name'")
    private String message;

    @Schema(description = "Timestamp when the error occurred", example = "2026-04-15T10:30:00")
    private LocalDateTime timestamp;

    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public int getStatus() { return status; }
    public String getError() { return error; }
    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
