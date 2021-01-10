package com.zmx.study.datastructure.queue;

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

    public ArrayQueueDemo(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        rear = -1;
        front = -1;
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
