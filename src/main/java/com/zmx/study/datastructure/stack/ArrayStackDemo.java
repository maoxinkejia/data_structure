package com.zmx.study.datastructure.stack;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack as = new ArrayStack(3);
        as.push(1);
        as.push(2);
        as.push(3);

        as.list();

        System.out.printf("从栈中取出数据%d\n", as.pop());
        System.out.printf("从栈中取出数据%d\n", as.pop());

        as.list();

        as.push(4);
        as.push(5);
        as.push(6);
        as.list();

        System.out.printf("从栈中取出数据%d\n", as.pop());
        System.out.printf("从栈中取出数据%d\n", as.pop());
        System.out.printf("从栈中取出数据%d\n", as.pop());

        try {
            as.pop();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        as.list();


    }


}

class ArrayStack {
    // 栈的大小
    private int maxSize;
    // 数组，模拟栈
    private int[] stack;
    // 表示栈顶，初始化为-1
    private int top = -1;

    ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    // 返回运算符的优先级，优先级由程序员来确定
    // 优先级用数字表示，数字越大，优先级越高
    static int priority(int operator) {
        // int 和 char 可以混用，符号可以用char来表示
        if (operator == '*' || operator == '/') {
            return 1;
        }

        if (operator == '+' || operator == '-') {
            return 0;
        }

        // 假定目前的表达式只有 + - * /
        return -1;
    }

    // 判断是不是运算符
    static boolean isOperator(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 计算方法
     * 减法和除法因为栈的FILO的关系，需要注意倒序运算
     *
     * @param num1     数字1
     * @param num2     数字2
     * @param operator 运算符
     * @return 计算结果
     */
    static int cal(int num1, int num2, int operator) {
        //
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num2 - num1;
            case '*':
                return num1 * num2;
            case '/':
                return num2 / num1;
            default:
                return 0;
        }
    }

    int peek() {
        return stack[top];
    }

    boolean isFull() {
        return top == maxSize - 1;
    }

    boolean isEmpty() {
        return top == -1;
    }

    void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }

        stack[++top] = value;
    }

    int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据");
        }

        return stack[top--];
    }

    void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }

        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }
}
