package com.zmx.study.datastructure.poland;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class PolandNotation {
    public static void main(String[] args) {
        // 先定义逆波兰表达式 (3+4)x5-6 ===>> 3 4 + 5 x 6 -
        String suffixExpression = "3 4 + 5 * 6 -";
        // 思路
        // 1.先将表达式放到ArrayList中
        // 2.将ArrayList传给一个方法，遍历ArrayList 配合栈完成计算
        List<String> list = getListString(suffixExpression);
        System.out.println(list);

        int result = calculate(list);
        System.out.println("result = " + result);
    }

    private static List<String> getListString(String expression) {
        String[] split = expression.split(" ");
        return Arrays.stream(split).collect(Collectors.toList());
    }

    private static int calculate(List<String> list) {
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            if (item.matches("\\d+")) {// 匹配多位数
                stack.push(item);
                continue;
            }

            String num1 = stack.pop();
            String num2 = stack.pop();
            int cal = cal(Integer.parseInt(num1), Integer.parseInt(num2), item);
            stack.push(String.valueOf(cal));
        }

        return Integer.parseInt(stack.pop());
    }

    /**
     * stack先pop出来的是最后放进去的，若是减法或除法时，应作为被减数，被除数
     */
    private static int cal(int num1, int num2, String operator) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num2 - num1;
            case "*":
                return num1 * num2;
            case "/":
                return num2 / num1;
            default:
                throw new RuntimeException("符号错误");
        }
    }
}
