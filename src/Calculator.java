import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Введите выражение для вычисления (например, 1 + 2 или III - II): ");
        String expression = input.nextLine();

        String[] parts = expression.split(" ");

        if (parts.length != 3) {
            System.out.println("Ошибка ввода! Пожалуйста, введите выражение в формате 'число оператор число' (например, 1 + 2 или III - II).");
            return;
        }

        String num1Str = parts[0];
        String operator = parts[1];
        String num2Str = parts[2];

        int num1, num2;

        try {
            num1 = parseInput(num1Str);
            num2 = parseInput(num2Str);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка ввода! Пожалуйста, введите числа от 1 до 10 включительно.");
            return;
        }

        int result = calculate(num1, num2, operator);
        if (result == -1) {
            System.out.println("Ошибка ввода! Пожалуйста, введите допустимый оператор (+, -, *, /).");
            return;
        }

        String output = convertToOutput(result, num1Str, num2Str, operator);
        System.out.println("Результат: " + output);
    }

    private static int parseInput(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }

    private static int calculate(int num1, int num2, String operator) {
        int result;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                return -1; // Invalid operator
        }
        return result;
    }

    private static String convertToOutput(int result, String num1Str, String num2Str, String operator) {
        boolean isRoman = isRomanNumeral(num1Str) && isRomanNumeral(num2Str);
        if (isRoman) {
            if (result < 1) {
                throw new IllegalArgumentException();
            }
            return toRoman(result);
        } else {
            return Integer.toString(result);
        }
    }

    private static boolean isRomanNumeral(String str) {
        return str.matches("^[IVXLCDM]+$");
    }

    private static String toRoman(int number) {
        if (number == 0) {
            return "N";
        }
        String[] romanNumerals = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        StringBuilder roman = new StringBuilder();
        int digit = 0;
        while (number > 0) {
            int num = number % 10;
            if (num > 0) {
                roman.insert(0, romanNumerals[num] + romanNumerals[10 * digit]);
            }
            number /= 10;
            digit++;
        }
        return roman.toString();
    }
}
