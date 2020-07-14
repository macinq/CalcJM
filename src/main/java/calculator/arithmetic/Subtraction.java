package calculator.arithmetic;

public class Subtraction implements Action {
    @Override
    public int invoke(int num1, int num2){
        return num1 - num2;
    }
}
