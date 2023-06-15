package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Calculator {
    private final Map<Character, Integer> romanInt = new HashMap<>();

    {
        romanInt.put('I', 1);
        romanInt.put('V', 5);
        romanInt.put('X', 10);
        romanInt.put('L', 50);
        romanInt.put('C', 100);
        romanInt.put('D', 500);
        romanInt.put('M', 1000);
    }

    private final TreeMap<Integer, String> intRoman = new TreeMap<Integer, String>();

    {

        intRoman.put(1000, "M");
        intRoman.put(900, "CM");
        intRoman.put(500, "D");
        intRoman.put(400, "CD");
        intRoman.put(100, "C");
        intRoman.put(90, "XC");
        intRoman.put(50, "L");
        intRoman.put(40, "XL");
        intRoman.put(10, "X");
        intRoman.put(9, "IX");
        intRoman.put(5, "V");
        intRoman.put(4, "IV");
        intRoman.put(1, "I");

    }

    private String toRoman(int number) throws Exception {// преобразование арабских чисел в римские
        if (number < 1) {
            throw new Exception("Римские числа не могуть быть меньше 1!");
        }
        int l = intRoman.floorKey(number);
        if (number == l) {
            return intRoman.get(number);
        }
        return intRoman.get(l) + toRoman(number - l);
    }


    private int transformationToInt(String str) throws Exception { // преобоазование римских чисел в арабские
        int result = 0;
        int previous = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            int current = romanInt.get(str.charAt(i));
            if (current < previous) {
                result -= current;
            } else {
                result += current;
            }
            previous = current;
        }
        if (result < 1 || result > 10) {
            throw new Exception("Диапазон чисел должен быть от 1 до 10");
        }
        return result;
    }

    private ArgType valueType(String str1) { // проверем к какому типу относится передоваемое згачение
        ArgType type;

        try {
            int number = Integer.parseInt(str1);
            type = ArgType.ARABIC;
        } catch (NumberFormatException e) {
            type = ArgType.ROMAN;
        }
        return type;
    }

    public String calcValue(String str1, String str2, String elem) throws Exception { // выводим итговое значение
        ArgType typeStr1 = valueType(str1);
        ArgType typeStr2 = valueType(str2);


        String result = "";

        if (typeStr1 != typeStr2) {
            throw new Exception("Числа разных типов!");
        }
        if (typeStr1 == ArgType.ARABIC) {
            int numberOne = Integer.parseInt(str1);
            int numberTwo = Integer.parseInt(str2);
            if (numberOne > 10 || numberTwo > 10) {
                throw new Exception("Ввeдeнные числа должны находится в диапащоне от 1 до 10 включительно!");
            }
            switch (elem) {
                case "+" -> result = String.valueOf(numberOne + numberTwo);
                case "-" -> result = String.valueOf(numberOne - numberTwo);
                case "*" -> result = String.valueOf(numberOne * numberTwo);
                case "/" -> result = String.valueOf(numberOne / numberTwo);
                default -> throw new Exception("Неверный математический оператор!");
            }
        } else {
            switch (elem) {
                case "+" -> result = toRoman(transformationToInt(str1) + transformationToInt(str2));
                case "-" -> result = toRoman(transformationToInt(str1) - transformationToInt(str2));
                case "*" -> result = toRoman(transformationToInt(str1) * transformationToInt(str2));
                case "/" -> result = toRoman(transformationToInt(str1) / transformationToInt(str2));
                default -> throw new Exception("Неверный математический оператор!");
            }
        }
        return result;
    }

}
