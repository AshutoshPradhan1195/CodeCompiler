package com.test.codecompiler.Models.InMemory;

public class InMemoryClassLoader extends ClassLoader {

    private final InMemoryJavaFile classFile;

    public InMemoryClassLoader(InMemoryJavaFile classFile) {
        super(null);
        this.classFile = classFile;
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = classFile.getBytes();
        return defineClass(name, bytes, 0, bytes.length);
    }
}
