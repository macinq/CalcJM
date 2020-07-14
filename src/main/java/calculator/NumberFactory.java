package calculator;

import java.lang.RuntimeException;

class NumberFactory {

    static Number getNumber(String number) {
        Number num;

        try {
            num = new ArabicNumber(number);
        } catch (NumberFormatException e) {
            num = new RomanNumber(number);
        }

//        if (num.number <  || num.number > 10) {
//            throw new RuntimeException("Not the right number");
//        }

        return num;
    }

    static Number setNumber(int number, String type) {
        switch (type) {
            case "arabic":
                return new ArabicNumber(number);
            case "roman":
                return new RomanNumber(number);
            default:
                throw new RuntimeException("Unknown type");
        }
    }
}
