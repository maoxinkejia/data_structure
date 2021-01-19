package com.zmx.study.datastructure.linkedlist;

public class Josephus {
    public static void main(String[] args) {
        CircleSingleLinkedList csl = new CircleSingleLinkedList();
        csl.addBoy(5);
        csl.showBoys();

        csl.countBoy(1, 2, 5);
    }

    static class CircleSingleLinkedList {
        private Boy first;

        // 添加
        void addBoy(int nums) {
            if (nums < 2) {
                System.out.println("小孩的数量太少了");
                return;
            }

            Boy curr = null;
            for (int i = 1; i <= nums; i++) {
                Boy boy = new Boy(i);
                if (i == 1) {
                    first = boy;
                    first.next = first;
                    curr = boy;
                    continue;
                }

                curr.next = boy;
                boy.next = first;
                curr = boy;
            }
        }

        void showBoys() {
            if (first == null) {
                System.out.println("没有任何小孩");
                return;
            }

            Boy curr = first;
            while (true) {
                System.out.println(curr);

                if (curr.next.no == first.no) {
                    break;
                }

                curr = curr.next;
            }

            System.out.println();
            System.out.println();
            System.out.println();
        }

        /**
         * 计算小孩出圈的顺序
         *
         * @param startNo  表示从第几个小孩开始数数 k
         * @param countNum 表示数几下 m
         * @param nums     表示有多少个小孩 n
         */
        void countBoy(int startNo, int countNum, int nums) {
            if (first == null || startNo < 1 || startNo > nums) {
                System.out.println("参数输入有误，请重新输入");
                return;
            }

            // 构造辅助指针helper，将指针指向first的前一个节点
            Boy helper = first;
            while (helper.next != first) {
                helper = helper.next;
            }

            // 小孩报数前先将指针移动到第k个小孩，移动k - 1次
            for (int i = 0; i < startNo - 1; i++) {
                first = first.next;
                helper = helper.next;
            }

            // 小孩报数时将指针移动m - 1次，然后出圈
            while (helper != first) {

                for (int i = 0; i < countNum - 1; i++) {
                    first = first.next;
                    helper = helper.next;
                }

                System.out.printf("小孩%d出圈了\n", first.no);

                first = first.next;
                helper.next = first;

            }

            System.out.printf("最后留在圈中的小孩是%d", first.no);
        }
    }

    static class Boy {
        private int no;
        // 指向下一个节点，默认null
        private Boy next;

        Boy(int no) {
            this.no = no;
        }

        @Override
        public String toString() {
            return "Boy{" +
                    "no=" + no +
                    '}';
        }
    }
}
