package org.example.instrumentation.classloader;

import org.example.instrumentation.asm.TestClassVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;


public class InstrumentationClassLoader extends ClassLoader {

    public InstrumentationClassLoader(final ClassLoader parent) {
        super(parent);
    }

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("load " + name);
        return super.loadClass(name);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if (!name.startsWith("org.example")) {
            return super.loadClass(name, resolve);
        }

        try {
            byte[] classData = loadClassFileData(name);
            ClassReader reader = new ClassReader(classData);
            ClassWriter writer = new ClassWriter(0);
            ClassVisitor visitor = new TestClassVisitor(writer);

            reader.accept(visitor, 0);

            byte[] resultClass = writer.toByteArray();
            Class<?> result = defineClass(name, resultClass, 0, resultClass.length);
            resolveClass(result);

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] loadClassFileData(String name) throws IOException {
        String filename = "./" + name.replace('.', '/') + ".class";
        InputStream classStream = getClass().getClassLoader().getResourceAsStream(filename);

        int size = classStream.available();
        byte buff[] = new byte[size];
        DataInputStream in = new DataInputStream(classStream);
        in.readFully(buff);
        in.close();
        return buff;
    }

}
