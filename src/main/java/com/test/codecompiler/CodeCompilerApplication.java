package com.test.codecompiler;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FilePermission;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "My Application API", version = "1.0", description = "Documentation for My Application v1.0"))
public class CodeCompilerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeCompilerApplication.class, args);
    }

}
