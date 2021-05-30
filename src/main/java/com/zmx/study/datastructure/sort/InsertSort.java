package com.zmx.study.datastructure.sort;

import java.util.stream.IntStream;

public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = new int[]{15, 3, 9, -1, 10, 12, 5};

        int[] arr = IntStream.range(0, 80000).parallel().map(i -> (int) (Math.random() * 8000000)).toArray();

        long start = System.currentTimeMillis();
        insertSort(arr);
        System.out.println("use: " + (System.currentTimeMillis() - start));
//        System.out.println(Arrays.toString(arr));
    }


    private static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            // 1.定义待插入的数
            int insertValue = arr[i];
            // 2.定义待插入的索引（当前数的前一个数）
            int insertIndex = i - 1;

            // 3.当待插入的数字比前一个索引位置的数字小，则待插入索引位置的数字后移，直到找到要插入的位置
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

             // 4.已经找到待插入位置
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertValue;
            }

//            System.out.println("num=" + insertValue);
//            System.out.println("index=" + insertIndex);
        }
    }
}
