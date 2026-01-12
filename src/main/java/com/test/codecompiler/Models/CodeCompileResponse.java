package com.test.codecompiler.Models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CodeCompileResponse {
    private String output;
    private String[] error;

    public CodeCompileResponse(String output, String[] error) {
        this.output = output;
        this.error = error;
    }

    public CodeCompileResponse() {}

}
