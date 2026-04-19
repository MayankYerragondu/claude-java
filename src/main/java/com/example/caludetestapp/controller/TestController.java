package com.example.caludetestapp.controller;

import com.example.caludetestapp.domain.Data;
import com.example.caludetestapp.domain.ErrorResponse;
import com.example.caludetestapp.exception.TestException;
import com.example.caludetestapp.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Test", description = "Endpoints for testing the application")
@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @Operation(summary = "Get test data", description = "Returns a Data object with content retrieved from TestService")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved data",
                    content = @Content(schema = @Schema(implementation = Data.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping
    public ResponseEntity<Data> getData() {
        return ResponseEntity.ok(testService.getData());
    }

    @ExceptionHandler(TestException.class)
    public ResponseEntity<ErrorResponse> handleTestException(TestException ex) {
        ErrorResponse body = new ErrorResponse(
                ex.getStatusCode(),
                HttpStatus.valueOf(ex.getStatusCode()).getReasonPhrase(),
                ex.getMessage()
        );
        return ResponseEntity.status(ex.getStatusCode()).body(body);
    }
}
