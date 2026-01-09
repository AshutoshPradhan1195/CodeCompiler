package com.test.codecompiler.Models.InMemory;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;

public class InMemoryFileManager
        extends ForwardingJavaFileManager<JavaFileManager> {

    private InMemoryJavaFile classFile;

    public InMemoryFileManager(JavaFileManager fileManager) {
        super(fileManager);
    }

    @Override
    public JavaFileObject getJavaFileForOutput(
            Location location,
            String className,
            JavaFileObject.Kind kind,
            FileObject sibling
    ) {
        classFile = new InMemoryJavaFile(className);
        return classFile;
    }

    public InMemoryJavaFile getClassFile() {
        return classFile;
    }
}
