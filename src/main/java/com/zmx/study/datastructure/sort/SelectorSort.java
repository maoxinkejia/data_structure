package com.zmx.study.datastructure.sort;

import java.util.Arrays;

public class SelectorSort {
    public static void main(String[] args) {
        int[] arr = new int[]{15, 3, 9, -1, 10, 12, 5};
        selectorSort(arr);
    }

    private static void selectorSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            int minIndex = 0;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minIndex = j;
                }
            }

            int tmp = arr[i];
            arr[i] = min;
            arr[minIndex] = tmp;
        }

        System.out.println(Arrays.toString(arr));
    }
}
