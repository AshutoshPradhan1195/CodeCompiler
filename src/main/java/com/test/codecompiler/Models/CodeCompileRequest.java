package com.test.codecompiler.Models;

import lombok.Data;

@Data
public class CodeCompileRequest {
    private String code;

    public String getCode() {
        return code;
    }
}
