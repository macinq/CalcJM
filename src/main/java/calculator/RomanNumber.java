package calculator;

import java.util.TreeMap;
import java.lang.RuntimeException;

public class RomanNumber extends Number {
    private final static TreeMap<Integer, String> map = new TreeMap<>();

    static {
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
        map.put(0, "0");
    }

    RomanNumber(String number) {
        if (number.matches("^(X|IX|IV|V?I{0,3})$")) {
            this.number = toArabic(number);
            type = "roman";
        } /*else if (number.equals("-INF") || number.equals("+INF") || number.equals("NaN")) {
            throw new ArithmeticException("Division by zero is not possible");
        } */else {
            throw new RuntimeException("Not the right number");
        }
    }

    RomanNumber(int number) {
        this.number = number;
        type = "roman";
    }

    private static String toRoman(int number) {
        int num = map.floorKey(number);
        if (number == num) {
            return map.get(number);
        }
        return map.get(num) + toRoman(number - num);
    }

    private static int toArabic(String roman) {
        if (roman.isEmpty()) {
            return 0;
        } else if (roman.startsWith("X")) {
            return 10 + toArabic(roman.substring(1));
        } else if (roman.startsWith("IX")) {
            return 9 + toArabic(roman.substring(2));
        } else if (roman.startsWith("V")) {
            return 5 + toArabic(roman.substring(1));
        } else if (roman.startsWith("IV")) {
            return 4 + toArabic(roman.substring(2));
        } else if (roman.startsWith("I")) {
            return 1 + toArabic(roman.substring(1));
        } else {
            throw new RuntimeException("Wrong value");
        }
    }

    @Override
    public String toString() {
        return toRoman(number);
    }
}
