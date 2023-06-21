package org.example;

import org.example.classloader.CheckClassloaders;
import org.example.classloader.ExampleClassLoader;
import org.example.classloader.HelloClass;


public class Main {
    public static void main(String[] args) throws Exception{
        CheckClassloaders checker = new CheckClassloaders();
        checker.checkClassloader(String.class);
        checker.checkClassloader(Main.class);
        checker.checkClassloader(CheckClassloaders.class);

        System.out.println("----------------------");
        checker.printChain();

        System.out.println("----------------------");
        checker.printContextClassloaders();

        System.out.println("----------------------");

        ExampleClassLoader classLoader = new ExampleClassLoader(checker.getClass().getClassLoader(), "./src/main/resources/classes");

        Class<HelloClass> result = classLoader.loadClass("org.example.classloader.Hello1");
        HelloClass helloClass = (HelloClass) result.getDeclaredConstructors()[0].newInstance();
        helloClass.printHello();

        result = classLoader.loadClass("org.example.classloader.Hello2");
        helloClass = (HelloClass) result.getDeclaredConstructors()[0].newInstance();
        helloClass.printHello();

        System.out.println("----------------------");

        checker.printChain(helloClass.getClass());


    }
}