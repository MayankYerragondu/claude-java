package com.example.caludetestapp.domain;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response payload containing the requested data")
public class Data {

    @Schema(description = "The content of the data", example = "This is dummy content from TestService")
    private String content;

    public Data(String content) {
        this.content = content;
    }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
