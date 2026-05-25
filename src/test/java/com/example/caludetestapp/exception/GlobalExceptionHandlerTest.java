package com.example.caludetestapp.exception;

import com.example.caludetestapp.domain.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void handleTestException_returns_correctStatusAndBody() {
        TestException ex = new TestException("test error", 400);

        ResponseEntity<ErrorResponse> response = handler.handleTestException(ex);

        assertThat(response.getStatusCode().value()).isEqualTo(400);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(400);
        assertThat(response.getBody().getError()).isEqualTo(HttpStatus.BAD_REQUEST.getReasonPhrase());
        assertThat(response.getBody().getMessage()).isEqualTo("test error");
    }

    @Test
    void handleTestException_with404() {
        TestException ex = new TestException("not found", 404);

        ResponseEntity<ErrorResponse> response = handler.handleTestException(ex);

        assertThat(response.getStatusCode().value()).isEqualTo(404);
        assertThat(response.getBody().getError()).isEqualTo(HttpStatus.NOT_FOUND.getReasonPhrase());
        assertThat(response.getBody().getMessage()).isEqualTo("not found");
    }

    @Test
    void handleTestException_with500() {
        TestException ex = new TestException("server error", 500);

        ResponseEntity<ErrorResponse> response = handler.handleTestException(ex);

        assertThat(response.getStatusCode().value()).isEqualTo(500);
        assertThat(response.getBody().getError()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @Test
    void handleGenericException_returns500() {
        Exception ex = new Exception("unexpected failure");

        ResponseEntity<ErrorResponse> response = handler.handleGenericException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(500);
        assertThat(response.getBody().getError()).isEqualTo("Internal Server Error");
        assertThat(response.getBody().getMessage()).isEqualTo("unexpected failure");
    }

    @Test
    void handleGenericException_withNullMessage() {
        Exception ex = new Exception((String) null);

        ResponseEntity<ErrorResponse> response = handler.handleGenericException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody().getMessage()).isNull();
    }
}
