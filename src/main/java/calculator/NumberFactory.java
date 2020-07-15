package calculator;

import java.lang.RuntimeException;

class NumberFactory {

    static Number getNumber(String number) {
        Number num;

        try {
            num = new ArabicNumber(number);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Not the right number");
        }

        return num;
    }

    static Number setNumber(int number, String type) {
        switch (type) {
            case "arabic":
                return new ArabicNumber(number);
            default:
                throw new RuntimeException("Unknown type");
        }
    }
}
