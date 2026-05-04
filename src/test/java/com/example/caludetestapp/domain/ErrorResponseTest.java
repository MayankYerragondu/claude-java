package com.example.caludetestapp.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorResponseTest {

    @Test
    void constructor_setsAllFields() {
        LocalDateTime before = LocalDateTime.now();
        ErrorResponse response = new ErrorResponse(404, "Not Found", "Resource missing");
        LocalDateTime after = LocalDateTime.now();

        assertThat(response.getStatus()).isEqualTo(404);
        assertThat(response.getError()).isEqualTo("Not Found");
        assertThat(response.getMessage()).isEqualTo("Resource missing");
        assertThat(response.getTimestamp()).isBetween(before, after);
    }

    @Test
    void constructor_with500Status() {
        ErrorResponse response = new ErrorResponse(500, "Internal Server Error", "Something went wrong");

        assertThat(response.getStatus()).isEqualTo(500);
        assertThat(response.getError()).isEqualTo("Internal Server Error");
        assertThat(response.getMessage()).isEqualTo("Something went wrong");
        assertThat(response.getTimestamp()).isNotNull();
    }

    @Test
    void timestamp_isSetAtCreationTime() {
        LocalDateTime before = LocalDateTime.now();
        ErrorResponse response = new ErrorResponse(400, "Bad Request", "Invalid input");
        LocalDateTime after = LocalDateTime.now();

        assertThat(response.getTimestamp()).isAfterOrEqualTo(before);
        assertThat(response.getTimestamp()).isBeforeOrEqualTo(after);
    }
}
