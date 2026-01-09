package com.test.codecompiler.Models.InMemory;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URI;


public class InMemoryJavaFile extends SimpleJavaFileObject {

    private final ByteArrayOutputStream byteCode = new ByteArrayOutputStream();
    private String code;

    public InMemoryJavaFile( String code, String name) {
        super(URI.create("string:///" + name + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
    }

    public InMemoryJavaFile( String name) {
        super(URI.create("string:///" + name + Kind.SOURCE.extension), Kind.SOURCE);
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }

    @Override
    public OutputStream openOutputStream() {
        return byteCode;
    }

    public byte[] getBytes() {
        return byteCode.toByteArray();
    }

}
