package com.test.codecompiler.Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeCompileResponse {
    @Nullable
    private String output;
    @Nullable
    private String[] error;
}
