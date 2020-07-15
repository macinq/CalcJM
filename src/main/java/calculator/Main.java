package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static void printHelp() {
        System.out.println("****HELP****");
        System.out.println("Numbers: positive , negative");
        System.out.println("Actions: + - * / [arabic & arabic]");
        System.out.println("Input format: <number><space><action><space><number>");
        System.out.println("Exit: <exit>");
        System.out.println("Help: <help>");
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        String exit = "exit";
        String help = "help";

        printHelp();

        do {
            System.out.println("************");

            System.out.println("Input:");
            try {
                str = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException("Invalid input");
            }

            if (!str.equals(exit) && !str.equals(help)) {
                String[] elems = str.split("\\s");

                if (elems.length != 3) {
                    throw new RuntimeException("Invalid input");
                }

                String op = elems[1];
                Number num1 = NumberFactory.getNumber(elems[0]);
                Number num2 = NumberFactory.getNumber(elems[2]);

                Number res = Calculator.calc(op, num1, num2);

                System.out.println("Result:");
                System.out.println(res);
            } else if (str.equals(help)) {
                printHelp();
            }
        } while (!str.equals(exit));
        System.out.println("Goodbye");
    }
}
