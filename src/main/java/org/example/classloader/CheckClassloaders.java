package org.example.classloader;

import java.util.*;
import java.util.stream.Stream;


public class CheckClassloaders {
    public void checkClassloader(Class checkClass) {
        System.out.println(checkClass.getCanonicalName() + "-->" + checkClass.getClassLoader());
    }

    public void printChain() {
        printChain(this.getClass());
    }

    public void printChain(Class checkClass) {
        List<ClassLoader> classLoaders = new ArrayList<>();
        addParent(classLoaders, checkClass.getClassLoader());
        classLoaders.forEach(System.out::println);
    }

    void addParent(List<ClassLoader> classLoaders, ClassLoader classLoader) {
        if (classLoader == null) {
            return;
        }
        classLoaders.add(classLoader);
        addParent(classLoaders, classLoader.getParent());
    }

    public void printContextClassloaders() {
        Map<Thread, StackTraceElement[]> traces = Thread.getAllStackTraces();

        traces.entrySet().forEach( trace -> {
            Arrays.stream(trace.getValue()).forEach(item ->
                    System.out.println(trace.getKey() + " " + item.getClassName() + " " + item.getClassLoaderName()));
        });
    }
}
