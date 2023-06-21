import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        try {
            System.out.println(calculator(input));
        }catch (Exception e){
            System.out.println(e);
        }
    }

    static int  calculator(String expression) throws Exception {
        Pattern patternOperand = Pattern.compile("(^-?\\d+\\.?\\d?+|-{2}\\d+\\.?\\d?+|\\d+\\.?\\d?+|\\s\\d+\\.?\\d?+)");
        Matcher matcherOperand = patternOperand.matcher(expression);
        Pattern patternOperator = Pattern.compile("\\d[+\\-*/]");
        Matcher matcherOperator = patternOperator.matcher(expression);
        String operator = "";
        int count = 0;
        List<String> operandStr = new ArrayList<>();
        List<Integer> operandInt = new ArrayList<>();
        while (matcherOperator.find()){
            operator = String.valueOf(matcherOperator.group().charAt(1));
            count ++;
        }
        while (matcherOperand.find()){
            operandStr.add(matcherOperand.group());
        }

        if( operandStr.size() ==2 && count == 1) {
            for (String o : operandStr) {
                if (o.matches("^(-{0,2}[1-9]|-{0,2}1[0])$")) {
                    operandInt.add(Integer.parseInt(o.replaceAll("--", "-")));
                } else throw new Exception("Используйте только целые числа от 1-10");
            }
            switch (operator){
                case "+":
                    return operandInt.get(0) + operandInt.get(1);

                case ("-"):
                    return operandInt.get(0) - operandInt.get(1);

                case ("/"):
                    return operandInt.get(0) / operandInt.get(1);

                case ("*"):
                    return operandInt.get(0) * operandInt.get(1);
                }
        }else throw new Exception(" формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        return 0;
    }
}