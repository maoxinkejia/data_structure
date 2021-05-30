package com.zmx.study.datastructure.recursion;

public class Queue8 {
    private int max = 8;
    private int count = 0;
    private int[] arr = new int[max];

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);

        System.out.println("queue8.count = " + queue8.count);
    }

    private void check(int n) {
        if (n == max) {
            // n为第n+1个皇后，n=8时是放置的第9个皇后，无需再计算
            print();
            return;
        }

        // 每一行都要从0开始尝试
        for (int i = 0; i < max; i++) {
            // 先将第一个皇后，放到当前行的第一列
            arr[n] = i;

            // 如果当前的皇后不冲突，则继续放置下一个皇后
            if (judge(n)) {
                check(n + 1);
            }
        }
    }

    /**
     * 检查新放的皇后与原来已经放置的皇后是否冲突
     *
     * @param n 放置的第n+1个皇后
     * @return true：不冲突，false：冲突
     */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            /*
             * 条件1：当前放置的皇后和前面已经放置的皇后是否在同一列
             * 条件2：当前放置的皇后和前面放置的皇后是否在同一斜线
             */
            if (arr[n] == arr[i] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
//                System.out.println("judge, i=" + i + "  n=" + n);
                return false;
            }
        }

        return true;
    }

    private void print() {
        count++;

        for (int i : arr) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }
}
