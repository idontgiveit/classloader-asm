package org.example;

import org.example.classloader.*;


public class OwnClassLoader {
    public static void main(String[] args) throws Exception {
        ClassLoader customizedClassLoader = new LoggedClassLoader("logged", OwnClassLoader.class.getClassLoader());
        Class<?> helloLoadedClass = customizedClassLoader.loadClass("org.example.classloader.SimpleHelloClass");

        CheckClassloaders checker = new CheckClassloaders();

        HelloClass helloClass = new SimpleHelloClass();
        helloClass.printHello();
        checker.checkClassloader(helloClass.getClass());

        System.out.println("------------------------------------------");

        HelloClass loadedInstance = (HelloClass) helloLoadedClass.getDeclaredConstructors()[0].newInstance();
        loadedInstance.printHello();
        checker.checkClassloader(loadedInstance.getClass());
    }
}
