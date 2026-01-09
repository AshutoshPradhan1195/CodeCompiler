package com.test.codecompiler.Controller;

import com.test.codecompiler.Models.CodeCompileRequest;
import com.test.codecompiler.Models.CodeCompileResponse;
import com.test.codecompiler.Services.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeController {

    private CodeService codeService;

    @Autowired
    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @PostMapping("execute")
    public CodeCompileResponse execute(@RequestBody CodeCompileRequest request) {
        try {
            return codeService.compile(request);
        }
        catch (UnsupportedOperationException e){
            System.out.println(e.getMessage());
            return new CodeCompileResponse(null, new String[]{e.getMessage()});
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new CodeCompileResponse(null, new String[]{"Something went wrong"});
        }

    };
}
