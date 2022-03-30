package CalculatorTest;

import java.io.IOException;
import java.util.Scanner;

public class Calculator {
    public static void main(String[]args){
        System.out.println("Запишите пример: ");
        Scanner scan = new Scanner(System.in);

        NormalValue value1 = new NormalValue();
        String input1 = scan.next();
        value1.valueCountSystem = NormalValue.revealCountSystem(input1);
        value1.operatingValue = NormalValue.normalizeValue(input1);

        String inputOperator = scan.next();


        NormalValue value2 = new NormalValue();
        String input2 = scan.next();
        value2.valueCountSystem = NormalValue.revealCountSystem(input2);
        value2.operatingValue = NormalValue.normalizeValue(input2);

        NormalValue.compareCountSystems(value1.valueCountSystem, value2.valueCountSystem);

        ArabicToRoman arabicToRoman = new ArabicToRoman();
        ArabicToRoman.result = NormalValue.count(value1.operatingValue, inputOperator, value2.operatingValue);
        System.out.println(ArabicToRoman.convertToInputCountSystem(ArabicToRoman.result, value1.valueCountSystem, value2.valueCountSystem));


    }
}


class NormalValue {

    int operatingValue;
    String valueCountSystem;

    static String[] romanNumerals = {"X", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    static String[] arabicNumerals = {"10", "1", "2", "3", "4", "5", "6", "7", "8", "9"};


    public static int normalizeValue(String value) {
        int operatingValue = 0;

        for (int i = 0; i < 10; i++) {
            if (value.equals(romanNumerals[0])) {
                operatingValue = 10;
            } else if (value.equals(romanNumerals[i])) {
                operatingValue = i;
            }
        }

        for (int i = 0; i < 10; i++) {
            if (value.equals(arabicNumerals[0])) {
                operatingValue = 10;
            } else if (value.equals(arabicNumerals[i])) {
                operatingValue = i;
            }
        }

        if (operatingValue == 0) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Вы ввели недопустимое значение...");
            }
        }
        return operatingValue;
    }

    public static String revealCountSystem(String value) {
        String valueCountSystem = null;

        for (int i = 0; i < 10; i++) {
            if (value.equals(romanNumerals[0])) {
                valueCountSystem = "roman";
            } else if (value.equals(romanNumerals[i])) {
                valueCountSystem = "roman";
            }
        }

        for (int i = 0; i < 10; i++) {
            if (value.equals(arabicNumerals[0])) {
                valueCountSystem = "arabic";
            } else if (value.equals(arabicNumerals[i])) {
                valueCountSystem = "arabic";
            }
        }

        return valueCountSystem;
    }

    public static void compareCountSystems(String countSystem1, String countSystem2) {

        if (!countSystem1.equals(countSystem2)) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("Системы исчисления не совпадают...");
            }
        }
    }

    public static int count(int value1, String operator, int value2) {
        int result = 0;

        switch (operator) {
            case "+":
                result = value1 + value2;
                break;
            case "-":
                result = value1 - value2;
                break;
            case "*":
                result = value1 * value2;
                break;
            case "/":
                result = value1 / value2;
                break;
            default:
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("Операция невозможна...");
                }
        }
        return result;
    }
}

class ArabicToRoman {
    public static int result;


    public static String convertToInputCountSystem(int result, String value1CountSystem, String value2CountSystem) {
        String output1 = null;
        String output2 = null;
        int i;
        int j;

        if (value1CountSystem.equals(value2CountSystem)){
            if (value1CountSystem == "arabic"){
                System.out.println("Итог вычислений: " + result);
            }
        }

        if (value1CountSystem.equals(value2CountSystem)) {
            if (value1CountSystem == "roman") {
                if (result < 1) {
                    try {
                        throw new InappropriateNumberException();
                    } catch (InappropriateNumberException e) {
                        System.out.println("Результат вычисления в римской системе исчисления не может быть меньше единицы...");
                    }
                }
            }
        }


        if (value1CountSystem.equals(value2CountSystem)) {
            if (value1CountSystem == "roman") {

                if (0 < result){
                    if (result < 10){
                        for (RomanNumeral r : RomanNumeral.values()){
                            if (result == r.getValue()){
                                return "Итог вычислений: " + r.toString();
                            }
                        }
                    }
                }

                for (RomanNumeral n : RomanNumeral.values()) {
                    if (result / n.getValue() == 1) {
                        if (result % n.getValue() == 0) {
                            return "Итог вычислений: " + n.toString();
                        }
                    }
                }


                if (result % 10 > 0) {
                    i = result / 10 * 10;
                    j = result % 10;
                    for (RomanNumeral l : RomanNumeral.values()) {
                        if (i == l.getValue()) {
                            output1 = l.toString();
                        }
                    }
                    for (RomanNumeral l : RomanNumeral.values()) {
                        if (j == l.getValue()) {
                            output2 = l.toString();
                        }
                    }
                    return "Итог вычислений: " + output1 + output2;
                }
            }
        }
        return "Программа завершила свою работу.";
    }
}



