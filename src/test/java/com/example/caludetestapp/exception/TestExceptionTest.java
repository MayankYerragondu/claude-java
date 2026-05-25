package com.example.caludetestapp.exception;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestExceptionTest {

    @Test
    void constructor_setsMessageAndStatusCode() {
        TestException ex = new TestException("something failed", 400);

        assertThat(ex.getMessage()).isEqualTo("something failed");
        assertThat(ex.getStatusCode()).isEqualTo(400);
    }

    @Test
    void constructor_with500StatusCode() {
        TestException ex = new TestException("internal error", 500);

        assertThat(ex.getMessage()).isEqualTo("internal error");
        assertThat(ex.getStatusCode()).isEqualTo(500);
    }

    @Test
    void isRuntimeException() {
        TestException ex = new TestException("error", 422);
        assertThat(ex).isInstanceOf(RuntimeException.class);
    }
}
