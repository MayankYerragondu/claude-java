package com.example.caludetestapp.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DataTest {

    @Test
    void constructor_setsContent() {
        Data data = new Data("hello");
        assertThat(data.getContent()).isEqualTo("hello");
    }

    @Test
    void setContent_updatesContent() {
        Data data = new Data("initial");
        data.setContent("updated");
        assertThat(data.getContent()).isEqualTo("updated");
    }

    @Test
    void constructor_withNullContent() {
        Data data = new Data(null);
        assertThat(data.getContent()).isNull();
    }

    @Test
    void setContent_withNull() {
        Data data = new Data("value");
        data.setContent(null);
        assertThat(data.getContent()).isNull();
    }
}
