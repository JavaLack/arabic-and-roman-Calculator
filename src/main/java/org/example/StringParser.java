package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StringParser {
    private String valueOne;
    private String valueTwo;
    private String symbol;

    public StringParser(String str) throws Exception {// метод для парсинга введенной строки
        String[] s = str.split("\\W");
        String elem = "";

        if (s.length > 2) {
            throw new Exception("Вводите только два числа и оператор между ними!");
        }

        Pattern pattern = Pattern.compile("\\W");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            elem = matcher.group();
        } else {
            throw new Exception("Некорректный ввод математического оператора!");
        }
        this.valueOne = s[0];
        this.valueTwo = s[1];
        this.symbol = elem;
    }

    public String getValueOne() {
        return valueOne;
    }

    public String getValueTwo() {
        return valueTwo;
    }

    public String getSymbol() {
        return symbol;
    }
}
