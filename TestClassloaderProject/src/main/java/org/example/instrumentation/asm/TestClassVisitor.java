package org.example.instrumentation.asm;


import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;


public class TestClassVisitor extends ClassAdapter {
    public TestClassVisitor(final ClassVisitor classVisitor) {
        super(classVisitor);
    }

    @Override
    public void visit(int i, int i1, String s, String s1, String s2, String[] strings) {
        super.visit(i, i1, s, s1, s2, strings);
    }

    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        if (s1.lastIndexOf('I') == s1.length() - 1) {
            return new PlusTwoMethodVisitor(super.visitMethod(i, s, s1, s2, strings));
        }
        return super.visitMethod(i, s, s1, s2, strings);
    }



    @Override
    public AnnotationVisitor visitAnnotation(String s, boolean b) {
        return super.visitAnnotation(s, b);
    }

    @Override
    public void visitSource(String s, String s1) {
        System.out.println();
        super.visitSource(s, s1);
    }
}
