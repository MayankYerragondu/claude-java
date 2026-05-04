package com.example.caludetestapp.controller;

import com.example.caludetestapp.domain.Data;
import com.example.caludetestapp.exception.TestException;
import com.example.caludetestapp.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestController.class)
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestService testService;

    @Test
    void getData_returns200WithData() throws Exception {
        when(testService.getData()).thenReturn(new Data("mock content"));

        mockMvc.perform(get("/test").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("mock content"));
    }

    @Test
    void getData_returnsCorrectContentType() throws Exception {
        when(testService.getData()).thenReturn(new Data("some data"));

        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("some data"));
    }

    @Test
    void getData_whenServiceThrowsTestException_returns400() throws Exception {
        when(testService.getData()).thenThrow(new TestException("bad request error", 400));

        mockMvc.perform(get("/test").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").value("bad request error"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    void getData_whenServiceThrowsTestException_returns404() throws Exception {
        when(testService.getData()).thenThrow(new TestException("resource not found", 404));

        mockMvc.perform(get("/test").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message").value("resource not found"));
    }

    @Test
    void getData_whenServiceThrowsTestException_returns500() throws Exception {
        when(testService.getData()).thenThrow(new TestException("internal failure", 500));

        mockMvc.perform(get("/test").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.message").value("internal failure"));
    }
}
