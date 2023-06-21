package org.example.classloader;

public class SimpleHelloClass implements HelloClass{
    @Override
    public void printHello() {
        System.out.println("Just Hello");
    }
}
