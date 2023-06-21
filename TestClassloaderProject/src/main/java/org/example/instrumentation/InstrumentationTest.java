package org.example.instrumentation;

import org.example.instrumentation.classloader.InstrumentationClassLoader;
import org.example.instrumentation.data.Calculation;

import java.net.URL;


public class InstrumentationTest {

    //-javaagent:TransformAgent/build/libs/InstrumentationAgent.jar
    public static void main(String[] args) throws Exception {
        Calculation calculation = new Calculation();
        System.out.println("--->" + calculation.square(5));
        System.out.println("--->" + calculation.sum(4, 3));
    }
}
