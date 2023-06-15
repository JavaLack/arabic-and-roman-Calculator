package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String input = reader.readLine();
            System.out.println(calc(input));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String calc(String input) throws Exception{
        StringParser parser = new StringParser(input);
        Calculator calculator = new Calculator();
        return calculator.calcValue(parser.getValueOne(), parser.getValueTwo(), parser.getSymbol());
    }

}
