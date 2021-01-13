package com.zmx.study.datastructure.queue;

import java.util.Scanner;

/**
 * 数组模拟队列
 */


public class ArrayQueueDemo {
    // 数组大小
    private int maxSize;
    // 头指针
    private int front;
    // 尾指针
    private int rear;
    // 用来存放数据的数组
    private int[] arr;

    private ArrayQueueDemo(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        rear = -1;
        front = -1;
    }

    public static void main(String[] args) {
        ArrayQueueDemo queue = new ArrayQueueDemo(3);
        char key;
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列获取数据");
            System.out.println("h(head)：查看队列头部数据");

            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.show();
                    break;
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = queue.head();
                        System.out.printf("队列头的数据是%d\n", head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    loop = false;
                    break;
                default:
                    System.out.println("输入不正确，请重新输入");

            }
        }

        System.out.println("程序已经退出");
    }

    // 队列是否满
    private boolean isFull() {
        return rear == maxSize - 1;
    }

    // 队列是否空
    private boolean isEmpty() {
        return rear == front;
    }

    // 添加数据到队列
    public void addQueue(int num) {
        if (isFull()) {
            System.out.println("队列已满，无法添加");
            return;
        }

        rear++;
        arr[rear] = num;
    }

    // 出队一个数据
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能获取");
        }

        front++;
        return arr[front];
    }

    // 显示所有
    public void show() {
        if (isEmpty()) {
            System.out.println("队列为空，不能获取");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    // 显示队列头数据
    public int head() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能获取");
        }

        return arr[front + 1];
    }
}
