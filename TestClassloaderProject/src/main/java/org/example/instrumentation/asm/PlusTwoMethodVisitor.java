package org.example.instrumentation.asm;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


public class PlusTwoMethodVisitor extends MethodAdapter {
    public PlusTwoMethodVisitor(final MethodVisitor methodVisitor) {
        super(methodVisitor);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String s, boolean b) {
        return super.visitAnnotation(s, b);
    }

    @Override
    public void visitInsn(int i) {
        if (i == Opcodes.IRETURN) {
            super.visitInsn(Opcodes.ICONST_2);
            super.visitInsn(Opcodes.IADD);
            super.visitInsn(Opcodes.IRETURN);
            return;
        }
        super.visitInsn(i);
    }
}
