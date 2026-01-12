package com.test.codecompiler.Controller;

import com.test.codecompiler.Models.CodeCompileRequest;
import com.test.codecompiler.Models.CodeCompileResponse;
import com.test.codecompiler.Services.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class CodeController {

    private final CodeService codeService;

    @Autowired
    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @PostMapping("execute")
    public ResponseEntity<CodeCompileResponse> execute(@RequestBody CodeCompileRequest request) {
        try {
            CodeCompileResponse response = codeService.compile(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            CodeCompileResponse response = new CodeCompileResponse(null, new String[]{"Something went wrong"});
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}
