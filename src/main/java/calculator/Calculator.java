package calculator;

import java.lang.RuntimeException;

class Calculator {

    static Number calc(String op, Number num1, Number num2) {
        if (num1.type.equals(num2.type)) {
            switch (op) {
                case "+":
                    return num1.add(num2);
                case "-":
                    return num1.sub(num2);
                case "*":
                    return num1.mult(num2);
                case "/":
                    return num1.div(num2);
                default:
                    throw new RuntimeException("Operation unknown");
            }
        } else {
            throw new RuntimeException("Different types of numbers");
        }
    }
}
