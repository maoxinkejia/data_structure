package com.zmx.study.datastructure.sort;

import java.util.stream.IntStream;

public class BubbleSort {
    public static void main(String[] args) {
//        int[] arr = new int[]{15, 3, 9, -1, 10, 12, 5};

        //生成[0,8000000)随机数,区分
        int[] arr = IntStream.range(0, 80000).parallel().map(i -> (int) (Math.random() * 8000000)).toArray();

        long start = System.currentTimeMillis();
        System.out.println("start time: " + start);
        bubbleSorted(arr);
        System.out.printf("use time: %d", System.currentTimeMillis() - start);

    }

    /**
     * 冒泡排序时始终为最内层循环进行比较（永远从j=0时开始，从头开始进行比较）
     * 比较时同后一个元素进行比较，若满足条件则后移，每一轮一定会将最大/最小的数字移动到最后
     * 外层循环每+1，说明最右侧有几个已经排序完成的数字，内层循环时不需要再去与他们进行比较，所以arr-1-i，跳过即可
     * 外层循环是控制第几轮的内层循环
     */
    private static void bubbleSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

//         压测使用并行流式计算
//        IntStream.range(0, arr.length - 1)
//                .parallel()
//                .forEach(i -> IntStream.range(0, arr.length - 1 - i)
//                        .parallel()
//                        .forEach(j -> {
//                            if (arr[j] > arr[j + 1]) {
//                                int tmp = arr[j];
//                                arr[j] = arr[j + 1];
//                                arr[j + 1] = tmp;
//                            }
//                        }));
    }
}
