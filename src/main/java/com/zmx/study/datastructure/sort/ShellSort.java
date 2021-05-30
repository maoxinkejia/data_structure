package com.zmx.study.datastructure.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        int[] arr = IntStream.range(0, 80000).parallel().map(i -> (int) (Math.random() * 8000000)).toArray();

//        shellSortByStep(arr);
        long start = System.currentTimeMillis();
//        shellSort(arr);
        shellSort2(arr);
        System.out.println("use: " + (System.currentTimeMillis() - start));

//        System.out.println(Arrays.toString(arr));
    }

    private static void shellSortByStep(int[] arr) {
        for (int i = 5; i < arr.length; i++) {
            for (int j = i - 5; j >= 0; j -= 5) {
                if (arr[j] > arr[j + 5]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = tmp;
                }
//                System.out.println("i=" + i + "  " + "j=" + j + "  " + Arrays.toString(arr));
            }
        }

        System.out.println("\t\t  "+Arrays.toString(arr));

        for (int i = 2; i < arr.length; i++) {
            // 共有2组，每组有5个元素，步长2
            // i = 8时，j = 6  组内元素为0,2,4,6,8，每个都需要对比交换
            for (int j = i - 2; j >= 0; j -= 2) {
                System.out.println("before  i=" + i + "  " + "j=" + j + "  " + Arrays.toString(arr));

                if (arr[j] > arr[j + 2]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
                System.out.println("changed i=" + i + "  " + "j=" + j + "  " + Arrays.toString(arr));
            }
        }

        // 第三轮时，是将是个数据分成了1组  2/2 = 1
        for(int i = 1; i < arr.length; i++){
            for(int j = i - 1; j >= 0; j -= 1) {
                System.out.println(String.format("before: i=%d, j=%d, arr: %s", i, j, Arrays.toString(arr)));
                if(arr[j] > arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                System.out.println(String.format("after:  i=%d, j=%d, arr: %s", i, j, Arrays.toString(arr)));
            }
        }
        System.out.println("final: " + Arrays.toString(arr));
    }


    private static void shellSort(int[] arr) {
        // 根据逐步推导，收集规律一次到位
        for(int gap = arr.length / 2; gap > 0; gap /= 2){
            for(int i = gap; i < arr.length; i++){
                for(int j = i - gap; j >= 0; j -= gap) {
                    if(arr[j] > arr[j + gap]){
                        int temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
//                    System.out.println(String.format("gap=%d, j=%d, i=%d, step=%d, res=%s", gap, j, i, j + gap, Arrays.toString(arr)));
                }
            }
        }
    }

    private static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int tmp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && tmp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }

                    arr[j] = tmp;
                }
            }
        }
    }
}
