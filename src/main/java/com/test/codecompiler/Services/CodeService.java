package com.test.codecompiler.Services;

import com.test.codecompiler.Models.CodeCompileRequest;
import com.test.codecompiler.Models.CodeCompileResponse;
import com.test.codecompiler.Models.InMemory.InMemoryClassLoader;
import com.test.codecompiler.Models.InMemory.InMemoryFileManager;
import com.test.codecompiler.Models.InMemory.InMemoryJavaFile;
import org.springframework.stereotype.Service;
import javax.tools.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class CodeService {
    private final DiagnosticCollector<JavaFileObject> diagnostics;
    private final JavaCompiler compiler;

    public CodeService() {
        this.diagnostics = new DiagnosticCollector<>();
        this.compiler = ToolProvider.getSystemJavaCompiler();
    }

    public CodeCompileResponse compile(CodeCompileRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String name = "Main" + UUID.randomUUID().toString().replace("-", "").toLowerCase();
        String classCode = "public class " + name + " {\n" +
                           "    public static void main(String[] args) {\n" +
                           request.getCode() + "\n" +
                           "    }\n" +
                           "}";

        InMemoryJavaFile sourceFile =
                new InMemoryJavaFile(classCode, name);

        StandardJavaFileManager standardFileManager =
                compiler.getStandardFileManager(diagnostics, null, null);

        InMemoryFileManager fileManager =
                new InMemoryFileManager(standardFileManager);

        JavaCompiler.CompilationTask task = compiler.getTask(
                null,
                fileManager,
                diagnostics,
                List.of("-classpath", System.getProperty("java.class.path")),
                null,
                List.of(sourceFile)
        );

        boolean success = task.call();


        if (!success) {
            String[] errors = diagnostics.getDiagnostics()
                    .stream()
                    .map(d -> d.getMessage(null))
                    .toArray(String[]::new);

            return new CodeCompileResponse(null, errors);
        }


        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(output));

        InMemoryClassLoader classLoader =
                new InMemoryClassLoader(fileManager.getClassFile());

        Class<?> clazz = classLoader.loadClass(name);

        clazz.getMethod("main", String[].class)
                .invoke(null, (Object) new String[]{});

        System.setOut(new PrintStream(originalOut));

        return new CodeCompileResponse(output.toString(), null);


    }
}


