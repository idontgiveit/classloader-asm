package org.example.instrumentation.data;

import org.example.instrumentation.annotation.Instrumented;


public class Calculation {
    public int sum(int op1, int op2) {
        return op1 + op2;
    }

    @Instrumented
    public int square(int op) {
        int a =  op * op;
        return a;
    }
}
