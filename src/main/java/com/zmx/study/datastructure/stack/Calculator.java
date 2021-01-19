package com.zmx.study.datastructure.stack;

public class Calculator {
    public static void main(String[] args) {
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack operatorStack = new ArrayStack(10);

        String expression = "30+2*60-21";
//        String expression = "7*7*2-5+1-5+3-4";
        // 定义需要的相关变量
        int index = 0;
        StringBuilder tmp = new StringBuilder();

        while (index != expression.length()) {
            // 截取字符串的每个字符
            char c = expression.charAt(index++);

            // 当前字符是数字
            if (!ArrayStack.isOperator(c)) {
                // 当有连续数字时，需要对数字进行拼接
                tmp.append(c);

                // 如果不是最后一个字符，且，下一个字符也是数字，则保留tmp，继续下一轮的拼接
                if (index != expression.length() && !ArrayStack.isOperator(expression.charAt(index))) {
                    continue;
                }

                // 如果是最后一个字符，或，下一个字符是符号，将tmp转换成数字放到栈中，并重置tmp
                numStack.push(Integer.parseInt(tmp.toString()));
                tmp = new StringBuilder();
                continue;
            }

            if (operatorStack.isEmpty()) {
                operatorStack.push(c);
                continue;
            }

            if (ArrayStack.priority(c) > ArrayStack.priority(operatorStack.peek())) {
                operatorStack.push(c);
                continue;
            }

            int n1 = numStack.pop();
            int n2 = numStack.pop();
            int o1 = operatorStack.pop();
            int r = ArrayStack.cal(n1, n2, o1);
            numStack.push(r);
            operatorStack.push(c);
        }

        while (!operatorStack.isEmpty()) {
            int cal = ArrayStack.cal(numStack.pop(), numStack.pop(), operatorStack.pop());
            numStack.push(cal);
        }

        System.out.println(numStack.pop());
    }
}
