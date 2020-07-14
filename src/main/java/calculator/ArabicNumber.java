package calculator;

class ArabicNumber extends Number {
    ArabicNumber(String number) {
        this.number = Integer.parseInt(number);
        type = "arabic";
    }

    ArabicNumber(int number) {
        this.number = number;
        type = "arabic";
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
