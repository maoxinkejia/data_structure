package com.zmx.study.datastructure.poland;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Convert2PolandNotation {
    public static void main(String[] args) {
        /*
         *  1.  1+((2+3)x4)-5 ===>>> 转成 1 2 3 + 4 x + 5
         *  2.  因为直接对str进行操作不方便，因此先将 "1+((2+3)x4)-5"转换成对应的list，即 ArrayList[1,+,(,(,2,+,3,),x,4,),-,5]
         *  3.  将得到的中缀表达式转成一个后缀表达式对应的list
         */
        String expression = "1+((2+30)*4)-51";
        List<String> list = toInfixExpression(expression);
        System.out.println(list);

        List<String> parseSuffixExpression = parseSuffixExpression(list);
        System.out.println("parseSuffixExpression: " + parseSuffixExpression);
    }

    private static List<String> parseSuffixExpression(List<String> list) {
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        for (String s : list) {
            if (s.matches("\\d+")) {
                s2.add(s);
            } else if (s.equals("(")) {
                s1.push(s);
            } else if (s.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();// 将 ( 弹出s1
            } else {
                // 当s 的优先级小于或等于s1栈顶运算符，将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新栈顶运算符比较
                // 缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(s)) {
                    s2.add(s1.pop());
                }
                // 还需要将s 压入栈中
                s1.push(s);
            }
        }

        // 将s1中剩余的运算符一次加入到s2中
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;
    }

    private static List<String> toInfixExpression(String s) {
        List<String> list = new ArrayList<>();
        int index = 0;
        while (index < s.length()) {
            char c = s.charAt(index);
            if (c < 48 || c > 57) {
                list.add("" + c);
                index++;
            } else {
                // 如果是一个数，需要考虑多位数
                StringBuilder str = new StringBuilder();// 先将str置成空串
                while (index < s.length() && s.charAt(index) >= 48 && (c = s.charAt(index)) <= 57) {
                    str.append(c);//拼接数字
                    index++;
                }
                list.add(str.toString());
            }
        }

        return list;
    }

    private static class Operation {
        private static int ADD = 1, SUB = 1;
        private static int MUL = 2, DIV = 2;

        static int getValue(String s) {
            switch (s) {
                case "+":
                    return ADD;
                case "-":
                    return SUB;
                case "*":
                    return MUL;
                case "/":
                    return DIV;
                case "(":
                case ")":
                    return 3;
                default:
                    throw new RuntimeException();
            }
        }
    }
}
