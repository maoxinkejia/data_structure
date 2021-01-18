package com.zmx.study.datastructure.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    // 数组大小
    private int maxSize;
    // 头指针，指向队列的第一个元素，也就是说arr[front]，初始值0
    private int front;
    // 尾指针，指向队列最后一个元素的后一个位置，相当于空出来一个位置，初始为0
    private int rear;
    // 用于存放数据的数组
    private int[] arr;

    private CircleArrayQueueDemo(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public static void main(String[] args) {
        System.out.println("环形队列");

        CircleArrayQueueDemo queue = new CircleArrayQueueDemo(3);
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

    // 判断队列是否满
    private boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    // 判断队列是否空
    private boolean isEmpty() {
        return rear == front;
    }

    // 添加一个元素
    private void addQueue(int num) {
        if (isFull()) {
            System.out.println("队列已满，无法添加");
            return;
        }

        // 直接将数据加入
        arr[rear] = num;
        // 将rear后移，必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    // 取出一个元素
    private int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，不能获取");
        }

        //1.先把当前front对应的值取出来
        int num = arr[front];
        //2.front后移
        front = (front + 1) % maxSize;
        //3.返回数据
        return num;
    }

    // 显示队列所有数据
    private void show() {
        if (isEmpty()) {
            System.out.println("队列为空，不能获取");
            return;
        }
        // 遍历数据时一定是从front开始，遍历个数是front+有效数据的个数
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 有效数据的个数
    private int size() {
        return (rear + maxSize - front) % maxSize;
    }

    // 显示队列头数据
    private int head() {
        if (isEmpty()) {
            throw new RuntimeException();
        }
        return arr[front];
    }
}
