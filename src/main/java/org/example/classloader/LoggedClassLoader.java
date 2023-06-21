package org.example.classloader;

import java.io.*;


public class LoggedClassLoader extends ClassLoader{
    public LoggedClassLoader(final String name, final ClassLoader parent) {
        super(name, parent);
    }


    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        System.out.println("Load :" + name);
        return super.loadClass(name, resolve);
    }
}
