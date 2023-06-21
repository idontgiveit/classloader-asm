package org.example.instrumentation;

import org.example.instrumentation.asm.TestClassVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.net.URL;
import java.security.ProtectionDomain;
import java.util.concurrent.ConcurrentHashMap;


public class InstrumentationAgent {
    private static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    public static void premain(String agentArgs, Instrumentation inst) {

        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                return ClassFileTransformer.super.transform(loader, className, classBeingRedefined, protectionDomain, classfileBuffer);
            }

            @Override
            public byte[] transform(Module module, ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                if (className.startsWith("org/example/instrumentation/data") && !map.containsKey(className)) {
                    map.put(className, "");
                    return transformClass(classfileBuffer);
                }
                return ClassFileTransformer.super.transform(module, loader, className, classBeingRedefined, protectionDomain, classfileBuffer);
            }
        });
    }

    private static byte[] transformClass(byte[] initialClass) {
        ClassReader reader = new ClassReader(initialClass);
        ClassWriter writer = new ClassWriter(0);
        ClassVisitor visitor = new TestClassVisitor(writer);
        reader.accept(visitor, 0);

        return writer.toByteArray();
    }
}
