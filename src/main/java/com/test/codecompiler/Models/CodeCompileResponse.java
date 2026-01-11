package com.test.codecompiler.Models;


public class CodeCompileResponse {
    private String output;
    private String[] error;

    public CodeCompileResponse(String output, String[] error) {
        this.output = output;
        this.error = error;
    }

    public CodeCompileResponse() {}

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String[] getError() {
        return error;
    }

    public void setError(String[] error) {
        this.error = error;
    }
}
