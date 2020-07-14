package calculator;

import calculator.arithmetic.*;

abstract class Number {
    int number;
    String type;

    Number add(Number other) {
        return NumberFactory.setNumber(new Addition().invoke(number, other.number), type);
    }
    Number sub(Number other) {
        return NumberFactory.setNumber(new Subtraction().invoke(number, other.number), type);
    }
    Number mult(Number other) {
        return NumberFactory.setNumber(new Multiplication().invoke(number, other.number), type);
    }
    Number div(Number other) {
        return NumberFactory.setNumber(new Division().invoke(number, other.number), type);
    }
}
